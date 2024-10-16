// OpenWeatherMap API 사용
const weatherBox = document.getElementById('weatherBox');
const apiKey = '6b70384c796149a5476daf005444d347'; // 여기에 OpenWeatherMap API 키 입력
var locationURL = window.location.href; // 현재 페이지의 전체 URL 가져옴
var CityName = locationURL.split("/").pop();
// "/"로 배열을 만듦 .pop() = 배열의 마지막 요소
var city = CityName;// 원하는 도시 설정



async function fetchWeather() {
    if (city==='hukuoka'){
        city='fukuoka';
    }
    const response = await fetch(`https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${apiKey}&units=metric`);
    const data = await response.json();

    const forecasts = data.list; // 5일간 3시간 간격의 날씨 리스트
    let forecastHTML = '';

    // 5일 간격으로 표시 (하루마다 하나의 날씨 정보만 추출)
    for (let i = 0; i < forecasts.length; i += 8) {
        const forecast = forecasts[i];
        const date = new Date(forecast.dt * 1000); // 날짜 변환
        const temperature = forecast.main.temp;
        const weather = forecast.weather[0].main;
        const iconCode = forecast.weather[0].icon; // 날씨 아이콘 코드
        const iconUrl = `https://openweathermap.org/img/wn/${iconCode}@2x.png`; // 아이콘 URL
        const today = date.toLocaleDateString('ko-KR').substring(5,date.toLocaleDateString().length-1);
        forecastHTML += `
            <div style="    
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
            width: 20%;
            height: 80%;
            justify-content: center;
            align-items: center;
            ">
                <p style="margin: 0;">${today}</p>
                <img src="${iconUrl}" alt="날씨 아이콘">
                <p style="margin: 0;"> ${temperature}°C</p>
            </div>
        `;



    }

    weatherBox.innerHTML = forecastHTML;
}

fetchWeather();
