package com.digitalojt.web.form;

import com.digitalojt.web.validation.CategoryInfoFormValidator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 分類情報管理画面のフォームクラス
 *
 * @author Kazuma Kuroki
 */
@Data
@CategoryInfoFormValidator
public class CategoryInfoForm {

	/** 検索分類名 */
	@NotBlank(message = "{invalid.input}")
	@Pattern(regexp = "^[^{}()'*;$=&#]*$", message = "{invalid.input}")
	private String categoryName;

}

