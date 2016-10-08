package com.zkr.peoplehomedoc.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;

import com.zkr.peoplehomedoc.R;


/**
 * @PackageName: ---------widget
 * @Description: ---------自定义选择图片对话框--从相册或者照相选择照片
 * @author: ---------------LF
 * @date: -----------------2016/6/21 10:03
 * @Copyright: -----------中科软
 */
public class PhotoDialogUtil extends Dialog{


    public PhotoDialogUtil(Context context) {
        super(context);
    }

    public PhotoDialogUtil(Context context, int dialog) {
        super(context,dialog);
    }

    public static class Builder {
        private Context context;
        private String chosePictureFromPhone;
        private String chosePictureFromCamera;
        private String chosePictureCancelText;
        private View contentView;
        private OnClickListener chosePictureFromPhoneButtonClickListener;
        private OnClickListener chosePictureFromCameraButtonClickListener;
        private OnClickListener chosePictureCancelButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setChosePictureFromPhoneButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.chosePictureFromPhone = (String) context
                    .getText(positiveButtonText);
            this.chosePictureFromPhoneButtonClickListener = listener;
            return this;
        }

        public Builder setChosePictureFromPhoneButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.chosePictureFromPhone = positiveButtonText;
            this.chosePictureFromPhoneButtonClickListener = listener;
            return this;
        }

        public Builder setChosePictureFromCameraButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.chosePictureFromCamera = (String) context
                    .getText(negativeButtonText);
            this.chosePictureFromCameraButtonClickListener = listener;
            return this;
        }

        public Builder setChosePictureFromCameraButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.chosePictureFromCamera = negativeButtonText;
            this.chosePictureFromCameraButtonClickListener = listener;
            return this;
        }

        public Builder setChosePictureCancelButton(
                String chosePictureCancelButtonText,
                OnClickListener listener) {
            this.chosePictureCancelText =chosePictureCancelButtonText;
            this.chosePictureCancelButtonClickListener = listener;
            return this;
        }

        public PhotoDialogUtil create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final PhotoDialogUtil dialog = new PhotoDialogUtil(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_choise_photo_layout, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            // set the confirm button
            if (chosePictureFromPhone != null) {
                ((Button) layout.findViewById(R.id.chosePictureFromPhone))
                        .setText(chosePictureFromPhone);
                if (chosePictureFromPhoneButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.chosePictureFromPhone))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    chosePictureFromPhoneButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.chosePictureFromPhone).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (chosePictureFromCamera != null) {
                ((Button) layout.findViewById(R.id.chosePictureFromCamera))
                        .setText(chosePictureFromCamera);
                if (chosePictureFromCameraButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.chosePictureFromCamera))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    chosePictureFromCameraButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.chosePictureFromCamera).setVisibility(
                        View.GONE);
            }

            // set the cancel button
            if (chosePictureCancelText != null) {
                ((Button) layout.findViewById(R.id.chosePictureCancel))
                        .setText(chosePictureCancelText);
                if (chosePictureCancelButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.chosePictureCancel))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    chosePictureCancelButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.chosePictureFromCamera).setVisibility(
                        View.GONE);
            }

            dialog.setContentView(layout);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.dimAmount = 0.6f;
            dialog.getWindow().setAttributes(lp);
            dialog.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            return dialog;
        }
    }

}
