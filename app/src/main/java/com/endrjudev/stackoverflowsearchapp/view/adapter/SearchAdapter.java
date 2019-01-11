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
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends ListAdapter<Item, SearchAdapter.SearchViewHolder> {

    public SearchAdapter() {
        super(new ItemDiffCallback());
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
        holder.bind(createOnClickListener(holder.binding.getRoot().getContext()), getItem(position));
    }

    private View.OnClickListener createOnClickListener(Context context) {
        return v -> Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
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

    static class ItemDiffCallback extends DiffUtil.ItemCallback<Item> {

        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getQuestionId() == newItem.getQuestionId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.equals(newItem);
        }
    }
}
