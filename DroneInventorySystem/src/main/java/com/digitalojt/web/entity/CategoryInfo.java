package com.digitalojt.web.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 分類名情報 Entity
 * 
 * @author Kazuma Kuroki
 */
@Data
@Getter
@Setter
@Entity
public class CategoryInfo {

	/**
	 * カテゴリーID
	 */
	@Id
	private int categoryId;
	
	/**
	 * 分類名
	 */
	private String categoryName;
	
	/**
	 * 論理削除フラグ
	 */
	private String deleteFlag;

	/**
	 * 更新日
	 */
	private Timestamp updateDate;

	/**
	 * 登録日
	 */
	private Timestamp createDate;
}
