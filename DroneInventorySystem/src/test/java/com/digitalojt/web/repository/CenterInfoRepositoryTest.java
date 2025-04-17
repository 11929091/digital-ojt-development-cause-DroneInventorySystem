package com.digitalojt.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.digitalojt.web.entity.CenterInfo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CenterInfoRepositoryTest {

	@Autowired
	private CenterInfoRepository repository;

	// CategoryInfo Entity
	CenterInfo centerInfo;

	@BeforeEach
	void setUp() {
		// インスタンス生成
		centerInfo = new CenterInfo();
	}

	/**
	 * 初期表示用に在庫センター情報を取得
	 */
	@Test
	void displayCenterInfoTest() {

		List<CenterInfo> result = repository.findActiveCenters("0", "0");

		// 検証
		assertNotNull(result);
		assertEquals(6, result.size());
		assertEquals("神戸物流センター", result.get(0).getCenterName());
		assertEquals("北海道物流センター", result.get(1).getCenterName());
		assertEquals("大阪物流センター", result.get(2).getCenterName());
		assertEquals("名古屋物流センター", result.get(3).getCenterName());
		assertEquals("東京物流センター", result.get(4).getCenterName());
		assertEquals("福岡物流センター", result.get(5).getCenterName());
	}

	/**
	 * 引数(センター名)が合致する在庫センター情報を取得（部分一致）
	 */
	@ParameterizedTest
	@ValueSource(strings = { "東京" })
	void findByCenterNameAndRegionAndStorageCapacityTest01(String centerName) {

		List<CenterInfo> result = repository.findByCenterNameAndRegionAndStorageCapacity(centerName, "", null, null);

		// 検証
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(1, result.get(0).getCenterId());
		assertEquals("東京物流センター", result.get(0).getCenterName());
	}

	/**
	 * 引数(住所)が合致する在庫センター情報を取得（部分一致）
	 */
	@ParameterizedTest
	@ValueSource(strings = { "兵庫" })
	void findByCenterNameAndRegionAndStorageCapacityTest02(String address) {

		List<CenterInfo> result = repository.findByCenterNameAndRegionAndStorageCapacity("", address, null, null);

		// 検証
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(16, result.get(0).getCenterId());
		assertEquals("神戸物流センター", result.get(0).getCenterName());
	}

	/**
	 * 引数(容量Fromのみ)が合致する在庫センター情報を取得（部分一致）
	 */
	@ParameterizedTest
	@ValueSource(ints = { 300 })
	void findByCenterNameAndRegionAndStorageCapacityTest03(Integer storageCapacityFrom) {

		List<CenterInfo> result = repository.findByCenterNameAndRegionAndStorageCapacity("", "", storageCapacityFrom,
				null);

		// 検証
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(1, result.get(0).getCenterId());
		assertEquals("東京物流センター", result.get(0).getCenterName());
		assertEquals(16, result.get(1).getCenterId());
		assertEquals("神戸物流センター", result.get(1).getCenterName());
	}

	/**
	 * 引数(容量Toのみ)が合致する在庫センター情報を取得（部分一致）
	 */
	@ParameterizedTest
	@ValueSource(ints = { 200 })
	void findByCenterNameAndRegionAndStorageCapacityTest04(Integer storageCapacityTo) {

		List<CenterInfo> result = repository.findByCenterNameAndRegionAndStorageCapacity("", "", null,
				storageCapacityTo);

		// 検証
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(5, result.get(0).getCenterId());
		assertEquals("福岡物流センター", result.get(0).getCenterName());
	}

	/**
	 * 引数(容量From, 容量To)が合致する在庫センター情報を取得（部分一致）
	 */
	@ParameterizedTest
	@CsvSource({ "200, 300" })
	void findByCenterNameAndRegionAndStorageCapacityTest05(Integer storageCapacityFrom, Integer storageCapacityTo) {

		List<CenterInfo> result = repository.findByCenterNameAndRegionAndStorageCapacity("", "", storageCapacityFrom,
				storageCapacityTo);

		// 検証
		assertNotNull(result);
		assertEquals(3, result.size());
		assertEquals(2, result.get(0).getCenterId());
		assertEquals("大阪物流センター", result.get(0).getCenterName());
		assertEquals(3, result.get(1).getCenterId());
		assertEquals("名古屋物流センター", result.get(1).getCenterName());
		assertEquals(6, result.get(2).getCenterId());
		assertEquals("北海道物流センター", result.get(2).getCenterName());
	}

}
