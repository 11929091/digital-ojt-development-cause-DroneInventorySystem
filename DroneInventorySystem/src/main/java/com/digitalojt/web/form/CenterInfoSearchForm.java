package com.digitalojt.web.form;

import com.digitalojt.web.validation.CenterInfoSearchFormValidator;

import lombok.Data;

/**
 * 在庫センター情報画面の検索フォームクラス
 * 
 * @author Kazuma Kuroki
 *
 */
@Data
@CenterInfoSearchFormValidator
public class CenterInfoSearchForm {

	/**
	 * センター名
	 */
	private String centerName;

	/**
	 * 都道府県
	 */
	private String region;

	/**
	 * 容量(From)
	 */
	private String storageCapacityFrom;

	/**
	 * 容量(To)
	 */
	private String storageCapacityTo;
}
