// 모달 열기/닫기 기능
var modal = document.getElementById("calendarModal");
var openModalBtn = document.getElementById("scheduleBtn");
var closeModalBtn = document.getElementsByClassName("close")[0];
var cityName = locationURL.split("/").pop();
// 모달 열기
openModalBtn.onclick = function () {
    modal.style.display = "block";
}

// 모달 닫기
closeModalBtn.onclick = function() {
    modal.style.display = "none";
}

// 모달 외부 클릭 시 닫기
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// Flatpickr 설정 (출발하는 날)
flatpickr("#startDate", {
    dateFormat: "Y-m-d",
    appendTo: document.getElementById("calendarModal"), // 모달 내에서 캘린더 표시
    onChange: function(selectedDates, dateStr, instance) {
        console.log("출발하는 날: ", dateStr);
    },
    positionElement: document.getElementById("startDate") // 입력 필드 옆에 캘린더 표시
});

// Flatpickr 설정 (돌아오는 날)
flatpickr("#endDate", {
    dateFormat: "Y-m-d",
    appendTo: document.getElementById("calendarModal"), // 모달 내에서 캘린더 표시
    onChange: function(selectedDates, dateStr, instance) {
        console.log("돌아오는 날: ", dateStr);
    },
    positionElement: document.getElementById("endDate"), // 입력 필드 옆에 캘린더 표시
});

// 일정 저장 버튼 클릭 시
document.getElementById("saveScheduleBtn").onclick = function() {
    var startDate = document.getElementById("startDate").value;
    var endDate = document.getElementById("endDate").value;
    // 출발 날짜와 도착 날짜가 선택된 경우
    if (startDate && endDate) {
        window.location.href = `/trip/schedule?startDate=${startDate}&endDate=${endDate}&city=${cityName}`;

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
    } else {
        alert("출발 날짜와 도착 날짜를 선택하세요.");
    }
};