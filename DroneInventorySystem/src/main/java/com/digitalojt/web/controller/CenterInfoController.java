package com.digitalojt.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.digitalojt.web.consts.Region;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.service.CenterInfoService;
import com.digitalojt.web.util.MessageManager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 在庫センター情報画面のコントローラークラス
 * 
 * @author Kazuma Kuroki
 *
 */
@Controller
@RequiredArgsConstructor
public class CenterInfoController {

	/** センター情報 サービス */
	private final CenterInfoService centerInfoService;

	/** メッセージソース */
	private final MessageSource messageSource;

	// 在庫センターリスト初期化
	private List<CenterInfo> centerInfoList = new ArrayList<>();

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO)
	public String index(Model model) {

		// 在庫センター情報画面に表示するデータを取得
		centerInfoList = centerInfoService.getCenterInfoData();

		// 画面表示用に在庫センター情報リストをセット
		model.addAttribute("centerInfoList", centerInfoList);

		// 都道府県Enumをリストに変換
		List<Region> regions = Arrays.asList(Region.values());
		// 都道府県プルダウン情報をセット
		model.addAttribute("regions", regions);

		return "admin/centerInfo/index";
	}

	/**
	 * 検索結果表示
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping(UrlConsts.CENTER_INFO_SEARCH)
	public String search(@Valid CenterInfoForm form, BindingResult bindingResult,
			Model model) {

		// Valid項目チェック
		if (bindingResult.hasErrors()) {
			
			// エラーメッセージをプロパティファイルから取得
			String errorMsg = MessageManager.getMessage(messageSource,
					bindingResult.getGlobalError().getDefaultMessage());
			model.addAttribute("errorMsg", errorMsg);

			// 都道府県Enumをリストに変換
			List<Region> regions = Arrays.asList(Region.values());

			// 都道府県プルダウン情報をセット
			model.addAttribute("regions", regions);
			// 在庫センター初期表示リスト
			model.addAttribute("centerInfoList", centerInfoList);

			return "admin/centerInfo/index";
		}

		// 在庫センター情報画面に表示するデータを取得
		List<CenterInfo> centerInfoList = centerInfoService.searchCenterInfoData(form.getCenterName(),
				form.getRegion(), form.getStorageCapacityFrom(), form.getStorageCapacityTo());

		// 画面表示用に商品情報リストをセット
		model.addAttribute("centerInfoList", centerInfoList);

		// 都道府県Enumをリストに変換
		List<Region> regions = Arrays.asList(Region.values());

		// 都道府県プルダウン情報をセット
		model.addAttribute("regions", regions);

		return "admin/centerInfo/index";
	}

	/**
	 * 登録画面遷移
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO_REGISTER)
	public String registerDisplay(Model model) {
		return "admin/centerInfo/register";
	}
}
