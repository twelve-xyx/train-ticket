package com.ticket.trainticket.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author duanchao
 * @date 2019-09-16
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getDomain() {
        HttpServletRequest request = getHttpServletRequest();
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    public static String getServerUrl() {
        HttpServletRequest request = getHttpServletRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + "/";
    }

    public static String getOrigin() {
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取当前环境参数
     *
     * @return String
     */
    public static String getActiveProfile() {
        String[] profiles = applicationContext.getEnvironment().getActiveProfiles();
        if (ArrayUtils.isNotEmpty(profiles)) {
            return profiles[0];
        }
        return "";
    }

    /**
     * 判断是否生产环境
     *
     * @return boolean
     */
    public static boolean isProdEnv() {
        return "prod".equals(getActiveProfile());
    }

    /**
     * 判断是否新演示环境
     *
     * @return boolean
     */
    public static boolean isStagingnewEnv() {
        return "stagingnew".equals(getActiveProfile());
    }

    /**
      * 判断是否为开发环境
      */
    public static boolean isDevEnv() {
        return "dev".equals(getActiveProfile());
    }
}
