

$(document).ready(function () {
    $("#pic").mouseover(function () {

        for (var i = 0; i < 3; i++) {
            $(this).fadeOut(500);
            $(this).fadeIn(500);
        }

    });
});