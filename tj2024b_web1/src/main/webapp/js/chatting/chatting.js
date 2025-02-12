console.log('chatting.js open');

// [1] WebSocket 클래스 이용하여 클라이언스 소켓 구현
// new WebSocket('서버소켓주소');
let clientSocket = new WebSocket('ws://localhost:8080/tj2024b_web1/chatsocket');
// [2] 접속/연결(상태유지)된 서버소켓에게 메시지 전송 , .send( "메시지" )
// clientSocket.send( '서버소켓 안녕!' ); // 오류 발생 , 즉] 
// [전송] 버튼을 클릭했을때 실행 할 함수 정의
const onMsgSend = ( ) => {
	// clientSocket.send( '서버 소켓 안녕!' );
	
	// (1) 입력받은 값 가져오기 
	const msginput = document.querySelector('.msginput')
	const msg = msginput.value;
	// (2) 서버소켓에게 값 보내기 
	clientSocket.send( msg );
	
} // onMsgSend end

// [3] 서버 소켓이 클라이언트 소켓으로 부터 메시지를 보냈을때
clientSocket.onmessage = ( msgEvent ) => {
	console.log( clientSocket );
	console.log('서버소켓으로 부터 메시지 왔다.')
	console.log( msgEvent );
	console.log( msgEvent.data );

	// (1) 받은 메시지를 html에 출력하기 
	// 1. 어디에 
	const msgbox = document.querySelector('.msgbox')
	// 2. 무엇을 
	let html = `<div> ${ msgEvent.data } </div>`;
	// 3. 출력 , = 대입 (기존값사라짐) , += (기존값연결) 사용한 이유 : 앞전 메시지 와 연결하기 위해
	msgbox.innerHTML += html;

	
} // clientSocket.onmessage end





