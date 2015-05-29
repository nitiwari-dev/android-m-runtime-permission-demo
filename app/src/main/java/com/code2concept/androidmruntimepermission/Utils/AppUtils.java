package com.code2concept.androidmruntimepermission.Utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;


public class AppUtils {

    public static boolean verifyAllPermissions(int[] permissions) {
        for (int result : permissions) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasSelfPermission(Activity activity, String[] permissions) {
        if (!isMNCBuildVersion()) {
            return true;
        }

        // Verify that all the permissions.
        for (String permission : permissions) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public static boolean isMNCBuildVersion() {
        return "MNC".equals(Build.VERSION.CODENAME);
    }
}
