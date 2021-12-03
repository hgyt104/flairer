$(function () {
    //카테고리 하위메뉴
    $('nav1>a').hide();

    $('#nav1>a').mouseover(function () {
        $('.nav_hide').slideDown();
    });

    $('#nav1').mouseleave(function () {
        $('.nav_hide').slideUp();
    });

    // //아이콘 하위메뉴
    // $('icon1>a').hide();

    // $('.icon1').mouseover(function () {
    //     $('.hide1').slideDown();
    // });
    // $('.icon1').mouseleave(function () {
    //     $('.hide1').slideUp();
    // });
});
