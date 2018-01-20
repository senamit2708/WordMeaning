package com.example.senamit.wordmeaning;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.senamit.wordmeaning.Data.WordContract;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by senamit on 20/1/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();

    public static final String LOG_TAG = RecyclerAdapter.class.getSimpleName();
    Context context;
    Cursor mCursor;



    public RecyclerAdapter() {
        expansionsCollection.openOnlyOne(true);
    }
    public RecyclerAdapter(Context context) {
        expansionsCollection.openOnlyOne(true);
        this.context=context;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecyclerHolder.buildFor(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {

        int wordNameIndex = mCursor.getColumnIndex(WordContract.WordListDiary.COLUMN_WORD_NAME);
        int wordDescriptionIndex = mCursor.getColumnIndex(WordContract.WordListDiary.COLUMN_WORD_DESCRIPTION);

        mCursor.moveToPosition(position);
        String wordName = mCursor.getString(wordNameIndex);
        String wordDescription = mCursor.getString(wordDescriptionIndex);


        holder.mWordName.setText(wordName);
        holder.mWordDescription.setText(wordDescription);

        expansionsCollection.add(holder.getExpansionLayout());
    }

    @Override
    public int getItemCount() {

        return mCursor.getCount();
    }



    public Cursor swapCursor(Cursor cursor) {
        Log.i(LOG_TAG, "inside swap cursor");
        if (mCursor==cursor){
            Log.i(LOG_TAG, "the if portion of swapcursor");
            return null;
        }
        Cursor tempCursor = mCursor;
        this.mCursor=cursor;
        if (cursor!=null){
            Log.i(LOG_TAG, "if cursor is not null");
            notifyDataSetChanged();
        }
        return tempCursor;
    }


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
