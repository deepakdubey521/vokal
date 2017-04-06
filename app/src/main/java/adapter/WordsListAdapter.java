package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vokaltest.vokaltestapp.R;
import com.vokaltest.vokaltestapp.model.Card;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WordsListAdapter extends RecyclerView.Adapter<WordsListAdapter.WordsHolder> {

    private static final String TAG = WordsListAdapter.class.getSimpleName();
    private List<Card> cardList = new ArrayList<>();

    public WordsListAdapter(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public WordsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new WordsHolder(v);
    }

    @Override
    public void onBindViewHolder(final WordsHolder holder, final int position) {
        Card card = cardList.get(position);
        holder.range.setText(card.getRange().toString());
        holder.words.setText(card.toString());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class WordsHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.word_range)
        TextView range;

        @Bind(R.id.words_text)
        TextView words;


        public WordsHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
