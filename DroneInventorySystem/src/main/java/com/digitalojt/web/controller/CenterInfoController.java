package com.digitalojt.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.digitalojt.web.consts.Region;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoRegisterForm;
import com.digitalojt.web.form.CenterInfoSearchForm;
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

	/** アプリケーションログ */
	private static final Logger logger = LoggerFactory.getLogger("APP");

	/** 在庫センター情報 サービス */
	private final CenterInfoService service;

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
		// 開始ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: GET | 処理結果: 開始",
                System.currentTimeMillis());

		// 在庫センター情報画面に表示するデータを取得
		centerInfoList = service.getCenterInfoData();

		// 画面表示用に在庫センター情報リストをセット
		model.addAttribute("centerInfoList", centerInfoList);

		// 都道府県Enumをリストに変換
		List<Region> regions = Arrays.asList(Region.values());
		// 都道府県プルダウン情報をセット
		model.addAttribute("regions", regions);

		// 終了ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: GET | 処理結果: 成功",
                System.currentTimeMillis());

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
	public String search(@Valid CenterInfoSearchForm form, BindingResult bindingResult,
			Model model) {

		// 開始ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: 開始",
                System.currentTimeMillis());

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

			// エラーログ
			logger.error("【ERROR】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: エラー | エラー内容: {}",
					System.currentTimeMillis(), errorMsg);

			return "admin/centerInfo/index";
		}

		// 在庫センター情報画面に表示するデータを取得
		List<CenterInfo> centerInfoList = service.searchCenterInfoData(form.getCenterName(),
				form.getRegion(), form.getStorageCapacityFrom(), form.getStorageCapacityTo());

		// 画面表示用に商品情報リストをセット
		model.addAttribute("centerInfoList", centerInfoList);

		// 都道府県Enumをリストに変換
		List<Region> regions = Arrays.asList(Region.values());

		// 都道府県プルダウン情報をセット
		model.addAttribute("regions", regions);

		// 終了ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: 成功",
				System.currentTimeMillis());

		return "admin/centerInfo/index";
	}

	/**
	 * 登録画面表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO_REGISTER_DISPLAY)
	public String registerDisplay(Model model) {

		// 登録フォーム入力画面へ
		return "admin/centerInfo/register";
	}

	/**
	 * 登録処理
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping(UrlConsts.CENTER_INFO_REGISTER)
	public String register(@Valid CenterInfoRegisterForm form, BindingResult bindingResult,
			Model model) {

		// 開始ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: 登録開始",
				System.currentTimeMillis());

		// Valid項目チェック
		if (bindingResult.hasErrors()) {

			// エラーメッセージをプロパティファイルから取得
			String errorMsg = MessageManager.getMessage(messageSource,
					bindingResult.getGlobalError().getDefaultMessage());
			model.addAttribute("errorMsg", errorMsg);

			// エラーログ
			logger.error("【ERROR】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: エラー | エラー内容: {}",
					System.currentTimeMillis(), errorMsg);

			return "admin/centerInfo/register";
		}

		// フォームで受けた入力を登録
		service.registerCenterInfoData(form);

		// 終了ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: 成功 | 登録内容: {}",
				System.currentTimeMillis(), form.getCenterName());

		// 登録完了後、初期表示へ
		return "redirect:/admin/centerInfo";
	}

	/**
	 * 更新画面表示
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping(UrlConsts.CENTER_INFO_UPDATE_DISPLAY + "/{id}")
	public String updateDisplay(@PathVariable int id, Model model) {

		// センターIDをもとにレコードを検索
		Optional<CenterInfo> centerInfo = service.findById(id);

		// レコードが存在しなければ初期表示に戻る
		if (!centerInfo.isPresent()) {
			return "redirect:/admin/centerInfo";
		}

		model.addAttribute(centerInfo.get());

		// 更新フォーム入力画面へ
		return "/admin/centerInfo/update";
	}

	/**
	 * 更新処理
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping(UrlConsts.CENTER_INFO_UPDATE + "/{id}")
	public String update(@Valid CenterInfoRegisterForm form, BindingResult bindingResult,
			@PathVariable int id, Model model) {

		// 開始ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: 更新開始 (ID: {})",
				System.currentTimeMillis(), id);

		Optional<CenterInfo> centerInfo = Optional.empty();

		// Valid項目チェック
		if (bindingResult.hasErrors()) {
			centerInfo = service.findById(id);
			if (centerInfo.isPresent()) {
				model.addAttribute("centerInfo", centerInfo.get());
			} else {
				logger.warn("【WARN】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: 失敗 | 更新対象の在庫センター情報が見つかりません (ID: {})",
						System.currentTimeMillis(), id);
				return "redirect:/admin/centerInfo";
			}

			// エラーメッセージをプロパティファイルから取得
			String errorMsg = MessageManager.getMessage(messageSource,
					bindingResult.getGlobalError().getDefaultMessage());

			model.addAttribute("errorMsg", errorMsg);

			// エラーログ
			logger.error("【ERROR】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: エラー | エラー内容: {}",
					System.currentTimeMillis(), errorMsg);

			return "admin/centerInfo/update";
		}

		// 更新対象を取得
		centerInfo = service.findById(id);
		if (!centerInfo.isPresent()) {
			logger.warn("【WARN】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: 失敗 | 更新対象の在庫センター情報が見つかりません (ID: {})",
					System.currentTimeMillis(), id);
			return "redirect:/admin/centerInfo";
		}

		// フォームで受けた入力で編集
		service.updateCenterInfoData(centerInfo, form);

		// 終了ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: 成功 | 更新内容: ID: {}, Name: {}",
				System.currentTimeMillis(), id, form.getCenterName());

		// 編集完了後、初期表示へ
		return "redirect:/admin/centerInfo";
	}

	/**
	 * 削除画面表示
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping(UrlConsts.CENTER_INFO_DELETE_DISPLAY + "/{id}")
	public String deleteDisplay(@PathVariable int id, Model model) {

		// センターIDをもとにレコードを検索
		Optional<CenterInfo> centerInfo = service.findById(id);

		// レコードが存在しなければ初期表示に戻る
		if (!centerInfo.isPresent()) {
			return "redirect:/admin/centerInfo";
		}

		model.addAttribute(centerInfo.get());

		// 削除確認画面へ
		return "/admin/centerInfo/delete";
	}

	/**
	 * 削除処理
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping(UrlConsts.CENTER_INFO_DELETE + "/{id}")
	public String delete(@PathVariable int id, Model model) {

		// 開始ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: 削除開始 (ID: {})",
				System.currentTimeMillis(), id);

		Optional<CenterInfo> centerInfo = service.findById(id);
		if (!centerInfo.isPresent()) {
			logger.warn("【WARN】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: 失敗 | 削除対象の在庫センター情報が見つかりません (ID: {})",
					System.currentTimeMillis(), id);
			return "redirect:/admin/centerInfo";
		}

		// センターIDをもとに削除
		service.deleteById(id);

		// 終了ログ
		logger.info("【INFO】時間: {} | 処理対象: 在庫センター情報画面 | 処理内容: POST | 処理結果: 成功 | 削除内容: ID: {}",
				System.currentTimeMillis(), id);

		// 削除完了後、初期表示へ
		return "redirect:/admin/centerInfo";
	}
}
