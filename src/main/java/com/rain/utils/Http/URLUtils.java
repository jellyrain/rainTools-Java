package com.rain.utils.Http;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * url 工具类
 *
 * @author rain
 * @date 2024/08/18
 */
public final class URLUtils {
    private URLUtils() {

    }

    /**
     * 将字符串转换为 URL
     *
     * @param url 网址
     * @return {@link URL }
     * @throws MalformedURLException Malformed 异常
     */
    public static URL toURL(String url) throws MalformedURLException {
        return new URL(url);
    }

    /**
     * 将字符串 和 params参数 转换为 URL
     *
     * @param url    网址
     * @param params 参数
     * @return {@link URL }
     * @throws MalformedURLException Malformed 异常
     */
    public static URL toURL(String url, Map<String, String> params) throws MalformedURLException {
        StringBuilder queryString = new StringBuilder();
        if (params != null) {
            queryString.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                queryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return new URL(url + queryString);
    }

    /**
     * 将 URI 转换为 URL
     *
     * @param uri uri
     * @return {@link URL }
     * @throws MalformedURLException UriSyntax 异常
     */
    public static URL toURL(URI uri) throws MalformedURLException {
        return uri.toURL();
    }

    /**
     * 将字符串转换为 URI
     *
     * @param url 网址
     * @return {@link URI }
     */
    public static URI toURI(String url) {
        return URI.create(url);
    }

    /**
     * 将 URL 转换为 URI
     *
     * @param url url
     * @return {@link URI }
     * @throws URISyntaxException UriSyntax 异常
     */
    public static URI toURI(URL url) throws URISyntaxException {
        return url.toURI();
    }

    /**
     * 获取协议
     *
     * @param url 网址
     * @return {@link String }
     */
    public static String getProtocol(URL url) {
        return url.getProtocol();
    }

    /**
     * 获取主机
     *
     * @param url 网址
     * @return {@link String }
     */
    public static String getHost(URL url) {
        return url.getHost();
    }

    /**
     * 获取端口
     *
     * @param url 网址
     * @return {@link int }
     */
    public static int getPort(URL url) {
        return url.getPort();
    }

    /**
     * 获取主机和端口
     *
     * @param url 网址
     * @return {@link String }
     */
    public static String getHostAndPort(URL url) {
        return url.getHost() + ":" + url.getPort();
    }

    /**
     * 获取全路径
     *
     * @param url 网址
     * @return {@link String }
     */
    public static String getFile(URL url) {
        return url.getFile();
    }

    /**
     * 获取路径
     *
     * @param url 网址
     * @return {@link String }
     */
    public static String getPath(URL url) {
        return url.getPath();
    }

    /**
     * 获取查询字符串
     *
     * @param url 网址
     * @return {@link String }
     */
    public static String getQueryString(URL url) {
        return url.getQuery();
    }

    /**
     * 获取查询参数
     *
     * @param url 网址
     * @return {@link Map }<{@link String }, {@link String }>
     */
    public static Map<String, String> getQueryParams(URL url) {
        Map<String, String> queryMap = new HashMap<>();
        String query = url.getQuery();
        if (query != null) {
            String[] params = query.split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                queryMap.put(keyValue[0], keyValue[1]);
            }
        }
        return queryMap;
    }

    /**
     * 获取锚点
     * <p>
     * 锚点是 URL 中的 # 后面的内容
     *
     * @param url 网址
     * @return {@link String }
     */
    public static String getRef(URL url) {
        return url.getRef();
    }
}
