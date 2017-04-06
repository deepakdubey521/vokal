package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vokaltest.vokaltestapp.CardObject;
import com.vokaltest.vokaltestapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WordsListAdapter extends RecyclerView.Adapter<WordsListAdapter.ViewHolder> {

    private static final String TAG = "NumberListAdapter";
    private Context mContext;
    private List<CardObject> mCardList = new ArrayList<CardObject>();


    public WordsListAdapter(Context context, List<CardObject> cardObjectList) {
        mContext = context;
        mCardList = cardObjectList;

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

        CardObject cardObject = (CardObject) mCardList.get(position);
        holder.mWordHeadline.setText(cardObject.range);
        holder.mWordData.setText(cardObject.toString());
    }

    @Override
    public int getItemCount() {
        //hack it to double
        return mCardList.size();
    }

    public void clearData() {
        mCardList.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.word_headline)
        TextView mWordHeadline;

        @Bind(R.id.words_data)
        TextView mWordData;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }


}
