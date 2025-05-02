package com.digitalojt.web.consts;

/**
 * エラーメッセージ定数クラス
 * 
 * @author Kazuma Kuroki
 *
 */
public class ErrorMessage {
	
	// ログイン情報の入力に誤りがあった場合に、出力するエラーメッセージのID
	public static final String  LOGIN_WRONG_INPUT = "login.wrongInput";

	// すべての項目が空の場合のエラーメッセージ
	public static final String ALL_FIELDS_EMPTY_ERROR_MESSAGE = "allField.empty";

	// 予期せぬ入力検知に関するエラーメッセージ
	public static final String UNEXPECTED_INPUT_ERROR_MESSAGE = "unexpected.input";

	// 不正な文字列を使用した検索に関するエラーメッセージ
	public static final String INVALID_INPUT_ERROR_MESSAGE = "invalid.input";

	// 分類情報管理_空文字検索に関するエラーメッセージ
	public static final String CATEGORY_NAME_EMPTY_ERROR_MESSAGE = "categoryName.empty";

	// 分類情報管理_文字超過に関するエラーメッセージ
	public static final String CATEGORY_NAME_LENGTH_ERROR_MESSAGE = "categoryName.length.wrongInput";

	// 在庫センター情報_空文字に関するエラーメッセージ
	public static final String CENTER_NAME_EMPTY_ERROR_MESSAGE = "centerName.empty";

	// 在庫センター情報_文字超過に関するエラーメッセージ
	public static final String CENTER_NAME_LENGTH_ERROR_MESSAGE = "centerName.length.wrongInput";

	// 在庫センター情報_郵便番号空文字に関するエラーメッセージ
	public static final String POST_CODE_EMPTY_ERROR_MESSAGE = "postCode.empty";

	// 在庫センター情報_住所空文字に関するエラーメッセージ
	public static final String ADDRESS_EMPTY_ERROR_MESSAGE = "address.empty";

	// 在庫センター情報_電話番号空文字に関するエラーメッセージ
	public static final String PHONE_NUMBER_EMPTY_ERROR_MESSAGE = "phoneNumber.empty";

	// 在庫センター情報_管理者名空文字に関するエラーメッセージ
	public static final String MANAGER_NAME_EMPTY_ERROR_MESSAGE = "managerName.empty";

	// 在庫センター情報_最大容量空文字に関するエラーメッセージ
	public static final String MAX_STORAGE_EMPTY_ERROR_MESSAGE = "maxStorageCapacity.empty";

	// 在庫センター情報_現在容量空文字に関するエラーメッセージ
	public static final String CURRENT_STORAGE_EMPTY_ERROR_MESSAGE = "currentStorageCapacity.empty";

	// 在庫センター情報_現在容量空文字に関するエラーメッセージ
	public static final String CURRENT_STORAGE_CHECK_INPUT_VALUE_ERROR_MESSAGE = "currentStorageCapacity.checkInputValue";

	// 在庫センター情報_容量入力に関するエラーメッセージ
	public static final String STORAGE_CAPACITY_WRONG_INPUT_ERROR_MESSAGE = "storageCapacity.wrongInput";

	// 在庫センター情報_容量比較に関するエラーメッセージ
	public static final String STORAGE_CAPACITY_COMPARE_CURRENT_CAPACITY_ERROR_MESSAGE = "storageCapacity.compareCurrentCapacity";

	// 在庫一覧情報_部品名文字数超過に関するエラーメッセージ
	public static final String STOCK_NAME_LENGTH_ERROR_MESSAGE = "stockName.length.wrongInput";

	// 在庫一覧情報_在庫数入力に関するエラーメッセージ
	public static final String AMOUNT_WRONG_INPUT_ERROR_MESSAGE = "amount.wrongInput";

	// 在庫一覧情報_在庫数比較に関するエラーメッセージ
	public static final String COMPARE_AMOUNT_ERROR_MESSAGE = "compareAmount.error";
}
