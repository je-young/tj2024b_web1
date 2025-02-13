/* ================= 클릭한 위치에 마커 표기하기 =====================  */
// https://apis.map.kakao.com/web/sample/addMapClickEventWithMarker/
/*
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
		center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level: 3 // 지도의 확대 레벨
	};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도를 클릭한 위치에 표출할 마커입니다
var marker = new kakao.maps.Marker({ 
	// 지도 중심좌표에 마커를 생성합니다 
	position: map.getCenter() 
}); 
// 지도에 마커를 표시합니다
marker.setMap(map);

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
	// 클릭한 위도, 경도 정보를 가져옵니다 
	var latlng = mouseEvent.latLng; 
	// 마커 위치를 클릭한 위치로 옮깁니다
	marker.setPosition(latlng);
  	
	var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
	message += '경도는 ' + latlng.getLng() + ' 입니다';
	
	console.log( message ); // 학원의 위도 : 37.4910841087311 , 경도 : 126.72057774665798
});
*/

/* ================= 마커에 클릭 이벤트 등록하기 ================= */
// https://apis.map.kakao.com/web/sample/addMarkerClickEvent/
/*
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
		center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level: 3 // 지도의 확대 레벨
	};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
// 마커를 표시할 위치입니다 
var position =  new kakao.maps.LatLng(33.450701, 126.570667);

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
  position: position,
  clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
});

// 아래 코드는 위의 마커를 생성하는 코드에서 clickable: true 와 같이
// 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
// marker.setClickable(true);

// 마커를 지도에 표시합니다.
marker.setMap(map);

// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
	content : iwContent,
	removable : iwRemoveable
});

// *********** 마커에 클릭이벤트를 등록합니다 ***************
kakao.maps.event.addListener(marker, 'click', function() {
	  // 마커 위에 인포윈도우를 표시합니다
	  // infowindow.open(map, marker);  
	  alert('마커를 클릭했군요.');
});
*/

// [3] ============== 여러개 마커 표시하기 ============== //
// https://apis.map.kakao.com/web/sample/multipleMarkerImage/
/*
// (1) HTML 의 div를 가져오기 
var mapContainer = document.querySelector('#map'), // 지도를 표시할 div  
	mapOption = { // (2) 처음에 지도가 열렸을때 중심 좌표 와 확대레벨 설정 
		center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level: 3 // 지도의 확대  , 0(최대확대) ~ 14(최대축소)
	};

// (3) 설정된 지도 정보를 map 변수에 저장 1:지도를 표시할 div , 2. 중심 좌표/지도확대축소
var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다


// (4) 여러개 마커를 표기할 자료 준비 
// 마커를 표시할 위치와 title 객체 배열입니다 , 활용 : fetch 이용하여 마커에 표시할 위도경도
var positions = [
	{title: '카카오', latlng: new kakao.maps.LatLng(33.450705, 126.570677)
	},
	{ title: '생태연못', latlng: new kakao.maps.LatLng(33.450936, 126.569477)
	},
	{  title: '텃밭',  latlng: new kakao.maps.LatLng(33.450879, 126.569940)
	},
	{ title: '근린공원',latlng: new kakao.maps.LatLng(33.451393, 126.570738)
	},
	{title: '더조은 컴퓨터학원 부평점', //  학원의 위도 : 37.4910841087311 , 경도 : 126.72057774665798
	latlng : new kakao.maps.LatLng( 37.4910841087311 , 126.72057774665798  )
	}
];

// (5)마커 이미지의 이미지 주소입니다. ( 배포된 이미지 HTTP 경로 )
// 배포전(개발자) /  C:\Users\tj-bu-702-teacher\git\tj2024b_web1\tj2024b_web1\src\main\webapp\img
// 배포후(웹서버 로컬) /  C:\Users\tj-bu-702-teacher\Desktop\tj2024b_web1\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\tj2024b_web1\img
// 배포후(웹서버 HTTP) /  http://localhost:8080/tj2024b_web1/img
var imageSrc = "http://localhost:8080/tj2024b_web1/img/logo.jpg"; 
    
// (6) 자료 정보들을 반복문 이용하여 마커를 하나씩 만들기 
for (var i = 0; i < positions.length; i ++) {
    
	// (6-1) 마커 이미지의 이미지 크기 입니다
	var imageSize = new kakao.maps.Size(20, 20); 
    
	// (6-2) 마커 이미지를 생성합니다    
	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
    
	// (6-3) 하나씩 마커를 생성합니다
	var marker = new kakao.maps.Marker({
		map: map, // 마커를 표시할 지도
		position: positions[i].latlng, // 마커를 표시할 위치
		title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		image : markerImage // 마커 이미지 
	});
	
	// (6-4) 마커에 이벤트 등록 
	// *********** 마커에 클릭이벤트를 등록합니다 ***************
	kakao.maps.event.addListener(marker, 'click', function(  ) {
		  // 마커 위에 인포윈도우를 표시합니다
		  // infowindow.open(map, marker);  
		  alert( `클릭했군요.`);
	});
	
} // f end 
*/

