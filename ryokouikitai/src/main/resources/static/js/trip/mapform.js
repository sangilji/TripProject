let map;
let markers = []; // 마커 저장 배열
let savedComments = [];
let commentLocations = []; // 각 코멘트와 관련된 위치 저장 배열

// 지도 생성 및 초기 위치 설정 (도쿄)
function initMap() {
    const initialPosition = { lat: 35.6897, lng: 139.692 };

    // 지도 표시
    map = new google.maps.Map(document.getElementById("map"), {
        center: initialPosition,
        zoom: 15,
    });

    // 검색 박스 (자동 완성)
    const input = document.getElementById("search-box");
    const searchBox = new google.maps.places.Autocomplete(input);

    // 장소가 선택될 때 이벤트
    searchBox.addListener("place_changed", function () {
        const place = searchBox.getPlace();
        if (!place.geometry || !place.geometry.location) return;

        const location = place.geometry.location;

        // 지도 중심을 검색한 위치로 이동
        map.setCenter(location);
        map.setZoom(15);

        // 마커 추가
        addMarker(location);
        displayLatlng(location);
    });

    // 클릭 이벤트로 마커 추가
    map.addListener("click", function (event) {
        const clickedLocation = event.latLng;
        addMarker(clickedLocation);
        displayLatlng(clickedLocation);
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

// 클릭한 위치의 위도와 경도를 화면에 표시
function displayLatlng(location) {
    const lat = location.lat();
    const lng = location.lng();
    document.getElementById("info").textContent = `위도: ${lat}, 경도: ${lng}`;
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
    modal.style.display = "none";
}

// 모달 외부 클릭 시 닫기
window.onclick = function(event) {
    if (event.target == modal) {
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
    const locationList = document.getElementById('location-list');
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
            deleteButton.textContent = '삭제';
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




// 초기 설정: 전체 일정 표시
/*
document.getElementById('currentDay').textContent = '전체 일정';
document.getElementById('scheduleContent').textContent = '출발일 : ' + params['startDate'] +'\n ' + ' 도착일 : ' + params['endDate'];
*/
// 페이지 로드될 때 initMap 함수 호출
window.onload = initMap;
