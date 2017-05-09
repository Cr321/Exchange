package me.cr.exchange.currencylist;

import java.util.List;

import me.cr.exchange.BasePresenter;
import me.cr.exchange.BaseView;
import me.cr.exchange.data.Currency;

/**
 * Created by 10190178 on 2017/1/17.
 */

public interface CurrencyListContract {

    interface View extends BaseView<Presenter> {
        void showCurrency(List<Currency> currencies);
    }

    interface Presenter extends BasePresenter {
        void loadCurrency();
    }
}
