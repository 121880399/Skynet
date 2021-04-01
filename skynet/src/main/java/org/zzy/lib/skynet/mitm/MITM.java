package org.zzy.lib.skynet.mitm;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2021/3/18 21:17
 * 描    述：中间人攻击检查
 * 通用做法：
 * 1.证书固定：使用自签名证书，APP内置公钥，TLS的时候验证
 * 2.双向认证：客户端跟服务端相互验证
 * 3.判断是否使用代理
 * 修订历史：
 * ================================================
 */
public class MITM {

   
    /**
    * 判断设备 是否使用代理上网
    * 作者: ZhouZhengyi
    * 创建时间: 2021/3/18 21:18
    */
    public boolean isProxy(Context context) {

        String proxyAddress;

        int proxyPort;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

            proxyAddress = System.getProperty("http.proxyHost");

            String portStr = System.getProperty("http.proxyPort");

            proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));

        } else {

            proxyAddress = android.net.Proxy.getHost(context);

            proxyPort = android.net.Proxy.getPort(context);

        }

        return (!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1);

    }
}
