$(function(){
    /*更改颜色*/
    $(".sign").hover(function() {
        // console.log($(this));
        $(this).css({"color":"sandybrown"});
    }, function() {
        $(this).css({"color":"white"});
    });

    $(".logout").click(function() {
        $.ajax({
            type: "post",
            url: "logout",
            async: false,
            success: function (data) {
                alert(data);
                window.location.replace("index");
            },
            error:function (data) {
                alert(data);
                window.location.reload();
            }
        })
    })

});

