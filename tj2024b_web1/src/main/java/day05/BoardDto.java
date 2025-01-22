package day05;

import java.security.Timestamp;

public class BoardDto {
	
	// 게시판 데이터를 위한 DTO(Data Transfer Object) 클래스
	private int bno;                // 게시글 번호
    private String btitle;          // 게시글 제목
    private String bcontent;        // 게시글 내용
    private String bwriter;         // 게시글 작성자
    private String bpwd;            // 게시글 비밀번호
    private int bview;              // 조회수
    private String bdate;        // 게시글 작성일
    
    
    // 생성자
    public BoardDto() { }
    public BoardDto(int bno, String btitle, String bcontent, String bwriter, String bpwd, int bview, String bdate) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bpwd = bpwd;
		this.bview = bview;
		this.bdate = bdate;
	}

	// Getter와 Setter
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public String getBpwd() {
		return bpwd;
	}

	public void setBpwd(String bpwd) {
		this.bpwd = bpwd;
	}

	public int getBview() {
		return bview;
	}

	public void setBview(int bview) {
		this.bview = bview;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	
	// toString
	@Override
	public String toString() {
		return "BoardDto [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bwriter=" + bwriter
				+ ", bpwd=" + bpwd + ", bview=" + bview + ", bdate=" + bdate + "]";
	}
	
} // BoardDTO end
