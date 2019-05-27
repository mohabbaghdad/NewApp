package com.example.new_app;

import android.content.AsyncTaskLoader;
import android.content.Context;


import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<customArray>> {

    private static final String LOG_TAG = NewsLoader.class.getName();

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<customArray> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<customArray> news = QueryUtils.fetchNewsData(mUrl);
        return news;
    }
}