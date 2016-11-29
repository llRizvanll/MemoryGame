package com.mynta.rz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by rizvan.
 */

public class ImageListAdapter extends  RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
    private Map<Integer,ImageItem> dataList;
    private Context context;
    private ImageSelection imageSelectionListener;
    private ViewHolder viewHolder;
    public ImageListAdapter(Context _context,Map<Integer,ImageItem> list, ImageSelection imageSelection){
        this.dataList = list;
        this.context = _context;
        this.imageSelectionListener = imageSelection;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView,flipImageview;
        LinearLayout item_layout;
        public ViewHolder(View v){
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image_grid_item);
            item_layout = (LinearLayout) v.findViewById(R.id.item_layout);
            flipImageview = (ImageView) v.findViewById(R.id.image_grid_flip_item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.image_item,parent,false);
        viewHolder = new ViewHolder(view);
        Log.d("DATA ","RETURNED");
        return viewHolder;
    }

    private Boolean isClicked = false ;
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final ImageItem item = this.dataList.get(position);

        if (item.getVisited()){
            holder.imageView.setVisibility(View.VISIBLE);
            holder.flipImageview.setVisibility(View.GONE);
        }
        else{
            holder.imageView.setVisibility(View.GONE);
            holder.flipImageview.setVisibility(View.VISIBLE);
        }

        if (!isClicked) {
            holder.flipImageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isClicked = true;
                    if (imageSelectionListener.imageSelected(position,item.getImagePath())) {
                        holder.flipImageview.setVisibility(View.GONE);
                        holder.imageView.setVisibility(View.VISIBLE);
                    }
                    isClicked = false;
                }
            });
        }

        Picasso.with(this.context).load(item.getImagePath()).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }

    public interface ImageSelection{
        public Boolean imageSelected(int position, String imagePath);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
