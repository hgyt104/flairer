
$(function() {
	$(".main_price").each(function(index) {
		$(this).text(($(this).text()*1).toLocaleString());
	});
});