package com.example.androidlocalizationguide

class MainActivity {
    Locale locale = new Locale("fr");
    Configuration config = getBaseContext().getResources().getConfiguration();
    config.locale = locale;
    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
}