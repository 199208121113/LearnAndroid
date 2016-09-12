package com.xdf.learn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.xdf.learn.R;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.annotation.InjectView;
import com.xdf.learn.base.SupperActivity;
import com.xdf.learn.view.MyButton;
import com.youloft.xcore.util.LogUtil;

/**
 * View的事件转发流程:
 * <<p >
 * 1.view.dispatchEvent---View.setOnTouchListener---View OnTouchEvent
 * 2.dispathcEvent会判断OnTouchListener 如果OnTouchListener不为null 并且返回true，则事件被消费 OnTouchEvent不会被执行，否则执行OnTouchEvent
 *
 * 3.onTouchEvent中的DOWN,MOVE,UP:
 DOWN时：
 a、首先设置标志为PREPRESSED，设置mHasPerformedLongPress=false ;然后发出一个115ms后的mPendingCheckForTap；
 b、如果115ms内没有触发UP，则将标志置为PRESSED，清除PREPRESSED标志，同时发出一个延时为500-115ms的，检测长按任务消息；
 c、如果500ms内（从DOWN触发开始算），则会触发LongClickListener:
 此时如果LongClickListener不为null，则会执行回调，同时如果LongClickListener.onClick返回true，才把mHasPerformedLongPress设置为true;
 否则mHasPerformedLongPress依然为false;

 MOVE时：
 主要就是检测用户是否划出控件，如果划出了：
 115ms内，直接移除mPendingCheckForTap；
 115ms后，则将标志中的PRESSED去除，同时移除长按的检查：removeLongPressCallback();

 UP时：
 a、如果115ms内，触发UP，此时标志为PREPRESSED，则执行UnsetPressedState，setPressed(false);会把setPress转发下去，可以在View中复写dispatchSetPressed方法接收；
 b、如果是115ms-500ms间，即长按还未发生，则首先移除长按检测，执行onClick回调；
 c、如果是500ms以后，那么有两种情况：
 i.设置了onLongClickListener，且onLongClickListener.onClick返回true，则点击事件OnClick事件无法触发；
 ii.没有设置onLongClickListener或者onLongClickListener.onClick返回false，则点击事件OnClick事件依然可以触发；
 d、最后执行mUnsetPressedState.run()，将setPressed传递下去，然后将PRESSED标识去除；
 * </p>/>
 * Created by xdf on 16-9-9.
 */
@ContentView(R.layout.act_view_event)
public class ViewEventTestActivity extends SupperActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, ViewEventTestActivity.class);
    }

    @InjectView(R.id.id_btn)
    MyButton btnMyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnMyButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        LogUtil.e(tag, "onTouch ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtil.e(tag, "onTouch ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtil.e(tag, "onTouch ACTION_UP");
                        break;
                }
                return false;//如果我们设置了setOnTouchListener 并且返回true那么view自己的OnTouchEvent就不会执行了
            }
        });
    }
}
