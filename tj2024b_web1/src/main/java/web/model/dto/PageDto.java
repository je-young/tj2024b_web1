package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 룸복 준비
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageDto { // 페이징 처리된 데이터들의 이동객체

	private int totalCount; // 전체 자료 개수 ()
	private int page; // 현재 페이지
	private int totalpage; // 전체 페이지수
	private int startbtn; // 페이징 버튼 시작번호
	private int endbtn; // 페이징 버튼 끝 번호
	// * Object 타입으로 사용한 이유
	// - Object 타입은 자바의 최상위 클래스 이므로 모든 타입
	// - data 에는 List<boardDto> , List<replyDto>
	private Object data;
	
} // class end
