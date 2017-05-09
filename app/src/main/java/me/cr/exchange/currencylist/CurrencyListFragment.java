package me.cr.exchange.currencylist;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.cr.exchange.R;
import me.cr.exchange.data.Currency;
import me.cr.exchange.main.MainActivity;


public class CurrencyListFragment extends Fragment implements CurrencyListContract.View{

    private CurrencyListContract.Presenter mPresenter;

    private CurrenciesAdapter mListAdapter;

    private final int resultCode = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new CurrenciesAdapter(getActivity(), new ArrayList<Currency>(0), mItemListener);
    }

    CurrencyItemListener mItemListener = new CurrencyItemListener() {
        @Override
        public void onItemClick(Currency clickedCurrency) {
            Intent intent = new Intent();
            intent.putExtra("currency", clickedCurrency.getCode());
            getActivity().setResult(resultCode, intent);
            getActivity().finish();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_curreny_list, container, false);
        ListView listView = (ListView) root.findViewById(R.id.currencies_list);
        listView.setAdapter(mListAdapter);

        return root;
    }

    @Override
    public void showCurrency(List<Currency> currencies) {
        mListAdapter.replaceData(currencies);
    }

    @Override
    public void setPresenter(CurrencyListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private static class CurrenciesAdapter extends BaseAdapter {

        private List<Currency> mCurrencies;
        private CurrencyItemListener mItemListener;
        private Context mContext;

        public CurrenciesAdapter(Context context, List<Currency> currencies, CurrencyItemListener itemListener) {
            mContext = context;
            setList(currencies);
            mItemListener = itemListener;
        }

        private void setList(List<Currency> tasks) {
            mCurrencies = tasks;
        }

        @Override
        public int getCount() {
            return mCurrencies.size();
        }

        public void replaceData(List<Currency> currencies) {
            setList(currencies);
            notifyDataSetChanged();
        }

        @Override
        public Currency getItem(int position) {
            return mCurrencies.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.currency_item, parent, false);
            }
            final Currency currency = getItem(position);
            TextView titleTV = (TextView) rowView.findViewById(R.id.tx_list_code);
            ImageView flagImg = (ImageView) rowView.findViewById(R.id.img_list_flag);
            Resources resources = mContext.getResources();
            int id_title = resources.getIdentifier(currency.getCode(), "string", "me.cr.exchange");
            titleTV.setText(id_title);
            int id_flag = resources.getIdentifier(currency.getFlag_small_id(), "drawable", "me.cr.exchange");
            flagImg.setImageResource(id_flag);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onItemClick(currency);
                }
            });

            return rowView;
        }
    }

    public interface CurrencyItemListener {

        void onItemClick(Currency clickedCurrency);

    }
}
