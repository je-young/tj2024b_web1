// [2] 전체 조회
const boardFindAll = () => {
	
	// 1. 어디에
	let tbody = document.querySelector('tbody')
	// 2. 무엇을
	let html = '';
	
	// - fetch 옵션
	const option = { method : 'GET' }
	// - fetch 사용
	fetch('/tj2024b_web1/day05/board' , option)
		.then( response => response.json() )
		.then( data => {
			data.forEach( board => {
				html += `<tr>
							<td> ${ board.bno } </td>
							<td>
								<a href="view.jsp?bno=${ board.bno }">
									${ board.btitle }
								</a>
							</td>
							<td> ${ board.bdate } </td>
							<td> ${ board.bwriter } </td>
							<td> ${ board.bview } </td>
						</tr>`
			} );
			// 3. 출력
			tbody.innerHTML = html;
		} )
		.catch( error => { console.log( error ); } )
	
} // boardFindAll end
boardFindAll(); // - 최초 1번은 실행