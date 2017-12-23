
$(document).ready(function(){
	(function($){

		//���ʸ޴�
		$('.lnb_depth1 a').on('click', function(){
			var nn = $(this).parents('li').find('.lnb_depth2');
			if(nn.length > 0){
				$('.lnb_depth2').slideUp('fast');
				if( !$(this).hasClass('on') ) {
					$(this).parents('li').find('.lnb_depth2').slideDown('fast');
					$('.lnb_depth1 a').removeClass("on");
					$(this).addClass("on");
				} else {
					$(this).parents('li').find('.lnb_depth2').slideUp('fast');
					$(this).removeClass("on");
				}
			}
		});
		
		$('.lnb_depth2 > li > a').each(function(i){
			if ($('.lnb_depth2 > li > a').eq(i).hasClass('on')){
				$(this).parents('.lnb_depth2').slideDown('fast');
			};
		});

		//�޴�����
		var btn_openclose = $('.btn_openclose');
		btn_openclose.on('click', function(){
			if( !$(this).hasClass('open') ) {
				$('.aside').animate({"left":"-220px"}, 300);
				$('.page_container').animate({"padding-left":"0"},500);
				$(this).addClass("open");
				return false;
			} else {
				$('.aside').animate({"left":"0"}, 300);
				$('.page_container').animate({"padding-left":"220px"},500);
				$(this).removeClass("open");
			}
		});
		
		// tnb
		$('.depth1_tnb').on('focusin mouseenter', function(){
			if( !$('.tnb').hasClass('on') ) {
				$('.tnb').addClass('on');
				$(this).find('.snb').stop().slideDown('fast');
				return false;
			}
		}).on('focusout mouseleave', function(){
			if( $('.tnb').hasClass('on') ) {
				$('.tnb').removeClass('on');
				$(this).find('.snb').stop().slideUp(50);
				return false;
			}
			return false;
		});

		// popup
		var popup = $('.popup_wrap');
		if (popup){
			popup.each(function(i){
				var popWidth = popup.eq(i).width();
				var popHeight = popup.eq(i).height();
				if ( popup.eq(i).hasClass('valign_top')){
					var popStyle = "left:50%; width:"+popWidth+"px; margin-left:-"+popWidth/2+"px; top:0;"
				}else if (popup.eq(i).hasClass('valign_bottom')){
					var popStyle = "left:50%; width:"+popWidth+"px; margin-left:-"+popWidth/2+"px; bottom:0;"
				}else{
					var popStyle = "left:50%; width:"+popWidth+"px; margin-left:-"+popWidth/2+"px; top:50%; margin-top:-"+popHeight/2+"px;"
				}
				popup.eq(i).attr('style',popStyle);
				popup.eq(i).find('.ico_close').on('click', function(){
					if ($(this).hasClass('single')){
						popup.eq(i).animate({opacity:0}, 500, function(){
							popup.eq(i).hide();
						});
					}else{
						popup.eq(i).animate({opacity:0}, 500, function(){
							popup.eq(i).hide();
						});
						$('.blind_black').animate({opacity:0}, 500, function(){
							$('.blind_black').hide();
						});
					};
				});
			});
		};

		//tabmenu
		$.QueryString = (function(a) {
			if (a == "") return {};
			var b = {};
			for (var i = 0; i < a.length; ++i)
			{
				var p=a[i].split('=');
				if (p.length != 2) continue;
				b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
			}
			return b;
		})(window.location.search.substr(1).split('?'))
		var num = $.QueryString["tabNum"]

		var tabBtn = $('.tablist > li > a')
		var tabCon = $('.tabContents')

		tabBtn.each(function(j){
			if (tabBtn.eq(j).hasClass('active')){
				num = j;
			};
		});
		if (!num){
			num = "0";
		};
		tabBtn.removeClass('active');
		tabBtn.eq(num).addClass('active');
		tabCon.hide();
		tabCon.eq(num).show();

		tabBtn.click(function(){
			var num = tabBtn.index(this);
			tabCon.each(function(i){
				if(num == i){
					tabCon.hide();
					tabCon.eq(i).show();
					tabBtn.removeClass('active');
					tabBtn.eq(i).addClass('active');
					num = i;
				};
			});
		});
		
		// 게시판 이미지
		$('.item .photo img').bind('load',fitImg);
		$('.item .thumb img').bind('load',fitImg);

	})(jQuery);
})


function fitImg(e){
	var frWidth = $(this).width();
	var frHeight = $(this).height();

	var paWidth = $(this).parent().width();
	var paHeight = $(this).parent().height();

	//alert(frHeight+"-"+ paHeight);

	if (paHeight > frHeight){
		$(this).attr('style','top:0; margin-top:0;height:100%; width:auto;');
	}else{
			$(this).attr('style','top:50%; margin-top:-'+frHeight/2+'px;');
	}


}