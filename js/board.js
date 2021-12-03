
function boardCheck() {
	console.log("검사");
	if(document.frm.bpwd.value == "") {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	
	if(document.frm.btitle.value == "") {
		alert("제목을 입력하세요.");
		return false;
	}
	
	if(document.frm.bcontent.value == "") {
		alert("내용을 입력하세요.");
		return false;
	}
	return true;
}


function bpwdCheck() {
	if(document.frm.bpwd.value.legth == 0) {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	
	return true;
}

function open_win(url, name) {
	window.open(url, name, "width=500, height=230");
}

$(function(){
   $('.edit').click(function(){
      $(this).parent().prevAll('.content_box').children().attr('disabled',false);
      $(this).prop("type", "hidden");
      $(this).next().prop("type", "hidden");
      $(this).nextAll(".hdc1").prop("type", "button");
      $(this).nextAll(".hdc2").prop("type", "button");
   });
   $('.hdc1, .hdc2').click(function(){
      var $cont = $(this).parent().prevAll('.content_box').children()   
   
      $cont.attr('disabled',true);
      $(this).prevAll('.edit').prop("type", "button");
      $(this).prevAll('.del').prop("type", "button");
      $(this).prop("type", "hidden");
      if($(this).hasClass("hdc2")) {
         $(this).prev().prop("type", "hidden");
      } else {
         $(this).next().prop("type", "hidden");
         
         $.ajax({
            type: "POST", //요청 메소드 방식
            url:"/board/replymodify" ,
            data: {
               rcontent: $cont.val(),
               rcode : $(this).prevAll('.find_rcode').val()
            },
            dataType:"html", //서버가 요청 URL을 통해서 응답하는 내용의 타입
            success : function(result){
               console.log($cont.val());
            }
      });
      }
   });
});