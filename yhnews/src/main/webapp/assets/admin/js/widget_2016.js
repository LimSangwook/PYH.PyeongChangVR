//soft_go_to_top
function go_top(orix,oriy,desx,desy) {
    var Timer;
    if (document.body.scrollTop == 0) {
        var winHeight = document.documentElement.scrollTop;
    } else {
        var winHeight = document.body.scrollTop;
    }
    if(Timer) clearTimeout(Timer);
    startx = 0;
    starty = winHeight;
    if(!orix || orix < 0) orix = 0;
    if(!oriy || oriy < 0) oriy = 0;
    var speed = 7;
    if(!desx) desx = 0 + startx;
    if(!desy) desy = 0 + starty;
    desx += (orix - startx) / speed;
    if (desx < 0) desx = 0;
    desy += (oriy - starty) / speed;
    if (desy < 0) desy = 0;
    var posX = Math.ceil(desx);
    var posY = Math.ceil(desy);
    window.scrollTo(posX, posY);
    if((Math.floor(Math.abs(startx - orix)) < 1) && (Math.floor(Math.abs(starty - oriy)) < 1)){
        clearTimeout(Timer);
        window.scroll(orix,oriy);
    }else if(posX != orix || posY != oriy){
        Timer = setTimeout("go_top("+orix+","+oriy+","+desx+","+desy+")",15);
    }else{
        clearTimeout(Timer);
    }
}

//widget_frame_height
$(document).ready(function(){
   $('#widget_whole').css('height', $(window).height() - 278 );
   $(window).resize(function() {
        $('#widget_whole').css('height', $(window).height() - 278 );
   });
}); 

//widget_left_control
$(document).ready(function(){
    $("#widget_left_close").click(function(){
        $("#widget_whole #left").animate({left: "-240",}, 100);
        $("#widget_left_close").css("display", "none");
        $("#widget_left_open").css("display", "block");
    });
});
$(document).ready(function(){
    $("#widget_left_open").click(function(){
        $("#widget_whole #left").animate({left: "0",}, 100);
        $("#widget_left_close").css("display", "block");
        $("#widget_left_open").css("display", "none");
    });
});

//widget_main_background_setting_control
$(document).ready(function(){
    $("#background_setting_button").click(function(){
        $(".popup_whole#background_setting_form").css("display", "block");
    });
});
$(document).ready(function(){
    $("#background_setting_close").click(function(){
        $(".popup_whole#background_setting_form").css("display", "none");
    });
});

//manage_menu_left_control
$(document).ready(function(){
    $("#admin_left_close").click(function(){
        $("#admin_divine_area #admin_divine_left").animate({left: "-221",}, 100);
        $("#admin_divine_area #admin_divine_center").animate({"padding-left": "0",}, 100);
        $("#admin_left_close").css("display", "none");
        $("#admin_left_open").css("display", "block");
    });
});
$(document).ready(function(){
    $("#admin_left_open").click(function(){
        $("#admin_divine_area #admin_divine_left").animate({left: "0",}, 100);
        $("#admin_divine_area #admin_divine_center").animate({"padding-left": "220",}, 100);
        $("#admin_left_close").css("display", "block");
        $("#admin_left_open").css("display", "none");
    });
});

//admin_right_dropSlide
$(function(){ //DOM ready
	$('#admin_right .list ul li#select_group>a').click(function(){
		$(this).parent().siblings().removeClass('active').find('ul').slideUp(100);
		if($(this).parent().hasClass('active')){
			$(this).next().slideUp(100);
			$(this).parent().removeClass('active');
		}
		else{
			$(this).next().slideDown(100);
			$(this).parent().addClass('active');
		}
		return false;
	});
});

//admin_right_control
$(document).ready(function(){
    $("#admin_sub_gnb").click(function(){
        $("#admin_right").animate({right: "0",}, 100);
        $("#admin_whole").animate({"padding-right": "220",}, 100);
    });
});
$(document).ready(function(){
    $("#admin_right_close").click(function(){
        $("#admin_right").animate({right: "-220",}, 100);
        $("#admin_whole").animate({"padding-right": "0",}, 100);
    });
});