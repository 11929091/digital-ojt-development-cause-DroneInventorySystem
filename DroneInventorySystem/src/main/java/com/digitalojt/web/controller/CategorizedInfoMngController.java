package com.digitalojt.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.digitalojt.web.consts.ItemCategory;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.dto.SearchForm;

import jakarta.validation.Valid;

/**
 * 分割情報管理画面コントローラークラス
 * 
 * @author Kazuma Kuroki
 *
 */
@Controller
public class CategorizedInfoMngController extends AbstractController {

	// 分類名リスト初期化
	private List<String> categoryList = new ArrayList<>();

	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @return 
	 */
	@GetMapping(UrlConsts.CATEGORIZED_INFO_MNG)
	public String index(Model model) {

		// 分類名リストが空だったら分類名をリストに格納
		if (categoryList.isEmpty()) {
			inputCategoryList();
		}

		model.addAttribute("itemCategory", categoryList);
		model.addAttribute("searchForm", new SearchForm());

		return "admin/categorizedInfoMng/index";
	}

	/**
	 * 分類名検索
	 * 
	 * @param searchForm 検索用フォーム
	 * @param result 入力チェック
	 * @param model モデル
	 * @return
	 */
	@PostMapping("/searchCategory")
	public String searchCategory(@Valid @ModelAttribute("searchForm") SearchForm searchForm,
			BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("itemCategory", categoryList);

			return "admin/categorizedInfoMng/index";
		}

		String keyword = searchForm.getSearchKeyword();
		List<String> filteredCategories = categoryList.stream()
				.filter(category -> category.contains(keyword))
				.collect(Collectors.toList());

		// 検索結果をModelに格納
		model.addAttribute("itemCategory", filteredCategories);
		return "admin/categorizedInfoMng/index";
	}

	/**
	 * 分類名リスト格納
	 */
	public void inputCategoryList() {
		categoryList.add(ItemCategory.FRAME);
		categoryList.add(ItemCategory.PROPELLER);
		categoryList.add(ItemCategory.ELECTRIC_MOTOR);
		categoryList.add(ItemCategory.ELECTRONIC_SPEED_CONTROLLER);
		categoryList.add(ItemCategory.BATTERY);
		categoryList.add(ItemCategory.FLIGHT_CONTROLLER);
		categoryList.add(ItemCategory.REMOTE_CONTROLLER);
		categoryList.add(ItemCategory.RECEIVER);
		categoryList.add(ItemCategory.GPS_MODULE);
		categoryList.add(ItemCategory.CAMERA_SENSOR);
	}
}
