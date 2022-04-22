package ru.inai.kursach_2_0.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import androidx.preference.PreferenceManager;

import java.util.Locale;

public class LocaleHelper {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    public static Context setLocate(Context context, String language){
        persist(context,language);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return updateResources(context,language);
        }
        return updateResources(context,language);
    }

    private static Context updateResources(Context context, String language) {
//        Locale locale = new Locale(language);
//        Locale.setDefault(locale);
//        Configuration conf = context.getResources().getConfiguration();
//        conf.setLocale(locale);
//        conf.setLayoutDirection(locale);
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        return context.createConfigurationContext(config);
    }

    private static void persist(Context context, String language) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putString(SELECTED_LANGUAGE,language);
        spEditor.apply();
    }

    private static Context updateResourcesLegacy(Context context, String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            conf.setLayoutDirection(locale);
        }
        res.updateConfiguration(conf,res.getDisplayMetrics());
        return context;
    }

}
