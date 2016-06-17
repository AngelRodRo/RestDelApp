package com.example.restdeliveryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by angel on 17/06/16.
 */
public class Helpers {

    public static void closeAndGoToActivity(Context ctx, Class c){
        Intent i = new Intent(ctx,c);
        ctx.startActivity(i);
        ((Activity)ctx).finish();
    }

}
