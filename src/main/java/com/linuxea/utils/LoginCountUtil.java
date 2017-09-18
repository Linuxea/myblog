package com.linuxea.utils;

import com.jfinal.plugin.activerecord.Db;
import com.linuxea.model.BlackList;
import com.linuxea.model.LoginList;

/**
 * 统计登录情况
 * Created by Linuxea on 2017/9/18.
 */
public class LoginCountUtil {

	/**
	 * 统计该 ip 的登录历史情况
	 *
	 * @param ip
	 * @param state
	 */
	public static void countSuccess(String ip, boolean state) {
		String sql = "select count(*) from login_list where ip = ?";
		Long count = Db.queryLong(sql, ip);
		if (0 == count) {
			//该 ip 首次记录下来
			LoginList loginList = new LoginList();
			loginList.setId(IdKits.wantId());
			loginList.setIp(ip);
			if (state) {
				loginList.setSuccessCount(1);
			} else {
				loginList.setFailCount(1);
			}
			loginList.save();
			return;
		}
		if (state) {
			Db.update("update login_list set success_count = success_count + 1 where ip = ?", ip);
		} else {
			Db.update("update login_list set fail_count = fail_count + 1 where ip = ?", ip);
			checkAfterFail(ip);
		}


	}

	public static void checkAfterFail(String ip) {
		String countTotalFail = "select fail_count from login_list where ip = ?";
		Long totalFailCount = Db.queryLong(countTotalFail, ip);
		if (totalFailCount > 10) { //超过10次就列出黑名单
			addBlackList(ip);
		}
	}


	/**
	 * 将 ip  添加进黑名单
	 */
	public static void addBlackList(String ip) {
		BlackList blackList =
				BlackList.dao.findFirst("select count(*) AS SUM from black_list where ip = ?", ip);

		if (blackList.getLong("SUM") == 0) {
			BlackList innerBlack = new BlackList();
			innerBlack.setId(IdKits.wantId());
			innerBlack.setIp(ip);
			innerBlack.setRemark("登录出错次数过多");
			innerBlack.save();
		}
	}

}
