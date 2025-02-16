package com.digitalojt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.repository.CategoryInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報管理画面 Service
 *
 * @author Kazuma Kuroki
 */
@Service
@RequiredArgsConstructor
public class CategoryInfoService {

	/** 分類情報管理画面 Repository */
	@Autowired
	CategoryInfoRepository repository;

	/**
	 * 分類名情報全件検索
	 * 
	 * @return 分類名情報全件データ
	 */
	public List<CategoryInfo> getCategoryInfoList() {

		// Repositoryクラスからデータベースにアクセスし、全件検索。
		List<CategoryInfo> categoryInfoList = repository.findAll();

		return categoryInfoList;
	}

	/**
	 * 分類名情報検索
	 * 
	 * @param categoryName 検索分類名
	 * @return 分類名情報検索データ
	 */
	public List<CategoryInfo> searchCategoryInfo(String categoryName) {
		// Repositoryクラスからデータベースにアクセスし、検索結果取得
		return repository.findByCategoryName(categoryName);
	}

}
