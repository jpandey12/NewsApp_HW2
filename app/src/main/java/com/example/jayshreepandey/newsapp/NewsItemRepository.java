package com.example.jayshreepandey.newsapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;





public class NewsItemRepository {
    private static NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNewsItems;

    public NewsItemRepository(Application application) {
        NewsItemDatabase db = NewsItemDatabase.getDatabase(application.getApplicationContext());
        mNewsItemDao = db.newsItemDao();
        mAllNewsItems = mNewsItemDao.loadAllNewsItems();
    }

    public LiveData<List<NewsItem>> getAllNewsItems() {
        return mAllNewsItems;
    }

    public static void syncNews() {
        new SyncNewsTask(mNewsItemDao).execute(NetworkUtils.buildURL());
    }

    public static class SyncNewsTask extends AsyncTask<URL, Void, String> {
        private NewsItemDao mAsyncTaskDao;
        public static ArrayList<NewsItem> sNewItems;
        SyncNewsTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mAsyncTaskDao.clearAll();
        }

        @Override
        protected String doInBackground(URL... urls) {
            String newsSearchResults = "";
            try {
                newsSearchResults = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newsSearchResults;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<NewsItem> news = JsonUtils.parseNews(s);
            sNewItems = news;
            mAsyncTaskDao.insert(news);
        }
    }
}
