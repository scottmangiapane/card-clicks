package com.scottmangiapane.cardcliques;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class QuitDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Quit to menu?")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        StandardGame.gameActivity.finish();
                        StandardGame.gameActivity.overridePendingTransition(R.transition.zoom_1, R.transition.zoom_2);
                    }
                })
                .setNegativeButton("Cancel", null);
        return builder.create();
    }
}
