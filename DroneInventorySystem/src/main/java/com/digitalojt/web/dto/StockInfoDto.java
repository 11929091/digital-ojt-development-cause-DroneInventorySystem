package com.digitalojt.web.dto;

import lombok.Data;

/**
 * 在庫一覧 DTO
 * 
 * @author Kazuma Kuroki
 */
@Data
public class StockInfoDto {

	private String categoryName;
	private String name;
	private int amount;
	private String centerName;
	private String description;

}
