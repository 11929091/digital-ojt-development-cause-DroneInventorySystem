package com.digitalojt.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SearchForm {

	@NotEmpty(message = "※分類名を入力してください。")
	@Size(max = 15, message = "※分類名は1文字以上15文字以内で入力してください。")
	@Pattern(regexp = "^[^ {}()'*;$=&#]*$", message = "※不正な入力を検知しました。")
	private String searchKeyword;

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
}

