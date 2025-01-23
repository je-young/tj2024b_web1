// [1] 게시물 등록
const boardWrite = () => {
	// 1. input DOM 가져오기
	let writerInput = document.querySelector('.writerInput')
	let pwdInput = document.querySelector('.pwdInput')
	let titleInput = document.querySelector('.titleInput')
	let contentInput = document.querySelector('.contentInput')
	
	// 2. DOM 에서 value 가져오기
	let bwriter = writerInput.value;
	let bpwd = pwdInput.value;
	let btitle = titleInput.value;
	let bcontent = contentInput.value;
	
	// 3. 객체화
	let dataObj = {
		bwriter : bwriter , bpwd : bpwd ,
		btitle : btitle , bcontent : bcontent
	}
	
	// 4. fetch 옵션
	const option = {
		method : 'POST' ,
		headers : { 'Content-Type' : 'application/json' } ,
		body : JSON.stringify( dataObj )
	}
	
	// 5. fetch
	fetch('/tj2024b_web1/day05/board' , option )
	.then( response => response.json() )
	.then( data => { 
		if( data == true ){alert('글쓰기성공'); location.herf="board.jap";}
		else{ alert('글쓰기실패');}
	 } )
	 .catch( error => { alert('시스템오류 : 관리자에게 문의'); console.log(error) } )
	
} // boardWrite end


