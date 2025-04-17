package com.digitalojt.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 管理者情報Entity
 * 
 * @author Kazuma Kuroki
 *
 */
@Data
@Entity
public class AdminInfo {

	/**
	 * 管理者ID
	 */
	@Id
	private String adminId;
	
	/**
	 * 管理者名
	 */
	private String adminName;
	
	/**
	 * パスワード
	 */
	private String password;
}
