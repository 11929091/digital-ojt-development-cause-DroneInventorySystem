package com.digitalojt.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.CenterInfo;

/**
 * センター情報テーブルリポジトリー
 *
 * @author Kazuma Kuroki
 * 
 */
@Repository
public interface CenterInfoRepository extends JpaRepository<CenterInfo, Integer> {

	/**
	 * 初期表示用に在庫センター情報を取得
	 * 
	 * @param operationalStatus	稼働状況ステータス
	 * @param deleteFlag	削除フラグ
	 * @return 稼働状況ステータスが「0」（稼働中）かつ削除フラグが「0」（未削除）のデータを全件取得（住所で昇順）
	 */
	@Query("SELECT s FROM CenterInfo s WHERE s.operationalStatus = ?1 AND s.deleteFlag = ?2 ORDER BY s.address ASC")
	List<CenterInfo> findActiveCenters(String operationalStatus, String deleteFlag);

	/**
	 * 引数に合致する在庫センター情報を取得
	 * 
	 * @param centerName
	 * @param region
	 * @param storageCapacityFrom
	 * @param storageCapacityTo
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM CenterInfo s WHERE " +
			"(:centerName = '' OR s.centerName LIKE %:centerName%) AND " +
			"(:region = '' OR s.address LIKE %:region%) AND " +
			"(:storageCapacityFrom IS NULL OR s.currentStorageCapacity >= :storageCapacityFrom) AND " +
			"(:storageCapacityTo IS NULL OR s.currentStorageCapacity <= :storageCapacityTo) AND " +
			"(s.operationalStatus = 0)")
	List<CenterInfo> findByCenterNameAndRegionAndStorageCapacity(
			String centerName,
			String region,
			Integer storageCapacityFrom,
			Integer storageCapacityTo);

}