// ============= [4] 마커 클러스터러 사용하기 ==============//
//  https://apis.map.kakao.com/web/sample/basicClusterer/
// + jQuery(JS라이브러리) +&libraries=clusterer

// // (1) 카카오지도 중심좌표( 지도 시작 좌표 ) 와 확대레벨 설정 
var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
	center: new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표 // 활용 : Geolocation API = 접속된 유저의 좌표
	level: 10 // 지도의 확대 레벨 
});

// (2) 마커 클러스터( 여러개 마커들을 하나의 도형 )
var clusterer = new kakao.maps.MarkerClusterer({
	map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
	averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
	minLevel: 10 // 클러스터 할 최소 지도 레벨 
});

// (3) $(jquery문법)  = http 통신 함수들 , $.get( ) vs fetch() vs axios() vs ajax() 등등 
// $.get( '통신할주소' , function(data){ } )
// fetch( '통신할주소' , {} ).then().then()
// 1. 서블릿주소 2. api주소
// $.get( "api주소", function(data) {
// $.get( "https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=35&serviceKey=nwPZ%2F9Z3sVtcxGNXxOZfOXwnivybRXYmyoIDyvU%2BVDssxywHNMU2tA55Xa8zvHWK0bninVkiuZAA4550BDqIbQ%3D%3D", function(data) {
// $( 리스트 ).map( function( 인덱스,반복변수명 ){ return }
// 리스트명.forEach( ( 인덱스 , 반복변수명 ) => { })

// 카카오지도 샘플 : { positions : [ { "lat" : "" , "lng" : "" } , { "lat" : "" , "lng" : "" }  ]  }
// 공공데이터 : { data : [ { 위도 : "" , 경도 : "" } , { 위도 : "" , 경도 : "" } , { 위도 : "" , 경도 : "" } ] }
fetch("https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=35&serviceKey=nwPZ%2F9Z3sVtcxGNXxOZfOXwnivybRXYmyoIDyvU%2BVDssxywHNMU2tA55Xa8zvHWK0bninVkiuZAA4550BDqIbQ%3D%3D")
	.then(response => response.json())
	.then(data => { console.log(data); // 통신된 response 값
		// for vs .forEach( ( 반복변수명 ) => { } )  vs  .map( (반복변수명)=>{ return } )
		let markers = data.data.map((position) => {
			// 1개 마커 생성 후 변수에 저장 
			let marker = new kakao.maps.Marker({ position: new kakao.maps.LatLng(position.위도, position.경도) });
			
			// 위 변수의 생성된 마커의 클릭 이벤트 등록
			kakao.maps.event.addListener(marker, 'click', function() {
				// alert( `${ position.약국명 } 클릭 했군요.` );
										
				// 클릭한 마커 약국의 정보를 특정한(사이드바) html 에 대입하기.
				document.querySelector('.약국명').innerHTML = position.약국명;
				document.querySelector('.전화번호').innerHTML = position.전화번호;
				document.querySelector('.주소').innerHTML = position.소재지도로명주소;
				
				// 사이드바 버튼를 (JS 클릭이벤트) 강제 클릭
				document.querySelector('.사이드바').click(); // .click; : DOM 객체의 클릭 이벤트 실행
			}); // marker click event end
			
			// 위 변수의 생성된 마커 이벤트 등록후 반환/리턴
			return marker;

		}); // map end
		
		// 클러스터에 (마커배열)마커들을 추가합니다.
		clusterer.addMarkers(markers);
	}); // then end

/*
let markers = []
for( let index = 0 ; index <= data.data.length-1 ; index++ ){
   let position = data.data[index];
   // 마커 1개씩 생성 
   let marker = new kakao.maps.Marker({position : new kakao.maps.LatLng(position.위도, position.경도)});
   // 마커배열에 생성한 마커 추가.
   markers.push(  marker )
}
*/

/*
let markers = []
data.data.forEach( (position) => {
   let marker = new kakao.maps.Marker({position : new kakao.maps.LatLng(position.위도, position.경도)});
   markers.push(  marker )
})
*/

/*
let markers = data.data.map( ( position ) => {
   return new kakao.maps.Marker({position : new kakao.maps.LatLng(position.위도, position.경도)});
})
*/












