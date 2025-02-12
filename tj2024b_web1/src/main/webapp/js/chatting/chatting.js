console.log('chatting.js open');

// [1] WebSocket 클래스 이용하여 클라이언스 소켓 구현
// new WebSocket('서버소켓주소');
let clientSocket = new WebSocket('ws://localhost:8080/tj2024b_web1/chatsocket');
// [2] 접속/연결(상태유지)된 서버소켓에게 메시지 전송 , .send( "메시지" )
// clientSocket.send( '서버소켓 안녕!' ); // 오류 발생 , 즉] 

const onMsgSend = ( ) => {
	clientSocket.send( '서버소켓 안녕!' );
} // onMsgSend end

// [3] 서버 소켓이 클라이언트 소켓으로 부터 메시지를 보냈을때
clientSocket.onmessage = ( msgEvent ) => {
	console.log( '서버소켓으로 부터 메시지 왔다.' );
	console.log( evenmsgEventt );
	console.log( msgEvent.data );
} // clientSocket.onmessage end





