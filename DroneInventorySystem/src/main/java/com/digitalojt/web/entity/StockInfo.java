package com.digitalojt.web.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 在庫一覧 Entity
 * 
 * @author Kazuma Kuroki
 */
@Data
@Entity
public class StockInfo {

	/**
	 * 在庫ID
	 */
	@Id
	private int stockId;

	/**
	 * カテゴリーID
	 */
	private int categoryId;

	/**
	 * 部品名
	 */
	private String name;

	/**
	 * センターID
	 */
	private int centerId;

	/**
	 * 部品詳細
	 */
	private String description;
	
	/**
	 * 在庫数
	 */
	private Integer amount;
	
	/**
	 * 論理削除フラグ
	 */
	private String deleteFlag;

	/**
	 * 登録日
	 */
	private Timestamp createDate;

	/**
	 * 更新日
	 */
	private Timestamp updateDate;
}
