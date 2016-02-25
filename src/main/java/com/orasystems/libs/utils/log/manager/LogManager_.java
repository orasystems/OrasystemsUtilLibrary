//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.orasystems.libs.utils.log.manager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public final class LogManager_
    extends LogManager
{

    private Context context_;

    private LogManager_(Context context) {
        context_ = context;
        init_();
    }

    public static LogManager_ getInstance_(Context context) {
        return new LogManager_(context);
    }

    private void init_() {
        if (context_ instanceof Activity) {
            activity = ((Activity) context_);
        } else {
            Log.w("LogManager_", (("Due to Context class "+ context_.getClass().getSimpleName())+", the @RootContext Activity won't be populated"));
        }
    }

    public void rebind(Context context) {
        context_ = context;
        init_();
    }

}
