package com.example.rxjavademo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fxp.rxjavademo.R;
import com.example.rxjavademo.node.JokeNode;
import com.example.rxjavademo.rxretrofit.RequestManager;
import com.example.rxjavademo.rxretrofit.RetryWithDelay;

import io.reactivex.functions.Consumer;

public class RxRetrofitActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "RxRetrofitActivity";

    private Button getRequestBtn;

    private TextView requestResultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_retrofit);

        findViews();

        initData();

        initViews();

        initListener();
    }

    private void findViews(){
        getRequestBtn = findViewById(R.id.btn_get_data);
        requestResultTxt = findViewById(R.id.txt_result_content);
    }

    private void initData(){

    }

    private void initViews(){

    }

    private void initListener(){
        getRequestBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_get_data){
            getHotNovelList();
        }
    }

    private void getHotJokeList(){
        RequestManager.getHotJokeList(1, 1).subscribe(new Consumer<JokeNode>() {
            @Override
            public void accept(JokeNode jokeNode) throws Exception {
//                Log.e(TAG, jokeNode.getData().get(0).getText());

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG,throwable.getMessage());
            }
        });
    }

    private void getHotNovelList(){
        RequestManager.getHotNovelList().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
                updateRequestResult(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG,throwable.getMessage());
            }
        });
    }

    private void getHotNovelListRetryWithDelay(){
        RequestManager.getHotNovelList().retryWhen(new RetryWithDelay(3, 10 * 1000)).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
                updateRequestResult(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG,throwable.getMessage());
            }
        });
    }

    private void updateRequestResult(String result){
        requestResultTxt.setText(result);
    }

}
