package com.linuxea.interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by Linuxea on 2017-09-10.
 */
public class ExceptionInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {

        try {
            inv.invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
