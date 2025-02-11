// [1] URl(경로상의 cno) 매개변수 값 구하기.
// /tj2024_web1/board/board.jsp?cno=1
// /tj2024_web1/board/board.jsp?cno=2
// /tj2024_web1/board/board.jsp?cno=3
// - URL 상의 쿼리스트링 매개변수 : new URL( location/href ).searchParams
// - URL 상의 쿼리스트링 매개변수의 값 추출  : new URL( location/href ).searchParams.get('매개변수명')
console.log( new URL( location.href ).searchParams )
console.log( new URL( location.href ).searchParams.get( 'cno' ) )

// [2] 지정한 카테고리별 게시물 조회 요청
const findall = () => {
	
	// 1. 현재 페이지의 카테고리 구하기.
	const cno = new URL( location.href ).searchParams.get( 'cno' )
	// * 현재 경로상의 page 페이지 변수 찾기
	let page = new URL( location.href ).searchParams.get( 'page' )
	if( page == null ) page = 1; // 만약에 page가 없으면 1페이지 설정
	
	// 2. fetch option
	const option = { method : 'GET' }
	// 3. fetch
	fetch( `/tj2024b_web1/board?cno=${ cno }&page=${ page }` , option )
		.then( r => r.json() )
		.then( response => {
			console.log( response );
			
			// 4. 출력할 위치의 DOM 객체 반환
			const boardlist = document.querySelector('.boardlist > tbody')
			// 5. 출력할 내용을 담을 변수 선언
			let html = ``;
			
			let boardList = response.data;
			
			// 6. 서블릿으로 응답받은 자료들을 반복문 처리
			boardList.forEach((board) => {
				// 7. 게시물 하나씩 html 테이블의 행 으로 표현 하여 'html' 변수 누적 더하기.
				html += `<tr>
							<td> ${ board.bno } </td>
							<td> <a href="view.jsp?bno=${ board.bno }"> ${ board.btitle } </a> </td>
							<td> ${ board.mid } </td>
							<td> ${ board.bdate } </td>
							<td> ${ board.bview } </td>
						</tr>`
			}) // data.forEach((board) end
			// 8. 반복문 종료 표현된 html 출력
			boardlist.innerHTML = html;
			getPageBtn( response , cno ); // 페이징 버튼 생성 함수 실행 , 현재 페이지 번호 전달
		} ) // .then( data => { end
		.catch( e => { console.log(e); } )
} // findall end
findall(); // 페이지가 열리면 함수 실행

// [3] 페이지 버튼 생성 함수. 실행조건 : 게시물 출력후
const getPageBtn = (response , cno) => {
	page = parseInt(response.page); // parseInt() 정수로 타입 변환 함수.
	const pagebtnbox = document.querySelector('.pagebtnbox'); // 1. 어디에
	let html =``; // 2. 무엇을
		//  (1) 이전 버튼, 만약에 현재 페이지가 1이하 1로 고정, 아니면 -1
	html += `<li class="page-item">
				<a class="page-link" href="board.jsp?cno=${ cno }&page=${ page <= 1 ? 1 : page-1 }">이전</a>
		     </li>`
	// * 1부터 10까지 버튼 만들기. // 최대페이지 , 현재페이지의 시작버튼 번호 , 현재페이지의 끝버튼 번호
	// * startbtn 부터 endbtn 까지 버튼 만들기 
	// for( let index = 1 ; index <=10 ; index++ ){ 
	for( let index = response.startbtn; index <= response.endbtn; index++ ){
		// 만약에 현재 페이지가 index 와 같다면 부트스트램의 .active 클래스 부여하기.
		html += `<li class="page-item">
					<a class="page-link ${ page == index ? 'active' : '' }" href="board.jsp?cno=1&page=${ index }">
				       ${ index }
				    </a>
				 </li>`
	} // for end
		// (3) 다음 버튼, 만약에 현재페이지가 마지막페이지( 전체페이지수 ) 이면 마지막페이지 고정
	html +=`<li class="page-item">
				<a class="page-link" href="board.jsp?cno=${ cno }&page=${ page >= response.totalpage ? page : page+1  }">다음</a>
		    </li>` 
	pagebtnbox.innerHTML = html; // 3. 출력
	
}  // getPageBtn








