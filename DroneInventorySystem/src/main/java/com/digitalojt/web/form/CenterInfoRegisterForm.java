package com.digitalojt.web.form;

import com.digitalojt.web.validation.CenterInfoRegisterFormValidator;

import lombok.Data;

/**
 * 在庫センター情報画面の登録フォームクラス
 * 
 * @author Kazuma Kuroki
 *
 */
@Data
@CenterInfoRegisterFormValidator
public class CenterInfoRegisterForm {

	/**
	 * センター名
	 */
	private String centerName;

	/**
	 * 郵便番号
	 */
	private String postCode;

	/**
	 * 住所
	 */
	private String address;

	/**
	 * 電話番号
	 */
	private String phoneNumber;

	/**
	 * 管理者名
	 */
	private String managerName;

	/**
	 * 最大容量
	 */
	private String maxStorageCapacity;

	/**
	 * 現在容量
	 */
	private String currentStorageCapacity;
}
