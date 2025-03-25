package com.digitalojt.web.validation;

import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.FormParams;
import com.digitalojt.web.form.CenterInfoSearchForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 在庫センター情報画面のバリデーションチェック 実装クラス
 * 
 * @author Kazuma Kuroki
 */
public class CenterInfoSearchFormValidatorImpl implements ConstraintValidator<CenterInfoSearchFormValidator, CenterInfoSearchForm> {

	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(CenterInfoSearchForm form, ConstraintValidatorContext context) {

		boolean allFieldsEmpty = StringUtils.isEmpty(form.getCenterName()) &&
				StringUtils.isEmpty(form.getRegion()) && StringUtils.isEmpty(form.getStorageCapacityFrom())
				&& StringUtils.isEmpty(form.getStorageCapacityTo());

		// すべてのフィールドが空かをチェック
		if (allFieldsEmpty) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE)
					.addConstraintViolation();
			return false;
		}

		// センター名の入力チェック
		if (form.getCenterName() != null) {

			//			// センター名の入力がない場合
			//			if (form.getCenterName().isEmpty()) {
			//				context.disableDefaultConstraintViolation();
			//				context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_NAME_EMPTY_ERROR_MESSAGE)
			//						.addConstraintViolation();
			//				return false;
			//			}

			// 文字数チェック
			if (form.getCenterName().length() > FormParams.CENTER_INFO_MAX_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_NAME_LENGTH_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getCenterName())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// 容量の入力チェック
		// 容量(From)
		int storageCapacityFrom = 0;
		// 容量(To)
		int storoageCapacityTo = 0;

		// 容量(From)に入力があれば
		if (!form.getStorageCapacityFrom().isEmpty()) {
			try {
				// String型→int型への型変換に失敗すればエラーメッセージを表示
				storageCapacityFrom = Integer.parseInt(form.getStorageCapacityFrom());
			} catch (NumberFormatException e) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						ErrorMessage.STORAGE_CAPACITY_WRONG_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}
		// 容量(To)に入力があれば
		if (!form.getStorageCapacityTo().isEmpty()) {
			try {
				// String型→int型への型変換に失敗すればエラーメッセージを表示
				storoageCapacityTo = Integer.parseInt(form.getStorageCapacityTo());
				// 容量(From) < 容量(To) の場合、エラーメッセージを表示
				if (storageCapacityFrom > storoageCapacityTo) {
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

		// 都道府県のチェック
		if (form.getRegion() != null) {

			// センター名に入力があればTRUEを返す
			if (!form.getCenterName().isEmpty()) {
				return true;
			}

			// 容量のどちらかに入力があればTRUEを返す
			if (!form.getStorageCapacityFrom().isEmpty() || !form.getStorageCapacityTo().isEmpty()) {
				return true;
			}

			// 不正文字列チェック
			if (ParmCheckUtil.isRegionInvalid(form.getRegion())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// その他のバリデーションに問題なければtrueを返す
		return true;
	}
}
