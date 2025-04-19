package com.digitalojt.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.digitalojt.web.entity.StockInfo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StockInfoRepositoryTest {

	@Autowired
	private StockInfoRepository repository;

	// StockInfo Entity
	StockInfo stockInfo;

	@BeforeEach
	void setUp() {
		// インスタンス生成
		stockInfo = new StockInfo();
	}


	/**
	 * 引数に合致する在庫情報を取得（分類名検索）
	 */
	@ParameterizedTest
	@ValueSource(strings = { "フレーム" })
	void findByCategoryNameAndNameAndStockAmountTest01(String categoryName) {

		List<StockInfo> result = repository.findByCategoryNameAndNameAndStockAmount(categoryName, null, null, null);

		// 検証
		assertNotNull(result);
		assertEquals(5, result.size());
		assertEquals(1, result.get(0).getCategoryId());
		assertEquals("カーボンライトフレーム", result.get(0).getName());
		assertEquals("ステルスマックス", result.get(1).getName());
		assertEquals("アルマジロ・アーマー", result.get(2).getName());
		assertEquals("エアロダイナミクス・プロ", result.get(3).getName());
		assertEquals("クリスタル・ビジョン", result.get(4).getName());
	}

	/**
	 * 引数に合致する在庫情報を取得（部品名検索）
	 */
	@ParameterizedTest
	@ValueSource(strings = { "ブースト" })
	void findByCategoryNameAndNameAndStockAmountTest02(String name) {

		List<StockInfo> result = repository.findByCategoryNameAndNameAndStockAmount(null, name, null, null);

		// 検証
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("ターボブースト", result.get(0).getName());
		assertEquals("ダイナミックブースト", result.get(1).getName());
	}

	/**
	 * 引数に合致する在庫情報を取得（在庫数Fromのみ）
	 */
	@ParameterizedTest
	@ValueSource(ints = { 800 })
	void findByCategoryNameAndNameAndStockAmountTest03(int stockAmountFrom) {

		List<StockInfo> result = repository.findByCategoryNameAndNameAndStockAmount(null, null, stockAmountFrom, null);

		// 検証
		assertNotNull(result);
		assertEquals(10, result.size());
		assertEquals("カーボンライトフレーム", result.get(0).getName());
		assertEquals("サイレントウィンド", result.get(1).getName());
		assertEquals("パワーマックス", result.get(2).getName());
		assertEquals("スムーズコントロール", result.get(3).getName());
		assertEquals("パワーフライ3000", result.get(4).getName());
		assertEquals("スカイマインド", result.get(5).getName());
		assertEquals("スカイジョイ", result.get(6).getName());
		assertEquals("スカイリンクRX-200", result.get(7).getName());
		assertEquals("ジオトラッカーGT-100", result.get(8).getName());
		assertEquals("ビジョンクリアVC-1000", result.get(9).getName());
	}

	/**
	 * 引数に合致する在庫情報を取得（在庫数Toのみ）
	 */
	@ParameterizedTest
	@ValueSource(ints = { 3 })
	void findByCategoryNameAndNameAndStockAmountTest04(int stockAmountTo) {

		List<StockInfo> result = repository.findByCategoryNameAndNameAndStockAmount(null, null, null, stockAmountTo);

		// 検証
		assertNotNull(result);
		assertEquals(10, result.size());
		assertEquals("クリスタル・ビジョン", result.get(0).getName());
		assertEquals("エコスライス", result.get(1).getName());
		assertEquals("エコモーター", result.get(2).getName());
		assertEquals("プログラマブルESC", result.get(3).getName());
		assertEquals("エコフライト1500", result.get(4).getName());
		assertEquals("ドリームフライ", result.get(5).getName());
		assertEquals("パイロットタッチ", result.get(6).getName());
		assertEquals("エアーウェーブRX-450", result.get(7).getName());
		assertEquals("パスファインダーPF-500", result.get(8).getName());
		assertEquals("エコーパルスEP-500", result.get(9).getName());
	}

	/**
	 * 引数に合致する在庫情報を取得（在庫数Toのみ）
	 */
	@ParameterizedTest
	@CsvSource({ "6, 20" })
	void findByCategoryNameAndNameAndStockAmountTest04(int stockAmountFrom, int stockAmountTo) {

		List<StockInfo> result = repository.findByCategoryNameAndNameAndStockAmount(null, null, stockAmountFrom,
				stockAmountTo);

		// 検証
		assertNotNull(result);
		assertEquals(10, result.size());
		assertEquals("アルマジロ・アーマー", result.get(0).getName());
		assertEquals("ネオンフライト", result.get(1).getName());
		assertEquals("デュアルドライブ", result.get(2).getName());
		assertEquals("ステルスモード", result.get(3).getName());
		assertEquals("スカイセル20C", result.get(4).getName());
		assertEquals("ピクセルホーク", result.get(5).getName());
		assertEquals("フライトスティックX1", result.get(6).getName());
		assertEquals("マルチバンドRX-800", result.get(7).getName());
		assertEquals("ナビコアNC-300", result.get(8).getName());
		assertEquals("スカイスキャンSS-300", result.get(9).getName());
	}

}
