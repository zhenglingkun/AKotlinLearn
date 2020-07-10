package com.zlk.akotlinlearn.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BitmapUtilities {

    public static final float UPLOAD_W = 480f;
    public static final float UPLOAD_H = 800f;
    public static final int FILE_SIZE = 10240;

    public BitmapUtilities() {
    }

    /**
     * 图片压缩
     *
     * @param image    待压缩的图片
     * @param hh       图片压缩到的高
     * @param ww       图片压缩到的宽
     * @param fileSize 压缩到的图片大小，如果为0，则不进行指定大小压缩
     * @return 返回压缩后的图片
     */
    public static Bitmap comp(Bitmap image, float hh, float ww, int fileSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap;
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        if (fileSize != 0) {
            return compressImage(bitmap, fileSize);//压缩好比例大小后再进行质量压缩
        } else {
            return bitmap;
        }
    }

    /**
     * 质量压缩
     *
     * @param image    待压缩的图片
     * @param fileSize 压缩到的图片大小
     * @return 返回压缩后的图片
     */
    private static Bitmap compressImage(Bitmap image, int fileSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > fileSize) {  //循环判断如果压缩后图片是否大于200kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        return BitmapFactory.decodeStream(isBm, null, null);
    }

    /**
     * Drawable转换为Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    //    public static Bitmap getBitmapThumbnail(String path, float width, float height) {
//        Bitmap bitmap;
//        //这里可以按比例缩小图片：
//        /*BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inSampleSize = 4;//宽和高都是原来的1/4
//        bitmap = BitmapFactory.decodeFile(path, opts); */
//        /*进一步的，
//        如何设置恰当的inSampleSize是解决该问题的关键之一。BitmapFactory.Options提供了另一个成员inJustDecodeBounds。
//        设置inJustDecodeBounds为true后，decodeFile并不分配空间，但可计算出原始图片的长度和宽度，即opts.width和opts.height。
//        有了这两个参数，再通过一定的算法，即可得到一个恰当的inSampleSize。*/
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(path, opts);
//        opts.inSampleSize = Math.max((int) (opts.outHeight / height), (int) (opts.outWidth / width));
//        opts.inJustDecodeBounds = false;
//        bitmap = BitmapFactory.decodeFile(path, opts);
//        return bitmap;
//    }

//    /**
//     * @param bmp
//     * @param width
//     * @param height
//     * @return
//     */
//    public static Bitmap getBitmapThumbnail(Bitmap bmp, int width, int height) {
//        Bitmap bitmap = null;
//        if (bmp != null) {
//            int bmpWidth = bmp.getWidth();
//            int bmpHeight = bmp.getHeight();
//
//            int scaledWidth;
//            int scaledHeight;
//            if (bmpWidth > bmpHeight) {
//                int longerW = height * Math.max(bmpWidth, bmpHeight) / Math.min(bmpWidth, bmpHeight);
////                scaledWidth = bmpWidth > bmpHeight ? width : longerW;
////                scaledHeight = bmpWidth > bmpHeight ? longerW : height;
//                scaledWidth = width;
//                scaledHeight = longerW;
//            } else {
//                int longerW = width * Math.max(bmpWidth, bmpHeight) / Math.min(bmpWidth, bmpHeight);
////                scaledWidth = bmpWidth > bmpHeight ? longerW : width;
////                scaledHeight = bmpWidth > bmpHeight ? height : longerW;
//                scaledWidth = width;
//                scaledHeight = longerW;
//            }
//            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmp, scaledWidth, scaledHeight, true);
//
//            int xTopLeft = (scaledWidth - width) / 2;
//            int yTopLeft = (scaledHeight - height) / 2;
//
//            bitmap = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, width, height);
//            scaledBitmap.recycle();
//        }
//        return bitmap;
//    }

}
