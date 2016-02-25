//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.orasystems.libs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public final class InterfaceUtils_
    extends InterfaceUtils
{

    private Context context_;
    private Handler handler_ = new Handler(Looper.getMainLooper());

    private InterfaceUtils_(Context context) {
        context_ = context;
        init_();
    }

    public static InterfaceUtils_ getInstance_(Context context) {
        return new InterfaceUtils_(context);
    }

    private void init_() {
        if (context_ instanceof Activity) {
            activity = ((Activity) context_);
        } else {
            Log.w("InterfaceUtils_", (("Due to Context class "+ context_.getClass().getSimpleName())+", the @RootContext Activity won't be populated"));
        }
    }

    public void rebind(Context context) {
        context_ = context;
        init_();
    }

    @Override
    public void exibeMsg(final String msg) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                InterfaceUtils_.super.exibeMsg(msg);
            }

        }
        );
    }

    @Override
    public void exibeMsg(final String msg, final OnClickListener onClickListener) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                InterfaceUtils_.super.exibeMsg(msg, onClickListener);
            }

        }
        );
    }

    @Override
    public void exibeToast(final String string) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                InterfaceUtils_.super.exibeToast(string);
            }

        }
        );
    }

    @Override
    public void exibeMsgFinish(final String msg, final boolean finish) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                InterfaceUtils_.super.exibeMsgFinish(msg, finish);
            }

        }
        );
    }

}