package org.ct.ctTool.util.encrypt;

import org.ct.ctTool.constant.Constants;
import org.ct.ctTool.exception.OperationException;
import org.ct.ctTool.exception.ParamException;
import org.ct.ctTool.util.Base64Util;
import org.ct.ctTool.util.DataUtil;
import org.ct.ctTool.util.ParamUtil;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * @author Think
 * @Title: ThreeDESUtil
 * @ProjectName easyTool
 * @Description: 3DES工具类
 * @date 2019/2/25 19:13
 * @Version 1.0
 */
public abstract class ThreeDesUtil {

    /**
     * 默认密钥
     */
    private static final String KEY = "7rboiRgZeueTqAUJ5sIhaaXW8okqmF0z";

    /**
     * 默认偏移量
     */
    private static final byte[] KEY_IV = { 1, 2, 3, 4, 5, 6, 7, 8 };
    
    

    /**
     * 加密模式 常量 ECB CBC
     */
    private static final int ECB_MODE = 0;
    private static final int CBC_MODE = 1;
    
    /**
     * 去除魔法数
     */
    private static final String DESEDE_STR = "desede";


    /**
     * 根据参数生产对应的密码生成 非线程安全
     * @param keyStr 密钥
     * @param cipherMode 密码模式
     * @param codeMode 加密模式
     * @return
     */
    private static  Cipher getCipher(String keyStr ,int cipherMode,int codeMode){
        Cipher cipher = null;
        try{
            Key desKey = getDesKey(keyStr);
            switch (codeMode){
                case ECB_MODE :
                    cipher = Cipher.getInstance(DESEDE_STR + "/ECB/PKCS5Padding");
                    cipher.init(cipherMode, desKey);
                    break;
                case CBC_MODE :
                    cipher = Cipher.getInstance(DESEDE_STR + "/CBC/PKCS5Padding");
                    IvParameterSpec ips = new IvParameterSpec(KEY_IV);
                    cipher.init(cipherMode, desKey, ips);
                    break;
                default:
            }
        }catch(Exception e){
            throw new OperationException(e.getMessage(),e);
        }
        return cipher;
    }

    /**
     * 获取desKey 非线程安全
     * @param keyStr 密钥字符串
     * @return 返回desKey
     */
    private static  Key getDesKey(String keyStr){
        byte[] bytes = Base64Util.base64Decode(keyStr);
        Key desKey;
        try{
            DESedeKeySpec spec =  new DESedeKeySpec(bytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DESEDE_STR);
            desKey = keyFactory.generateSecret(spec);
        }catch(Exception e){
           throw new OperationException(e.getMessage(),e);
        }
        return desKey;
    }

    /**
     * 加密逻辑
     * @param str 明文
     * @param codeMode 加密模式
     * @return 密文
     */
    private static  String des3Encode(String str,int codeMode) {
        ParamUtil.checkParam(new Object[]{str});
        Cipher cipher = getCipher(KEY,Cipher.DECRYPT_MODE,codeMode);
        if(DataUtil.isEmpty(cipher)) {
            throw new ParamException("cipher为空");
        }
        byte[] bOut;
        try {
            byte[] data = str.getBytes(Constants.DEFAULT_CHARACTER_SET);
            bOut = cipher.doFinal(data);
        } catch (Exception e) {
            throw new OperationException(e.getMessage(),e);
        }
        return Base64Util.base64Encode(bOut);
    }

    /**
     * 解密逻辑
     * @param str 密文
     * @param codeMode 加密模式
     * @return 明文
     */
    private static  String des3Decode(String str,int codeMode) {
        ParamUtil.checkParam(new Object[]{str});  
        Cipher cipher = getCipher(KEY,Cipher.DECRYPT_MODE,codeMode);
        if(DataUtil.isEmpty(cipher)) {
            throw new ParamException("cipher为空");
        }
        byte[] data= Base64Util.base64Decode(str);
        String result;
        try {
            byte[] bOut = cipher.doFinal(data);
            result = new String(bOut, Constants.DEFAULT_CHARACTER_SET);
        } catch (Exception e) {
            throw new OperationException(e.getMessage(),e);
        }
        return result;
    }

    /**
     * ECB加密
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */
    public static  String des3EncodeECB(String str) {
        return des3Encode(str,ECB_MODE);
    }

    /**
     * ECB解密
     * @param str 加密的字符串
     * @return 解密后字符串
     */
    public static String des3DecodeECB(String str){
        return des3Decode(str,ECB_MODE);
    }

    /**
     * CBC 加密
     * @param str 字符串
     * @return 密文
     */
    public static String des3EncodeCBC(String str){
        return des3Encode(str,CBC_MODE);
    }

    /**
     * CBC解密
     * @param str 密文
     * @return 明文
     */
    public static String des3DecodeCBC( String str) {
        return des3Decode(str,CBC_MODE);
    }
    
    private ThreeDesUtil() {}
}
