package com.digitalojt.web.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoRegisterForm;
import com.digitalojt.web.repository.CenterInfoRepository;

@ExtendWith(MockitoExtension.class)
public class CenterInfoServiceTest {

	@Mock
	private CenterInfoRepository repository;

	@InjectMocks
	private CenterInfoService service;

	// CenterInfo Entity
	CenterInfo centerInfo;

	@BeforeEach
	void setUp() {
		// インスタンス生成
		centerInfo = new CenterInfo();

	}

	/**
	 * 検索処理（1件）
	 */
	@ParameterizedTest
	@CsvSource({ "東京物流センター, 東京, 100, 300" })
	void searchCenterInfoTest01(String centerName, String region, String storageCapacityFrom,
			String storageCapacityTo) {

		// 準備
		int storageCapacityFromInt = 0;
		int storageCapacityToInt = 0;

		if (!(storageCapacityFrom == null && storageCapacityTo == null)) {
			storageCapacityFromInt = Integer.parseInt(storageCapacityFrom);
			storageCapacityToInt = Integer.parseInt(storageCapacityTo);
		}

		centerInfo.setCenterName(centerName);
		centerInfo.setAddress(region);
		centerInfo.setCurrentStorageCapacity(250);

		// モック定義
		when(repository.findByCenterNameAndRegionAndStorageCapacity(centerName, region, storageCapacityFromInt,
				storageCapacityToInt)).thenReturn(Arrays.asList(centerInfo));

		// 実行
		List<CenterInfo> result = service.searchCenterInfo(centerName, region, storageCapacityFrom, storageCapacityTo);

		// 検証
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("東京物流センター", result.get(0).getCenterName());
		assertEquals("東京", result.get(0).getAddress());
		assertEquals(250, result.get(0).getCurrentStorageCapacity());

		verify(repository, times(1)).findByCenterNameAndRegionAndStorageCapacity(centerName, region,
				storageCapacityFromInt, storageCapacityToInt);
	}

	/**
	 * 検索処理（複数件）
	 */
	@ParameterizedTest
	@CsvSource({ "東京, 100, 300" })
	void searchCenterInfoTest02(String region, String storageCapacityFrom,
			String storageCapacityTo) {

		// 準備
		int storageCapacityFromInt = 0;
		int storageCapacityToInt = 0;

		if (!(storageCapacityFrom == null && storageCapacityTo == null)) {
			storageCapacityFromInt = Integer.parseInt(storageCapacityFrom);
			storageCapacityToInt = Integer.parseInt(storageCapacityTo);
		}

		centerInfo.setCenterName("東京物流センター");
		centerInfo.setAddress(region);
		centerInfo.setCurrentStorageCapacity(250);

		CenterInfo centerInfo2 = new CenterInfo();
		centerInfo2.setCenterName("品川物流センター");
		centerInfo2.setAddress(region);
		centerInfo2.setCurrentStorageCapacity(120);

		List<CenterInfo> centerInfoList = new ArrayList<>();
		centerInfoList.add(centerInfo);
		centerInfoList.add(centerInfo2);

		// モック定義
		when(repository.findByCenterNameAndRegionAndStorageCapacity(null, region, storageCapacityFromInt,
				storageCapacityToInt)).thenReturn(centerInfoList);

		// 実行
		List<CenterInfo> result = service.searchCenterInfo(null, region, storageCapacityFrom, storageCapacityTo);

		// 検証
		assertNotNull(result);
		assertEquals(2, result.size());
		// 1件目
		assertEquals("東京物流センター", result.get(0).getCenterName());
		assertEquals("東京", result.get(0).getAddress());
		assertEquals(250, result.get(0).getCurrentStorageCapacity());
		// 2件目
		assertEquals("品川物流センター", result.get(1).getCenterName());
		assertEquals("東京", result.get(1).getAddress());
		assertEquals(120, result.get(1).getCurrentStorageCapacity());

		verify(repository, times(1)).findByCenterNameAndRegionAndStorageCapacity(null, region,
				storageCapacityFromInt, storageCapacityToInt);
	}

	/**
	 * 検索処理（0件）
	 */
	@ParameterizedTest
	@CsvSource({ "100, 300" })
	void searchCenterInfoTest03(String storageCapacityFrom,
			String storageCapacityTo) {

		// 準備
		int storageCapacityFromInt = 0;
		int storageCapacityToInt = 0;

		if (!(storageCapacityFrom == null && storageCapacityTo == null)) {
			storageCapacityFromInt = Integer.parseInt(storageCapacityFrom);
			storageCapacityToInt = Integer.parseInt(storageCapacityTo);
		}

		centerInfo.setCenterName("東京物流センター");
		centerInfo.setAddress("東京");
		centerInfo.setCurrentStorageCapacity(350);

		// モック定義
		when(repository.findByCenterNameAndRegionAndStorageCapacity(null, null, storageCapacityFromInt,
				storageCapacityToInt)).thenReturn(Arrays.asList());

		// 実行
		List<CenterInfo> result = service.searchCenterInfo(null, null, storageCapacityFrom, storageCapacityTo);

		// 検証
		assertNotNull(result);
		assertEquals(0, result.size());

		verify(repository, times(1)).findByCenterNameAndRegionAndStorageCapacity(null, null,
				storageCapacityFromInt, storageCapacityToInt);
	}

	/**
	 * 登録処理
	 */
	@Test
	void registerCenterInfoTest() {

		// 準備
		CenterInfoRegisterForm form = new CenterInfoRegisterForm();
		form.setCenterName("神戸物流センター");
		form.setPostCode("000-0000");
		form.setAddress("兵庫県神戸市");
		form.setPhoneNumber("111-222-3333");
		form.setManagerName("神戸 太郎");
		form.setCurrentStorageCapacity("200");
		form.setMaxStorageCapacity("500");

		// モック定義
		CenterInfo expected = new CenterInfo();
		expected.setCenterId(1); // ID自動割当
		when(repository.save(any(CenterInfo.class))).thenReturn(expected);

		// 実行
		service.registerCenterInfo(form);

		// 検証：保存時の引数をチェック
		ArgumentCaptor<CenterInfo> captor = ArgumentCaptor.forClass(CenterInfo.class);
		verify(repository).save(captor.capture());

		CenterInfo captured = captor.getValue();

		assertEquals("神戸物流センター", captured.getCenterName());
		assertEquals("000-0000", captured.getPostCode());
		assertEquals("兵庫県神戸市", captured.getAddress());
		assertEquals("111-222-3333", captured.getPhoneNumber());
		assertEquals("神戸 太郎", captured.getManagerName());
		assertEquals(500, captured.getMaxStorageCapacity());
		assertEquals(200, captured.getCurrentStorageCapacity());
		assertEquals(0, captured.getDeleteFlag());
	}

	/**
	 * 削除処理
	 */
	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void deleteByIdTest(int id) {

		// モック定義
		CenterInfo dummy = new CenterInfo();
		dummy.setCenterId(id);

		doNothing().when(repository).deleteById(id);
		
		// 実行
		service.deleteById(id);
		
		// 検証
		verify(repository, times(1)).deleteById(id);
	}

}
