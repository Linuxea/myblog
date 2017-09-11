package com.linuxea.interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Linuxea on 2017-09-10.
 */
public class ExceptionInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Override
    public void intercept(Invocation inv) {

        try {
            inv.invoke();
        } catch (Exception e) {
            e.printStackTrace();

            StringBuilder sb = new StringBuilder("\n---Exception Log Begin---\n");
            sb.append("Controller:").append(inv.getController().getClass().getName()).append("\n");
            sb.append("Method:").append(inv.getMethodName()).append("\n");
            sb.append("Exception Type:").append(e.getClass().getName()).append("\n");
            sb.append("Exception Details:");
            logger.error(sb.toString(), e);

        }

    }
}
