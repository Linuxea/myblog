package com.linuxea.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.linuxea.controller.FileUploadController;
import com.linuxea.controller.articlemanager.ArticleManagerController;
import com.linuxea.controller.classmanager.ClassManagerController;
import com.linuxea.controller.index.IndexController;
import com.linuxea.controller.loginmanager.LoginController;
import com.linuxea.controller.tagmanager.TagManagerController;
import com.linuxea.interceptor.ExceptionInterceptor;
import com.linuxea.model._MappingKit;
import com.linuxea.plugins.CronPlugin;

/**
 * Created by Linuxea on 2017-09-10.
 */
public class JfinalSystem extends BaseConfig {
	
	static {
		PropKit.use("sqlConfig.properties");
	}
	
	/**
	 * druid 插件复用
	 *
	 * @return
	 */
	public static DruidPlugin getDruidPlugin() {
		String url = PropKit.get("url");
		String userName = PropKit.get("userName");
		String password = PropKit.get("password");
		DruidPlugin druidPlugin = new DruidPlugin(url, userName, password);
		return druidPlugin;
	}
	
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 8080, "/");
	}
	
	@Override
	public void configConstant(Constants me) {
//        me.setDevMode(true);
		me.setViewType(ViewType.JSP);
		me.setError404View("404.html");
		me.setError401View("404.html");
		me.setError403View("404.html");
		me.setError500View("404.html");
		me.setError500View("404.html");
		
	}
	
	@Override
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add("/tagController", TagManagerController.class);
		me.add("/kindController", ClassManagerController.class);
		me.add("/articleController", ArticleManagerController.class);
		me.add("/loginController", LoginController.class);
		me.add("/upload", FileUploadController.class);
	}
	
	@Override
	public void configEngine(Engine me) {
	
	}
	
	@Override
	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = getDruidPlugin();
		me.add(druidPlugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
//        arp.setShowSql(true);
		arp.setDialect(new MysqlDialect());
		_MappingKit.mapping(arp);
		me.add(arp);
		
		//定时任务
		me.add(new CronPlugin());
	}
	
	@Override
	public void configInterceptor(Interceptors me) {
//		me.add(new Restful());
		me.add(new ExceptionInterceptor());
	}
	
	@Override
	public void configHandler(Handlers me) {
	}
}
