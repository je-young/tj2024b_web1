package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class MemberDto {
	private int mno;			// 회원번호
	private String mid;			// 아이디
	private String mpwd;		// 비밀번호
	private String mname;		// 이름
	private String mphone;		// 연락처
	private String mdate;		// 가입일
	private	String mimg;		// 프로필
	
	// @NoArgsConstructor
	// public MemberDto()
	
	// @AllArgsConstructor
//	public MemberDto(int mno, String mid, String mpwd, String mname, String mphone, String mdate) {
//		super();
//		this.mno = mno;
//		this.mid = mid;
//		this.mpwd = mpwd;
//		this.mname = mname;
//		this.mphone = mphone;
//		this.mdate = mdate;
//	}
	
	// @Getter
//	public int getMno() {
//		return mno;
//	}
	
	// @Setter
//	public void setMno(int mno) {
//		this.mno = mno;
//	}
	
	// 
//	public String toString() {
//		return "MemberDto [mno=" + mno + ", mid=" + mid + ", mpwd=" + mpwd + ", mname=" + mname + ", mphone=" + mphone
//				+ ", mdate=" + mdate + "]";
//	}	

} // class end
