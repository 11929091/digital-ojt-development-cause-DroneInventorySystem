package com.digitalojt.web.validation;

import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.FormParams;
import com.digitalojt.web.form.StockInfoSearchForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 在庫一覧画面のバリデーションチェック 実装クラス
 * 
 * @author Kazuma Kuroki
 */
public class StockInfoSearchFormValidatorImpl
		implements ConstraintValidator<StockInfoSearchFormValidator, StockInfoSearchForm> {

	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(StockInfoSearchForm form, ConstraintValidatorContext context) {

		boolean allFieldsEmpty = StringUtils.isEmpty(form.getCategoryName()) &&
				StringUtils.isEmpty(form.getName()) && StringUtils.isEmpty(form.getStockAmountFrom())
				&& StringUtils.isEmpty(form.getStockAmountTo());

		// すべてのフィールドが空かをチェック
		if (allFieldsEmpty) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE)
					.addConstraintViolation();
			return false;
		}

		// 分類名の入力チェック
		if (form.getCategoryName() != null) {

			// 文字数チェック
			if (form.getCategoryName().length() > FormParams.CENTER_INFO_MAX_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_NAME_LENGTH_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getCategoryName())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// 部品名の入力チェック
		if (form.getName() != null) {

			// 文字数チェック
			if (form.getName().length() > FormParams.CENTER_INFO_MAX_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_NAME_LENGTH_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getName())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// 在庫数の入力チェック
		// 在庫数(From)
		int stockAmountFrom = 0;
		// 在庫数(To)
		int stockAmountTo = 0;

		// 在庫数(From)に入力があれば
		if (!form.getStockAmountFrom().isEmpty()) {
			try {
				// String型→int型への型変換に失敗すればエラーメッセージを表示
				stockAmountFrom = Integer.parseInt(form.getStockAmountFrom());
			} catch (NumberFormatException e) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						ErrorMessage.STORAGE_CAPACITY_WRONG_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}
		// 容量(To)に入力があれば
		if (!form.getStockAmountTo().isEmpty()) {
			try {
				// String型→int型への型変換に失敗すればエラーメッセージを表示
				stockAmountTo = Integer.parseInt(form.getStockAmountTo());
				// 容量(From) < 容量(To) の場合、エラーメッセージを表示
				if (stockAmountFrom > stockAmountTo) {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(
							ErrorMessage.STORAGE_CAPACITY_COMPARE_CURRENT_CAPACITY_ERROR_MESSAGE)
							.addConstraintViolation();
					return false;
				}
			} catch (NumberFormatException e) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						ErrorMessage.STORAGE_CAPACITY_WRONG_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// その他のバリデーションに問題なければtrueを返す
		return true;
	}
}
