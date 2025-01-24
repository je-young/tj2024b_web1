package day07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;


// 서블릿 X
public class Example2 {
	public static void main(String[] args) {
		
		// 1. ArrayList 인스턴스 생성
			// 1. 인스턴스 : new ArrayList<String>(); 	// 클래스
			// 2. 변수명 : list ,						// 아무거나
			// 3. 변수의타입 : List<String> list		// 인터페이스타입
		List<String> list = new ArrayList<String>();	// ArrayList 타입은 List 인터페이스를 구현했으므로
		
		// 2. List 인터페이스 메소드들
			// (1) .add( 자료 ) : 리스트 내 지정한 자료를 마지막 요소 추가.
		list.add("유재석"); System.out.println(list); // [유재석]
		list.add("강호동"); System.out.println(list); // [유재석 , 강호동]
		list.add("신동엽"); System.out.println(list); // [유재석 , 강호동 , 신동엽]
		list.add("하하"); System.out.println(list); // [유재석 , 강호동 , 신동엽 , 하하]
		list.add( 2 , "김희철" ); // 3번째 위치에 '김희철' 자료를 추가
		System.out.println(list); // [유재석, 강호동, 김희철, 신동엽, 하하]
		
			// (2) .set( 인덱스 , 자료 ) : 리스트 내 지정한 인덱스에 지정한 자료의 요소 수정/변경
		list.set(2, "서장훈"); // 3번째 위치에 '서장훈' 자료를 변경
		System.out.println(list); // [유재석, 강호동, 서장훈, 하하]
		
			// (3) .get( 인텍스 ) : 리스트내 지정한 인덱스의 요소 값 반환
		System.out.println(list.get(2)); // 3번째 위치한 요소 값 반환 // '서장훈'
		String str1 = list.get(0); // 1번째 위치한 요소 값 반환 // 유재석
		System.out.println(str1);
		
			// (4) .size() : 리스트내 요소 전체 개수 반환
		System.out.println(list.size()); // 5
		
			// (5) .contains( 자료 ) : 리스트내 지정한 자료 존재 여부 반환 , true/false
		System.out.println(list.contains("서장훈")); // true
		boolean result1 = list.contains("박명수");
		System.out.println(result1); // false
		
			// (6) .indexOf( 자료 ) : 리스트내 지정한 자료의 인덱스 반환, 없으면 -1 있으면 인덱스 수 반환
		System.out.println(list.indexOf("서장훈")); // 2 : 2번인덱스(3번째)위치
		int result2 = list.indexOf("박명수");
		System.out.println(result2); // -1 : 존재하지 않으면 -1
		
			// (7) .remove( 인덱스/자료 ) : 리스트내 지정한 인덱스/자료 의 요소 삭제
		list.remove( 0 ); // 0번 인덱스 요소 삭제
		System.out.println(list); // [강호동, 서장훈, 신동엽, 하하]
		
			// (8) .clear() : 리스트내 모든 요소 삭제
			// (9) .isEmpty() : 리스트내 요소가 비어 있으면 true 1이상 존재하면 false 반환
		System.out.println(list.isEmpty()); // false
		
			// (10) 리스트내 요소들을 순회(하나씩꺼내기) 하는 방법
				// 1. 일반 for 문
				for (int index = 0; index <= list.size()-1; index++) {
					System.out.println(list.get(index));
				} // f end
				// 2. 향상된 for 문 , for( 타입 반복변수명 : 리스트명 ) {  }
				for (String str : list) {
					System.out.println(str);
				} // f end
				// 3. forEach((반복변수명) -> { 실행문 }) , JS : ()=>{ } , JAVA : ( ) -> { }
				list.forEach((str) -> { System.out.println(str); });
		
		// 3. 클래스 들
			// 3가지 주요 클래스들은 사용법(메소드) 동일하다.
		ArrayList<String> list1 = new ArrayList<String>();
		Vector<String> list2 = new Vector<String>();			// + 멀티스레드 에서 주로 사용됨.
		LinkedList<String> lest3 = new LinkedList<String>();	// + 요소가 중간 삽입/삭제 용이하다.
			// 여러 클래스들의 인스턴스를 다루기 위해서는 주로 인터페이스 타입 사용한다.
		List<String> list4 = new ArrayList<String>();
		List<String> list5 = new Vector<String>();
		List<String> list6 = new LinkedList<String>();
		
	} // main end
	
	
	
	
	
	
	
} // class end












