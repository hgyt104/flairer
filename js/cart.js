// @command 전체선택
function allCheckedBox(obj) {
    // @brief 선택한 체크박스의 클래스 명칭을 가져온다.   
    const termClass = obj.getAttribute("class");
    // @brief 선택한 클래스명과 같은 클래스의 갯수
    //console.log(document.getElementsByClassName(termClass).length);
    Array.prototype.forEach.call(document.getElementsByClassName(termClass), function (element, index, array) {
        // @brief 엘리먼트의 값 출력
        //console.log(element.value);
        // @brief 선택한 클래스의 첫번째 checkbox의 상태가 체크가 되있는 경우
        if (document.getElementsByClassName(termClass)[0].checked == true) {
            // @brief 같은 클래스명을 가진 모든 checkbox의 상태를 선택 완료 처리 한다.   
            element.checked = true;
        }
        // @brief 선택한 클래스의 첫번째 checkbox의 상태가 체크가 해제된 경우
        else {
            // @brief 같은 클래스명을 가진 모든 checkbox의 상태를 선택 해제 처리 한다.
            element.checked = false;
        }
    });
}
// @command 개별 선택이 이루어진 경우에 전체값 체크의 예외처리
function eachCheckedBox(obj) {
    // @brief 선택한 체크박스의 상태가 선택해제인 경우
    if (obj.checked == false) {
        // @brief 선택한 체크박스의 클래스 명칭을 가져온다.
        const termClass = obj.getAttribute("class");
        // @brief 첫번째 checkbox의 상태가 체크가 되어있는경우
        if (document.getElementsByClassName(termClass)[0].checked == true) {
            // @brief 첫번째 checkbox의 상태를 체크해제한다.
            document.getElementsByClassName(termClass)[0].checked = false;
        }
    }
}


$(function () {

	var k = $(".amount").text()*1;	
	var j = $(".price").text()*1;	
	//$(".price").text(j.toLocaleString());

	$(".cbox").change(function(){
		var s = $(".dbox:checked").length;
		$("#count").text(s);
	});
});

