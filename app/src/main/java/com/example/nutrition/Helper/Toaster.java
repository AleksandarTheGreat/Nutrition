package com.example.nutrition.Helper;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

public class Toaster implements IToaster{
    private Context context;
    public Toaster(Context context){
        this.context = context;
    }

    @Override
    public void text(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void alert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert")
                .setMessage(text)
                .setCancelable(true)
                .show();
    }
}
