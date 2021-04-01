package org.zzy.lib.skynet.utils;

import android.util.Base64;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2021/3/18 17:22
 * 描    述：字符串过长（一般超过76）时会自动在中间加一个换行符，
 * 要使用NO_WRAP参数
 * 修订历史：
 * ================================================
 */
public class Base64Utils {

    /**
     * 二进制数组 编码成 二进制数组
     * @param input
     * @return
     */
    public static byte[] encodeToBytes(byte[] input) {
        return Base64.encode(input, Base64.NO_WRAP);
    }

    /**
     * 二进制数组 编码成 字符串
     * @param input
     * @return
     */
    public static String encodeToString(byte[] input) {
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }

    /**
     * 二进制数组 解码成 二进制数组
     * @param input
     * @return
     */
    public static byte[] decode(byte[] input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * 字符串 解码成 二进制数组
     * @return
     */
    public static byte[] decode(String str) {
        return Base64.decode(str, Base64.NO_WRAP);
    }
}
