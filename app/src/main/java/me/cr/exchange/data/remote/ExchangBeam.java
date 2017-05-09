package me.cr.exchange.data.remote;

import java.util.List;

/**
 * Created by 10190178 on 2017/1/12.
 */

public class ExchangBeam {

    /**
     * reason : 查询成功
     * result : [{"currencyF":"JPY","currencyF_Name":"日元","currencyT":"BHD","currencyT_Name":"巴林第纳尔","currencyFD":1,"exchange":"0.0032685972","result":"0.0032","updateTime":"2014-11-07 13:58:02"},{"currencyF":"BHD","currencyF_Name":"巴林第纳尔","currencyT":"JPY","currencyT_Name":"日元","currencyFD":1,"exchange":"305.9416445623","result":305.9416,"updateTime":"2014-11-07 13:58:01"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * currencyF : JPY
         * currencyF_Name : 日元
         * currencyT : BHD
         * currencyT_Name : 巴林第纳尔
         * currencyFD : 1
         * exchange : 0.0032685972
         * result : 0.0032
         * updateTime : 2014-11-07 13:58:02
         */

        private String currencyF;
        private String currencyF_Name;
        private String currencyT;
        private String currencyT_Name;
        private int currencyFD;
        private String exchange;
        private String result;
        private String updateTime;

        public String getCurrencyF() {
            return currencyF;
        }

        public void setCurrencyF(String currencyF) {
            this.currencyF = currencyF;
        }

        public String getCurrencyF_Name() {
            return currencyF_Name;
        }

        public void setCurrencyF_Name(String currencyF_Name) {
            this.currencyF_Name = currencyF_Name;
        }

        public String getCurrencyT() {
            return currencyT;
        }

        public void setCurrencyT(String currencyT) {
            this.currencyT = currencyT;
        }

        public String getCurrencyT_Name() {
            return currencyT_Name;
        }

        public void setCurrencyT_Name(String currencyT_Name) {
            this.currencyT_Name = currencyT_Name;
        }

        public int getCurrencyFD() {
            return currencyFD;
        }

        public void setCurrencyFD(int currencyFD) {
            this.currencyFD = currencyFD;
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
