package com.example.senamit.wordmeaning;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.senamit.wordmeaning.Data.WordContract;


import butterknife.BindView;




public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    RecyclerAdapter adapter;
    FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabButton = findViewById(R.id.floatingbtnSave);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabButtonAddWords();
            }

            private void fabButtonAddWords() {
                Intent intent = new Intent(MainActivity.this, WordNewEntry.class);
                startActivity(intent);
            }


        });


        getLoaderManager().initLoader(0, null, this);
    }

   protected void onResume() {

       super.onResume();
       Log.i(LOG_TAG, "inside onresume method");
       getLoaderManager().restartLoader(0, null, this);
   }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "inside the oncreateloader");

        return new AsyncTaskLoader<Cursor>(this) {


            Cursor mCursor;

            @Override
            protected void onStartLoading() {
                if (mCursor!=null){

                    deliverResult(mCursor);
                }else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
               try {
                   return getContentResolver().query(WordContract.WordListDiary.CONTENT_URI,
                           null,
                           null,
                           null,
                           WordContract.WordListDiary._ID+" DESC");
               }catch (Exception e){
                   Log.i(LOG_TAG, "Unable to load the data from table");
               }
               return null;
            }

            @Override
            public void deliverResult(Cursor data) {
                mCursor=data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter = new RecyclerAdapter(this);
        adapter.swapCursor(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        recyclerView.setAdapter(null);
    }


}
