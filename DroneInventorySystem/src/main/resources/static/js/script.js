function filterTable() {
	const searchTerm = document.getElementById('searchBox').value.toLowerCase(); // 検索ボックスの値を取得
	const rows = document.querySelectorAll('#dataTable tbody tr'); // 表の行を取得
	
	rows.forEach(row => {
		const cell = row.querySelector('td'); // 各行の分類名を取得
		const text = cell.textContent.toLowerCase(); // 小文字にして比較
		
		if (text.includes(searchTerm)) {
			row.style.display = ''; // 一致した場合は行を表示
		} else {
			row.style.display = 'none'; // 一致しない場合は行を非表示
		}
	});
}
