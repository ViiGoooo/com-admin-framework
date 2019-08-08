package com.admin.framework.component.http;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
/**
 *
 * @author ZSW
 * @date 2019/8/8
 */
public class SSLTrustManager implements X509TrustManager {
    X509TrustManager x509TrustManager;

    public SSLTrustManager(SSLKey sslKey) throws HttpException {
        if(sslKey == null){
            return;
        }
        try {
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(sslKey.getStore()), sslKey.getPass().toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
            tmf.init(ks);
            TrustManager tms [] = tmf.getTrustManagers();
            for (int i = 0; i < tms.length; i++) {
                if (tms[i] instanceof X509TrustManager) {
                    x509TrustManager = (X509TrustManager) tms[i];
                    return;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new HttpException("初始化证书失败",e);
        }
    }


    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if(x509TrustManager == null){
            return;
        }
        x509TrustManager.checkClientTrusted(chain, authType);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if(x509TrustManager == null){
            return;
        }
        x509TrustManager.checkServerTrusted(chain, authType);
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        if(x509TrustManager == null){
            return null;
        }
        return x509TrustManager.getAcceptedIssuers();
    }

}
