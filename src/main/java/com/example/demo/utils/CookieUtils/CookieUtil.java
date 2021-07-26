package com.example.demo.utils.CookieUtils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Locale;

public class CookieUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtil.class);

    /**
     * 得到cookie的值 不编码
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName){
        return getCookieValue(request,cookieName,false);
    }


    /**
     * 得到cookie的值
     * @param request
     * @param cookieName
     * @param isDecoder
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName,boolean isDecoder){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookieName == null){
            return null;
        }
        String retValue = null;
        try{
            for(int i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals(cookieName)){
                    if(isDecoder){
                        retValue = URLDecoder.decode(cookies[i].getValue(),"UTF-8");
                    }else{
                        retValue = cookies[i].getValue();
                    }
                    break;
                }
            }

        }catch (UnsupportedEncodingException e){
            LOGGER.error("Cookie Decode error.",e);
        }

        return retValue;
    }


    /**
     * 根据指定编码获取cookie的值
     * @param request
     * @param cookieName
     * @param encodeString
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName,String encodeString){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookieName == null){
            return null;
        }
        String retValue = null;
        try{
            for(int i =0; i < cookies.length; i++){
                if(cookies[i].getName().equals(cookieName)){
                    retValue = URLDecoder.decode(cookies[i].getValue(),encodeString);
                    break;
                }
            }
        }catch (UnsupportedEncodingException e){
            LOGGER.error("Cookie Decode error.",e);
        }

        return retValue;
    }


    /**
     * 设置cookie，不设置过期时间 默认浏览器关闭即生效，也不编码
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue){
            setCookie(request,response,cookieName,cookieValue,-1);
    }


    /**
     * 设置cookie的值，指定时间内生效，不编码
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage) {
        setCookie(request,response,cookieName,cookieValue,cookieMaxage,false);
    }

    /**
     * 设置cookie的值 不设置生效时间 设置编码
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param isEncode
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue,  boolean isEncode) {
        setCookie(request,response,cookieName,cookieValue,-1,isEncode);
    }


    /**
     * 设置cookie的值 设置生效时间 编码参数
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     * @param isEncode
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
            doSetCookie(request,response,cookieName,cookieValue,cookieMaxage,isEncode);
    }

    /**
     * 设置cookie的值，设置生效时间 指定编码类型
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     * @param encodeString
     */
    public static void setCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,String encodeString){
        doSetCookie(request,response,cookieName,cookieValue,cookieMaxage,encodeString);
    }


    /**
     * 删除cookie
     * @param request
     * @param response
     * @param cookieName
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,String cookieName){
        doSetCookie(request,response,cookieName,"",-1,false);
    }


    /**
     * 设置cookie的值 并在指定时间内生效
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     * @param isEncode
     */
    private static final void doSetCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,boolean isEncode){

        try{
          if(cookieValue == null){
              cookieValue = "";
          }else if(isEncode){
              //确认编码
              cookieValue = URLEncoder.encode(cookieValue,"utf-8");
          }
          Cookie cookie = new Cookie(cookieName,cookieValue);

          if(cookieMaxage > 0){
              cookie.setMaxAge(cookieMaxage);
          }

          if(null != request){
              //设置域名的cookie
              cookie.setDomain(getDomain(request));
          }
          cookie.setPath("/");
          response.addCookie(cookie);
        }catch (Exception e){
            LOGGER.error("Cookie Encode error.",e);
        }
    }


    /**
     *
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     * @param encodeString
     */
    private static final void doSetCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,String encodeString){
        try{
            if(cookieValue == null){
                cookieValue = "";
            }else{
                cookieValue = URLEncoder.encode(cookieValue,encodeString);
            }
            Cookie cookie = new Cookie(cookieName,cookieValue);
            if(cookieMaxage > 0){
                cookie.setMaxAge(cookieMaxage);
            }
            if(null !=  request){
                cookie.setDomain(getDomain(request));
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        }catch (Exception e){
            LOGGER.error("Cookie Encoder error.",e);
        }
    }


    /**
     * 得到cookie的域名
     * @param request
     * @return
     */
    private static String getDomain(HttpServletRequest request) {
        String domainName = null;
        String serverName = request.getRequestURL().toString();

        if(serverName == null || serverName.equals("")){
            domainName = "";
        }else{
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0,end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if(len > 3){
                //www.xxx.com.cn
                domainName = domains[len-3] + "." + domains[len - 2] + "." + domains[len -1];
            }else if(len <=3 && len > 1){
                //xxx.com or xxx.cn
                domainName = domains[len -2 ] + "." + domains[len -1];
            }else{
                domainName = serverName;
            }
        }


        if(domainName != null && domainName.indexOf(":") > 0){
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }


}
