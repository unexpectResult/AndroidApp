package com.example.androidapp.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

public class AssetsUtils {

//    读取assets文件夹当中的内容，存放在字符串中
    public static  String getJsonFromAssets(Context context, String filename){
//        获取Assets文件夹管理器
        AssetManager assets = context.getResources().getAssets();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        获取输入流
        try {
            InputStream open = assets.open(filename);
//            读取内容存放到内存流当中
            int hasRead = 0;
            byte[]buf = new byte[1024];
            while (true){
                hasRead = open.read(buf);
                if (hasRead == -1){
                    break;
                }
                baos.write(buf,0,hasRead);
                String msg = baos.toString();
                open.close();
                return msg;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

//    读取Assets文件夹下的图片，返回Bitmap对象
    public static Bitmap getBitmapFromAssets(Context context,String filename){
        Bitmap bitmap = null;
//        获取文件管理者
        AssetManager assets = context.getResources().getAssets();
        try {
            InputStream open = assets.open(filename);
//            通过位图管理器，将输入流转换为位图对象
                bitmap = BitmapFactory.decodeStream(open);
                open.close();
                return bitmap;
            } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}
