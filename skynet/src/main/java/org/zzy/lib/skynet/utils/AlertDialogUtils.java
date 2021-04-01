package org.zzy.lib.skynet.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import org.zzy.lib.skynet.Skynet;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2021/3/18 21:38
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class AlertDialogUtils {

    private AlertDialogUtils(){

    }

    private static class Holder{
        private static AlertDialogUtils INSTANCE = new AlertDialogUtils();
    }

    public static AlertDialogUtils getInstance(){
        return Holder.INSTANCE;
    }


    public void showDialog(Context context,String title,String content,String buttonText,DialogInterface.OnClickListener clickListener,DialogInterface.OnCancelListener onCancelListener){
        if(context == null){
            Log.d("Skynet","Context is null!");
            return;
        }
        Dialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setOnCancelListener(onCancelListener)
                .setPositiveButton(buttonText,clickListener)
                .create();

        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
