package com.digitalojt.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.form.CategoryInfoForm;
import com.digitalojt.web.service.CategoryInfoService;
import com.digitalojt.web.util.MessageManager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 分割情報管理画面 Controller
 * 
 * @author Kazuma Kuroki
 */
@Controller
@RequiredArgsConstructor
public class CategoryInfoController extends AbstractController {

	/** 分類情報管理画面 Service */
	@Autowired
	private CategoryInfoService service;

	/** メッセージソース */
	private final MessageSource messageSource;

	/** 分類名リスト初期化 */
	private List<CategoryInfo> categoryInfoList = new ArrayList<>();

	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @return 
	 */
	@GetMapping(UrlConsts.CATEGORY_INFO)
	public String index(Model model) {

		// 分類名リストが空だったら分類名をリストに格納
		if (categoryInfoList.isEmpty()) {
			// 分類情報管理画面に表示する一覧データを取得
			categoryInfoList = service.getCategoryInfoList();
		}

		// Modelに格納
		model.addAttribute("categoryInfo", categoryInfoList);
		model.addAttribute("CategoryInfoForm", new CategoryInfoForm());

		return "admin/categoryInfo/index";
	}

	/**
	 * 分類名検索
	 * 
	 * @param searchForm 検索用フォーム
	 * @param result 入力チェック
	 * @param model モデル
	 * @return
	 */
	@PostMapping(UrlConsts.CATEGORY_INFO_SEARCH)
	public String search(@Valid @ModelAttribute("CategoryInfoForm") CategoryInfoForm form,
			BindingResult bindingResult, Model model) {

		// 入力値が不正であれば、エラーメッセージと初期画面を表示
		if (bindingResult.hasErrors()) {

			// エラーメッセージをプロパティファイルから取得
			String errorMsg = MessageManager.getMessage(messageSource,
					bindingResult.getGlobalError().getDefaultMessage());
			model.addAttribute("errorMsg", errorMsg);

			// 分類名初期表示リスト
			model.addAttribute("categoryInfo", categoryInfoList);

			return "admin/categoryInfo/index";
		}

		// 分類名検索結果を取得
		List<CategoryInfo> categoryInfo = service.searchCategoryInfo(form.getCategoryName());
		// 検索結果をModelに格納
		model.addAttribute("categoryInfo", categoryInfo);

		return "admin/categoryInfo/index";
	}

}
