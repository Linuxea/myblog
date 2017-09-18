package com.linuxea.utils;

import java.util.UUID;

/**
 * id 获取工具
 * create by linuxea on 2017/9/11 12:13
 **/
public class IdKits {


	public static final String wantId() {
		return UUID.randomUUID().toString();
	}

}
