package org.zzy.skynet;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Log;

import org.zzy.lib.skynet.Skynet;
import org.zzy.lib.skynet.utils.Base64Utils;
import org.zzy.lib.skynet.utils.ContextUtils;
import org.zzy.lib.skynet.utils.MD5Utils;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2021/3/19 9:29
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class SkynetApp extends Application {

    public SkynetApp(){
        Skynet.getInstance().init(this,"SkynetApp","86CE18A0F4A231F7F87473AB166D5CA9")
        .check();
    }

}
