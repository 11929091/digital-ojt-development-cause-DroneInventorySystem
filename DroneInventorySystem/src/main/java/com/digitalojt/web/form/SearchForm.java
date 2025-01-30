package com.digitalojt.web.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 分類情報管理画面 Form
 *
 * @author Kazuma Kuroki
 */
public class SearchForm {

	/** 検索分類名 */
	@NotEmpty(message = "※分類名を入力してください。")
	@Size(max = 15, message = "※分類名は1文字以上15文字以内で入力してください。")
	@Pattern(regexp = "^[^ {}()'*;$=&#]*$", message = "※不正な入力を検知しました。")
	private String categoryName;

	/** Getter */
	public String getCategoryName() {
		return categoryName;
	}

	/** Setter */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}

