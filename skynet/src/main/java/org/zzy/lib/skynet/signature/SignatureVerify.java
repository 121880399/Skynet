package org.zzy.lib.skynet.signature;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Log;

import org.zzy.lib.skynet.Skynet;
import org.zzy.lib.skynet.utils.Base64Utils;
import org.zzy.lib.skynet.utils.ContextUtils;
import org.zzy.lib.skynet.utils.MD5Utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2021/3/18 16:51
 * 描    述：签名验证，防止二次打包
 * 修订历史：
 * ================================================
 */
public class SignatureVerify {


    /**
     * 检查Application是否被hook
     * 作者: ZhouZhengyi
     * 创建时间: 2021/3/18 16:54
     */
    public boolean checkApplication(Application application,String applicationName) {
        Application nowApplication = application;
        String trueApplicationName = applicationName;
        String nowApplicationName = nowApplication.getClass().getSimpleName();
        return trueApplicationName.equals(nowApplicationName);
    }


    /**
     * 做普通的签名校验
     */
    public boolean doSignCheck(String md5) {
        String trueSignMD5 = md5;
        String nowSignMD5 = "";
        Signature[] signs = null;
        try {
            // 得到签名 MD5
            if (Build.VERSION.SDK_INT >= 28) {
                PackageInfo packageInfo = ContextUtils.getContext().getPackageManager().getPackageInfo(
                        ContextUtils.getContext().getPackageName(),
                        PackageManager.GET_SIGNING_CERTIFICATES);
                signs = packageInfo.signingInfo.getApkContentsSigners();
            } else {
                PackageInfo packageInfo = ContextUtils.getContext().getPackageManager().getPackageInfo(
                        ContextUtils.getContext().getPackageName(),
                        PackageManager.GET_SIGNATURES);
                signs = packageInfo.signatures;
            }
            //不用转base64 否则得不到正确的md5
//            String signBase64 = Base64Utils.encodeToString(signs[0].toByteArray());
            nowSignMD5 = MD5Utils.getMd5(signs[0].toByteArray());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return trueSignMD5.equalsIgnoreCase(nowSignMD5);
    }

    /**
     * 检测 PM 代理
     */
    @SuppressLint("PrivateApi")
    public boolean checkPMProxy(){
        String truePMName = "android.content.pm.IPackageManager$Stub$Proxy";
        String nowPMName = "";
        try {
            // 被代理的对象是 PackageManager.mPM
            PackageManager packageManager = ContextUtils.getContext().getPackageManager();
            Field mPMField = packageManager.getClass().getDeclaredField("mPM");
            mPMField.setAccessible(true);
            Object mPM = mPMField.get(packageManager);
            // 取得类名
            nowPMName = mPM.getClass().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 类名改变说明被代理了
        return truePMName.equals(nowPMName);
    }








}
