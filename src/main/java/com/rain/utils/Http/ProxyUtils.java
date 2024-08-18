package com.rain.utils.Http;

import java.net.InetSocketAddress;
import java.net.Proxy;

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
