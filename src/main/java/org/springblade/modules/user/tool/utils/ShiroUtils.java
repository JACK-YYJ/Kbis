package org.springblade.modules.user.tool.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springblade.modules.user.entity.TechuserDict;

public class ShiroUtils {
	public static TechuserDict getUser() {
		TechuserDict user = (TechuserDict) SecurityUtils.getSubject().getPrincipal();
		System.out.println(user);
		return user;
	}

	public static String enPas(String pas, String salt) {
		String hashAlgorithmName = "MD5";   //使用哪种加密算法
		int hashIterations = 1024;          //加密次数
		ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
		Object obj = new SimpleHash(hashAlgorithmName, pas, credentialsSalt, hashIterations);
		return obj.toString();

	}

	public static void main(String[] args) {
		System.out.println(enPas("123", "123456"));
	}

}
