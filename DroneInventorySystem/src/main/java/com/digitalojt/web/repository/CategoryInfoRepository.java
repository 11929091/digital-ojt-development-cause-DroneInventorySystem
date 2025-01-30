package com.digitalojt.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.CategoryInfo;

/**
 * 分類情報管理画面 Repository
 *
 * @author Kazuma Kuroki
 */
@Repository
public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Integer> {
	/** 検索結果取得（完全一致）*/
	List<CategoryInfo> findByCategoryName(String categoryName);

}