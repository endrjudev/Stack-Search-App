package com.endrjudev.stackoverflowsearchapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.endrjudev.stackoverflowsearchapp.databinding.QueryItemBinding;
import com.endrjudev.stackoverflowsearchapp.model.Item;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Item> itemList;

    public SearchAdapter() {
        this.itemList = new LinkedList<>();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final QueryItemBinding binding = QueryItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        final Item item = itemList.get(position);
        holder.bind(createOnClickListener(holder.binding.getRoot().getContext()), item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private View.OnClickListener createOnClickListener(Context context) {
        return v -> Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
    }

    public void setItems(@NonNull List<Item> items) {
        itemList.clear();
        itemList.addAll(items);
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {

        private QueryItemBinding binding;

        SearchViewHolder(QueryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(View.OnClickListener listener, Item item) {
            binding.itemLayout.setOnClickListener(listener);
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
