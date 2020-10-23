package com.bashapplication.view;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public abstract class DelegateRecyclerAdapter<T> extends DelegateAdapter.Adapter<DelegateRecyclerAdapter.RecyclerViewHolder> {

    public List<T> mItems;
    public final Context mContext;

    protected LayoutInflater mInflater;
    protected OnItemClickListener mClickListener;
    protected OnItemLongClickListener mLongClickListener;
    protected OnItemViewListener mOnItemViewListener;

    public OnViewClickListener mViewCilckListener;

    public DelegateRecyclerAdapter(Context ctx, List<T> list) {
        mItems = (list != null) ? list : new ArrayList<T>();
        mContext = ctx.getApplicationContext();
        mInflater = LayoutInflater.from(ctx);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //实例化ViewHodler
        final RecyclerViewHolder holder = new RecyclerViewHolder(mContext, mInflater.inflate(getItemLayoutId(viewType), parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        bindData(holder, position, mItems.get(position));
        //触发 item click event
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(holder.itemView, mItems.get(position), position);
                }
            });
        }
        //触发 long click event
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClickListener.onItemLongClick(holder.itemView,mItems.get(position), position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public void add(int pos, T item) {
        mItems.add(pos, item);
        notifyItemInserted(pos);
    }

    public void delete(int pos) {
        if(mItems.size() <= 0)
            return;
        mItems.remove(pos);
        notifyItemRemoved(pos);
    }
    public void set(int pos,T item)
    {
        mItems.set(pos,item);
        notifyDataSetChanged();
    }



    public void AddHeaderItem(List<T> items){
        mItems.addAll(0,items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<T> items){
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void setData(List<T> items)
    {
        mItems.clear();
        mItems = items;
        notifyDataSetChanged();
    }


    public void clearData()
    {
        mItems.clear();
        notifyDataSetChanged();
    }
    /**
     * 重新排序
     * @param fromPosition
     * @param toPosition
     */
    public void swap(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }


    /**
     * 重写该方法，根据viewType设置item的layout
     *
     * @param viewType 通过重写getItemViewType（）设置，默认item是0
     * @return
     */
    protected abstract int getItemLayoutId(int viewType);

    /**
     * 重写该方法进行item数据项视图的数据绑定
     *
     * @param holder   通过holder获得item中的子View，进行数据绑定
     * @param position 该item的position
     * @param item     映射到该item的数据
     */
    protected abstract void bindData(RecyclerViewHolder holder, int position, T item);

    @Override
    public void onViewAttachedToWindow(RecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }



    /**
     * 自定义Item Click event
     */
    public interface OnItemClickListener {
        void onItemClick(View itemView, Object item, int pos);
    }

    /**
     * 自定义item long event
     */
    public interface OnItemLongClickListener {
        void onItemLongClick(View itemView, Object item, int pos);
    }

     public final void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public final void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mLongClickListener = listener;
    }



    /**
     * 自定义 View click event
     */
    public interface OnViewClickListener {
        void onViewClick(View v);
    }
    public void setOnViewClickListener(OnViewClickListener viewClickListener){
        mViewCilckListener = viewClickListener;
    }

    //item 点击
    public interface  OnItemViewListener{
        void onItemViewClick(View itemView, Object item, int pos);
    }

    public void setOnItemViewListener(OnItemViewListener listener){
        this.mOnItemViewListener = listener;
    }


    
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> mViews;
            private Context mContext;

        /**
         * 初始化ViewHolder
         * Context,View ,SparseArray
         */
        public RecyclerViewHolder(Context c, View view) {
            super(view);
            mContext = c;
            mViews = new SparseArray<View>();
        }


        /**
         * convertView
         * @return
         */
        public View getItemView()
        {
            return itemView;
        }

        /**
         * View = findViewById
         */
        public <T extends View> T findViewById(int id)
        {
            View view = mViews.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViews.put(id, view);
            }
            return (T) view;
        }

        public View getView(int viewId) {
           return findViewById(viewId);
        }

        public TextView getTextView(int viewId) {
            return (TextView) getView(viewId);
        }

        public Button getButton(int viewId) {
            return (Button) getView(viewId);
        }

        public ImageView getImageView(int viewId) {
            return (ImageView) getView(viewId);
        }

        public ImageButton getImageButton(int viewId) {
            return (ImageButton) getView(viewId);
        }

        public EditText getEditText(int viewId) {
            return (EditText) getView(viewId);
        }

        public RecyclerViewHolder setText(int viewId, String value) {
            TextView view = findViewById(viewId);
            view.setText(value);
            return this;
        }

        public RecyclerViewHolder setBackground(int viewId, int resId) {
            View view = findViewById(viewId);
            view.setBackgroundResource(resId);
            return this;
        }

        public RecyclerViewHolder setClickListener(int viewId, View.OnClickListener listener) {
            View view = findViewById(viewId);
            view.setOnClickListener(listener);
            return this;
        }
    }


}
