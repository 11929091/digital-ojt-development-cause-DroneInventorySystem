package com.digitalojt.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.CategoryInfo;

/**
 * 分類名情報テーブルリポジトリー
 *
 * @author Kazuma Kuroki
 * 
 */
@Repository
public interface CategorizedInfoMngRepository extends JpaRepository<CategoryInfo, Integer> {

}
