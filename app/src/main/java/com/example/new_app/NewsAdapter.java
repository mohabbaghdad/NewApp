package com.example.new_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<customArray> {

    public NewsAdapter(Context context, ArrayList<customArray> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item, parent, false);
        }

        customArray currentNews = getItem(position);
        TextView newsTitleTextView = (TextView) listItemView.findViewById(R.id.title_text_view);
        String title = currentNews.getmTitle();
        newsTitleTextView.setText(title);

        TextView newsCategorytextView = (TextView) listItemView.findViewById(R.id.category_text_view);
        String category = currentNews.getmCategory();
        newsCategorytextView.setText(category);

        TextView authertextView = (TextView) listItemView.findViewById(R.id.author_text_view);
        String auther = currentNews.getmAuther();
        authertextView.setText(auther);

        TextView newsDatetextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        String date = currentNews.getmDate();
        newsDatetextView.setText(date);

        return listItemView;
    }
}
