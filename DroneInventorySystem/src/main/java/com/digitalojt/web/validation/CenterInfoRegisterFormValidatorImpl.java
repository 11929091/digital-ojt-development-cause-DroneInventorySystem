package com.digitalojt.web.validation;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.FormParams;
import com.digitalojt.web.form.CenterInfoRegisterForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 在庫センター情報画面のバリデーションチェック 実装クラス
 * 
 * @author Kazuma Kuroki
 */
public class CenterInfoRegisterFormValidatorImpl
		implements ConstraintValidator<CenterInfoRegisterFormValidator, CenterInfoRegisterForm> {

	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(CenterInfoRegisterForm form, ConstraintValidatorContext context) {

		// センター名の入力チェック
		if (form.getCenterName() != null) {

			// センター名の入力がない場合
			if (form.getCenterName().isEmpty()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_NAME_EMPTY_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

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

		// 郵便番号の入力チェック
		if (form.getPostCode() != null) {

			// 郵便番号の入力がない場合
			if (form.getPostCode().isEmpty()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.POST_CODE_EMPTY_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 文字数チェック
			if (form.getPostCode().length() != FormParams.CENTER_INFO_POST_CODE_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.POST_CODE_LENGTH_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 数値入力チェック
			int postCode = 0;

			try {
				postCode = Integer.parseInt(form.getPostCode());
			} catch (NumberFormatException e) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.POST_CODE_INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getPostCode())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// 住所の入力チェック
		if (form.getAddress() != null) {

			// 住所の入力がない場合
			if (form.getAddress().isEmpty()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_NAME_EMPTY_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getAddress())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// 電話番号の入力チェック
		if (form.getPhoneNumber() != null) {

			// 電話番号の入力がない場合
			if (form.getPhoneNumber().isEmpty()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.PHONE_NUMBER_EMPTY_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 数値入力チェック
			int phoneNumber = 0;

			try {
				phoneNumber = Integer.parseInt(form.getPhoneNumber());
			} catch (NumberFormatException e) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.PHONE_NUMBER_INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getPhoneNumber())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// 管理者名の入力チェック
		if (form.getManagerName() != null) {

			// 管理者名の入力がない場合
			if (form.getManagerName().isEmpty()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.MANAGER_NAME_EMPTY_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getManagerName())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// 容量の入力チェック
		// 最大容量
		int maxStorageCapacity = 0;
		// 現在容量
		int currentStoroageCapacity = 0;

		// 最大容量に入力があれば
		if (!form.getMaxStorageCapacity().isEmpty()) {
			try {
				maxStorageCapacity = Integer.parseInt(form.getMaxStorageCapacity());
			} catch (NumberFormatException e) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						ErrorMessage.STORAGE_CAPACITY_WRONG_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}
		// 現在容量に入力があれば
		if (!form.getCurrentStorageCapacity().isEmpty()) {
			try {
				currentStoroageCapacity = Integer.parseInt(form.getCurrentStorageCapacity());
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
