package com.wz.mall.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {

    /***
     * 普通解密操作
     * @param encodedText：密文
     * @return
     */
    public static byte[] decode(String encodedText){
        final Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(encodedText);
    }

    /***
     * 普通加密操作
     * @param data
     * @return
     */
    public static String encode(byte[] data){
        final Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);
    }

    /***
     * url解密操作
     * @param encodedText
     * @return
     */
    public static byte[] decodeURL(String encodedText){
        final Base64.Decoder decoder = Base64.getUrlDecoder();
        return decoder.decode(encodedText);
    }

    /***
     * url加密操作
     * @param data
     * @return
     */
    public static String encodeURL(byte[] data){
        final Base64.Encoder encoder = Base64.getUrlEncoder();
        return encoder.encodeToString(data);
    }


}