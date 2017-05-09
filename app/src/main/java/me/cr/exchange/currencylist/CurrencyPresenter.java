package me.cr.exchange.currencylist;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import me.cr.exchange.constants.Constant;
import me.cr.exchange.data.Currency;


/**
 * Created by 10190178 on 2017/1/19.
 */

public class CurrencyPresenter implements CurrencyListContract.Presenter{

    private final CurrencyListContract.View mCurrenciesView;

    public CurrencyPresenter(@NonNull CurrencyListContract.View currenciesView) {
        mCurrenciesView = currenciesView;
        mCurrenciesView.setPresenter(this);
    }

    @Override
    public void loadCurrency() {
        ArrayList<Currency> currencyList = new ArrayList<Currency>();
        for (String str:
                Constant.ARRAY_CURRENCY) {
            currencyList.add(new Currency(str));
        }
        mCurrenciesView.showCurrency(currencyList);
    }

    @Override
    public void start() {
        loadCurrency();
    }
}
