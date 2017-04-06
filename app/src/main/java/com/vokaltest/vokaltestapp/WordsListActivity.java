package com.vokaltest.vokaltestapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vokaltest.vokaltestapp.model.CardCreator;
import com.vokaltest.vokaltestapp.model.Card;
import com.vokaltest.vokaltestapp.model.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.WordsListAdapter;

import static com.vokaltest.vokaltestapp.model.Card.Range.getRangeForValue;

public class WordsListActivity extends AppCompatActivity {

    private static final String TAG = WordsListActivity.class.getSimpleName();
    // @Bind(R.id.word_list)
    RecyclerView mWordListView;

    WordsListAdapter mWordListAdapter;
    LinearLayoutManager mLayoutManager;
    List<Card> cardList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        mWordListView = (RecyclerView) findViewById(R.id.word_list);

        Intent intent = getIntent();
        intent.getData();
        populateData(intent.getData());

        mWordListAdapter = new WordsListAdapter(getApplicationContext(), cardList);
        mWordListView.setAdapter(mWordListAdapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mWordListView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void populateData(final Uri data) {
        Log.d("image selected path", data.getPath());

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    readTextFromUri(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
        String[] words = stringBuilder.toString().toLowerCase().split(" ");
        HashMap<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            if (wordMap.get(word) == null) {
                wordMap.put(word, 1);
            } else {
                wordMap.put(word, wordMap.get(word) + 1);
            }
        }

        ArrayList<Word> wordList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            wordList.add(new Word(entry.getKey(), entry.getValue()));
        }
        Collections.sort(wordList);

        CardCreator cardCreator = CardCreator.getInstance();
        for (Word word : wordList) {
            Card card = cardCreator.getCardObject(getRangeForValue(word.getCount()));
            card.addWordsData(word);
        }

        cardList.clear();
        cardList.addAll(cardCreator.getCards().values());
        Collections.sort(cardList);
    }

}
