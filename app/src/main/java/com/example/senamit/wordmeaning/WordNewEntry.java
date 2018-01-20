package com.example.senamit.wordmeaning;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.senamit.wordmeaning.Data.WordContract;
import com.example.senamit.wordmeaning.Data.WordContract.*;

public class WordNewEntry extends AppCompatActivity {

    public static final String LOG_TAG = WordNewEntry.class.getSimpleName();

    EditText edtWordName;
    EditText edtWordDescription;
    Button btnSubmit;
    List<Words> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_new_entry);
        edtWordName=findViewById(R.id.edtWordName);
        edtWordDescription= findViewById(R.id.edtWordDescription);

        btnSubmit= findViewById(R.id.btnSubmit);
        list = new ArrayList<Words>();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues contentValues = new ContentValues();

                String wordName = edtWordName.getText().toString();
                String wordDescription = edtWordDescription.getText().toString();
                int priority = 3;

                contentValues.put(WordListDiary.COLUMN_WORD_NAME, wordName);
                contentValues.put(WordListDiary.COLUMN_WORD_DESCRIPTION, wordDescription);
                contentValues.put(WordListDiary.COLUMN_WORD_PRIORITY, priority );

                Log.i(LOG_TAG, "the values of content providers are "+ wordName + ", "+wordDescription+" , "+priority);

                Uri uriId = getContentResolver().insert(WordListDiary.CONTENT_URI, contentValues);
                if (uriId != null) {
                    Toast.makeText(WordNewEntry.this, "successful", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(WordNewEntry.this, "unsucessful", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
