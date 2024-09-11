package com.rain.utils.http;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * 代理 工具类
 *
 * @author rain
 * @date 2024/09/10
 */
public final class ProxyUtils {

    private ProxyUtils() {

    }

    /**
     * 代理 HTTP
     *
     * @param host 主机
     * @param port 端口
     * @return {@link Proxy }
     */
    public static Proxy toProxyHttp(String host, int port) {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
    }

    /**
     * 代理 SOCKS
     *
     * @param host 主机
     * @param port 端口
     * @return {@link Proxy }
     */
    public static Proxy toProxySocks(String host, int port) {
        return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(host, port));
    }
}
