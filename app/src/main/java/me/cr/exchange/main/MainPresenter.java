package me.cr.exchange.main;

import android.support.annotation.NonNull;

import me.cr.exchange.data.Currency;
import me.cr.exchange.data.local.DatabaseWrapper;
import me.cr.exchange.ui.EditView;

/**
 * Created by 10190178 on 2017/2/6.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    private DatabaseWrapper mDbWrapper;

    public MainPresenter(@NonNull MainContract.View mainView, @NonNull DatabaseWrapper dbWrapper) {

        mView = mainView;
        mainView.setPresenter(this);
        mDbWrapper = dbWrapper;

    }


    @Override
    public void exchange(@NonNull Currency from, @NonNull Currency to, boolean isFrom) {
        float rate = mDbWrapper.getExchangeRate(from.getCode(), to.getCode());
        to.setCash(from.getCash()*rate);
        if (isFrom) {
            mView.updateFrom();
        } else {
            mView.updateTo();
        }
    }

    @Override
    public void start() {
        mView.show();
    }
}
