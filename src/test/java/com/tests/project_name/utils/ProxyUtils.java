package com.tests.project_name.utils;

import org.openqa.selenium.Proxy;

public class ProxyUtils {

    /**
     * Creates and returns a Proxy object configured with the specified HTTP and SSL proxy addresses.
     *
     * @param httpProxy Address and port for the HTTP proxy (e.g., "127.0.0.1:9000").
     * @param sslProxy Address and port for the SSL proxy (e.g., "127.0.0.1:9000").
     * @return A configured Proxy object.
     */
    public static Proxy getHttpAndSslProxy(String httpProxy, String sslProxy) {
        if (httpProxy == null || sslProxy == null) {
            throw new IllegalArgumentException("HTTP and SSL Proxy addresses cannot be null.");
        }

        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setHttpProxy(httpProxy);
        proxy.setSslProxy(sslProxy);
        return proxy;
    }

    /**
     * Creates a Proxy object for other types (SOCKS, FTP).
     *
     * @param proxyType The type of proxy (HTTP, SSL, SOCKS, FTP).
     * @param proxyAddress The address and port of the proxy (e.g., "127.0.0.1:9000").
     * @return A configured Proxy object.
     */
    public static Proxy getProxyForType(String proxyType, String proxyAddress) {
        if (proxyAddress == null) {
            throw new IllegalArgumentException("Proxy address cannot be null.");
        }

        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);

        switch (proxyType.toUpperCase()) {
            case "HTTP":
                proxy.setHttpProxy(proxyAddress);
                break;
            case "SSL":
                proxy.setSslProxy(proxyAddress);
                break;
            case "SOCKS":
                proxy.setSocksProxy(proxyAddress);
                break;
            case "FTP":
                proxy.setFtpProxy(proxyAddress);
                break;
            default:
                throw new IllegalArgumentException("Unsupported proxy type: " + proxyType);
        }

        return proxy;
    }

    /**
     * Logs the details of the proxy for debugging purposes.
     *
     * @param proxy The Proxy object to log.
     */
    public static void logProxyDetails(Proxy proxy) {
        if (proxy != null) {
            System.out.println("Proxy Configurations:");
            System.out.println("HTTP Proxy: " + proxy.getHttpProxy());
            System.out.println("SSL Proxy: " + proxy.getSslProxy());
            System.out.println("SOCKS Proxy: " + proxy.getSocksProxy());
            System.out.println("FTP Proxy: " + proxy.getFtpProxy());
        } else {
            System.out.println("Proxy is not configured.");
        }
    }
}

