
// 1. 등록함수
const waitingWrite = () => {
	// 1. html 으로 부터 input dom 가져오기
	// - document.querySelector(선택자) : 선택자 마크업을 객체로 반환함수. 
	let phoneInput = document.querySelector('.phoneInput')
	let countInput = document.querySelector('.countInput')
	
	// 2. 입력받은 값 가져오기
	// - .value : 마크업의 value 속성
	let phone  = phoneInput.value;
	let count = countInput.value;
	
	// 3. 객체화
	// - { 속성명 : 값 , 속성명 : 값 }
	let dataObj = { 'phone' : phone , 'count' : count };
	
	// 4. fetch 통신
	let option = {
		method : 'POST' , // http method 방법선택
		headers : { 'Content-Type' : 'application/json' } , // http 요청 
		body : JSON.stringify(dataObj) // 본문에 보낼 자료를 문자열 타입으로 변환
		
	} // o end
	fetch(`/tj2024b_web1/day03/waiting2` , option)
		.then( r => r.json() )
		.then( data => {
			// 5. 결과에 따른 화면 구현
			if( data == true ){ alert('등록성공'); waitingFindAll(); }
			else{ alert( '등록실패' ); }
		} ) // then2 end
		.catch( e => { console.log(e); } )
	
	
} // waitingWrite end

// 2. 전체 조회 함수 , 실행조건 : 1.js열릴때 2.등록/수정/삭제 성공 했을때
const waitingFindAll = () => {
	// 1. 어디에 , tbody
	let tbody = document.querySelector('tbody')
	
	// 2. 무엇을 , fetch 으로 받은 자료들을
	let html = '';
		// 2-1 fetch 이용한 서블릿에게 자료를 HTTP GET METHOD 요청
		const option = { method : 'GET' }
		// 2-2 fetch
		fetch(`/tj2024b_web1/day03/waiting2`)
			.then( r => r.json() ) // 통신 응답 성공시 json 타입으로 변환
			.then( data => {
				// 방법1
				// for( let index = 0; index <= data.length -1; index++ ){ }
				// 방법2
				data.forEach( obj => {
					html += `<tr>
								<td> ${ obj.num } </td>
								<td> ${ obj.phone } </td>
								<td> ${ obj.count } </td>
								<td>
									<button onclick="waitingUpdate( ${ obj.num } )" > 수정 </button>
									<button onclick="waitingDelete( ${ obj.num } )" > 삭제 </button>
								</td>
							</tr>`
				} ) // for end
				// 3. 출력 // .innerHTML : 지정한 마크업의 html 문자열 속성
				tbody.innerHTML = html;
			} ) // then2 end
			.catch( e => { console.log(e); } ) // 통신 응답 오류/실패 시
} // f end

waitingFindAll(); // 1.js열릴때

// 3. 수정함수 , 누구를 수정할건지 = num[pk]
const waitingUpdate = ( num ) => {
	// 1. 수정할 식별자(num)
	// 2. 수정할 내용 content/age
	let newPhone = prompt('new Phone : '); // prompt() 알람창에서 입력input 지원하는 함수.
	let newCount = prompt('new Count : ');
	// 3. 객체화 , { 수정할번호 , 수정할내용 , 수정할나이 }
	let dataObj = { num : num , phone : newPhone , count : newCount }
	// 4. fetch 이용한 서블릿에게 HTTP PUT METHOD 처리 요청 , BODY
	const option = {
		method : 'PUT' ,
		headers : { 'Content-Type' : 'application/json' } ,
		body : JSON.stringify( dataObj )
	} // o end
	fetch(`/tj2024b_web1/day03/waiting2` , option)
		.then( r => r.json() )
		.then( data => {
			if( data == true ){ alert('수정성공'); waitingFindAll(); }
			else{ alert('수정실패'); }
		} )
		.catch( e => { console.log( e ); } )
	
} // waitingUpdate end


// 4. 삭제함수 , 누구를 삭제할건지 = num[pk]
const waitingDelete = ( num ) => {
	// 1. 삭제할 식별자(num)
	// 2. fetch 이용한 서블릿에게 HTTP METHOD 처리요청
	const option = { method : 'DELETE' }
	fetch(`/tj2024b_web1/day03/waiting2?num=${ num }` , option)
		.then( r => r.json() ) // 응답 결과를 json 타입으로 변환
		.then( data => {
			if( data == true ){ alert('삭제성공'); waitingFindAll(); } // 만약에 응답이 true 이면 alert 안내후 출력함수 호출
			else{ alert('삭제실패'); }
		} )
		.catch( e => { console.log( e ); } )
} // waitingDelete end



