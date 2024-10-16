let map;
let markers = []; // 마커 저장 배열
let savedComments = [];
let commentLocations = []; // 각 코멘트와 관련된 위치 저장 배열
var initialPosition;
let geocoder;

$(document).ready(function (){
    var params = new URLSearchParams(window.location.search);

    // 'city'라는 파라미터 값 추출
    var city = params.get('city');
    if(city === 'tokyo'){
        initialPosition ={lat: 35.68160, lng: 139.766687}
    } else if (city === 'osaka'){
        initialPosition = {lat: 34.70258, lng: 135.49614}
    } else if (city === 'hukuoka'){
        initialPosition = {lat: 33.58993, lng: 130.42077}
    } else if (city === 'hokkaido'){
        initialPosition = {lat: 43.06879, lng: 141.35115}
    } else if (city === 'okinawa'){
        initialPosition = {lat: 26.22323, lng : 127.69838}
    }
});


// 지도 생성 및 초기 위치 설정
function initMap() {


    // 지도 표시
    map = new google.maps.Map(document.getElementById("map"), {
        center: initialPosition,
        zoom: 10,
    });

    // Geocoder 객체 생성
    geocoder = new google.maps.Geocoder();

    // 검색 박스 (자동 완성)
    const input = document.getElementById("search-box");
    const searchBox = new google.maps.places.Autocomplete(input);

    // 장소가 선택될 때 이벤트
    searchBox.addListener("place_changed", function () {
        const place = searchBox.getPlace();
        if (!place.geometry || !place.geometry.location) return;

        const location = place.geometry.location;
        const address = place.formatted_address; // 주소 정보 가져오기

        // 지도 중심을 검색한 위치로 이동
        map.setCenter(location);
        map.setZoom(15);

        // 마커 추가
        addMarker(location);
        displayLatlng(location, address);
    });

    // 클릭 이벤트로 마커 추가
    map.addListener("click", function (event) {
        const clickedLocation = event.latLng;

        /*
        // 클릭 이벤트로 마커 추가
        map.addListener("click", function (event) {
            const clickedLocation = event.latLng;
            addMarker(clickedLocation);
            displayLatlng(clickedLocation, address);
         */
        geocodeLatLng(clickedLocation);
        });
}

// 위도와 경도를 주소로 변환
function geocodeLatLng(location) {
    geocoder.geocode({ location: location }, function(results, status) {
        if (status === 'OK') {
            if (results[0]) {
                const address = results[0].formatted_address;
                displayLatlng(location, address);  // 변환된 주소 전달
                addMarker(location);
            } else {
                console.log('주소를 찾을 수 없습니다.');
            }
        } else {
            console.log('Geocoder 실패: ' + status);
        }
    });
}

// 마커 추가 함수
function addMarker(location) {
    const marker = new google.maps.Marker({
        position: location,
        map: map,
    });

    // 마커를 배열에 저장
    markers.push(marker);

    // 마커 클릭 시 삭제 이벤트
    marker.addListener("click", function () {
        marker.setMap(null); // 마커를 지도에서 제거
        removeMarker(marker); // 배열에서 마커 삭제
    });

    // 마커의 위치를 commentLocations 배열에 저장 (마커와 코멘트 연결)
    commentLocations.push(location);
}

// 마커 배열에서 특정 마커를 삭제
function removeMarker(marker) {
    const index = markers.indexOf(marker);
    if (index > -1) {
        markers.splice(index, 1);
        commentLocations.splice(index, 1); // 관련된 위치 정보도 삭제
    }
}

// URL에서 쿼리 스트링 값 가져오기
function getQueryParams() {
    let params = {};
    window.location.search.substring(1).split("&").forEach(function(part) {
        let item = part.split("=");
        params[item[0]] = decodeURIComponent(item[1]);

    });
    return params;
}

let params = getQueryParams();
let startDate = new Date(params['startDate']);
let endDate = new Date(params['endDate']);

// 날짜 차이 계산
let timeDifference = endDate.getTime() - startDate.getTime();
let dayCount = Math.ceil(timeDifference / (1000 * 3600 * 24)) + 1; // 출발일 포함

//위도, 경도, 주소 표시
function displayLatlng(location) {
    console.log('위도:', location.lat(), '경도:', location.lng(), '주소:', address); // 디버깅용 로그 추가
    document.getElementById('latitude').textContent = location.lat();
    document.getElementById('longitude').textContent = location.lng();
    document.getElementById('address').textContent = address;
}
// DB 연결



