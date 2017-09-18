package com.linuxea.interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.linuxea.exception.BlogException;
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
		} catch (BlogException blogException) {
			blogException.printStackTrace();
			doLog(inv, blogException);
		} catch (Exception exception) {
			exception.printStackTrace();
			doLog(inv, exception);
		} finally {
			//Do nothing
		}

	}

	/**
	 * 异常记录
	 *
	 * @param invocation
	 * @param e
	 */
	private void doLog(Invocation invocation, Exception e) {
		StringBuilder sb = new StringBuilder("\n---Exception Log Begin---\n");
		sb.append("Controller:").append(invocation.getController().getClass().getName()).append("\n");
		sb.append("Method:").append(invocation.getMethodName()).append("\n");
		sb.append("Exception Type:").append(e.getClass().getName()).append("\n");
		sb.append("Exception Details:");
		logger.error(sb.toString(), e);
	}
}
