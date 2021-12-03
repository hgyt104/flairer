function putCart(object) {
	$.ajax({
		type: "GET", 
        url: "/order/orderlist",
        data: {
        	pcode: object.dataset.pcode,
           	amount: $('#amount').val()
       	},
        dataType: "html",
        success : function(data) {
        	alert('장바구니에 담겼습니다.');
        }
	});
}
$(function () {

	var k = $("#this").text()*1;	
	$("#price").text(k.toLocaleString());
	$("#this").text(k.toLocaleString());
	
	$("#amount").change(function(){
		var i = $(this).val();
		var j = $("#this").text().replace(",", "")*1;
		$("#price").text((j*i).toLocaleString());
		var s = ($('.btn_1').attr('href').slice(0, -1) + i);
		$('.btn_1').attr('href', s);
	});
});
