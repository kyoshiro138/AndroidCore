package com.app.androidcore.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Locale;

public class FileUtil {
    protected Context mContext;

    protected FileUtil(Context context) {
        mContext = context;
    }

    public File createTempImageFile() {
        String imageFileName = createTempFileName();
        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            return File.createTempFile(imageFileName, getImageFileExtension(), storageDir);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public File createTempFileFromBitmap(Bitmap bitmap) {
        String imageFileName = createTempFileName();
        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            File file = File.createTempFile(imageFileName, getImageFileExtension(), storageDir);
            OutputStream outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outStream);
            outStream.flush();
            outStream.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getImageFileExtension() {
        return ".jpg";
    }

    private String createTempFileName() {
        return String.format(Locale.getDefault(), "temp_%s", String.valueOf(System.currentTimeMillis()));
    }
}
