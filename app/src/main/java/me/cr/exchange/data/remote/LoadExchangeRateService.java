package me.cr.exchange.data.remote;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.List;

import me.cr.exchange.constants.Constant;
import me.cr.exchange.data.local.DatabaseWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class LoadExchangeRateService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_LOAD = "me.cr.exchange.data.remote.action.LOAD";
    private static final String ACTION_BAZ = "me.cr.exchange.data.remote.action.BAZ";

    private static final String TAG = "LoadExchangeRateService";

    private DatabaseWrapper mDbWrapper;

    public LoadExchangeRateService() {
        super("LoadExchangeRateService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionLoad(Context context) {
        Intent intent = new Intent(context, LoadExchangeRateService.class);
        intent.setAction(ACTION_LOAD);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_LOAD.equals(action)) {
                handleActionLoad();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionLoad() {
        mDbWrapper = new DatabaseWrapper(this);
        mDbWrapper.updateLogTime(System.currentTimeMillis());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://op.juhe.cn/onebox/exchange/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ExchangeService service = retrofit.create(ExchangeService.class);

        for (int i = 0; i < Constant.ARRAY_CURRENCY.length - 1; i++) {
            for (int j = i + 1; j < Constant.ARRAY_CURRENCY.length; j++) { // from

                Call<ExchangBeam> result = service.getExchangRate("0da7e0faf14080dabac70c3b31fd7a47", Constant.ARRAY_CURRENCY[j], Constant.ARRAY_CURRENCY[i]);
                result.enqueue(new Callback<ExchangBeam>() {
                    @Override
                    public void onResponse(Call<ExchangBeam> call, Response<ExchangBeam> response) {
                        List<ExchangBeam.ResultBean> list = response.body().getResult();

                        if (list != null){
                            String from = list.get(0).getCurrencyF();
                            String to = list.get(0).getCurrencyT();
                            String result = list.get(0).getResult();
                            mDbWrapper.updateExchange(from, to,result);
                            from = list.get(1).getCurrencyF();
                            to = list.get(1).getCurrencyT();
                            result = list.get(1).getResult();
                            mDbWrapper.updateExchange(from, to,result);
                        } else {
                            Log.d(TAG, "onResponse: reason:"+response.body().getReason()+" error_code: "+response.body().getError_code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ExchangBeam> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
            }
        }
    }

}
