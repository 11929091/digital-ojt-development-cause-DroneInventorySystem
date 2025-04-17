package com.digitalojt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalojt.web.consts.FormParams;
import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.repository.CategoryInfoRepository;
import com.digitalojt.web.repository.StockInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 在庫一覧画面のサービスクラス
 *
 * @author Kazuma Kuroki
 * 
 */
@Service
@RequiredArgsConstructor
public class StockInfoService {

	/** 在庫一覧テーブル リポジトリー */
	@Autowired
	private final StockInfoRepository stockInfoRepository;

	/** 分類情報管理画面 Repository */
	@Autowired
	private final CategoryInfoRepository categoryInfoRepository;

	/**
	 * 在庫一覧を全件検索で取得
	 * 
	 * @return
	 */
	@Transactional
	public List<StockInfo> getStockList() {

		return stockInfoRepository.findAll();
	}

	/**
	 * 分類名一覧を取得
	 * 
	 * @return
	 */
	@Transactional
	public List<CategoryInfo> getCategoryList() {

		return categoryInfoRepository.findAll();
	}

	/**
	 * 検索処理
	 * 
	 * @param categoryName
	 * @param name
	 * @param stockAmountFrom 
	 * @param stockAmountTo 
	 * @return
	 */
	@Transactional
	public List<StockInfo> searchStockInfo(String categoryName, String name, String stockAmountFrom,
			String stockAmountTo) {

		int stockAmountFromInt = 0;
		int stockAmountToInt = 0;

		if (stockAmountFrom != null && stockAmountTo != null) {
			try {
				// String型で渡って来た値をint型に型変換
				stockAmountFromInt = Integer.parseInt(stockAmountFrom);
			} catch (NumberFormatException e) {
				// 在庫数の最小値を設定
				stockAmountFromInt = FormParams.STOCK_LIST_MIN_AMOUNT;
			}
			try {
				// String型で渡って来た値をint型に型変換
				stockAmountToInt = Integer.parseInt(stockAmountTo);
			} catch (NumberFormatException e) {
				// 在庫数の最大値を設定
				stockAmountToInt = FormParams.STOCK_LIST_MAX_AMOUNT;
			}
		}

		// 検索結果取得
		return stockInfoRepository.findByCategoryNameAndNameAndStockAmount(categoryName, name,
				stockAmountFromInt, stockAmountToInt);
	}

}
