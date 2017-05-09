package me.cr.exchange.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.cr.exchange.R;
import me.cr.exchange.data.Currency;

/**
 * Created by 10190178 on 2017/2/4.
 */

public class EditView extends RelativeLayout {

    private static final String TAG = "EditView";

    private ImageView img_Flag;

    private TextView tv_Currency_Code;

    private EditText editText;

    private TextView tv_Currency;

    private Context mContext;

    private String Currency_Code;

    private int num;

    public EditView(Context context) {
        super(context);
        mContext = context;
        init(context);
    }

    public EditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditView);
        if (typedArray != null) {
            Currency_Code = typedArray.getString(R.styleable.EditView_currency_code);
            num = typedArray.getInt(R.styleable.EditView_edit_number_hint, 100);
            typedArray.recycle();
        }
        Currency currency = new Currency(Currency_Code);
        setCurrencyCode(currency);
        setEditNum(num);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.edit_box, this);
        img_Flag = (ImageView) findViewById(R.id.edit_img_flag);
        tv_Currency_Code = (TextView) findViewById(R.id.edit_tv_currency_code);
        tv_Currency = (TextView) findViewById(R.id.edit_tv_currency);
        editText = (EditText) findViewById(R.id.edit_editbox);

    }

    public void setEditNum(float num) {
        editText.setText(String.valueOf(num));
    }

    public String getEditNum() {
        return editText.getText().toString();
    }

    public void setCurrencyCode(Currency currency) {
        Resources resources = mContext.getResources();
        tv_Currency_Code.setText(currency.getCode());
        int id_title = resources.getIdentifier(currency.getCode(), "string", "me.cr.exchange");
        tv_Currency.setText(id_title);
        int id_flag = resources.getIdentifier(currency.getFlag_2x_id(), "drawable", "me.cr.exchange");
        img_Flag.setImageResource(id_flag);
    }

    public void setOnClickListener(OnClickListener onClickLintener) {
        img_Flag.setOnClickListener(onClickLintener);
    }

    public void addTextChangeListener(TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

    public void setOnFocusChangeListener(OnFocusChangeListener foucesChangeListener) {
        editText.setOnFocusChangeListener(foucesChangeListener);
    }

    public void removeTextChangeListener(TextWatcher textWatcher) {
        editText.removeTextChangedListener(textWatcher);
    }

    public void setSelection(int index) {
        editText.setSelection(index);
    }


}
