

// @breif contenteditable 속성을 가진경우
contents = document.getElementsByClassName("rowColumn");
document.addEventListener("DOMContentLoaded", function() {
   // @breif rowColumn 클래스의 갯수 만큼 반복문을 실행한다.
   console.log(contents);
   Array.from(contents).forEach(function(content) {
      // @breif 마우스로 해당영역을 더블클릭 한경우
      content.addEventListener("dblclick", function(event) {
         // @breif 전체 테이블 컬럼( td > p )에서 현재 사용중인 값의 존재여부를 확인한다.
         Array.from(contents).forEach(function(defaultVal) {
            // @details 저장하지 않은 내용이라고 판단하여 data 태그의 기본값으로 되돌린다.
            defaultVal.textContent = defaultVal.dataset.default;
            // @breif 수정 불가 상태로 되돌린다.
            defaultVal.contentEditable = false;
            defaultVal.style.border = "0px";
         });
         if(content.isContentEditable == false) {
            // @details 편집 가능 상태로 변경
            content.contentEditable = true;
            // @details 텍스트 문구 변경
            // content.textContent = "";
            // @details CSS 효과 추가
            content.style.border = "1px solid #FFB6C1";
            // @details 포커스 지정
            content.focus();
         }
      });
      // @breif 키보드 입력이 방생한 경우 실행
      content.addEventListener("keypress", function(event) {
         // @breif Enter키 입력시 실행
         if(event.key === "Enter") {
            // @details 입력된 값이 빈값( null )인지 체크한다.
            if(
                  content.textContent == ""
                     || content.textContent == null
                     || content.textContent == undefined
                     || (content.textContent != null
                           && typeof content.textContent == "object"
                              && !Object.keys(content.textContent).length == ""))
            {
               // @details 내용이 존재하지 않다면 data 태그의 기본값으로 되돌린다.
               content.textContent = content.dataset.default;
            } else {
               // @details 내용의 수정이 완료되었다면 data 태그의 기본값도 바꿔준다.
               console.log(content);
               content.dataset.default = content.textContent;

               $.ajax({
                  type: "POST", 
                  url: "/admin",
                  data: {
                     _method : "put",
                     name: content.dataset.colname,
                     value: content.textContent,
                     code: content.dataset.code,
                     type: content.dataset.type
                  },
                  dataType: "html",
                  success : function(data) {
                     content.dataset.default = content.textContent;
                     if($(content).hasClass("per14")){
                        location.reload();
                     }
                  }
               });
            }
            // @breif 수정 불가 상태로 되돌린다.
            content.contentEditable = false;
            content.style.border = "0px";
         }
      });
   });
});

$(document).tooltip({
    items: 'input[data-tooltip], img[data-tooltip], td[data-tooltip]',
    tooltipClass: 'preview-ui-tooltip',
    open: function (event, ui) {
        $(this).tooltip('option', 'position', {
            my: "right center",
            at: "left center",
            collision: "flipfit flipfit"
        });
    },
    track: false,
    content: function () {
        var $this = $(this);
        var contents = $($this.data('tooltip'));
        var width = 70;
        var height = 70;
        /*if ($this.width() > $this.height()) width = 500;
        var height = width * ($this.height() / $this.width());
        if (height <=  0 || isNaN(height)) contents.css({ 'max-height': '450px' }); 
        else contents.css({ 'height': height + 'px' });*/
        contents.css({ 'height': height + 'px' });
        contents.css({ 'width': width + 'px' });
        return contents;
    }
});
var remove_input = function(){      
   //$("#newProduct").children().last().remove();      
   $("#newProduct tr:last-child").remove();      
   $(this).parent().remove();      
   
}

