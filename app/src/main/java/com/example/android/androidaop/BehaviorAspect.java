package com.example.android.androidaop;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 切面
 * 你想要切下来的蛋糕
 */
@Aspect
public class BehaviorAspect {
    private static final String TAG = "dongnao";
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 如何切蛋糕，切成什么样的形状
     * 切点
     */
    @Pointcut("execution(@com.example.android.androidaop.TBehaviorTrace  * *(..))")
    public void annoBehavior()
    {

    }

    /**
     * 切面
     * 蛋糕按照切点切下来之后   怎么吃
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("annoBehavior()")
    public Object dealPoint(ProceedingJoinPoint point) throws  Throwable {
        //方法执行前
        MethodSignature methodSignature= (MethodSignature) point.getSignature();
        TBehaviorTrace  behaviorTrace=methodSignature.getMethod().getAnnotation(TBehaviorTrace.class);
        String contentType=behaviorTrace.value();
        Log.i(TAG,contentType+"使用时间：   "+simpleDateFormat.format(new Date()));
        long beagin=System.currentTimeMillis();
        //方法执行时
        Object object=null;
        try {
            object=point.proceed();
        }catch (Exception e)
        {

        }
        //方法执行完成
        Log.i(TAG,"消耗时间：  "+(System.currentTimeMillis()-beagin)+"ms");

        return  object;
    }



    @Pointcut("call(* com.example.android.androidaop.Animal.*(..))")
    public void callMethod() {}

    @Before("callMethod()")
    public void beforeMethodCall(JoinPoint joinPoint) {
        Log.e(TAG, "before->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());
    }

    @After("callMethod()")
    public void afterMethodCall(JoinPoint joinPoint) {
        Log.e(TAG, "After->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());
    }



}

