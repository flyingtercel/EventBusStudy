package us.mifeng.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void busEvent(View view) {

        EventBusLoad eventBus =  new EventBusLoad();
        eventBus.setStr("你好，我是SecondActivity中传递过来的数据");
        EventBus.getDefault().post(eventBus);
        //EventBus.getDefault().postSticky(eventBus);
        finish();
    }

}
