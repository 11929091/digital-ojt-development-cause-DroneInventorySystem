package com.digitalojt.web.validation;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.FormParams;
import com.digitalojt.web.form.CategoryInfoForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 在庫センター情報画面のバリデーションチェック 実装クラス
 * 
 * @author Kazuma Kuroki
 */
public class CategoryInfoFormValidatorImpl implements ConstraintValidator<CategoryInfoFormValidator, CategoryInfoForm> {

	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(CategoryInfoForm form, ConstraintValidatorContext context) {

		// センター名のチェック
		if (form.getCategoryName() != null) {

			// センター名の入力がない場合
			if (form.getCategoryName().isEmpty()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CATEGORY_NAME_EMPTY_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getCategoryName()) || form.getCategoryName().isBlank()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 文字数チェック
			if (form.getCategoryName().length() > FormParams.CATEGORY_INFO_MAX_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CATEGORY_NAME_LENGTH_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// その他のバリデーションに問題なければtrueを返す
		return true;
	}
}
