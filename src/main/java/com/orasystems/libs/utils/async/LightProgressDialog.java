/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orasystems.libs.utils.async;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.orasystems.libs.utils.R;

/**
 * Progress dialog in Holo Light theme
 */
public class LightProgressDialog extends ProgressDialog {

    public static AlertDialog createDialog(Context context,boolean progressHorizontal) {
        return create(context,progressHorizontal);
    }

    public static ProgressDialog create(Context context,boolean progressHorizontal) {
            ProgressDialog dialog;
            if (SDK_INT >= ICE_CREAM_SANDWICH)
                dialog = new LightProgressDialog(context);
            else {
                dialog = new ProgressDialog(context);
            }
            if(!progressHorizontal){
            	dialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.spinner));
            	dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            }else{
            	dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            }
            return dialog;
    }

    private LightProgressDialog(Context context) {
        super(context, THEME_HOLO_LIGHT);
    }
}
