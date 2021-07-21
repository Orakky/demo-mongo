package com.example.demo.utils.Md5Utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * md5工具类
 */
public class MD5Util {



     public static String md5(String src){
         return DigestUtils.md5Hex(src);
     }

     private static final String salt = "1a2b3c4d";


     public static String inputPassToFromPass(String inputPass){
         String str = salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
         return md5(str);
     }


    /**
     * 二次加密
     * @param formPass
     * @param salt
     * @return
     */
     public static String fromPassToDBPass(String formPass,String salt){
        String str = salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
     }

    /**
     * 对外调用方法 加密
     * @param inputPass
     * @param salt
     * @return
     */
     public static String inputPassToDBPass(String inputPass,String salt){
         String fromPass = inputPassToFromPass(inputPass);
         String dbPass = fromPassToDBPass(fromPass,salt);
         return dbPass;
     }
}
