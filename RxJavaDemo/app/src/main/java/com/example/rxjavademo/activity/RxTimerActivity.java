package com.example.rxjavademo.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fxp.rxjavademo.R;
import com.example.rxjavademo.rxtimer.RxTimerUtil;

import io.reactivex.disposables.Disposable;

public class RxTimerActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;

    private TextView timerLableTextView, timerCountTextView;

    private TextView intervalLableTextView, intervalCountTextView;

    private int timerCount, intervalCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_timer);

        findViews();

        initData();

        initViews();

        initListener();
    }

    private void findViews(){
        timerLableTextView = findViewById(R.id.txt_timer_lable);
        timerCountTextView = findViewById(R.id.txt_timer_count);
        intervalLableTextView = findViewById(R.id.txt_interval_lable);
        intervalCountTextView = findViewById(R.id.txt_interval_count);
    }

    private void initData(){
        context = getApplicationContext();
        timerCount = 0;
        intervalCount = 0;
    }

    private void initViews(){

    }

    private void initListener(){
        timerLableTextView.setOnClickListener(this);
        intervalLableTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.txt_timer_lable){
            initCommonTimer();
        }else if (id == R.id.txt_interval_lable){
            initIntervalTimer();
        }else {}
    }

    /**
     * @Description:    初始化定时器 - timer
     *
     * @Author:  fxp
     * @Date:    2018/6/25   下午11:11
     * @param       
     * @return   void
     * @exception/throws
     */
    private void initCommonTimer(){
        new RxTimerUtil().timer(3000, new RxTimerUtil.IRxNext() {
            @Override
            public void doNext(long number, Disposable disposable) {
                updateTimerCount();
            }
        });
    }

    /**  
     * @Description: 初始化定时器 - interval
     * 
     * @Author:  fxp
     * @Date:    2018/6/25   下午11:12
     * @param
     * @return   void 
     * @exception/throws
     */  
    private void initIntervalTimer(){
        new RxTimerUtil().interval(1000, new RxTimerUtil.IRxNext() {
            @Override
            public void doNext(long number, Disposable disposable) {
                updateIntervalCount();
            }
        });
    }

    private void updateTimerCount(){
        timerCountTextView.setText(++timerCount + "");
    }

    private void updateIntervalCount(){
        intervalCountTextView.setText(++intervalCount + "");
    }

}
