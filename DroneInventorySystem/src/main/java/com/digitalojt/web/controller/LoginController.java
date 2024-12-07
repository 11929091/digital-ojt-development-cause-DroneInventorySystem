package com.digitalojt.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.form.LoginForm;
import com.digitalojt.web.util.MessageManager;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面のコントローラークラス
 * 
 * @author Kazuma Kuroki
 *
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

	/** ログインID */
	private static final String LOGIN_ID = "user";

	/** ログインパスワード */
	private static final String PASSWORD = "pwd";

	/** メッセージソース */
	private final MessageSource messageSource;

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.LOGIN)
	public String index(Model model, LoginForm form) {

		return "admin/login/index";
	}

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return 
	 * @return
	 */
	@PostMapping(UrlConsts.AUTHENTICATE)
	public String login(Model model, LoginForm form, RedirectAttributes redirectAttributes) {

		// 管理者IDとパスワードの組み合わせが合致するレコードを取得
		Boolean isCorrectUserAuth = LOGIN_ID.equals(form.getAdminId()) && PASSWORD.equals(form.getPassword());

		if (isCorrectUserAuth) {

			return "redirect:" + UrlConsts.CATEGORIZED_INFO_MNG;
		} else {

			// エラーメッセージをプロパティファイルから取得
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LOGIN_WRONG_INPUT);
	        redirectAttributes.addFlashAttribute("errorMsg", errorMsg);
	        return "redirect:" + UrlConsts.LOGIN;
		}
	}
}
