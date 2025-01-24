package day07;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Example3 {
	public static void main(String[] args) {
		
		// [1] HashSet 인스턴스 생성
		Set<String> set = new HashSet<>();
		// [2] set 인터페이스 주요 메소드들
			// (1) .add( 자료 ) : set 내 지정한 자료를 추가한다.
		set.add("유재석");	System.out.println(set); // [유재석]
		set.add("강호동");	System.out.println(set); // [유재석, 강호동]
		set.add("신동엽");	System.out.println(set); // [유재석, 강호동, 신동엽]
			// (2) .size() : set 내 전체 요소 개수 반환
		System.out.println(set.size()); // 3
			// (3) .remove( 자료 ) : set 내 지정한 자료가 존재하면 삭제
		set.remove("강호동");	System.out.println(set); // [유재석, 신동엽]
			// (4) .contains( 자료 ) : set 내 지정한 자료가 존재하는지 여부 반환
		System.out.println(set.contains("유재석")); // true
			// (5) 순회 , JDBC(DAO) : ResultSet
		Iterator<String> rs =  set.iterator(); // iterate : 반복하다 뜻 , iterator : 반복자 뜻
		while (rs.hasNext()) {
			System.out.println(rs.next());
		} // w end
			// 1. 일반 for 문 , 사용불가능 , 왜? 인덱스가 존재하지 않으므로 순회 불가능.
			// 2. 향상된 for 문 , 향상된 for 문 문법 자체가 Iterator 기반으로 만들었기 때문에.
		for (String str : set) { System.out.println(str); }
			// 3. forEach() 함수 , forEach 함수도 Iterator 기반으로 만들었기 때문에
		set.forEach((str) -> { System.out.println(str); });
		
			// (6) set 컬렉션은 중복을 허용하지 않는다.
		set.add("유재석");	System.out.println(set); // [유재석, 신동엽]
		set.add("유재석");	System.out.println(set); // [유재석, 신동엽]
		
		// [3] TreeSet 인스턴스 생성
		TreeSet<String> set2 = new TreeSet<String>();
		set2.add("유재석");
		set2.add("강호동");
		set2.add("서장훈");
		System.out.println(set2); // 자동으로 오름차순 정렬 : [강호동, 서장훈, 유재석]
		System.out.println(set2.descendingSet()); // 내림차순 정렬 : [유재석, 서장훈, 강호동]
		
	} // main end

} // class end










