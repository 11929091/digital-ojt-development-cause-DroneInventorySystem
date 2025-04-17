package com.digitalojt.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.StockInfo;

/**
 * 在庫情報管理画面 Repository
 *
 * @author Kazuma Kuroki
 */
@Repository
public interface StockInfoRepository extends JpaRepository<StockInfo, Integer> {

	/**
	 * 引数に合致する在庫情報を取得
	 * 
	 * @param categoryName
	 * @param name
	 * @param stockAmountFrom
	 * @param stockAmountTo
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM StockInfo s INNER JOIN CategoryInfo c ON s.categoryId = c.categoryId WHERE " +
			"(:categoryName IS NULL OR c.categoryName LIKE %:categoryName%) AND " +
			"(:name IS NULL OR s.name LIKE %:name%) AND " +
			"(:stockAmountFrom IS NULL OR s.amount >= :stockAmountFrom) AND " +
			"(:stockAmountTo IS NULL OR s.amount <= :stockAmountTo) AND " +
			"(s.deleteFlag = '0')")
	public List<StockInfo> findByCategoryNameAndNameAndStockAmount(
			String categoryName,
			String name,
			Integer stockAmountFrom,
			Integer stockAmountTo);

	/**
	 * 分類名リストを取得
	 * 
	 * @return paramで検索した結果
	 */
	@Query("SELECT c.categoryName FROM StockInfo s INNER JOIN CategoryInfo c ON s.categoryId = c.categoryId")
	public List<StockInfo> getCategoryNameList();
		
}