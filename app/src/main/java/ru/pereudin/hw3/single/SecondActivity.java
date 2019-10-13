package ru.pereudin.hw3.single;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.pereudin.hw3.R;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "SecondActivity";

    private SecondPresenter presenter;
    private Single<String> single;
    private Disposable disposable;

    @BindView(R.id.subscribe_button_activity_second)
    Button subButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ButterKnife.bind(this);

        presenter = new SecondPresenter();
        single = presenter.getMessage();
    }

    @OnClick(R.id.subscribe_button_activity_second)
    public void subscribe(View view) {

        disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(string -> {
            Log.d(TAG, "onSuccess: " + string);
        }, throwable -> {
            Log.e(TAG, "onError: ");
        });

    }

}
