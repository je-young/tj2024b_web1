# 프로젝트 : jspweb DML 과 샘플 INSERT
# 1. DB 구성한다.
drop database if exists jspweb;                
create database jspweb;                           
use jspweb;                                                  
# 2. 테이블 생성  , PK 테이블 먼저 생성 한다. 적절하게 타입 과 제약조건을 고려한다.

# [1] 회원테이블 생성 
create table member(
    mno int unsigned auto_increment , 
    mid varchar(30) not null unique ,
    mpwd varchar(30) not null ,
    mname varchar(20) not null ,
    mphone varchar(13) not null unique , 
    mdate datetime default now() , 
    mimg varchar(255) default 'default.jpg',
    constraint primary key( mno )
); # table end

# [*] 회원테이블 샘플 레코드 삽입
insert into member ( mid , mpwd , mname , mphone ) values( 'qwe123' , 'a123456' , '유재석' ,  '010-3333-3333' );
insert into member ( mid , mpwd , mname , mphone ) values( 'asd123' , 'b123456' , '강호동' ,  '010-4444-4444' );
insert into member ( mid , mpwd , mname , mphone ) values( 'zxc123' , 'c123456' , '신동엽' ,  '010-5555-5555' );
insert into member ( mid , mpwd , mname , mphone ) values( 'vbn123' , 'd123456' , '서장훈' ,  '010-6666-6666' );
insert into member ( mid , mpwd , mname , mphone ) values( 'rty123' , 'e123456' , '하하'   ,  '010-7777-7777' );
select * from member;

# [2] 포인트 테이블 생성 
create table pointlog(
	pono int unsigned auto_increment , 
    pocomment varchar(255) not null , 
    pocount int ,
    podate datetime default now() ,
    mno int unsigned , 
    constraint primary key( pono ),
    constraint foreign key( mno ) references member( mno ) on update cascade on delete cascade 
);

# [*] 회원테이블 샘플 레코드 삽입
insert into pointlog ( pocomment , pocount , mno ) values( '회원가입축하' , 100 , 1  );
insert into pointlog ( pocomment , pocount , mno ) values( '로그인' , 1 , 1  );
insert into pointlog ( pocomment , pocount , mno ) values( '아이템구매' , -50 , 1  );
insert into pointlog ( pocomment , pocount , mno ) values( '포인트구매' , 2000 , 1  );
insert into pointlog ( pocomment , pocount , mno ) values( '아이템구매' , 1000 , 1  );

select * from pointlog;

-- 카테고리 테이블[ 카테고리번호 , 카테고리 이름 ( 공지사항 , 커뮤니티 , QnA , 노하우 등등 ) ]
create table category(
    cno     int unsigned auto_increment, 
    cname   varchar(100) not null ,
    constraint primary key( cno) 
);

insert into category (cname) values ('뉴스');
insert into category (cname) values ('이벤트');
insert into category (cname) values ('FAQ');
insert into category (cname) values ('튜토리얼');
insert into category (cname) values ('사용자 리뷰');

select * from category;

-- 게시물 테이블 [ 번호 , 제목 , 내용 , 첨부파일 , 작성일 , 조회수 , 좋아요수 , 싫어요수 , 작성자 , 카테고리번호 ]
create table board(
   bno         int unsigned auto_increment, 
    btitle       varchar(1000) not null ,
    bcontent   longtext   not null ,
    bdate       datetime default now() ,
    bview      int default 0 ,
    mno         int unsigned , -- 회원번호 fk
    cno         int unsigned , -- 카테고리번호 fk
    constraint primary key( bno ) ,
    constraint foreign key ( mno ) references member( mno ) on update cascade on delete cascade ,     -- [회원]pk가 삭제되면 게시물fk는 null 변경
   constraint foreign key ( cno ) references category( cno ) on update cascade on delete cascade    -- [카테고리]pk가 삭제되면 게시물 같이 삭제 
);



