package com.example.senamit.wordmeaning.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by senamit on 20/1/18.
 */

public class WordContract {

    public static final String AUTHORITY="com.example.senamit.wordmeaning";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_WORD_LIST = "WordEntry";

    private WordContract() {
    }

    public static class WordListDiary implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WORD_LIST).build();

        public static final String TABLE_NAME = "WordEntry";
        public static final String COLUMN_WORD_NAME="WordName";
        public static final String COLUMN_WORD_DESCRIPTION="WordDescription";
        public static final String COLUMN_WORD_PRIORITY="WordPriority";

    }

}
