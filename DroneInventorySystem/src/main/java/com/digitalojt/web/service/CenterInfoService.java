package com.digitalojt.web.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalojt.web.consts.FormParams;
import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoRegisterForm;
import com.digitalojt.web.repository.CenterInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 在庫センター情報画面のサービスクラス
 *
 * @author Kazuma Kuroki
 * 
 */
@Service
@RequiredArgsConstructor
public class CenterInfoService {

	/** 在庫センター情報テーブル リポジトリー */
	@Autowired
	private final CenterInfoRepository repository;

	/**
	 * 在庫センター情報を全件検索で取得
	 * 
	 * @return
	 */
	@Transactional
	public List<CenterInfo> getCenterInfoData() {

		// 稼働中の在庫センター情報を検索し、表示
		return repository.findActiveCenters("0", "0");
	}

	/**
	 * 検索処理
	 * 
	 * @param centerName
	 * @param region
	 * @param storageCapacityFrom 
	 * @param storageCapacityTo 
	 * @return
	 */
	@Transactional
	public List<CenterInfo> searchCenterInfoData(String centerName, String region, String storageCapacityFrom,
			String storageCapacityTo) {

		int storageCapacityFromInt = 0;
		int storageCapacityToInt = 0;

		if (storageCapacityFrom != null && storageCapacityTo != null) {
			try {
				// String型で渡って来た値をint型に型変換
				storageCapacityFromInt = Integer.parseInt(storageCapacityFrom);
			} catch (NumberFormatException e) {
				// 容量の最小値を設定
				storageCapacityFromInt = FormParams.CENTER_INFO_MIN_CAPACITY;
			}
			try {
				// String型で渡って来た値をint型に型変換
				storageCapacityToInt = Integer.parseInt(storageCapacityTo);
			} catch (NumberFormatException e) {
				// 容量の最大値を設定
				storageCapacityToInt = FormParams.CENTER_INFO_MAX_CAPACITY;
			}
		}

		// 検索結果取得
		return repository.findByCenterNameAndRegionAndStorageCapacity(centerName, region,
				storageCapacityFromInt, storageCapacityToInt);
	}

	/**
	 * 登録処理
	 * 
	 * @param centerInfoRegisterForm
	 * @return
	 */
	@Transactional
	public void registerCenterInfoData(CenterInfoRegisterForm form) {
		// Entity初期化
		CenterInfo centerInfo = new CenterInfo();

		// フォームで受けた入力をEntityに変換
		centerInfo.setCenterName(form.getCenterName());
		centerInfo.setPostCode(form.getPostCode());
		centerInfo.setAddress(form.getAddress());
		centerInfo.setPhoneNumber(form.getPhoneNumber());
		centerInfo.setManagerName(form.getManagerName());
		centerInfo.setOperationalStatus(0);
		int maxStorageCapacity = Integer.parseInt(form.getMaxStorageCapacity());
		centerInfo.setMaxStorageCapacity(maxStorageCapacity);
		int currentStorageCapacity = Integer.parseInt(form.getCurrentStorageCapacity());
		centerInfo.setMaxStorageCapacity(currentStorageCapacity);
		centerInfo.setDeleteFlag(0);
		centerInfo.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		centerInfo.setCreateDate(new Timestamp(System.currentTimeMillis()));
		
		// 登録
		repository.save(centerInfo);
	}

	/**
	 * 編集/削除対象検索処理
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Optional<CenterInfo> findById(int id) {

		// センターIDをもとにレコードを検索
		return repository.findById(id);
	}

	/**
	 * 編集処理
	 * 
	 * @param centerInfoRegisterForm
	 * @return
	 */
	@Transactional
	public void updateCenterInfoData(Optional<CenterInfo> centerInfo, CenterInfoRegisterForm form) {

		// フォームで受けた入力をEntityに変換
		centerInfo.get().setCenterName(form.getCenterName());
		centerInfo.get().setPostCode(form.getPostCode());
		centerInfo.get().setAddress(form.getAddress());
		centerInfo.get().setPhoneNumber(form.getPhoneNumber());
		centerInfo.get().setManagerName(form.getManagerName());
		centerInfo.get().setOperationalStatus(0);
		int maxStorageCapacity = Integer.parseInt(form.getMaxStorageCapacity());
		centerInfo.get().setMaxStorageCapacity(maxStorageCapacity);
		int currentStorageCapacity = Integer.parseInt(form.getCurrentStorageCapacity());
		centerInfo.get().setMaxStorageCapacity(currentStorageCapacity);
		centerInfo.get().setDeleteFlag(0);
		centerInfo.get().setUpdateDate(new Timestamp(System.currentTimeMillis()));

		// 更新
		repository.save(centerInfo.get());
	}

	/**
	 * 削除処理
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public void deleteById(int id) {

		// センターIDをもとにレコードを削除
		repository.deleteById(id);
	}

}
