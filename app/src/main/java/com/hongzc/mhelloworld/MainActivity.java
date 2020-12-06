package com.hongzc.mhelloworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //private Boolean isPlay = false;
    private Boolean doublePress = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.button)).setText("点击设置为TRUE,目前为FALSE");
        isPlayListener();
    }
    private void isPlayListener()
    {
        //通过ViewModelProviders得到ViewModel
        IsPlayListener timerWithLiveDataViewModel = ViewModelProviders.of(this).get(IsPlayListener.class);

        //得到ViewModel中的LiveData
        final MutableLiveData<Boolean> liveData = (MutableLiveData<Boolean>)timerWithLiveDataViewModel.getCurrentIsPlay();
       // liveData.setValue(isPlay);

        //通过LiveData.observe()实现对ViewModel中数据变化的观察
        liveData.observe(this, new Observer<Boolean>()
        {
            @Override
            public void onChanged(@Nullable Boolean isPlay)
            {
                //收到回调后更新UI界面OR do something
                ((TextView)findViewById(R.id.textView)).setText("livedata已被更改");
                Toast.makeText(MainActivity.this,"livedata已被更改:"+ liveData.getValue(),Toast.LENGTH_SHORT).show();
            }
        });


        //可以换成广播去更新这个livedata的
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
            //通过LiveData.setValue()/LiveData.postValue()完成对ViewModel中数据的更新
                if(!doublePress){
                     liveData.setValue(true);
                     ((Button)findViewById(R.id.button)).setText("点击设置为FALSE,目前为TRUE");
                     doublePress = true;
                }else{
                    liveData.setValue(false);
                    ((Button)findViewById(R.id.button)).setText("点击设置为TRUE,目前为FALSE");
                    doublePress = false;
                }
            }
        });

    }

}
