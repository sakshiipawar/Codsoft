package com.example.quote_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyQuotesAdapter extends RecyclerView.Adapter<MyQuotesAdapter.ViewHolder> {

    private List<String> quotes;


    public MyQuotesAdapter() {
        this.quotes = quotes;
    }


    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_quote, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String quote = quotes.get(position);
        holder.itemQuoteTextView.setText(quote);

    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemQuoteTextView;



        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemQuoteTextView = itemView.findViewById(R.id.itemQuoteTextView);
        }
    }
}