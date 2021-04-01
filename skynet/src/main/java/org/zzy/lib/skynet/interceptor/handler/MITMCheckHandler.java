package org.zzy.lib.skynet.interceptor.handler;

import android.content.DialogInterface;
import android.util.Log;

import org.zzy.lib.skynet.interceptor.CheckRequest;
import org.zzy.lib.skynet.mitm.MITM;
import org.zzy.lib.skynet.utils.AlertDialogUtils;
import org.zzy.lib.skynet.utils.ContextUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2021/3/18 18:50
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class MITMCheckHandler extends SecurityCheckHandler{


    @Override
    protected boolean isOpen(CheckRequest request) {
        return request.isOpenMITMCheck();
    }

    @Override
    protected void check(CheckRequest request) {
        MITM mitm = new MITM();
        try {
            if(mitm.isProxy(ContextUtils.getContext())){
                Log.e("Skynet","MIIM check use proxy!");
//                AlertDialogUtils.getInstance().showDialog(ContextUtils.getContext(), "警告", "请不要使用代理运行本APP！", "退出", new DialogInterface.OnClickListener() {
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
//                android.os.Process.killProcess(android.os.Process.myPid());
                throw new Error();
//                System.exit(1);
            }
            if(getNext()!=null){
                getNext().handle(request);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
