package org.zzy.lib.skynet.interceptor;

import android.app.Application;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2021/3/18 19:11
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class CheckRequest {

    /**
     * 信任的Application名称
     */
    private String trustApplicationName;

    /**
     * 信任的证书MD5值
     */
    private String trustSignMD5;

    /**
     * 宿主Application实例
     */
    private Application mApplication;

    /**
     * 是否开启签名验证
     */
    private boolean isOpenSignatureCheck;

    /**
     * 是否开启中间人攻击检测
     */
    private boolean isOpenMITMCheck;


    public String getTrustApplicationName() {
        return trustApplicationName;
    }

    public void setTrustApplicationName(String trustApplicationName) {
        this.trustApplicationName = trustApplicationName;
    }

    public String getTrustSignMD5() {
        return trustSignMD5;
    }

    public void setTrustSignMD5(String trustSignMD5) {
        this.trustSignMD5 = trustSignMD5;
    }

    public Application getApplication() {
        return mApplication;
    }

    public void setApplication(Application mApplication) {
        this.mApplication = mApplication;
    }

    public boolean isOpenSignatureCheck() {
        return isOpenSignatureCheck;
    }

    public void setOpenSignatureCheck(boolean openSignatureCheck) {
        isOpenSignatureCheck = openSignatureCheck;
    }

    public boolean isOpenMITMCheck() {
        return isOpenMITMCheck;
    }

    public void setOpenMITMCheck(boolean openMITMCheck) {
        isOpenMITMCheck = openMITMCheck;
    }
}
