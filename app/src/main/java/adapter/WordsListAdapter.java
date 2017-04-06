package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vokaltest.vokaltestapp.model.Card;
import com.vokaltest.vokaltestapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WordsListAdapter extends RecyclerView.Adapter<WordsListAdapter.ViewHolder> {

    private static final String TAG = "NumberListAdapter";
    private Context mContext;
    private List<Card> cardList = new ArrayList<Card>();


    public WordsListAdapter(Context context, List<Card> cardList) {
        mContext = context;
        this.cardList = cardList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Card card = cardList.get(position);
        holder.range.setText(card.getRange().toString());
        holder.words.setText(card.toString());
    }

    @Override
    public int getItemCount() {
        //hack it to double
        return cardList.size();
    }

    public void clearData() {
        cardList.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.word_headline)
        TextView range;

        @Bind(R.id.words_data)
        TextView words;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }


}
