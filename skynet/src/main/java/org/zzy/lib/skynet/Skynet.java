package org.zzy.lib.skynet;

import android.app.Application;

import org.zzy.lib.skynet.interceptor.CheckRequest;
import org.zzy.lib.skynet.interceptor.handler.MITMCheckHandler;
import org.zzy.lib.skynet.interceptor.handler.SecurityCheckHandler;
import org.zzy.lib.skynet.interceptor.handler.SignatureCheckHandler;

import java.security.InvalidParameterException;

import me.weishu.reflection.Reflection;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2021/3/18 16:49
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class Skynet {

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
     * 判断是否初始化
     */
    private volatile boolean isInit = false;

    /**
     * 是否开启签名验证
     */
    private boolean isOpenSignatureCheck = true;

    /**
     * 是否开启中间人攻击检测
     */
    private boolean isOpenMITMCheck = true;

    private SignatureCheckHandler signatureCheckHandler;

    private MITMCheckHandler mitmCheckHandler;

    private Skynet(){

    }

    private static class Holder{
        private static Skynet INSTANCE = new Skynet();
    }

    public static Skynet getInstance(){
        return Holder.INSTANCE;
    }

    /**
    * 初始化数据
    * 作者: ZhouZhengyi
    * 创建时间: 2021/3/18 19:00
    */
    public Skynet init(Application application,String applicationName,String trustSignMD5){
        if(isInit){
           return this;
        }
        try {
            //解除 android P 反射限制
            Reflection.unseal(application);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        this.mApplication = application;
        this.trustApplicationName = applicationName;
        this.trustSignMD5 = trustSignMD5;
        this.isInit = true;
        return this;
    }

    /**
    * 是否开启签名验证
    * 作者: ZhouZhengyi
    * 创建时间: 2021/3/18 18:59
    */
    public Skynet signature(boolean isOpenSignature){
        this.isOpenSignatureCheck = isOpenSignature;
        return this;
    }

    /**
    * 是否开启中间人攻击检测
    * 作者: ZhouZhengyi
    * 创建时间: 2021/3/18 18:59
    */
    public Skynet mitmCheck(boolean isOpen){
        this.isOpenMITMCheck = isOpen;
        return this;
    }


    public void check(){
        if(!isInit){
            throw new InvalidParameterException("Must call init method!");
        }
        signatureCheckHandler = new SignatureCheckHandler();
        mitmCheckHandler = new MITMCheckHandler();
        signatureCheckHandler.setNext(mitmCheckHandler);
        CheckRequest checkRequest = new CheckRequest();
        checkRequest.setApplication(mApplication);
        checkRequest.setOpenMITMCheck(isOpenMITMCheck);
        checkRequest.setOpenSignatureCheck(isOpenSignatureCheck);
        checkRequest.setTrustApplicationName(trustApplicationName);
        checkRequest.setTrustSignMD5(trustSignMD5);
        signatureCheckHandler.handle(checkRequest);
    }

}
