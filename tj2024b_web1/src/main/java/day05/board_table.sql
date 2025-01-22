# DDL 
# 1. 데이터베이스 준비  							: db 서버의 여러개 테이블들을 저장할 수 있는 공간 
DROP DATABASE IF EXISTS mydb0122;    		#[1] 만약에 동일한 DB명이 존재하면 데이터베이스 삭제 
CREATE DATABASE mydb0122;           		#[2] 지정한 DB명으로 데이터베이스 생성
USE mydb0122;                    			#[3] 지정한 DB를 활성화/사용

# 2. 테이블 생성 								: 행/열 로 이루어진 표/테이블
CREATE TABLE board (         				# 테이블명은 임의로 하되 저장할 데이터와 의미있는 이름으로 정하기.
    bno INT AUTO_INCREMENT,        			# 게시물번호
    btitle VARCHAR(100),              		# 게시물제목
    bcontent LONGTEXT,             			# 게시물내용
    bwriter VARCHAR(10),            		# 게시물작성자
    bpwd VARCHAR(10),            			# 게시물비밀번호
    bview INT DEFAULT 0,					# 게시물조회수
    bdate DATETIME DEFAULT NOW(),			# 게시물작성일
    PRIMARY KEY (bno)             			# 게시물번호를 pk로 설정
);

# DML : insert , select , update , delete 
#(1) 게시물 등록 
insert into board( btitle , bcontent , bwriter, bpwd ) values( '테스트제목1' , '테스트내용1' , '테스트작성자1' , '테스트비밀번호1' );
# DAO : String sql = "insert into board( btitle , bcontent , bwriter, bpwd ) values( ? , ? , ? , ? )";
#(2) 게시물 전체 조회
select * from board;
# DAO :String sql = "select * from board";
#(3) 게시물 개별 조회
select * from board where bno = 1;
# DAO :String sql = "select * from board where bno = ?";
#(4) 게시물 개별 수정
update board set btitle = '수정제목1' , bcontent = '수정내용1' where bno = 1;
# DAO :String sql = "update board set btitle = ? , bcontent = ? where bno = ?";
#(5) 게시물 개별 삭제 
delete from board where bno = 1;
# DAO :String sql = "delete from board where bno = ?";