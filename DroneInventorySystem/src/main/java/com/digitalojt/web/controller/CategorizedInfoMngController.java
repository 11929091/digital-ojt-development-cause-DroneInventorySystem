package com.digitalojt.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalojt.web.consts.ItemCategory;
import com.digitalojt.web.consts.UrlConsts;

/**
 * 分割情報管理画面コントローラークラス
 * 
 * @author Kazuma Kuroki
 *
 */
@Controller
public class CategorizedInfoMngController extends AbstractController {

	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @return 
	 */
	@GetMapping(UrlConsts.CATEGORIZED_INFO_MNG)
	public String index(Model model) {

		List<String> CategoryList = new ArrayList<>();
		CategoryList.add(ItemCategory.FRAME);
		CategoryList.add(ItemCategory.PROPELLER);
		CategoryList.add(ItemCategory.ELECTRIC_MOTOR);
		CategoryList.add(ItemCategory.ELECTRONIC_SPEED_CONTROLLER);
		CategoryList.add(ItemCategory.BATTERY);
		CategoryList.add(ItemCategory.FLIGHT_CONTROLLER);
		CategoryList.add(ItemCategory.REMOTE_CONTROLLER);
		CategoryList.add(ItemCategory.RECEIVER);
		CategoryList.add(ItemCategory.GPS_MODULE);
		CategoryList.add(ItemCategory.CAMERA_SENSOR);

		model.addAttribute("itemCategory", CategoryList);

		return "admin/categorizedInfoMng/index";
	}
}
