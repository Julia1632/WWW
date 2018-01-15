package com.vk_2.RecycleAdapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vk_2.DataModel.Item;
import com.vk_2.ImageLoader.Malevich;
import com.vk_2.R;

import java.util.List;

import static android.content.ContentValues.TAG;


class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progressBar);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView name, number;
    ImageView imageView;


    public ItemViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.txtName);
        number = itemView.findViewById(R.id.txtNumber);
        imageView = (ImageView) itemView.findViewById(R.id.new_image);
    }
}

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    ILoadMore loadMore;
    boolean isLoading;
    Context activity;
    List<Item> items;
    int visibleThreshold = 5;
    int lastVisibleItem;
    int totalItemCount;

    public MyAdapter(RecyclerView recyclerView, Context activity, List<Item> items) {
        this.activity = activity;
        this.items = items;

        final LinearLayoutManager linearLayoutManager
                = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {

                    if (loadMore != null) {
                        loadMore.onLoadMore();
                    }
                    isLoading = true;
                }


            }
        });
    }



    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_layout, parent, false);

            return new ItemViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            Item item = items.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.number.setText(String.valueOf(position + 1));
            viewHolder.name.setText(item.getName());

            Malevich.INSTANCE.load(item.getUrl()).into(viewHolder.imageView);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingHolder = (LoadingViewHolder) holder;
            loadingHolder.progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int layoutPosition = holder.getLayoutPosition();
        Log.d(TAG, "onViewAttachedToWindow: getayoutPosition = " + layoutPosition);

        layoutPosition = holder.getAdapterPosition();
        Log.d(TAG, "onViewAttachedToWindow: getAdapterPosition = " + layoutPosition);

    }


}



