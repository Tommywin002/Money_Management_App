package com.example.moneymanagement.ui.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

import android.content.Context;


public class LanguageManagement {
    Context context;
    SharedPreferences sharedPreference;

    public LanguageManagement( ) {
        sharedPreference = context.getSharedPreferences("Lang", Context.MODE_PRIVATE);
    }

    public void updateResource(Activity activity, String code){
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        setLang(code);
    }
    public String getLang(){
        return sharedPreference.getString("lang", "en");
    }
    public void setLang(String code){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString("lang", code);
        editor.commit();
    }
}
