package com.digitalojt.web.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.form.StockInfoSearchForm;
import com.digitalojt.web.repository.StockInfoRepository;

@ExtendWith(MockitoExtension.class)
public class StockInfoServiceTest {

	@Mock
	private StockInfoRepository repository;

	@InjectMocks
	private StockInfoService service;

	// StockInfo Entity
	StockInfo stockInfo;

	// StockInfoSearchForm DTO
	StockInfoSearchForm form;

	@BeforeEach
	void setUp() {
		// インスタンス生成
		stockInfo = new StockInfo();
		form = new StockInfoSearchForm();
	}

	/**
	 * 検索処理（1件）
	 */
	@ParameterizedTest
	@CsvSource({ "フレーム, カーボンライトフレーム, 10, 500" })
	void searchStockInfoTest01(String categoryName, String name, String storageCapacityFrom, String storageCapacityTo) {

		// 準備
		form.setCategoryName(categoryName);
		form.setName(name);
		form.setStockAmountFrom(storageCapacityFrom);
		form.setStockAmountTo(storageCapacityTo);

		int storageCapacityFromInt = Integer.parseInt(storageCapacityFrom);
		int storageCapacityToInt = Integer.parseInt(storageCapacityTo);

		// モック定義
		stockInfo.setCategoryId(1);
		stockInfo.setName(name);
		stockInfo.setAmount(200);
		when(repository.findByCategoryNameAndNameAndStockAmount(categoryName, name, storageCapacityFromInt,
				storageCapacityToInt)).thenReturn(Arrays.asList(stockInfo));

		// 実行
		List<StockInfo> result = service.searchStockInfo(categoryName, name, storageCapacityFrom, storageCapacityTo);

		// 検証
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(1, result.get(0).getCategoryId());
		assertEquals("カーボンライトフレーム", result.get(0).getName());
		assertEquals(200, result.get(0).getAmount());

		verify(repository, times(1)).findByCategoryNameAndNameAndStockAmount(categoryName, name, storageCapacityFromInt,
				storageCapacityToInt);
	}

	/**
	 * 検索処理（複数件）
	 */
	@ParameterizedTest
	@CsvSource({ "フレーム, フレーム, 10, 500" })
	void searchStockInfoTest02(String categoryName, String name, String storageCapacityFrom, String storageCapacityTo) {

		// 準備
		form.setCategoryName(categoryName);
		form.setName(name);
		form.setStockAmountFrom(storageCapacityFrom);
		form.setStockAmountTo(storageCapacityTo);

		int storageCapacityFromInt = Integer.parseInt(storageCapacityFrom);
		int storageCapacityToInt = Integer.parseInt(storageCapacityTo);

		// モック定義
		List<StockInfo> stockInfoList = new ArrayList<>();

		stockInfo.setCategoryId(1);
		stockInfo.setName("カーボンライトフレーム");
		stockInfo.setAmount(200);

		StockInfo stockInfo2 = new StockInfo();
		stockInfo2.setCategoryId(1);
		stockInfo2.setName("カーボンフレーム");
		stockInfo2.setAmount(300);

		stockInfoList.add(stockInfo);
		stockInfoList.add(stockInfo2);

		when(repository.findByCategoryNameAndNameAndStockAmount(categoryName, name, storageCapacityFromInt,
				storageCapacityToInt)).thenReturn(stockInfoList);

		// 実行
		List<StockInfo> result = service.searchStockInfo(categoryName, name, storageCapacityFrom, storageCapacityTo);

		// 検証
		assertNotNull(result);
		assertEquals(2, result.size());
		// 1件目
		assertEquals(1, result.get(0).getCategoryId());
		assertEquals("カーボンライトフレーム", result.get(0).getName());
		assertEquals(200, result.get(0).getAmount());
		// 2件目
		assertEquals(1, result.get(1).getCategoryId());
		assertEquals("カーボンフレーム", result.get(1).getName());
		assertEquals(300, result.get(1).getAmount());

		verify(repository, times(1)).findByCategoryNameAndNameAndStockAmount(categoryName, name, storageCapacityFromInt,
				storageCapacityToInt);
	}

	/**
	 * 検索処理（0件）
	 */
	@ParameterizedTest
	@CsvSource({ "フレーム, カーボンライトフレーム, 10, 500" })
	void searchStockInfoTest03(String categoryName, String name, String storageCapacityFrom, String storageCapacityTo) {

		// 準備
		form.setCategoryName(categoryName);
		form.setName(name);
		form.setStockAmountFrom(storageCapacityFrom);
		form.setStockAmountTo(storageCapacityTo);

		int storageCapacityFromInt = Integer.parseInt(storageCapacityFrom);
		int storageCapacityToInt = Integer.parseInt(storageCapacityTo);

		// モック定義
		when(repository.findByCategoryNameAndNameAndStockAmount(categoryName, name, storageCapacityFromInt,
				storageCapacityToInt)).thenReturn(Arrays.asList(stockInfo));

		// 実行
		List<StockInfo> result = service.searchStockInfo(categoryName, name, storageCapacityFrom, storageCapacityTo);

		// 検証
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(0, result.get(0).getCategoryId());
		assertNull(result.get(0).getName());
		assertNull(result.get(0).getAmount());

		verify(repository, times(1)).findByCategoryNameAndNameAndStockAmount(categoryName, name, storageCapacityFromInt,
				storageCapacityToInt);
	}

}
