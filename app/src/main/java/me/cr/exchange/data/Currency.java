package me.cr.exchange.data;

import me.cr.exchange.R;

/**
 * Created by 10190178 on 2017/1/19.
 */

public class Currency {

    private String code;

    private String flag_small_id;

    private String flag_2x_id;

    private float cash;

    public Currency(String code) {
        this.code = code;
        String country_code = code.substring(0,2).toLowerCase();
        flag_small_id = country_code;
        flag_2x_id = country_code + "_2x";
        cash = 0;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public String getCode() {
        return code;
    }

    public String getFlag_small_id() {
        return flag_small_id;
    }

    public String getFlag_2x_id() {
        return flag_2x_id;
    }
}
