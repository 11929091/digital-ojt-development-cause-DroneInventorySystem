package com.digitalojt.web.consts;

/**
 * URL定数クラス
 *
 * @author Kazuma Kuroki
 * 
 */
public class UrlConsts {

	// ログイン
	public static final String LOGIN = "/";

	// 認証
	public static final String AUTHENTICATE = "/authenticate";

	// 在庫一覧画面
	public static final String STOCK_LIST = "/admin/stockList";

	// 在庫一覧画面 検索
	public static final String STOCK_LIST_SEARCH = "/admin/stockList/search";

	// 在庫センター情報画面
	public static final String  CENTER_INFO = "/admin/centerInfo";

	// 在庫センター情報画面 検索
	public static final String CENTER_INFO_SEARCH = "/admin/centerInfo/search";

	// 認証不要画面
	public static final String[] NO_AUTHENTICATION = {LOGIN, AUTHENTICATE};

	// 分割情報管理画面
	public static final String CATEGORIZED_INFO_MNG = "/admin/categorizedInfoMng";
}
