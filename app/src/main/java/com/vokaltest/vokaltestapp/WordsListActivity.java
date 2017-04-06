package com.vokaltest.vokaltestapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.WordsListAdapter;

public class WordsListActivity extends AppCompatActivity {

    private static final String TAG = WordsListActivity.class.getSimpleName();
    // @Bind(R.id.word_list)
    RecyclerView mWordListView;

    WordsListAdapter mWordListAdapter;
    LinearLayoutManager mLayoutManager;
    List<CardObject> mCardList = new ArrayList<>();
    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        mWordListView = (RecyclerView) findViewById(R.id.word_list);

        Intent intent = getIntent();
        intent.getData();
        populateData(intent.getData());

        mWordListAdapter = new WordsListAdapter(getApplicationContext(), mCardList);
        mWordListView.setAdapter(mWordListAdapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mWordListView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void populateData(Uri data) {
        Log.d("image selected path", data.getPath());
        try {
            readTextFromUri(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readTextFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(" " + line);

        }

        formatData(stringBuilder);

    }

    private void formatData(StringBuilder stringBuilder) {
        String[] stringData = stringBuilder.toString().toLowerCase().split(" ");
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String a : stringData) {
            if (hashMap.get(a) != null) {
                int value = hashMap.get(a);
                ++value;
                hashMap.put(a, value);
            } else {
                hashMap.put(a, 1);
            }
        }

        ArrayList<WordsData> wordList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            wordList.add(new WordsData(entry.getKey(), entry.getValue()));
        }
        Collections.sort(wordList);

        String range = "";
        CardDataCreator cardDataCreator = new CardDataCreator();
        for (WordsData wordsData : wordList) {
            range = getRangeForValue(wordsData.mValue);
            CardObject cardObject = cardDataCreator.getCardObject(range);
            cardObject.wordsDatas.add(wordsData);
        }


        Collection<CardObject> cardObjectList = cardDataCreator.getCards().values();
        mCardList.addAll(cardObjectList);
        Collections.reverse(mCardList);
    }

    String getRangeForValue(int value) {
        int initialRange = 10;
        int lastRange = 10;
        int res = value / initialRange;
        if (res >= initialRange) {
            initialRange = initialRange * initialRange;
        } else if (res < initialRange) {
            initialRange = (res * initialRange);
        }
        lastRange = initialRange + 10;

        return (initialRange + 1) + " - " + lastRange;
    }


    String getRange(int value) {

        int result = value / 10;

        switch (result) {
            case 0:
                return "00 - 10";
            case 1:
                return "11 - 20";
            case 2:
                return "21 - 30";
            case 3:
                return "31 - 40";
            case 4:
                return "41 - 50";
            case 5:
                return "51 - 60";
            case 6:
                return "61 - 70";
            case 7:
                return "71 - 80";
            case 8:
                return "81 - 90";
            case 9:
                return "91 - 100";
            case 10:
                return "101 - 110";
            case 11:
                return "111 - 120";
            case 12:
                return "121 - 130";
            case 13:
                return "131 - 140";
            case 14:
                return "141 - 150";
            case 15:
                return "151 - 160";
            case 16:
                return "161 - 170";
            case 17:
                return "171 - 180";
            case 18:
                return "181 - 190";
            case 19:
                return "191 - 200";
        }

        return "";
    }


}
