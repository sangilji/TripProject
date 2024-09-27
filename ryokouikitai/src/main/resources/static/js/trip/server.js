const express = require('express');
const mysql = require('mysql');
const cors = require('cors');
const bodyParser = require('body-parser');

// Express 앱 설정
const app = express();
app.use(cors());
app.use(bodyParser.json());

// MySQL 연결 설정
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',  // MySQL 사용자 이름
    password: 'root',  // MySQL 비밀번호
    database: 'trip'
});

db.connect((err) => {
    if (err) throw err;
    console.log('MySQL 연결 성공!');
});

// 장소 저장 API (일차별)
app.post('/save-location', (req, res) => {
    const { latitude, longitude, comment, day } = req.body;

    const query = 'INSERT INTO day_locations (latitude, longitude, comment, day) VALUES (?, ?, ?, ?)';
    db.query(query, [latitude, longitude, comment, day], (err, result) => {
        if (err) throw err;
        res.send({ message: '장소 저장 성공', id: result.insertId });
    });
});

// 특정 일차의 장소 불러오기
app.get('/locations/:day', (req, res) => {
    const day = req.params.day;

    const query = 'SELECT * FROM day_locations WHERE day = ?';
    db.query(query, [day], (err, results) => {
        if (err) throw err;
        res.send(results);
    });
});

// 모든 장소 삭제 API (취소 버튼 기능)
app.delete('/delete-all', (req, res) => {
    const query = 'DELETE FROM day_locations';
    db.query(query, (err, result) => {
        if (err) throw err;
        res.send({ message: '모든 장소가 삭제되었습니다.' });
    });
});

// 서버 실행
app.listen(3306, () => {
    console.log('서버가 http://localhost:3306 에서 실행 중입니다.');
});
