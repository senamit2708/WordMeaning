package com.example.senamit.wordmeaning;

/**
 * Created by senamit on 20/1/18.
 */

public class Words {
    String WordName;
    String WordDescrition;

    public Words(String wordName, String wordDescrition) {
        WordName = wordName;
        WordDescrition = wordDescrition;
    }

    public String getWordName() {
        return WordName;
    }

    public String getWordDescrition() {
        return WordDescrition;
    }
}
