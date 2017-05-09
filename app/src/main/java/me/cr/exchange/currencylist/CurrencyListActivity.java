package me.cr.exchange.currencylist;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import me.cr.exchange.R;

public class CurrencyListActivity extends AppCompatActivity {

    private CurrencyPresenter mCurrencyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);

        CurrencyListFragment currencyListFragment =
                (CurrencyListFragment) getSupportFragmentManager().findFragmentById(R.id.list_contentFrame);

        if (currencyListFragment == null) {
            // Create the fragment
            currencyListFragment = new CurrencyListFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.list_contentFrame, currencyListFragment);
            transaction.commit();
        }

        // Create the presenter
        mCurrencyPresenter = new CurrencyPresenter(currencyListFragment);
    }
}
