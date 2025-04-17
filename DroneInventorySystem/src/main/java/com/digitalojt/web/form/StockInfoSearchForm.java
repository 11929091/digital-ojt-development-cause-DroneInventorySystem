package com.digitalojt.web.form;

import com.digitalojt.web.validation.StockInfoSearchFormValidator;

import lombok.Data;

/**
 * 在庫センター情報画面の検索フォームクラス
 * 
 * @author Kazuma Kuroki
 *
 */
@Data
@StockInfoSearchFormValidator
public class StockInfoSearchForm {

	/**
	 * 分類名
	 */
	private String categoryName;

	/**
	 * 在庫名
	 */
	private String name;

	/**
	 * 在庫数(From)
	 */
	private String stockAmountFrom;

	/**
	 * 在庫数(To)
	 */
	private String stockAmountTo;
}
