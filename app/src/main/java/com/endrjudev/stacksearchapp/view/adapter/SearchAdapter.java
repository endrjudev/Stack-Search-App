package com.endrjudev.stacksearchapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.endrjudev.stacksearchapp.databinding.QueryItemBinding;
import com.endrjudev.stacksearchapp.model.Item;
import com.endrjudev.stacksearchapp.view.SearchFragmentDirections;

import org.apache.commons.lang3.StringUtils;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

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
        holder.bind(createOnClickListener(getItem(position)), getItem(position));
    }

    private View.OnClickListener createOnClickListener(Item item) {
        return v -> {
            Timber.i("Question clicked");
            final SearchFragmentDirections.ActionSearchFragmentToDetailsFragment action =
                    SearchFragmentDirections.actionSearchFragmentToDetailsFragment();
            final String url = item.getLink();
            if (StringUtils.isNotBlank(url)) {
                action.setUrl(url);
            }
            Navigation.findNavController(v).navigate(action);
        };
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
