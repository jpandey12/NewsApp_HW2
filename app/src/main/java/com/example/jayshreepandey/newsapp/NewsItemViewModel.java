package com.example.jayshreepandey.newsapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {
    private NewsItemRepository repository;

    private LiveData<List<NewsItem>> mAllNewsItems;

    public NewsItemViewModel(Application application) {
        super(application);
        repository = new NewsItemRepository(application);
        mAllNewsItems = repository.getAllNewsItems();
    }

    public LiveData<List<NewsItem>> getAllNewsItems() {
        return mAllNewsItems;
    }

    public void syncNews() {
        repository.syncNews();
    }
}