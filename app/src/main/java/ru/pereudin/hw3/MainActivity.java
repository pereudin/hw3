package ru.pereudin.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private MainPresenter presenter;
    private Observable<String> observable;
    private Disposable disposable;

    @BindView(R.id.subscribe_button_activity_main)
    Button subButton;

    @BindView(R.id.unsubscribe_button_activity_main)
    Button unsubButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new MainPresenter();
        observable = presenter.getMessage();
    }

    @OnClick(R.id.subscribe_button_activity_main)
    public void subscribe(View view) {

        disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(string -> {
            Log.d(TAG, "onNext: " + string);
        }, throwable -> {
            Log.e(TAG, "onError: ");
        }, () -> {
            Log.d(TAG, "onComplete: ");
        });

    }

    @OnClick(R.id.unsubscribe_button_activity_main)
    public void unsubscribe(View view) {
        disposable.dispose();
    }
}
