package com.digitalojt.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.repository.CategorizedInfoMngRepository;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報管理画面のサービスクラス
 *
 * @author Kazuma Kuroki
 * 
 */
@Service
@RequiredArgsConstructor
public class CategorizedInfoMngService {

	/** 分類情報テーブル リポジトリー */
	private final CategorizedInfoMngRepository repository;

	/**
	 * 分類名情報を全件検索で取得
	 * 
	 * @return 分類名情報全件データ
	 */
	public List<CategoryInfo> getCategoryInfoData() {

		// 分類名情報作成
		List<CategoryInfo> categoryList = repository.findAll();

		return categoryList;
	}

}
