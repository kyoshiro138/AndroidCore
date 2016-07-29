package com.app.androidcore.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;

public class GalleryUtil {
    private static final int REQUEST_IMAGE_GALLERY = 102;

    private Activity mActivity;
    private GallerySelectListener mListener;

    public GalleryUtil(Activity activity) {
        mActivity = activity;
    }

    public void open(GallerySelectListener listener) {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        mActivity.startActivityForResult(Intent.createChooser(intent, "GALLERY"), REQUEST_IMAGE_GALLERY);

        mListener = listener;
    }

    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK) {
            try {
                if (mListener != null) {
                    Uri selectedImageUri = null;
                    if (data != null && data.getData() != null) {
                        selectedImageUri = data.getData();
                    }
                    int orientationAngle = ImageUtil.getOrientation(mActivity, selectedImageUri);

                    Bitmap imageBitmap = ImageUtil.decodePhoto(mActivity, selectedImageUri);
                    Bitmap orientedImageBitmap = ImageUtil.rotateImage(imageBitmap, orientationAngle);
                    mListener.onGallerySelected(orientedImageBitmap, selectedImageUri);
                    mListener = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface GallerySelectListener {
        void onGallerySelected(Bitmap photoBitmap, Uri imageUri);
    }
}
