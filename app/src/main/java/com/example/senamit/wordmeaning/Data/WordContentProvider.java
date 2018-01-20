package com.example.senamit.wordmeaning.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.senamit.wordmeaning.Data.WordContract.*;

/**
 * Created by senamit on 20/1/18.
 */

public class WordContentProvider extends ContentProvider {

    WordDbHelper mWordDbHelper;
    SQLiteDatabase db;

    private static final String LOG_TAG = WordContentProvider.class.getSimpleName();

    public static final int WORD_LIST=100;
    public static final int WORD_LIST_ITEM=101;

    private static final UriMatcher mUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(WordContract.AUTHORITY, WordContract.PATH_WORD_LIST, WORD_LIST);
        uriMatcher.addURI(WordContract.AUTHORITY, WordContract.PATH_WORD_LIST + "/#", WORD_LIST_ITEM);
        return uriMatcher;
    }


    @Override
    public boolean onCreate() {
        mWordDbHelper = new WordDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        db= mWordDbHelper.getReadableDatabase();
        Log.i(LOG_TAG, "inside the query method of provider");
        int match = mUriMatcher.match(uri);
        Cursor retCursor = null;

        switch (match){
            case WORD_LIST:
                Log.i(LOG_TAG, "inside the word list table switch 1 block ");
              retCursor =  db.query(WordListDiary.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
              Log.i(LOG_TAG, "the cursor value is "+retCursor);
                break;
            case WORD_LIST_ITEM:
                break;
            default:
                throw new android.database.SQLException("uri is bad" + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        Log.i(LOG_TAG, "inside the insert method");
        db = mWordDbHelper.getWritableDatabase();
        int match =mUriMatcher.match(uri);
        long id;
        Uri retUri = null;
        switch (match){
            case WORD_LIST:
                Log.i(LOG_TAG, "inside the switch statement of table");
                id= db.insert(WordListDiary.TABLE_NAME,null, contentValues );
                Log.i(LOG_TAG, "the id is "+id);
                if (id>0){
                    retUri = ContentUris.withAppendedId(WordListDiary.CONTENT_URI, id);
                }else {
                    Log.i(LOG_TAG, "inside the exception block of insertion of new data");
                }
                break;
            case WORD_LIST_ITEM:
                break;
            default:
                Log.i(LOG_TAG, "inside the default block ");
                throw new android.database.SQLException("the uri is bad"+uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return retUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {



        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
