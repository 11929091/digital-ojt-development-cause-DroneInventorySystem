package com.digitalojt.web.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.repository.CategoryInfoRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryInfoServiceTest {

	@Mock
	private CategoryInfoRepository repository;

	@InjectMocks
	private CategoryInfoService service;

	// CategoryInfo Entity
	CategoryInfo categoryInfo;

	@BeforeEach
	void setUp() {
		// インスタンス生成
		categoryInfo = new CategoryInfo();
	}

	/**
	 * 分類名情報検索（該当あり）
	 */
	@ParameterizedTest
	@ValueSource(strings = { "フレーム", "電子速度調整器", "フライトコントローラー" })
	void searchCategoryInfoTest01(String categoryName) {
		categoryInfo.setCategoryName(categoryName);
		when(repository.findByCategoryName(categoryName)).thenReturn(Arrays.asList(categoryInfo));

		List<CategoryInfo> result = service.searchCategoryInfo(categoryName);

		// 検証
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(categoryName, result.get(0).getCategoryName());
	}

	/**
	 * 分類名情報検索（該当なし）
	 */
	@ParameterizedTest
	@ValueSource(strings = { "カメラ", "バッテリ", "コントローラー" })
	void searchCategoryInfoTest02(String categoryName) {
		categoryInfo.setCategoryName(categoryName);
		when(repository.findByCategoryName(categoryName)).thenReturn(Arrays.asList());

		List<CategoryInfo> result = service.searchCategoryInfo(categoryName);

		// 検証
		assertNotNull(result);
		assertEquals(0, result.size());
	}

}
