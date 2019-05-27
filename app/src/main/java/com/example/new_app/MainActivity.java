package com.example.new_app;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<customArray>> {
    private static final String USGS_REQUEST_URL = "http://content.guardianapis.com/search?q=debates&api-key=test&show-tags=contributor";
    private NewsAdapter mAdapter;
    private static final int News_LOADER_ID = 1;
    private TextView mEmptyStateTextView;
    boolean isConnected = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsListView = (ListView) findViewById(R.id.list);
        mAdapter = new NewsAdapter(this, new ArrayList<customArray>());
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);
        newsListView.setAdapter(mAdapter);

        LoaderManager loaderManager = getLoaderManager();
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        isConnected = networkInfo != null && networkInfo.isConnected();
        loaderManager.initLoader(News_LOADER_ID, null, this);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                customArray currentNews = mAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getmUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });
    }

    @Override
    public Loader<List<customArray>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<customArray>> loader, List<customArray> news) {
        mAdapter.clear();

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        if (!isConnected) {
            mEmptyStateTextView.setText(R.string.no_connetion);
        } else {
            mEmptyStateTextView.setText(R.string.no_News);
        }
        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<customArray>> loader) {
        mAdapter.clear();
    }
}

