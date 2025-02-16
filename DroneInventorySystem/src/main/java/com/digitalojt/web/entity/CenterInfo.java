package com.digitalojt.web.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * センター情報Entity
 * 
 * @author Kazuma Kuroki
 *
 */
@Data
@Getter
@Setter
@Entity
public class CenterInfo {

	/**
	 * センターID
	 */
	@Id
	private int centerId;
	
	/**
	 * センター名
	 */
	private String centerName;
	
	/**
	 * 住所
	 */
	private String address;
	
	/**
	 * 電話番号
	 */
	private String phoneNumber;
	
	/**
	 * 管理者名
	 */
	private String managerName;
	
	/**
	 * 未使用フラグ
	 */
	private int operationalStatus;
	
	/**
	 * 最大容量
	 */
	private Integer maxStorageCapacity;
	
	/**
	 * 現在容量
	 */
	private Integer currentStorageCapacity;
	
	/**
	 * 論理削除フラグ
	 */
	private int deleteFlag;

	/**
	 * 更新日
	 */
	private Timestamp updateDate;

	/**
	 * 登録日
	 */
	private Timestamp createDate;
}
