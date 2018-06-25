package com.example.rxjavademo.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fxp.rxjavademo.R;
import com.example.rxjavademo.rxbus.MessageRxBus;
import com.example.rxjavademo.rxbus.RxBus;

import io.reactivex.functions.Consumer;

public class RxBusActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;

    private EditText msgInputEditText;

    private Button msgPostBtn;

    private TextView msgReceivedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);

        findViews();

        initData();

        initViews();

        initListener();

        registRxBus();
    }

    private void findViews(){
        msgInputEditText = findViewById(R.id.edt_msg_input);
        msgPostBtn = findViewById(R.id.btn_post_msg);
        msgReceivedTextView = findViewById(R.id.txt_recieve_msg);
    }

    private void initData(){
        context = getApplicationContext();
    }

    private void initViews(){

    }

    private void initListener(){
        msgPostBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_post_msg){
            postMsg();
        }
    }

    /**
     * @Description: 注册RxBus
     *
     * @Author:  fxp
     * @Date:    2018/6/25   下午6:17
     * @param
     * @return   void
     * @exception/throws
     */
    private void registRxBus() {
        RxBus.get().register(MessageRxBus.class, new Consumer<MessageRxBus>() {
            @Override
            public void accept(MessageRxBus messageRxBus) throws Exception {
                String id = messageRxBus.getId();
                String msg = messageRxBus.getMessage();
                if (id.equals("1") ){
                    showReceivedMsg(msg);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    private void postMsg(){
        RxBus.get().post(new MessageRxBus("1", msgInputEditText.getText().toString()));
    }

    private void showReceivedMsg(String msg){
        msgReceivedTextView.setText(msg);
    }

}
