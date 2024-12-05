package com.digitalojt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	 * @return String(path)
	 */
	@GetMapping(UrlConsts.CATEGORIZED_INFO_MNG)
	public String index() {

		return "admin/categorizedInfoMng/index";
	}
}
