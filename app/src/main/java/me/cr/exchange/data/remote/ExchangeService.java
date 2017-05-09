package me.cr.exchange.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 10190178 on 2017/1/12.
 */

public interface ExchangeService {
    /**
     * 查询实时汇率
     * @param appkey
     * @param from
     * @param to
     * @return
     */
    @GET("currency")
    Call<ExchangBeam> getExchangRate(@Query("key") String appkey,
                                            @Query("from") String from, @Query("to") String to);
}
