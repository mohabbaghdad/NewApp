package com.example.new_app;

public class customArray {
    private String mTitle;
    private String mAuther;
    private String mCategory;
    private String mDate;
    private String mUrl;

    public customArray(String mTitle, String mCategory, String mDate, String mUrl,String mAuther) {
        this.mTitle = mTitle;
        this.mCategory = mCategory;
        this.mDate = mDate;
        this.mUrl = mUrl;
        this.mAuther=mAuther;
    }

    public String getmTitle() {

        return mTitle;
    }

    public String getmCategory() {

        return mCategory;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmAuther() {
        return mAuther;
    }
}
