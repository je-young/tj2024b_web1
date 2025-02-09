
// [*] 조회할 bno(게시물번호)
// - view.jsp?bno=1
// - view.jsp?bno=2
// - view.jsp?bno=3

// [1] 게시물 1개 조회
const findByBno = () => {
	
	// 1. URL 주소상의 bno(조회할번호)를 가져오기
	const bno = new URL( location.href ).searchParams.get( bno );
	// 2. fetch
	const option = { method : 'GET' }
	fetch( `/tj2024b_web1/board/view?bno=${ bno }` , option )
		.then( r => r.json() )
		.then( data => {
			console.log( data );
			document.querySelector('.titlebox').innerHTML = data.btitle;
			document.querySelector('.contentbox').innerHTML = data.bcontent;
			document.querySelector('.midbox').innerHTML = data.med;
			document.querySelector('.viewbox').innerHTML = data.bview;
			document.querySelector('.datebox').innerHTML = data.bdate;
		}) // .then( data => { end
		.catch( e => { console.log(e); })
	
}; // findByBno end
findByBno(); // JS 열렸을때 함수 실행




