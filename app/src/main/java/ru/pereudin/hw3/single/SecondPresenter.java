package ru.pereudin.hw3.single;

import android.util.Log;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class SecondPresenter {

    private static final String TAG = "SecondPresenter";

    public Single<String> getMessage() {

        Single<String> single = Single.create((SingleOnSubscribe<String>) emitter -> {
            String mes = "One message for you";
            Log.d(TAG, "getMessage: " + Thread.currentThread().getName() + ": " + mes);
            emitter.onSuccess(mes);
        }).subscribeOn(Schedulers.io());
        return single;
    }

}
