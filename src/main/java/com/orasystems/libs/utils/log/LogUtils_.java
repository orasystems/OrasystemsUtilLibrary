//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.orasystems.libs.utils.log;

import android.content.Context;
import com.orasystems.libs.utils.log.manager.LogManager_;

public final class LogUtils_
    extends LogUtils
{

    private Context context_;

    private LogUtils_(Context context) {
        context_ = context;
        init_();
    }

    public static LogUtils_ getInstance_(Context context) {
        return new LogUtils_(context);
    }

    private void init_() {
        logManager = LogManager_.getInstance_(context_);
    }

    public void rebind(Context context) {
        context_ = context;
        init_();
    }

}
