package org.ct.ctTool.enums;

/**
 * @Classname SizeEnum
 * @Description 对象大小泛型
 * @Date 2019/2/21 14:33
 * @Created by Think
 */
public enum SizeEnum {
    B {
        @Override
        public double getFromByteSize(long byteSize) {
            return byteSize;
        }
    },K {
        @Override
        public double getFromByteSize(long byteSize) {
            return byteSize*1.0/1024;
        }
    },M {
        @Override
        public double getFromByteSize(long byteSize) {
            return byteSize*1.0/1024/1024;
        }
    },G {
        @Override
        public double getFromByteSize(long byteSize) {
            return byteSize*1.0/1024/1024/1024;
        }
    };

    public abstract double getFromByteSize(long byteSize);
}
