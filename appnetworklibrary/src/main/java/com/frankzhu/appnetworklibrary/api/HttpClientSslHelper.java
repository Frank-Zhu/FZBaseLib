package com.frankzhu.appnetworklibrary.api;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2014/12/15  16:19.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2014/12/15        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class HttpClientSslHelper {
    private static final String KEY_STORE_TYPE_BKS = "bks";
    private static final String KEY_STORE_TYPE_P12 = "PKCS12";

    public static final String KEY_STORE_CLIENT_PATH = "client.p12";//正式
    private static final String KEY_STORE_TRUST_PATH = "client.truststore";//正式
    public static final String KEY_STORE_PASSWORD = "123456";//正式
    private static final String KEY_STORE_TRUST_PASSWORD = "123456";//正式

    public static OkHttpClient.Builder getSslOkHttpClient(Context context) {
        return new OkHttpClient.Builder()
                .sslSocketFactory(getSslContext(context).getSocketFactory())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
    }

    private static SSLContext sslContext = null;

    public static SSLContext getSslContext(Context context) {
        if (sslContext == null) {
            try {
                // 服务器端需要验证的客户端证书
                KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE_P12);
                // 客户端信任的服务器端证书
                KeyStore trustStore = KeyStore.getInstance(KEY_STORE_TYPE_BKS);

                InputStream ksIn = context.getResources().getAssets().open(KEY_STORE_CLIENT_PATH);
                InputStream tsIn = context.getResources().getAssets().open(KEY_STORE_TRUST_PATH);
                try {
                    keyStore.load(ksIn, KEY_STORE_PASSWORD.toCharArray());
                    trustStore.load(tsIn, KEY_STORE_TRUST_PASSWORD.toCharArray());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        ksIn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        tsIn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                sslContext = SSLContext.getInstance("TLS");
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(trustStore);
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
                keyManagerFactory.init(keyStore, KEY_STORE_PASSWORD.toCharArray());
                sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sslContext;
    }

    private static SSLContext webClientBelow4SSLContext = null;//webview client ssl认证,低于等于 android4.0

    public static SSLContext createSSLContext(Context context) {
        if (webClientBelow4SSLContext == null) {
            try {
                // 创建一个证书库，并将证书导入证书库
                KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
                keyStore.load(
                        context.getResources()
                                .getAssets()
                                .open(KEY_STORE_CLIENT_PATH),// client

                        KEY_STORE_PASSWORD.toCharArray());// CERTFILE_PASSWORD 为你的证书的密码
                KeyManagerFactory localKeyManagerFactory = KeyManagerFactory
                        .getInstance(KeyManagerFactory.getDefaultAlgorithm());
                localKeyManagerFactory.init(keyStore,
                        KEY_STORE_PASSWORD.toCharArray());
                KeyManager[] arrayOfKeyManager = localKeyManagerFactory
                        .getKeyManagers();
                webClientBelow4SSLContext = SSLContext.getInstance("TLS");

                webClientBelow4SSLContext.init(arrayOfKeyManager,
                        new TrustManager[]{new X509TrustManager() {
                            @SuppressLint("TrustAllX509TrustManager")
                            public void checkClientTrusted(
                                    X509Certificate[] chain, String authType)
                                    throws CertificateException {
                            }

                            @SuppressLint("TrustAllX509TrustManager")
                            public void checkServerTrusted(
                                    X509Certificate[] chain, String authType)
                                    throws CertificateException {
                            }

                            public X509Certificate[] getAcceptedIssuers() {
                                return null;
                            }
                        }}, null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return webClientBelow4SSLContext;
    }
}
