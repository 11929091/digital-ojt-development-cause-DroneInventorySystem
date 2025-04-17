package com.digitalojt.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.form.StockInfoSearchForm;
import com.digitalojt.web.service.StockInfoService;
import com.digitalojt.web.util.MessageManager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 在庫一覧画面のコントローラークラス
 * 
 * @author Kazuma Kuroki
 *
 */
@Controller
@RequiredArgsConstructor
public class StockInfoController {

	/** アプリケーションログ */
	private static final Logger logger = LoggerFactory.getLogger("APP");

	/** 在庫一覧 サービス */
	private final StockInfoService service;

	/** メッセージソース */
	private final MessageSource messageSource;

	// 在庫一覧リスト初期化
	private List<StockInfo> stockList = new ArrayList<>();

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.STOCK_LIST)
	public String index(Model model) {
		// 開始ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫一覧画面 | 処理内容: 初期表示 | 処理結果: 開始",
				System.currentTimeMillis());

		// 在庫一覧画面に表示するデータを取得
		stockList = service.getStockList();

		// 在庫一覧画面に表示する分類名リストを取得
		List<CategoryInfo> categoryList = service.getCategoryList();

		// 画面表示用に在庫一覧リストと分類名リストをセット
		model.addAttribute("stockList", stockList);
		model.addAttribute("categoryList", categoryList);

		// 終了ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫一覧画面 | 処理内容: 初期表示 | 処理結果: 成功",
				System.currentTimeMillis());

		return "admin/stockList/index";
	}

	/**
	 * 検索処理
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping(UrlConsts.STOCK_LIST_SEARCH)
	public String search(@Valid StockInfoSearchForm form, BindingResult bindingResult,
			Model model) {

		// 開始ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫一覧画面 | 処理内容: 検索処理 | 処理結果: 開始",
				System.currentTimeMillis());

		// Valid項目チェック
		if (bindingResult.hasErrors()) {

			// エラーメッセージをプロパティファイルから取得
			String errorMsg = MessageManager.getMessage(messageSource,
					bindingResult.getGlobalError().getDefaultMessage());
			model.addAttribute("errorMsg", errorMsg);

			// 初期表示の在庫一覧リストをセット
			model.addAttribute("stockList", stockList);

			// 在庫一覧画面に表示する分類名リストを取得
			List<CategoryInfo> categoryList = service.getCategoryList();
			model.addAttribute("categoryList", categoryList);

			// エラーログ
			logger.error("【ERROR】時間: {} | 処理対象: 在庫一覧画面 | 処理内容: 検索処理 | 処理結果: エラー | エラー内容: {}",
					System.currentTimeMillis(), errorMsg);

			return "admin/stockList/index";
		}

		// 在庫一覧画面に表示するデータを取得
		List<StockInfo> stockList = service.searchStockInfo(form.getCategoryName(),
				form.getName(), form.getStockAmountFrom(), form.getStockAmountTo());

		// 画面表示用に在庫一覧リストと分類名リストをセット
		model.addAttribute("stockList", stockList);

		// 在庫一覧画面に表示する分類名リストを取得
		List<CategoryInfo> categoryList = service.getCategoryList();
		model.addAttribute("categoryList", categoryList);

		// 終了ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫一覧画面 | 処理内容: 検索処理 | 処理結果: 成功",
				System.currentTimeMillis());

		return "admin/stockList/index";
	}
}
