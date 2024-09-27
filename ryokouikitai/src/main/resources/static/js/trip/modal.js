// 모달 열기/닫기 기능
var modal = document.getElementById("calendarModal");
var openModalBtn = document.getElementById("scheduleBtn");
var closeModalBtn = document.getElementsByClassName("close")[0];

// 모달 열기

openModalBtn.onclick = function() {
    console.log("열림?")
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

// Flatpickr 설정 (출발 날짜)
flatpickr("#startDate", {
    dateFormat: "Y-m-d",  // 날짜 형식
    onChange: function(selectedDates, dateStr, instance) {
        console.log("출발하는 날짜: ", dateStr);  // 선택된 날짜 콘솔 출력
    }
});

// Flatpickr 설정 (도착 날짜)
flatpickr("#endDate", {
    dateFormat: "Y-m-d",  // 날짜 형식
    onChange: function(selectedDates, dateStr, instance) {
        console.log("돌아오는 날짜: ", dateStr);  // 선택된 날짜 콘솔 출력
    }
});

// 일정 저장 버튼 클릭 시
document.getElementById("saveScheduleBtn").onclick = function() {
    var startDate = document.getElementById("startDate").value;
    var endDate = document.getElementById("endDate").value;

    // 출발 날짜와 도착 날짜가 선택된 경우
    if (startDate && endDate) {
        // 새로운 페이지로 데이터 전송 (GET 방식)
        window.location.href = `tokyoSchedule.html?startDate=${startDate}&endDate=${endDate}`;
    } else {
        alert("출발하는 날짜와 돌아오는 날짜를 선택하세요.");
    }
}


