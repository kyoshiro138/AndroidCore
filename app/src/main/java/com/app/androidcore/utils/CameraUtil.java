package com.app.androidcore.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

public class CameraUtil {
    private static final int REQUEST_IMAGE_CAPTURE = 101;

    private Activity mActivity;
    private PhotoCapturedListener mListener;

    private String mCurrentPhotoPath;

    public CameraUtil(Activity activity) {
        mActivity = activity;
    }

    public void open(PhotoCapturedListener listener) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            File photoFile = new FileUtil(mActivity).createTempImageFile();
            mCurrentPhotoPath = "file:" + photoFile.getAbsolutePath();

            mListener = listener;
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            mActivity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            try {
                if (mListener != null) {
                    Uri imageUri = Uri.parse(mCurrentPhotoPath);
                    int orientationAngle = ImageUtil.getOrientation(mActivity, imageUri);

                    Bitmap imageBitmap = ImageUtil.decodePhoto(mActivity, imageUri);
                    Bitmap orientedImageBitmap = ImageUtil.rotateImage(imageBitmap, orientationAngle);
                    mListener.onPhotoCaptured(orientedImageBitmap, imageUri);
                    mListener = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface PhotoCapturedListener {
        void onPhotoCaptured(Bitmap photoBitmap, Uri imageUri);
    }
}
