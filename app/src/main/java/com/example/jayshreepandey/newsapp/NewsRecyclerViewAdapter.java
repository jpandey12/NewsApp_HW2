package com.example.jayshreepandey.newsapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemViewHolder> {

    Context nct;
    ArrayList<NewsItem> nItems;


    public NewsRecyclerViewAdapter(Context context,ArrayList<NewsItem> newsItem){
        this.nct=context;
        this.nItems=newsItem;
    }

    @NonNull
    @Override
    public NewsRecyclerViewAdapter.NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        boolean atp=false;
        View view=layoutInflater.inflate(R.layout.news_item,parent,atp);
        NewsItemViewHolder newsItemViewHolder=new NewsItemViewHolder(view);
        return newsItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewAdapter.NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return nItems.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView desc;
        TextView date;

        public NewsItemViewHolder(View itemView){
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.title);
            desc=(TextView) itemView.findViewById(R.id.description);
            date= (TextView) itemView.findViewById(R.id.date);

        }
        public void bind(final int index){
            title.setText(nItems.get(index).getTitle());
            desc.setText(nItems.get(index).getDesc());
            date.setText(nItems.get(index).getDate());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String url1=nItems.get(getAdapterPosition()).getUrl();
            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url1));
            nct.startActivity(intent);
        }
    }
}
