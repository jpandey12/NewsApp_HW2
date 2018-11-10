package com.example.jayshreepandey.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {
    public static ArrayList<NewsItem> parseNews(String JSONString){
        ArrayList<NewsItem> newsEntity=new ArrayList<>();
        try{
            JSONObject JObject=new JSONObject(JSONString);
            JSONArray JArray=JObject.getJSONArray("articles");
            for (int i=0;i<JArray.length();i++){
                JSONObject article=JArray.getJSONObject(i);
                newsEntity.add(new NewsItem(article.getString("author"),
                        article.getString("title"),
                        article.getString("description"),
                        article.getString("url"),
                        article.getString("publishedAt")
                ));
            }
        }
        catch (JSONException ex){
            ex.printStackTrace();

        }
        return newsEntity;
    }
}