-- 뉴스 카테고리 게시물 20개 삽입
insert into board (btitle, bcontent, mno, cno) values 
('2025년 여름 시즌 인기 상품 발표', '2025년 여름 시즌에 가장 인기 있는 상품들이 발표되었습니다. 새로운 아이템들을 만나보세요.', 1, 1),
('새로운 할인 행사 시작!', '이번 주말부터 시작되는 할인 행사에서 다양한 제품을 할인된 가격으로 구매할 수 있습니다.', 2, 1),
('AI 기술 혁신, 최신 연구 결과 발표', 'AI 기술의 최신 연구 결과와 그 응용 가능성에 대해 알아보세요.', 3, 1),
('온라인 쇼핑몰 보안 강화 정책', '온라인 쇼핑몰에서 발생할 수 있는 보안 사고를 방지하기 위한 강화된 보안 정책이 발표되었습니다.', 4, 1),
('2025년 신제품 출시 예고', '2025년에 출시될 신제품에 대한 정보를 미리 공개합니다.', 5, 1);

-- 이벤트 카테고리 게시물 20개 삽입
insert into board (btitle, bcontent, mno, cno) values 
('회원 가입 이벤트!', '회원 가입 시 포인트 500점을 지급하는 이벤트가 시작되었습니다. 지금 가입하고 혜택을 누리세요!', 1, 2),
('친구 초대 이벤트', '친구를 초대하고, 친구가 첫 구매 시 보너스 포인트를 지급하는 이벤트를 진행 중입니다.', 2, 2),
('구매 후 리뷰 작성 이벤트', '구매 후 리뷰를 작성하면 포인트를 추가로 지급하는 이벤트에 참여하세요.', 3, 2),
('기프트 카드 추첨 이벤트', '기프트 카드 추첨 이벤트에 참여하고, 행운을 잡으세요!', 4, 2),
('여름 세일 이벤트', '여름 시즌 제품을 최대 50% 할인하는 세일 이벤트를 놓치지 마세요!', 5, 2);

-- FAQ 카테고리 게시물 20개 삽입
insert into board (btitle, bcontent, mno, cno) values 
('회원 가입 방법 안내', '회원 가입 절차에 대한 자세한 설명을 드립니다.', 1, 3),
('비밀번호 변경 방법', '비밀번호를 안전하게 변경하는 방법에 대해 설명드립니다.', 2, 3),
('배송 조회 방법', '구매한 상품의 배송 현황을 확인하는 방법에 대해 안내드립니다.', 3, 3),
('포인트 사용 방법', '적립된 포인트를 사용하는 방법을 알아봅니다.', 4, 3),
('상품 반품 절차', '상품 반품 및 환불 절차에 대한 정보를 제공합니다.', 5, 3);

-- 튜토리얼 카테고리 게시물 20개 삽입
insert into board (btitle, bcontent, mno, cno) values 
('HTML5 기초 튜토리얼', 'HTML5의 기본적인 문법과 요소들을 소개하는 튜토리얼입니다.', 1, 4),
('CSS 레이아웃 기초', 'CSS를 활용하여 다양한 레이아웃을 구성하는 방법을 배워봅시다.', 2, 4),
('자바스크립트 함수 사용법', '자바스크립트에서 함수를 정의하고 활용하는 방법을 설명합니다.', 3, 4),
('React 기본 사용법', 'React를 사용하여 동적인 웹 애플리케이션을 만드는 방법을 소개합니다.', 4, 4),
('파이썬 기초 문법', '파이썬 프로그래밍의 기초 문법을 배워보세요.', 5, 4);

-- 사용자 리뷰 카테고리 게시물 20개 삽입
insert into board (btitle, bcontent, mno, cno) values 
('최고의 스마트폰 사용 후기', '최근에 구매한 스마트폰에 대한 리뷰를 공유합니다. 성능과 디자인에 대해 설명합니다.', 1, 5),
('이전에 사용했던 노트북 후기', '내가 사용해본 노트북에 대한 후기를 남겨봅니다. 속도와 디자인을 비교해봤습니다.', 2, 5),
('온라인 쇼핑몰 이용 후기', '최근에 이용한 온라인 쇼핑몰에 대한 후기를 남깁니다. 배송과 서비스 품질을 평가합니다.', 3, 5),
('가성비 좋은 이어폰 추천', '가성비 좋은 이어폰을 찾고 있다면, 이 제품을 추천드립니다. 가격 대비 성능이 뛰어나요.', 4, 5),
('전문적인 카메라 리뷰', '프로페셔널 카메라를 사용해본 후기를 남깁니다. 사용감과 화질을 평가해보았습니다.', 5, 5);

select * from board;