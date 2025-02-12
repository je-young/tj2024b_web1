package web.controller.chatting;


import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;


// 서블릿 클래스가 아닌 웹소켓 클래스로 사용할 예정
// - @WebServlet HTTP VS - @ServerEndpoint WS
@ServerEndpoint("/chatsocket")
public class ServerSocket {
	
	// [1] 클라이언트 소켓이 서버소켓에 접속을 했을때 , onOpen
	@OnOpen
	public void onOpen( Session session ) {
		System.out.println("클라이언트가 서버에 접속 성공");
		System.out.println(session);
	} // onOpen end
	
	// [2] 클라이언트 소켓이 서버소켓으로 부터 메시지를 보냈을때 , onMessage
	public void opMessage( Session session , String message ) {
		System.out.println("클라이언트 소켓으로 부터 메시지 왔다.");
		System.out.println(message);
		
		// * 서버가 클라이언트 에게 메시지 전송
		try {
		session.getBasicRemote().sendText("클라이언트 소켓 안녕!");
		}catch (Exception e) { System.out.println( e ); }
	} // opMessage end
} // ServerSocket end





