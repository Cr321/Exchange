package me.cr.exchange.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import me.cr.exchange.R;
import me.cr.exchange.currencylist.CurrencyListActivity;
import me.cr.exchange.data.Currency;
import me.cr.exchange.data.local.DatabaseWrapper;
import me.cr.exchange.data.remote.LoadExchangeRateService;
import me.cr.exchange.ui.EditView;


public class MainActivity extends AppCompatActivity implements MainContract.View {
    private static final String TAG = "MainActivity";

    public static final int REQUEST_SELECT_FROM = 1;

    public static final int REQUEST_SELECT_TO = 2;

    private DatabaseWrapper mDbWrapper;

    private EditView editView_from;

    private EditView editView_to;

    private Currency currency_from;

    private Currency currency_to;

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editView_from = (EditView) findViewById(R.id.editview_from);
        editView_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CurrencyListActivity.class);
                startActivityForResult(i, REQUEST_SELECT_FROM);
            }
        });

        editView_to = (EditView) findViewById(R.id.editview_to);
        editView_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CurrencyListActivity.class);
                startActivityForResult(i, REQUEST_SELECT_TO);
            }
        });

        currency_from = new Currency("CNY");
        currency_to = new Currency("USD");

        mDbWrapper = new DatabaseWrapper(this);

        mPresenter = new MainPresenter(this, mDbWrapper);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 && data != null) {
            String currency_code = data.getStringExtra("currency");
            Currency currency = new Currency(currency_code);
            switch (requestCode) {
                case REQUEST_SELECT_FROM:
                    currency_from = currency;
                    break;
                case REQUEST_SELECT_TO:
                    currency_to = currency;
                    break;
                default:
                    break;
            }
        }
    }

    private TextWatcher mFromWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            Log.d(TAG, "onTextChanged() called with: s = [" + s + "], start = [" + start + "], before = [" + before + "], count = [" + count + "]");
            editView_from.setSelection(start + count);
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (!s.toString().equals("")) {
                currency_from.setCash(Float.parseFloat(s.toString()));
                mPresenter.exchange(currency_from, currency_to, false);
            }

        }
    };

    private TextWatcher mToWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            editView_to.setSelection(start + count);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().equals("")) {
                currency_to.setCash(Float.parseFloat(s.toString()));
                mPresenter.exchange(currency_to, currency_from, true);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //半小时内不更新数据库
        if ((System.currentTimeMillis() - mDbWrapper.getLogTime()) > 1800000 ) {
            LoadExchangeRateService.startActionLoad(this);
        }

        mPresenter.start();

    }

    @Override
    public void show() {
        editView_to.setCurrencyCode(currency_to);
        editView_from.setCurrencyCode(currency_from);

        editView_from.removeTextChangeListener(mFromWatcher);
        editView_to.removeTextChangeListener(mToWatcher);
        editView_to.setEditNum(currency_to.getCash());
        editView_from.setEditNum(currency_from.getCash());
        editView_from.addTextChangeListener(mFromWatcher);
        editView_to.addTextChangeListener(mToWatcher);

        if (editView_from.getEditNum() != null) {
            editView_from.setSelection(editView_from.getEditNum().length());
        }

        if (editView_to.getEditNum() != null) {
            editView_to.setSelection(editView_to.getEditNum().length());
        }
    }

    @Override
    public void updateFrom() {
        editView_from.setCurrencyCode(currency_from);
        editView_from.removeTextChangeListener(mFromWatcher);
        editView_from.setEditNum(currency_from.getCash());
        editView_from.addTextChangeListener(mFromWatcher);
    }

    @Override
    public void updateTo() {
        editView_to.setCurrencyCode(currency_to);

        editView_to.removeTextChangeListener(mToWatcher);
        editView_to.setEditNum(currency_to.getCash());
        editView_to.addTextChangeListener(mToWatcher);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
