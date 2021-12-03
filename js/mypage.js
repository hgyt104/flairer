

function pwdCheck() {
	if(document.frm.pwd.value.legth == 0) {
		alert("비밀번호를 입력하세요.");
		return false;
}
}

function open_win(url, name) {
	window.open(url, name, "width=500, height=230");
}	
	 