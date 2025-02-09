// [*] 썸머노트 실행 
$(document).ready(function() {
  $('#summernote').summernote({
        placeholder : '게시물 내용 입력해주세요.',
        height : 500,
        lang : 'ko-KR'
  }); // send 
}); // f end 

// [1] 글쓰기 요청 메소드
const onWrite = () => {
	// 첨부파일 있다 = form 전송 vs 첨부파일 없다 = form 또는 JSON
	// 1. 입력받은 값들을 가져오기 위해서 DOM 객체 호출
	const cnoselect = document.querySelector('.cnoselect');
	const titleinput = document.querySelector('.titleinput');
	const contentinput = document.querySelector('.contentinput');
	
	// 2. DOM 객체 에서의 입력받은 (value속성)값 호출
	const cno = cnoselect.value;
	const btitle = titleinput.value;
	const bcontent = contentinput.value;
	console.log( bcontent );
	
	// 3. 값들을 묶어주는 객체( json ) 만들기, json의 속성명은 rest명세서 맞게 정의
	let obj = { cno : cno , btitle : btitle , bcontent : bcontent }
	
	// 4. fetch option
	const option = {
		method : 'POST' , 
		headers : { 'Content-Type' : 'application/json' } ,
		body : JSON.stringify( obj ) // - JSON 형식(모양)의 문자열 타입으로 변환
	} // option end
	
	// 5.
	fetch( '/tj2024_web1/boaed' , option )
		.then( r => r.json() )
		.then( data => {
			console.log( data );
			if( data == true ){
				alert('글쓰기 성공');
			}else{
				alert('글쓰기 실패');
			} // if else end
		} ) // .then( data => { end
		.catch( e => { console.log(e); } )
	
} // onWrite end








