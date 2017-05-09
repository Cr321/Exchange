package me.cr.exchange.main;

import android.support.annotation.NonNull;

import me.cr.exchange.BasePresenter;
import me.cr.exchange.BaseView;
import me.cr.exchange.data.Currency;
import me.cr.exchange.ui.EditView;


/**
 * Created by 10190178 on 2017/1/24.
 */

public class MainContract {
    interface View extends BaseView<MainContract.Presenter> {
        void show();

        void updateFrom();

        void updateTo();
    }

    interface Presenter extends BasePresenter {
        void exchange(@NonNull Currency from, @NonNull Currency to, boolean isFrom);
    }
}
