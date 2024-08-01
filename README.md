# Dependencies 추가
Lombok,
Spring Web,
Spring Data JPA,
MariaDB Driver,
Spring Security,
OAuth2 Client, <- 소셜 로그인 시에 필요
validation,<- 어노테이션 라이브러리
Java Mail Sender,

# 상품sql
INSERT INTO tbl_shop (prod_name , prod_price , prod_imgpath , prod_detailimgpath , prod_category) VALUES
('크리넥스 카카오 여행용 20개', 11330, 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/afca/65eb10b6bcd79c9780058ca12032981668c45c42d7a9ae4e1527197b80c6.jpg', 'https://thumbnail8.coupangcdn.com/thumbnails/remote/q89/image/vendor_inventory/b1c5/6f64b5628dcbc6b990d7af4a885fe8f162309bb9ce670aa77674df2c13a1.jpg', '화장실'),
('슬림티슈 6P [내마음속 고래] / 슬림티슈 고래시리즈 프리미엄 차량용티슈 휴대용티슈', 13600, 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/3aaa/6d49aa191d3f5e50aedee3ee385ed9dc04e953635946fc33a1159a404757.jpg', 'http://image1.coupangcdn.com/image/vendor_inventory/3512/ef5ef6b779057ff12b4394d07dd05125814a317af50e7a82ce5d6ed3f772.jpg', '화장실'),
('크리넥스 마이비데 생분해 클린케어 물티슈 46매 x 6팩', 13860, 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/623f/b3b4ecefd2bf1d3d7cf9f0685aebf621b56c42a65cc568975503e407d067.jpg', 'https://image1.coupangcdn.com/image/vendor_inventory/e1d1/5c292f207753c9a2341a4a837b06d2fbdeb44a306f8ba681afbcf7857385.jpg', '화장실'),
('지앤 변기에 버리는 비데물티슈 물에녹는 화장실 비데용', 18900, 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/fd34/ccb319fd875309ce6c25bb120045fcbbfcf244a66b64dcf5bf186d212561.jpg', 'https://thumbnail7.coupangcdn.com/thumbnails/remote/q89/image/vendor_inventory/8e5d/485c105ce77c2335a53eb3099ee1d191b5ab3aa449698292c779594c70d6.png', '화장실'),
('네이버 포인트 5000원', 5000, 'https://www.biz-con.co.kr/upload/images/202309/400_20230907184904783_5000.jpg', '', '기프티콘'),
('네이버 포인트 10000원', 10000, 'https://www.biz-con.co.kr/upload/images/202309/400_20230907184856863_10000.jpg', '', '기프티콘'),
('스타벅스 아이스 카페 아메리카노 T', 4500, 'https://www.biz-con.co.kr/upload/images/202312/400_20231219104804672_2.jpg', 'https://www.biz-con.co.kr/upload/images/202312/20231229111545851_20231219133554461_2.jpg', '기프티콘'),
('스타벅스 e카드교환권 1만원권', 10000, 'https://www.biz-con.co.kr/upload/images/202312/400_20231219154234321_1.jpg', 'https://www.biz-con.co.kr/upload/images/202312/20231229104220375_20231219154502144_231123_%EC%8A%A4%ED%83%80%EB%B2%85%EC%8A%A4-%EA%B5%90%ED%99%98%EA%B6%8C-%EC%95%88%EB%82%B4%ED%8E%98%EC%9D%B4%EC%A7%80.jpg', '기프티콘'),
('탐사 깨끗 비데티슈 휴대형, 10매, 15팩', 7690, 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2919570609378640-37749422-fa8b-4098-b516-20c043914d08.jpg', 'https://thumbnail10.coupangcdn.com/thumbnails/remote/q89/image/retail/images/595790198568574-47bff927-b6b7-4c48-acbf-f188f999bfcf.jpg', '화장실'),
('바렌 손씻으러가자 휴대용 핸드워시, 30ml, 2개', 5740, 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/1947526786703726-c6ac7825-fb49-4619-9574-6448a6761a21.jpg', 'https://thumbnail9.coupangcdn.com/thumbnails/remote/q89/image/retail/images/87200736874938-ed3d9c18-7572-49d6-b933-99f8e5c2ff5c.jpg', '화장실'),
('아이깨끗해 휴대용 핸드워시 레몬, 50ml, 4개', 13500, 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/rs_quotation_api/bqkws8ca/18160ab9507d4532a50c682e12513eba.jpg', 'https://thumbnail6.coupangcdn.com/thumbnails/remote/q89/image/retail/images/2021/12/08/17/0/69f14149-f612-4d2d-872e-cfdf3d4646bd.jpg', '화장실'),
('깨끗한나라 닥터비데 화장실용 물티슈 캡형, 55g, 46매, 4개', 6490, 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/1492808520795775-b2477f87-087e-4c86-96fe-bdf476185ba4.jpg', 'https://thumbnail10.coupangcdn.com/thumbnails/remote/q89/image/retail/images/1251205558969168-d59bd4d0-1315-478d-9cae-071f1e2c334e.jpg', '화장실'),
('카카오페이상품권 5천원권(교환권)', 5000, 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20220218131037_d2d9d982452a4195826844940bd23d59.png', 'https://st.kakaocdn.net/product/gift/editor/20231025160118_a85ba6c5f0ec4a1fbeff4bd6bc55d5d3.png', '기프티콘'),
('올리브영 기프트카드 5천원권', 5000, 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20231123140757_82ad9c7c30aa48bb92fe6efa3d8fb1d4.jpg', 'https://st.kakaocdn.net/product/gift/editor/20240305152639_70433d932d954040a3216404d458c960.jpg', '기프티콘');
