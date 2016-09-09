package com.xdf.learn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xdf.learn.R;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.annotation.InjectView;
import com.xdf.learn.base.BaseActivity;
import com.xdf.learn.view.MyButton;

/**
 * ViewGroup的事件分发机制
 * <p/>
 *结论：
 * viewgroup的dispatchTouchEvent ---Viewgroup的onInterceptTouchEvent ---MyButton的dispathcTouchEvent ---MyButton的OnTouchEvent
 * 在View上触发事件：最先捕获到的事件为View所在的Viewgroup，然后才会到view本身
 *
 * ViewGroup的dispatchTouchEvent事件流程：
 * 1、ACTION_DOWN中，ViewGroup捕获到事件，然后判断是否拦截，如果没有拦截，则找到包含当前x,y坐标的子View，
 * 赋值给mMotionTarget，然后调用	mMotionTarget.dispatchTouchEvent
 * 2、ATION_MOVE中，ViewGroup捕获到事件，然后判断是否拦截，如果没有拦截，则直接调用mMotionTarget.dispatchTouchEvent(ev)
 * 3、ACTION_UP中，ViewGroup捕获到事件，然后判断是否拦截，如果没有拦截，则直接调用mMotionTarget.dispatchTouchEvent(ev)
 * 当然了在分发之前都会修改下坐标系统，把当前的x，y分别减去child.left 和 child.top ，然后传给child;
 *
 * 总结：
 * 5、总结
 关于代码流程上面已经总结过了~
 1、如果ViewGroup找到了能够处理该事件的View，则直接交给子View处理，自己的onTouchEvent不会被触发；
 2、可以通过复写onInterceptTouchEvent(ev)方法，拦截子View的事件（即return true），把事件交给自己处理，则会执行自己对应的onTouchEvent方法
 3、子View可以通过调用getParent().requestDisallowInterceptTouchEvent(true);  阻止ViewGroup对其MOVE或者UP事件进行拦截；

 好了，那么实际应用中能解决哪些问题呢？
 比如你需要写一个类似slidingmenu的左侧隐藏menu，主Activity上有个Button、ListView或者任何可以响应点击的View，
 你在当前View上死命的滑动，菜单栏也出不来；因为MOVE事件被子View处理了~ 你需要这么做：在ViewGroup的dispatchTouchEvent中判断用户是不是想显示菜单，
 如果是，则在onInterceptTouchEvent(ev)拦截子View的事件；自己进行处理，这样自己的onTouchEvent就可以顺利展现出菜单栏了~~
 * Created by xdf on 16-9-9.
 */
@ContentView(R.layout.act_view_group_event)
public class ViewGroupEventTestActivity extends BaseActivity {

    public static Intent createIntent(Context context){
        return new Intent(context,ViewGroupEventTestActivity.class);
    }

    @InjectView(R.id.id_btn2)
    MyButton myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