// 탭 생성
let dayTabs = document.getElementById('dayTabs');
for (let i = 0; i < dayCount; i++) {
    let dayTab = document.createElement('div');
    dayTab.classList.add('tab');
    if (i === 0) {
        dayTab.textContent = '전체 일정';
    } else {
        dayTab.textContent = `${i}일차`;
    }
    dayTabs.appendChild(dayTab);

    // 탭 클릭 시 해당 일차 표시
    dayTab.onclick = function() {
        document.getElementById('currentDay').textContent = dayTab.textContent;
        document.getElementById('scheduleContent').textContent = `${dayTab.textContent}`;
    }
}

// 모달 열기
const modal = document.getElementById("commentModal");
const closeModal = document.querySelector(".close");

document.getElementById('save-btn').addEventListener('click', function() {
    if (markers.length > 0) {
        // 모달 열기
        modal.style.display = "block";
    }
});

closeModal.onclick = function() {
    console.log('닫기');
    modal.style.display = "none";
}

// 모달 외부 클릭 시 닫기
window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

// 코멘트 저장 버튼 클릭 시
document.getElementById('save-comment-btn').addEventListener('click', function() {
    const comment = document.getElementById('comment-input').value;

    if (comment) {
        savedComments.push(comment);
        updateLocationList();
        document.getElementById('comment-input').value = ''; // 입력창 초기화
        modal.style.display = "none"; // 모달 닫기
    }
});

// 저장된 코멘트 리스트 업데이트
function updateLocationList() {
    const locationList = document.getElementById('SaveLocation');
    const targetTable = document.getElementById('DaySchedule'); // 테이블 ID 수정
    locationList.innerHTML = '';

    savedComments.forEach((comment, index) => {
        const li = document.createElement('li');
        li.textContent = `장소 ${index + 1}: ${comment}`;

        // + 버튼 생성
        const addButton = document.createElement('button');
        addButton.textContent = '+';
        addButton.style.marginLeft = '10px';  // 버튼과 텍스트 사이 여백
        addButton.addEventListener('click', function() {
            const row = document.createElement('tr');
            const cell = document.createElement('td');
            cell.textContent = comment;

            // 삭제 버튼 생성
            const deleteButton = document.createElement('button');
            deleteButton.textContent = '-';
            deleteButton.style.marginLeft = '10px';
            deleteButton.style.color = 'white';
            deleteButton.addEventListener('click', function() {
                targetTable.removeChild(row); // 행 삭제
            });

            // 셀에 삭제 버튼 추가
            cell.appendChild(deleteButton);
            row.appendChild(cell);
            targetTable.appendChild(row);  // 테이블에 행 추가

            // 코멘트 클릭 시 지도에 위치 표시
            row.addEventListener('click', function() {
                const location = commentLocations[index]; // 코멘트와 연결된 위치 정보
                if (location) {
                    map.setCenter(location);  // 지도의 중심을 해당 위치로 이동
                    map.setZoom(15);
                }
            });
        });

        li.appendChild(addButton); // li에 + 버튼 추가
        locationList.appendChild(li); // ul에 li 추가
    });
}

/*
saved-btn을 통해 title을 지정하는 모달창이 나오고
내용을 입력하고 저장 버튼 클릭시 ajax 실행 저장 쫘자작
*/
// POST 요청을 통해 DB에 일정 저장 요청
// $.ajax({
//     type: "POST",
//     url: "/api/trip/saveSchedule",  // 서버의 API 엔드포인트
//     contentType: "application/json",
//     data: JSON.stringify({
//         startAt: startDate,  // CourseRequestDTO의 startAt에 매핑
//         endAt: endDate       // CourseRequestDTO의 endAt에 매핑
//     }),
//     success: function(response) {
//         // 성공적으로 저장되면 tokyoSchedule 페이지로 이동
//
//     },
//     error: function(xhr, status, error) {
//         alert("일정을 저장하는 중 오류가 발생했습니다.");
//         console.log(error);
//     }
// });



// 초기 설정: 전체 일정 표시
/*
document.getElementById('currentDay').textContent = '전체 일정';
document.getElementById('scheduleContent').textContent = '출발일 : ' + params['startDate'] +'\n ' + ' 도착일 : ' + params['endDate'];
*/
// 페이지 로드될 때 initMap 함수 호출
window.onload = initMap;
