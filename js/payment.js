/**
 * 작성자 : 김정훈
 * 설명 : 체크 박스 감지
 * 전역 변수 : dms(부분 체크 박스 배열)
 * 			 dmSize(체크 된 체크박스 개수)
 * 			 ttcs(총 가격 스팬 배열)
 * 전역 상수 : ttValue(전체 물건 가격)
 */
 
 $(function() {
 	//선언부
 	let dms = $('.dm');
 	let dmSize = $(".dm:checked").length;
 	let ttcs = $('.ttc');
 	const ttValue = ((ttcs[0].textContent)*1).toLocaleString()+'원';
 	
 	changeTotal(true, null);
 	
 	$('.second_tr').each(function(){
 		const textPrice = $(this).find('.price').text();
 		const textTotal = $(this).find('.total').text();
 		
 		$(this).find('.price').text((textPrice*1).toLocaleString()+'원');
 		$(this).find('.total').text((textTotal*1).toLocaleString()+'원');
 	});
 	
 	//전체 선택 체크박스 감지
 	$('#isall').change(function() {
 		dms = $('.dm');
 		
 		for(let i = 0; i < dms.length; i++) {
 			dms[i].checked = $(this)[0].checked;
 		}
 		changeTotal($(this)[0].checked, null);
 		changeIdCount();
 	});
 	
 	//부분 선택 체크박스 감지
 	$('.dm').change(function() {
 		dms = $('.dm');
 		let ck_all = $('#isall')[0];
 		
 		let intTotal = $('.ttc')[0].textContent.replace(",", "").replace("원", "")*1;
 		const price = $(this).parents('.second_tr').find('.total').text().replace(",", "").replace("원", "")*1;
 		ck_all.checked = true;
 		
 		for(let i = 0; i < dms.length; i++) {
 			if(!dms[i].checked) {
 				ck_all.checked = dms[i].checked;
 				break;
 			}
 		}
 		
 		changeIdCount();
 		intTotal = ($(this).is(':checked') ? (intTotal + price) : (intTotal - price)).toLocaleString()+'원';
		changeTotal(true, intTotal);
 	});
 	
 	function changeIdCount() {
 		dmSize = $(".dm:checked").length;
 		$('#count').text(dmSize);
 	}
 	
 	function changeTotal(bool, total) {
 		let stVal = ttValue;
 		
 		if(total != null) stVal = total;
 		
 		if(!bool) stVal = '0원';
 		
 		ttcs[0].innerText = stVal;
 		ttcs[1].innerText = stVal;
 	}
 	
 	$('#all_order').click(function(){
 		let str = "";
 		let sttr = "";
 		$(".dm:checked").each(function(index, elm){
 			str += $(this).data("pcode") + '|';
 			sttr = $(this).data("pa_code");
 		});
 		$("#pa_code").val(sttr);
 		str = str.slice(0, -1);
 		if(str =="") {
 			alert("1개 이상 선택하여 주세요");
 		} else {
 			$("#pids").val(str);
 			$("#orderForm").submit();
 		}
 	});
 });
 
 