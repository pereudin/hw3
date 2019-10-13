package ru.pereudin.hw3;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private static final String TAG = "MainPresenter";

    public Observable<String> getMessage() {

        Observable<String> observable = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            try {
                for (int i = 0; i < 50; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    String mes = "Message for you: " + i;
                    Log.d(TAG, "getMessage: " + Thread.currentThread().getName() + ": " + mes);
                    emitter.onNext(mes);
                }
            } catch (InterruptedException e) {
                Log.d(TAG, "getMessage: not disposed");
            }

        }).subscribeOn(Schedulers.io());

        return observable;

    }

}
