package com.softeem.util;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 公用的配置文件处理类
 * @author Mr Du
 *
 */

public class GlobalUtil {
    private static Logger LOGGER = Logger.getLogger(GlobalUtil.class);

    /**
     * 文件配置路径
     */
    public static final String PATHCONFIG = "/global.properties";
    
    /**
     * 二维码配置
     */
    public static final String ORCODEPATH = "/orcode/orcode.png";

    /**
     * 获得配置的值
     * 
     * @param key 标示
     * @return 值
     */
    public static String getValue(String key) {
        try {
            Properties properties = new Properties();
            InputStream in = GlobalUtil.class.getResourceAsStream(PATHCONFIG);
            properties.load(in);
            in.close();
            return properties.getProperty(key);
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return null;
    }
    
    public static InputStream getFileStr(String str) {
        return GlobalUtil.class.getResourceAsStream(str);   
    }
    
    
}
