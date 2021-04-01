package org.zzy.lib.skynet.interceptor.handler;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.util.Log;

import org.zzy.lib.skynet.Skynet;
import org.zzy.lib.skynet.interceptor.CheckRequest;
import org.zzy.lib.skynet.signature.SignatureVerify;
import org.zzy.lib.skynet.utils.AlertDialogUtils;
import org.zzy.lib.skynet.utils.ContextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2021/3/18 18:48
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class SignatureCheckHandler extends SecurityCheckHandler{


    @Override
    protected boolean isOpen(CheckRequest request) {
        return request.isOpenSignatureCheck();
    }

    @Override
    protected void check(CheckRequest request) {
        SignatureVerify verify = new SignatureVerify();
        if(!verify.checkApplication(request.getApplication(),request.getTrustApplicationName())
            || !verify.checkPMProxy()
            || !verify.doSignCheck(request.getTrustSignMD5())){
            Log.e("Skynet","Signature check failed!");
//            //检测未通过
//            try {
//                AlertDialogUtils.getInstance().showDialog(ContextUtils.getContext(), "警告", "你所使用的App是被修改过的！", "退出", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        System.exit(0);
//                    }
//                }, new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        System.exit(0);
//                    }
//                });
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            }
//              android.os.Process.killProcess(android.os.Process.myPid());
            throw new Error();
//            System.exit(1);
        }
        if(getNext() != null){
            getNext().handle(request);
        }
    }

//    private void killProcessGroup(){
//        try {
//            Log.d("skynet","current thread id: "+ Thread.currentThread().getId() +" current thread name:"+Thread.currentThread().getName());
//            Class processClass = Class.forName("android.os.Process");
//            Method killProcessGroupMethod =
//                    processClass.getDeclaredMethod("killProcessGroup",int.class,int.class);
//            killProcessGroupMethod.setAccessible(true);
//            int result = (int) killProcessGroupMethod.invoke(null,(int)Thread.currentThread().getId(),android.os.Process.myPid());
//            Log.d("skynet","kill process group result: "+ result);
//        } catch (ClassNotFoundException | NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//    }
}