function add_input(){
   
var   addrow = "";
addrow += '<tr class="ignored-trgt">' +
        '<td class="per5 rowColumn pc"></td>' +
        '<td class="per15 rowColumn nm"><input class="inm"></td>' +
        '<td class="per9 rowColumn prc"><input size="8" class="iprc"></td>' +
        '<td class="per5 rowColumn stck"><input size="8" class="istck"></td>' +
        '<td class="per6 rowColumn ctgry"><input size="8" class="ictgry"></td>' +
        '<td class="per14 rowColumn pic"><input class="ipic"></td>' +
        '<td class="per14 rowColumn d1"><input class="id1"></td>' +
        '<td class="per14 rowColumn d2"><input class="id2"></td>' +
        '<td class="per14 rowColumn d3"><input class="id3"></td>' +
        '<td class="per4"><input class="combtn" type="button" value="완료" onclick="addpd(this)"><input class="cbtn" type="button" value="취소" onclick="remove_input()"></td></tr>';
   
$("#newProduct").append(addrow);
}
$(function(){
   $('#addbtn').click(function(){
      $(this).next().prop("type", "button");
   });
   $('#canclebtn').click(function(){
      $(this).prop("type", "hidden");
   });
});
function addpd(here){
   var $str = $(here).parent();
   var name = $($str).prevAll('.nm').children().first().val();
   var price = $($str).prevAll('.prc').children().first().val();
   var stock = $($str).prevAll('.stck').children().first().val();
   var category = $($str).prevAll('.ctgry').children().first().val();
   var pictureurl = $($str).prevAll('.pic').children().first().val();
   var des1 = $($str).prevAll('.d1').children().first().val();
   var des2 = $($str).prevAll('.d2').children().first().val();
   var des3 = $($str).prevAll('.d3').children().first().val();
   $.ajax({
      type: "POST", 
      url: "/adminproduct",
      data: {
         name : name,
         price : price,
         stock : stock,
         category : category,
         pictureurl : pictureurl,
         des1 : des1,
         des2 : des2,
         des3 : des3
      },
      success : function(data) {
         /*location.reload();*/
         var line = $("#newProduct>tr:first-child").clone();
         var addline = $(line.children()[5]).data("tooltip")
         addline = addline.substring(0, addline.indexOf("src")+5) + "/img/"+ pictureurl + "'>";
         
         var adddes1 = $(line.children()[6]).data("tooltip")
         adddes1 = adddes1.substring(0, adddes1.indexOf("src")+5) + "/img/perfume/des/"+ des1 + "'>";
         
         var adddes2 = $(line.children()[7]).data("tooltip")
         adddes2 = adddes2.substring(0, adddes2.indexOf("src")+5) + "/img/perfume/des/"+ des2 + "'>";
         
         var adddes3 = $(line.children()[8]).data("tooltip")
         adddes3 = adddes3.substring(0, adddes3.indexOf("src")+5) + "/img/perfume/des/"+ des3 + "'>";
         
         line
            .children()
            .data("code", data)
         .first()
            .data("default", data)
            .text(data)
         .next()
            .data("default", name)
            .text(name)
         .next()
            .data("default", price)
            .text(price)
         .next()
            .data("default", stock)
            .text(stock)
         .next()
            .data("default", category)
            .text(category)
         .next()
            .data("default", pictureurl)
            .data("tooltip", addline)
            .text(pictureurl)
         .next()
            .data("default", des1)
            .data("tooltip", adddes1)
            .text(des1)
         .next()
            .data("default", des2)
            .data("tooltip", adddes2)
            .text(des2)
         .next()
            .data("default", des3)
            .data("tooltip", adddes3)
            .text(des3)
         .next()
            .find("input[name=code]")
            .val(data);
         $(".ignored-trgt").before(line);
         $str.parent().remove(); 
      }
   });
   /*<td class="per5 rowColumn" th:contenteditable="false" th:data-type="product" th:data-code="${product.pcode}" th:data-colname="pcode" th:data-default="${product.pcode }" th:text="${product.pcode }"></td>
   <td class="per15 rowColumn" th:contenteditable="false" th:data-type="product" th:data-code="${product.pcode}" th:data-colname="name" th:data-default="${product.name }" th:text="${product.name }"></td>
   <td class="per9 rowColumn" th:contenteditable="false" th:data-type="product" th:data-code="${product.pcode}" th:data-colname="price" th:data-default="${product.price }" th:text="${product.price }"></td>
   <td class="per5 rowColumn" th:contenteditable="false" th:data-type="product" th:data-code="${product.pcode}" th:data-colname="stock" th:data-default="${product.stock }" th:text="${product.stock }"></td>
   <td class="per6 rowColumn" th:contenteditable="false" th:data-type="product" th:data-code="${product.pcode}" th:data-colname="category" th:data-default="${product.category }" th:text="${product.category }"></td>
   <td class="per14 rowColumn" th:contenteditable="false" th:data-type="product" th:data-code="${product.pcode}" th:data-colname="mainImg" th:data-default="${product.pictureURL }" th:text="${product.pictureURL }"
   th:data-tooltip="'<div style=\'position:relative;\'><img width=\'300\' height=\'450\' alt=\'등록된 이미지가 없습니다\'  src=\''+@{/img/}+${product.pictureURL }+'\'>'"></td>
   <td class="per14 rowColumn" th:contenteditable="false" th:data-type="product" th:data-code="${product.pcode}" th:data-colname="des1" th:data-default="${product.des1 }" th:text="${product.des1 }"
   th:data-tooltip="'<div style=\'position:relative;\'><img width=\'300\' height=\'450\' alt=\'등록된 이미지가 없습니다\'  src=\''+@{/img/perfume/des/}+${product.des1 }+'\'>'"></td>
   <td class="per14 rowColumn" th:contenteditable="false" th:data-type="product" th:data-code="${product.pcode}" th:data-colname="des2" th:data-default="${product.des2 }" th:text="${product.des2 }"
   th:data-tooltip="'<div style=\'position:relative;\'><img width=\'300\' height=\'450\' alt=\'등록된 이미지가 없습니다\'  src=\''+@{/img/perfume/des/}+${product.des2 }+'\'>'"></td>
   <td class="per14 rowColumn" th:contenteditable="false" th:data-type="product" th:data-code="${product.pcode}" th:data-colname="des3" th:data-default="${product.des3 }" th:text="${product.des3 }"
   th:data-tooltip="'<div style=\'position:relative;\'><img width=\'300\' height=\'450\' alt=\'등록된 이미지가 없습니다\'  src=\''+@{/img/perfume/des/}+${product.des3 }+'\'>'"></td>
   <td class="per4">*/
   
};
