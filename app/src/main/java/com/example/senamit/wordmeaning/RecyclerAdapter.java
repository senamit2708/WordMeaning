package com.example.senamit.wordmeaning;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by senamit on 20/1/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    List<Words> list = new ArrayList<>();
    ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();

    public RecyclerAdapter(List<Words> list) {
        expansionsCollection.openOnlyOne(true);
        this.list=list;
    }

    public RecyclerAdapter() {
        expansionsCollection.openOnlyOne(true);
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecyclerHolder.buildFor(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {

        holder.bind(list.get(position));
        holder.mWordName.setText(list.get(position).getWordName());
        holder.mWordDescription.setText(list.get(position).getWordDescrition());

        expansionsCollection.add(holder.getExpansionLayout());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItem(List<Words> item) {
//        this.item = item;
        this.list.addAll(item);
        notifyDataSetChanged();
    }

//    public void setItem(List<Object> items) {
//
//        this.list.addAll(items);
//        notifyDataSetChanged();
//    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder{
        private static final int LAYOUT = R.layout.recycler_cell;

        @BindView(R.id.expansionLayout)
        ExpansionLayout expansionLayout;
        TextView mWordName;
        TextView mWordDescription;

        public static RecyclerHolder buildFor(ViewGroup viewGroup){
            return new RecyclerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(LAYOUT, viewGroup, false));
        }

        public RecyclerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mWordName =(TextView) itemView.findViewById(R.id.txtWordName);
            mWordDescription = (TextView)itemView.findViewById(R.id.txtWordDescription);

        }
        public void bind(Object object){
            expansionLayout.collapse(false);
        }

        public ExpansionLayout getExpansionLayout() {
            return expansionLayout;
        }
    }
}
