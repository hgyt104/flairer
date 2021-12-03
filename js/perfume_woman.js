$(function() {
    const moneyArr = ["270,000", "42,000", "99,450", "218,500",
                        "49,900", "39,800", "27,400", "42,000",
                        "66,000", "134,000", "29,500", "155,000",
                        "85,800", "53,500", "145,800", "75,000",
                        "54,800", "39,800", "24,660", "109,000"];       
    const nameArr = ["킬리안 엔젤스 셰어 50ml", "로 겐조 뿌르팜므 50ml", "겔랑 아쿠아 알레 고리아 오 드 뚜왈렛 75ml", "바이레도 블랑쉬 오드 50ml",
                        "나르시소로드리게즈-우먼 오드 50ml", "필로소피 어메이징 그레이스 발레로즈 60ml", "랑방 메리미 100ml ", "랑방 에끌라 오드 100ml",
                        "불가리 옴니아 아메시스트 65ml", "입생로랑 리브르 오드 빠르펭 90ml", "랑방 루머2로즈 오드 100ml", "디올 쟈도르 오드 50ml",
                        "에르메스 트윌리 더르메스 오드 30ml", "빅토리아시크릿 느와르 티즈 오드 50ml", "롤리타렘피카 100ml", "록시땅 시트러스 버베나 오드 뚜왈렛 100ml",
                        "디올 블루밍 부케 롤러 펄 오드 뚜왈렛 20ml", "버버리 클래식우먼 오드 100ml", "지미츄 블러썸 40ml", "샤넬 가브리엘 에쌍스 오드 빠르펭 35ml"];
        
    for(i = 0; i < 20; i++) {
        
        let $div = $(
        );
            
            "<div class='col-3'>" +
                "<div class='row'>" +
                    "<div class = 'head'>" +
                        "<a href ='./woman_perfume_detail.html'>" +
                            "<img class='img'>" +
                        "</a>" +
                    "</div>" +
                    "<div class='col'>" +
                        "<a href ='./woman_perfume_detail.html'>" +
                            "<p class= 'name'></p><p class = 'money'></p>" +
                        "</a>" +
                    "</div>" +
                "</div>" +
            "</div>"
        $($div).find('.img').attr('src', '../../img/perfume/woman_perfume_' + [i+1] + '.jpeg');
        $($div).find('.name').text(nameArr[i]);
        $($div).find('.money').text(moneyArr[i]+"원");
        $($div).appendTo('#here');
            }
        });

//prepend   선택된 요소의 첫번째에 새로운 요소나 콘텐츠를 추가 
//prependTo 선택된 요소를 해당 요소의 첫번쨰에 추가 
//append   선택된 요소의 마지막에 새로운 요소나 콘텐츠를 추가
//appendTo 선택된 요소를 해당 요소의 마지막에 추가
        
/*
    $(document).ready(function () {
        let arr = [1,2,3,4,5,6,7,8,9,10,
        11,12,13,14,15,16,17,18,19,20];
        for (let i = 0; i < arr.length; i++) {
    
            $('#here').append('<div class="col-3">');
            arr.push(arr);
        
        }
        $('.col-3').append('<div class="row" id="here_2">');
    
        $('#here_2').append('<div class="head">');
    
        $('.head').append('<a href="#" id="pi1">');
    
        $('#pi1').append('<img src="img/w/w_"'+[arr+1]+'".jpeg">');
    
    
        $('#here_2').append('<div class="col" id="title_n_m">');
    
    
        $('#title_n_m').append('<a href="#" id="pi2">');
    
        $('#pi2').append('<p id="name">');
    
        $('#pi2').append('<p id="money">');  
    });
*/