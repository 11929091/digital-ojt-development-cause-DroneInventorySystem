package com.digitalojt.web.util;

import java.util.Arrays;

import com.digitalojt.web.consts.InvalidCharacter;
import com.digitalojt.web.consts.Region;

/**
 * パラメーターチェックに関する処理を行うクラス
 * 
 * @author Kazuma Kuroki
 *
 */
public class ParmCheckUtil {

	/**
	 * 不正文字チェック
	 *  
	 * @param val
	 * @return
	 */
	public static Boolean isParameterInvalid(String val) {

		return Arrays.stream(InvalidCharacter.values())
				.anyMatch(invalidChar -> val.indexOf(invalidChar.getCharacter()) >= 0);
	}

	/**
	 * 都道府県不正文字チェック
	 *  
	 * @param val
	 * @return
	 */
	public static boolean isRegionInvalid(String val) {

		return Arrays.stream(Region.values())
				.noneMatch(validRegion -> val.contains(validRegion.getName()));
	}
}
