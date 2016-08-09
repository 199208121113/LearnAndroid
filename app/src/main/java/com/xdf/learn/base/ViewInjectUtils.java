package com.xdf.learn.base;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * Created by xdf on 16-8-9.
 */
public class ViewInjectUtils {

    public static void inject(Activity activity){
        injectContentView(activity);
        injectViews(activity);
    }

    private static final String METHOD_SET_CONTENTVIEW = "setContentView";
    /**
     * 注入布局文件
     * @param activity act
     */
    private static void injectContentView(Activity activity){
        Class<? extends Activity> clazz = activity.getClass();
        //查询类上是否存在ContentView注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if(contentView != null){
            int contentViewId = contentView.value();
            try {
                Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,int.class);
                method.setAccessible(true);
                method.invoke(activity,contentViewId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

    /**
     * 注入所有控件
     * @param activity act
     */
    private static void injectViews(Activity activity){
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            InjectView viewInjectAnnotation = field.getAnnotation(InjectView.class);
            if(viewInjectAnnotation != null){
                int viewId = viewInjectAnnotation.value();
                if(viewId != -1){
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,int.class);
                        Object resView = method.invoke(activity,viewId);
                        field.setAccessible(true);
                        field.set(activity,resView);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
