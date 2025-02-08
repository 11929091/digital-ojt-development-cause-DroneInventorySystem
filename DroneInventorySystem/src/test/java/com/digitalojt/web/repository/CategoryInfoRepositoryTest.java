package com.digitalojt.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.digitalojt.web.entity.CategoryInfo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryInfoRepositoryTest {

	@Autowired
	private CategoryInfoRepository repository;

	// CategoryInfo Entity
	CategoryInfo categoryInfo;

	@BeforeEach
	void setUp() {
		// インスタンス生成
		categoryInfo = new CategoryInfo();
	}

	/**
	 * 分類名情報完全一致検索（フレーム）
	 */
	@Test
	void findByCategoryNameTest01() {

		categoryInfo.setCategoryName("フレーム");
		List<CategoryInfo> result = repository.findByCategoryName(categoryInfo.getCategoryName());

		// 検証
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(1, result.get(0).getCategoryId());
		assertEquals("フレーム", result.get(0).getCategoryName());
	}

	/**
	 * 分類名情報完全一致検索（受信機）
	 */
	@Test
	void findByCategoryNameTest02() {

		categoryInfo.setCategoryName("受信機");
		List<CategoryInfo> result = repository.findByCategoryName(categoryInfo.getCategoryName());

		// 検証
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(8, result.get(0).getCategoryId());
		assertEquals("受信機", result.get(0).getCategoryName());
	}

	/**
	 * 分類名情報完全一致検索（カメラ／センサー）
	 */
	@Test
	void findByCategoryNameTest03() {

		categoryInfo.setCategoryName("カメラ／センサー");
		List<CategoryInfo> result = repository.findByCategoryName(categoryInfo.getCategoryName());

		// 検証
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(10, result.get(0).getCategoryId());
		assertEquals("カメラ／センサー", result.get(0).getCategoryName());
	}

	/**
	 * 分類名情報完全一致検索（該当なし）
	 */
	@Test
	void findByCategoryNameTest04() {

		categoryInfo.setCategoryName("カメラ");
		List<CategoryInfo> result = repository.findByCategoryName(categoryInfo.getCategoryName());

		// 検証
		assertNotNull(result);
		assertEquals(0, result.size());
	}

}
