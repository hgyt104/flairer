window.onload = function(){

    const moneyArr = ["29,900","82,900", "35,900", "39,900",
                        "89,900","45,600", "41,630", "109,000",
                        "96,900","38,880", "64,600", "68,000",
                        "31,410","26,900", "143,000","148,000",
                        "63,000","58,900", "119,000","73,000"];
    const nameArr = ["페라리 블랙 EDT 75ml", "불가리 뿌르옴므 EDT 100ml","버버리 클래식 포 맨 EDT 30ml","몽블랑 엠블럼 EDT 40ml",
                        "불가리 블루 향수 100ml", "존 바바토스 아티산 EDT 125ml", "몽블랑 레전드 EDT 100ml","에르메스 H24 오 드 뚜왈렛 50ml",
                        "디올 소바쥬 오 드 뚜왈렛 60ml", "몽블랑 레전드 EDT 100ml","조르지아 아쿠아 디 지오 EDT 30ml","아프리모 미드나잇 향수 50ml",
                        "버버리 위크 앤드 포 맨 오 뚜왈렛 100ml", "겐조 로 겐조 뿌르 옴므 EDT 30ml","겔랑 옴므 이데알 오 드 뚜왈렛 100ml","샤넬 알뤼르 옴므 스포츠 100ml",
                        "아프리모 골드 향수 100ml","돌체앤가바나 라이트 블루 EDP 100ml","샤넬 블루 드 샤넬 오 드 빠르펭 100ml", "아프리모 트리플에이 향수 50ml"];
    
    for( i = 0; i < 20 ; i++){ //사진 번호 순차적으로 진행

        let come = document.createElement('div') ;// div 를 만듬
        come.setAttribute('class', 'col-3') ;// div 를 만들고 class 와 col 클래스명을 만들어줌

        let rome = document.createElement('div');
        rome.setAttribute('class','row');
        
        let boy0 = document.createElement('div');
        boy0.setAttribute('class', 'head');
        
        //let boy1 = document.createElement('div');
        //boy1.setAttribute('class' , 'col-12');
    
        let boy2 = document.createElement('div');
        boy2.setAttribute('class', 'col');
        //boy2.setAttribute('id','title_n_m');
        
        //let boy3 = document.createElement('div');// 칸 하나 줄임 
        //boy3.setAttribute('class' ,'col');//칸 하나 줄임
        //boy3.setAttribute('id','title_price') // 칸 하나 줄임
        let address = document.createElement('a');
        address.setAttribute('href','./man_perfume_detail.html');
        let img = document.createElement('img');// 이미지를 만듬
        img.setAttribute('src', '../../img/perfume/man_perfume_' + [i+1] + '.jpeg'); // 이미지  파일을 적고 이름 을 적는다.;
        
    
        let price = document.createElement('p');
        price.setAttribute('id','money');
        price.innerText = moneyArr[i]+'원';  //가격 이너 텍스트 를 넣는다. 배열로 하나씩 나오게 
        
        let title = document.createElement('p');
        title.setAttribute('id', 'name');
        title.innerText = nameArr[i];// 배열로 하나하나씩 나오게 

        let p_t_address = document.createElement('a');
        p_t_address.setAttribute('href' , './man_perfume_detail.html');

        // come 이라는 곳  밑에 row 를 넣어준다는 의미  자손이라는 의미로 생각 
        rome.appendChild(boy0);
        //rome.appendChild(boy1);
        rome.appendChild(boy2);
        //rome.appendChild(boy3); // 칸을 하나 줄여서 글씨랑 금액이랑 같이 표기 

        boy0.appendChild(address)
        boy2.appendChild(p_t_address);
        boy2.appendChild(p_t_address);
        address.appendChild(img)
        p_t_address.appendChild(title);
        p_t_address.appendChild(price);
        
        come.appendChild(rome);

        document.getElementById('here').appendChild(come)
    }
}