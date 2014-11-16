package me.cafecode.lib.lollipopdialoglib;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Map;

/**
 * Created by Natthawut Hemathulin on 11/16/14 AD.
 * Email: natthawut1991@gmail.com
 */
public class LollipopDialogFragment extends DialogFragment {

    public interface OnClickListener {
        public void onNegativeClick(Dialog dialog);
        public void onPositiveClick(Dialog dialog);
    }

    private TextView mTitleText;
    private TextView mMessageText;
    private Button mNegativeButton;
    private Button mPositiveButton;
    private OnClickListener mListener;

    private Bundle mArgs;

    public static LollipopDialogFragment newInstance(String title, String message) {
        LollipopDialogFragment dialogFragment = new LollipopDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    public static LollipopDialogFragment newInstance(String title, String message, String negativeButtonTitle, String positiveButtonTitle) {
        LollipopDialogFragment dialogFragment = new LollipopDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        args.putString("negative", negativeButtonTitle);
        args.putString("positive", positiveButtonTitle);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_lollipop, container);

        mTitleText = (TextView) view.findViewById(R.id.title);
        mMessageText = (TextView) view.findViewById(R.id.message);
        mNegativeButton = (Button) view.findViewById(R.id.button_negative);
        mPositiveButton = (Button) view.findViewById(R.id.button_positive);

        mArgs = getArguments();

        setTitle(mArgs.getString("title"));
        setMessage(mArgs.getString("message"));
        setNegativeButton(mArgs.getString("negative"));
        setPositiveButton(mArgs.getString("positive"));
        return view;
    }

    public void setTitle(String title) {
        if (title != null) {
            mTitleText.setText(title);
//            mTitleText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher, 0, 0, 0);
        } else {
            mTitleText.setVisibility(View.GONE);
        }
    }

    public void setMessage(String message) {
        if (message != null) {
            mMessageText.setText(message);
        } else {
            mMessageText.setVisibility(View.GONE);
        }
    }

    public void setNegativeButton(String negativeButtonTitle) {
        if (negativeButtonTitle != null) {
            mNegativeButton.setText(negativeButtonTitle);
            if (mArgs.getString("positive") == null) {
                // Move to right
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mNegativeButton.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                mNegativeButton.setLayoutParams(params);
                mPositiveButton.setVisibility(View.GONE);

                // On click
                mNegativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null)
                            mListener.onNegativeClick(getDialog());
                    }
                });
            }
        } else {
            mNegativeButton.setVisibility(View.GONE);
        }
    }

    public void setPositiveButton(String positiveButtonTitle) {
        if (positiveButtonTitle != null) {
            mPositiveButton.setText(positiveButtonTitle);

            // On click
            mPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                        mListener.onPositiveClick(getDialog());
                }
            });
        } else {
            mPositiveButton.setVisibility(View.GONE);
        }
    }

    public void setOnClick(OnClickListener listener) {
        mListener = listener;
    }
}
