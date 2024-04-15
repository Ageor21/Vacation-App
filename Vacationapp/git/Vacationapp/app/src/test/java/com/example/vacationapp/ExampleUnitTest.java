package com.example.vacationapp;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.pm.ApplicationInfo;


public class ExampleUnitTest {
    @Test
    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }
}
