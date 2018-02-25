# EventBusStudy

###### EventBus依赖 
```
compile 'org.greenrobot:eventbus:3.0.0'
```
EventBus是一个事件发布/订阅总线
    使用EventBus是组织之间的通信更加简单。
    针对事件的发送者和订阅者之间进行解耦。高内聚/耦合度低
    非常好的应用在Activity Fragment和后台线程中
    避开了联系精密易出错的依赖关系和容易出错的生命周期

     EventBus从EventBus.getDefault()开始
     在订阅者线程中进行注册和解除
     EventBus.getDefault().register(Content context)；
     提示参数是上下文(你的事件在哪接收,就在哪个类进行EventBus的注册和解除操作.)

     onEvent订阅者分析
           在3.0之前EventBus的四个方法解释如下
           在3.0之后则可以自己定义方法，但需要手动添加注解（@Subscribe）。

       在3.0之前EventBus使用，四个方法的解释如下
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
         }

      在3.0之后EventBus使用
        @Subscribe(threadMode = ThreadMode.MAIN)
        @Subscribe(threadMode = ThreadMode.POSTING)
        @Subscribe(threadMode = ThreadMode.BACKGROUND)
        @Subscribe(threadMode = ThreadMode.ASYNC)

                     在主线程发送事件     在子线程发送事件
        main：       主线程               主线程
        posting：    主线程               子线程
        background： 新开一个子线程       子线程
        async：      新开一个子线程       新开一个子线程

        案例如下：
        @Subscribe(threadMode = ThreadMode.ASYNC)
            public void onEventData(EventBusLoad busLoad){
                Toast.makeText(this,busLoad.getStr(),Toast.LENGTH_SHORT).show();
                Log.i("tag","=========2============");
                SystemClock.sleep(10*1000);
            }

       http://blog.csdn.net/itachi85/article/details/52205464
        EventBus 3.0的粘性事件
          粘性事件指在发送事件之后再订阅该事件也能收到该事件，跟黏性广播类似。

        处理粘性事件的方法
        @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
        public void onEventhread(EventBusLoad busLoad ){
                textView.setText(busLoad.getStr());
        }
        用来发送粘性事件
        EventBus.getDefault().postSticky(eventBus);
