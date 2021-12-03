function loginCheck() {
	if(document.frm.userid.value.length == 0) {
		alert("아이디를 입력해주세요!");
		frm.userid.focus();
		return false;
	}
	
	if(document.frm.pwd.value == "") {
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	
	return true;
}

function idCheck() {
	if(document.frm.userid.value =="") {
	alert('아이디를 입력해주세요.');
	document.frm.userid.focus();
	return;
	}
	
	var url = "/idcheck?userid=" + document.frm.userid.value;
	window.open(url, "_blank_1",
	"toolbar=yes, menubar=no, scrollbar=yes, resizable=yes, width=450, height=200");
}

function idFind() {
	var url = "/idFindview";
	//var url = "/idFind?name=" + document.frm.name.value + "&phone=" + document.frm.phone.value;
	window.open(url, "_blank_1",
	"toolbar=yes, menubar=no, scrollbar=yes, resizable=yes, width=450, height=200");
}
	

function idok(){
	opener.frm.userid.value=document.frm.userid.value;
	opener.frm.reid.value=document.frm.userid.value;
	self.close();
}
	
function joinCheck() {
	if(document.frm.name.value.length == 0) {
		alert("이름을 써주세요.");
		frm.name.focus();
		return false;
	}
	if(document.frm.userid.value.length == 0) {
		alert("아이디을 써주세요.");
		frm.userid.focus();
		return false;
	}
	
	if(document.frm.userid.value.length < 4) {
		alert("아이디는 4글자 이상이어야 합니다.");
		frm.userid.focus();
		return false;
	}
	if(document.frm.pwd.value.length < 8 || document.frm.pwd.value.length > 15) {
		alert("비밀번호는 8글자 이상 15글자 이내여야 합니다.");
		frm.pwd.focus();
		return false;
		
	}
	

    
	if(document.frm.pwd.value == "") {
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	if(document.frm.reid.value.length == 0) {
		alert("아이디 중복 체크를 하지 않았습니다.");
		frm.userid.focus();
		return false;
	}
	return true;
}



$(function() {    
     $('.pw').focusout(function () {
     var pwd1 = $("#password_1").val();
     var pwd2 = $("#password_2").val();
     if ( pwd1 != '' && pwd2 == '' ) {
	     null;
     } else if (pwd1 != "" || pwd2 != "") {
     if (pwd1 == pwd2) {
     $("#alert-success").css('display', 'inline-block');
     $("#alert-danger").css('display', 'none');
     } else {
     $("#alert-success").css('display', 'none');
     $("#alert-danger").css('display', 'inline-block');
       }
      }
   });
})



 