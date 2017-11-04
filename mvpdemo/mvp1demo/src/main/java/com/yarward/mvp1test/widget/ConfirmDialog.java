package com.yarward.mvp1test.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


/**
 * Created by Michelle_Hong on 2017/8/31 0031.
 *
 * @des
 */

public class ConfirmDialog extends DialogFragment {

    public static final String ARGUMENT = "arguments";
    private String message;
    private DialogInterface.OnClickListener positivewClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    };
    private DialogInterface.OnClickListener negativeClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    };


    public void setButtonClickListener(DialogInterface.OnClickListener positivewClickListener,
                                       DialogInterface.OnClickListener negativeClickListener){
        this.positivewClickListener = positivewClickListener;
        this.negativeClickListener = negativeClickListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            message = bundle.getString(ARGUMENT);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton(R.string.ok,positivewClickListener)
                .setNegativeButton(R.string.cancel,negativeClickListener);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public static ConfirmDialog  newInstance(String message){
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, message);
        ConfirmDialog confirDialog = new ConfirmDialog();
        confirDialog.setArguments(bundle);
        return confirDialog;
    }

    public static ConfirmDialog  newInstance(String message,
                                             DialogInterface.OnClickListener positivewClickListener,
                                             DialogInterface.OnClickListener negativeClickListener){
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, message);
        ConfirmDialog confirDialog = new ConfirmDialog();
        confirDialog.setArguments(bundle);
        confirDialog.setButtonClickListener(positivewClickListener,negativeClickListener);
        return confirDialog;
    }

}
