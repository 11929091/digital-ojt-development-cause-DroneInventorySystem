package com.digitalojt.web.form;

import com.digitalojt.web.validation.CenterInfoFormValidator;

import lombok.Data;

/**
 * 在庫センター情報画面のフォームクラス
 * 
 * @author Kazuma Kuroki
 *
 */
@Data
@CenterInfoFormValidator
public class CenterInfoForm {

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
