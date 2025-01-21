
// 1. 등록함수
const visitWrite = () => {
	// 1. html 으로 부터 input dom 가져오기
	// - document.querySelector(선택자) : 선택자 마크업을 객체로 반환함수. 
	let contentInput = document.querySelector('.contentInput')
	let ageInput = document.querySelector('.ageInput')
	
	// 2. 입력받은 값 가져오기
	// - .value : 마크업의 value 속성
	let content  = contentInput.value;
	let age = ageInput.value;
	
	// 3. 객체화
	// - { 속성명 : 값 , 속성명 : 값 }
	let dataObj = { 'content' : content , 'age' : age };
	
	// 4. fetch 통신
	let option = {
		method : 'POST' , // http method 방법선택
		headers : { 'Content-Type' : 'application/json' } , // http 요청 
		body : JSON.stringify(dataObj) // 본문에 보낼 자료를 문자열 타입으로 변환
		
	} // o end
	fetch(`/tj2024b_web1/day03/visit2` , option)
		.then( r => r.json() )
		.then( data => {
			// 5. 결과에 따른 화면 구현
			if( data == true ){ alert('등록성공'); visitFindAll(); }
			else{ alert( '등록실패' ); }
		} ) // then2 end
		.catch( e => { console.log(e); } )
	
	// 5. 결과에 따른 화면 구현
	
	
} // visitWrite end

// 2. 전체 조회 함수 , 실행조건 : 1.js열릴때 2.등록/수정/삭제 성공 했을때
const visitFindAll = () => {
	// 1. 어디에 , tbody
	let tbody = document.querySelector('tbody')
	
	// 2. 무엇을 , fetch 으로 받은 자료들을
	let html = '';
		// 2-1 fetch 이용한 서블릿에게 자료를 HTTP GET METHOD 요청
		const option = { method : 'GET' }
		// 2-2 fetch
		fetch(`/tj2024b_web1/day03/visit2`)
			.then( r => r.json() ) // 통신 응답 성공시 json 타입으로 변환
			.then( data => {
				// 방법1
				// for( let index = 0; index <= data.length -1; index++ ){ }
				// 방법2
				data.forEach( obj => {
					html += `<tr>
								<td> ${ obj.num } </td>
								<td> ${ obj.content } </td>
								<td> ${ obj.age } </td>
								<td>
									<button onclick="visitUpdate( ${ obj.num } )" > 수정 </button>
									<button onclick="visitDelete( ${ obj.num } )" > 삭제 </button>
								</td>
							</tr>`
				} ) // for end
				// 3. 출력 // .innerHTML : 지정한 마크업의 html 문자열 속성
				tbody.innerHTML = html;
			} ) // then2 end
			.catch( e => { console.log(e); } ) // 통신 응답 오류/실패 시
} // f end

visitFindAll(); // 1.js열릴때

// 3. 수정함수 , 누구를 수정할건지 = num[pk]
const visitUpdate = ( num ) => {
	// 1. 수정할 식별자(num)
	// 2. 수정할 내용 content/age
	let newContent = prompt('new Content : '); // prompt() 알람창에서 입력input 지원하는 함수.
	let newAge = prompt('new Age : ');
	// 3. 객체화 , { 수정할번호 , 수정할내용 , 수정할나이 }
	let dataObj = { num : num , content : newContent , age : newAge }
	// 4. fetch 이용한 서블릿에게 HTTP PUT METHOD 처리 요청 , BODY
	const option = {
		method : 'PUT' ,
		headers : { 'Content-Type' : 'application/json' } ,
		body : JSON.stringify( dataObj )
	} // o end
	fetch(`/tj2024b_web1/day03/visit2` , option)
		.then( r => r.json() )
		.then( data => {
			if( data == true ){ alert('수정성공'); visitFindAll(); }
			else{ alert('수정실패'); }
		} )
		.catch( e => { console.log( e ); } )
	
} // visitUpdate end


// 4. 삭제함수 , 누구를 삭제할건지 = num[pk]
const visitDelete = ( num ) => {
	// 1. 삭제할 식별자(num)
	// 2. fetch 이용한 서블릿에게 HTTP METHOD 처리요청
	const option = { method : 'DELETE' }
	fetch(`/tj2024b_web1/day03/visit2?num=${ num }` , option)
		.then( r => r.json() ) // 응답 결과를 json 타입으로 변환
		.then( data => {
			if( data == true ){ alert('삭제성공'); visitFindAll(); } // 만약에 응답이 true 이면 alert 안내후 출력함수 호출
			else{ alert('삭제실패'); }
		} )
		.catch( e => { console.log( e ); } )
} // visitDelete end




