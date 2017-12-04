package us.mifeng.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tView);
    }

    public void click(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventhread(EventBusLoad busLoad ){
        textView.setText(busLoad.getStr());
    }
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventData(EventBusLoad busLoad){
        Toast.makeText(this,busLoad.getStr(),Toast.LENGTH_SHORT).show();
        Log.i("tag","=========2============");
        SystemClock.sleep(10*1000);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void nian(View view) {
        EventBus.getDefault().register(this);
    }
    /**
     * EventBus的四个方法解释，在3.0之前，
     * 在3.0之后则可以自己定义方法，
     */
   /* @Subscribe
    public void onEventMainThread(Object obt){
        //运行在主线程中，与数据从那个线程中发布出来的没有关系
        //也就是无论发布事件在那个线程中，接收事件都运行在UI线程中
    }
    public void onEventPostThread(Object obt){
        //onEvent作为订阅函数，它与发布数据的线程保持一致
        //也就是发布事件与接收事件在同一个线程
    }
    public void onEventBackgroundThread(Object obt){
        //onEvent作为订阅函数，它运行在子线程中，
        //也就说，如果发布事件是在主线程中，则创建子线程，并在子线程中运行，
        //如果发布事件是在子线程中，则该函数直接运行在发布事件的子线程中。
    }
    public void onEventAsync(Object obt){
        //onEvent作为订阅这，无论发布数据是在那个线程中执行，
        //onEvent函数都会创建有一个新的线程，并运行在子线程中。
    }*/

}
