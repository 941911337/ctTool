package org.ct.ctTool.util;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Think
 * @Title: OkHttpUtil
 * @ProjectName Bonade-OMall
 * @Description: TODO
 * @date 2019/1/3 09:16
 * @Version 1.0
 */
public abstract class OkHttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpUtil.class);

    /**
     * 超时时间
     */
    public static final int TIMEOUT = 60;
    /**
     * 最大连接数
     */
    public static final int MAX_CONNECT = 200;
    /**
     * 链接保活时间
     */
    public static final int KEEP_ALIVE = 1;
    /**
     * 链接超时时间
     */
    public static final int CONNECT_TIMEOUT = 500;

    /**
     *json请求
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    /**
     * post请求 map为body
     * @param url url
     * @param param 参数
     * @throws IOException
     */
    public static  String post(String url, Map<String, String> param) throws IOException {
        RequestBody body = getPostRequestBody(param);
        Request request = new Request.Builder().url(url).post(body).build();
//        final Call call = getClient().newCall(request);
//        Response response = call.execute();
//        result = response.body().string();
//        LOGGER.info("调用url:{},耗时:{}ms",url,System.currentTimeMillis() - start );
//        return result;
        return requestUrl(request);
    }

    /**
     * post 请求
     * @param url url
     * @param headers 请求头
     * @param params 参数
     * @return 返回值
     * @throws IOException
     */
    public  String post(String url,Map<String, String> headers, Map<String, String> params) throws IOException {
        RequestBody body = getPostRequestBody(params);
        Headers.Builder hBuilder=new Headers.Builder();
        if (headers != null ) {
            headers.forEach((k,v) ->  hBuilder.add(k , v));
        }
        /**
         * 生成Header
         */
        Headers header=hBuilder.build();
        Request request = new Request.Builder().url(url).headers(header).post(body).build();
//        final Call call = getClient().newCall(request);
//        Response response = call.execute();
//        result=response.body().string();
//        LOGGER.info("调用url:{},耗时:{}ms",url,System.currentTimeMillis() - start );
//        return result;
        return requestUrl(request);
    }


    /**
     * 调用get请求url
     * @param url
     * @return
     * @throws IOException
     */
    public  String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
//        final Call call = getClient().newCall(request);
//        Response response = call.execute();
//        result=response.body().string();
//        LOGGER.info("调用url:{},耗时:{}ms",url,System.currentTimeMillis() - start );
//        return result;
        return requestUrl(request);
    }

    /**
     * 获取post请求体
     * @param param
     * @return
     */
    private static RequestBody getPostRequestBody(Map<String, String> param){

        /**
         * 创建请求的参数body
         */
        FormBody.Builder builder = new FormBody.Builder();

        /**
         * 遍历key
         */
        if (param != null) {
            param.forEach((k,v)-> builder.add(k, v));
        }
        RequestBody body = builder.build();
        return body;
    }

    /**
     * 请求url
     * @param request 请求
     * @return 返回结果
     * @throws IOException
     */
    private static String requestUrl(Request request) throws IOException{
        long start = System.currentTimeMillis();
        final Call call = getClient().newCall(request);
        Response response = call.execute();
        String result = response.body().string();
        LOGGER.info("调用url:{},耗时:{}ms",request.url().url(),System.currentTimeMillis() - start );
        return result;
    }


    private static class OkHttpHolder{
        static{
            System.out.println("okHttp实例化");
        }
        private final static ConnectionPool pool = new ConnectionPool(MAX_CONNECT, KEEP_ALIVE, TimeUnit.MINUTES);
        private final static OkHttpClient httpClient  = new OkHttpClient.Builder()
                //连接超时时间
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                //读取超时时间
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                //写入超时
            .writeTimeout(TIMEOUT,TimeUnit.SECONDS)
            .connectionPool(pool)
            .build();
    }

    /**
     * 获取http请求
     * @return
     */
    private static OkHttpClient getClient(){
        return OkHttpHolder.httpClient;
    }

    /**
     * 私有化初始化方法
     */
    private OkHttpUtil() {}
}
