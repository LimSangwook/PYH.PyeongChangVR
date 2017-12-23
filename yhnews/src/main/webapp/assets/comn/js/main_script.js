var popupList = function(){
  $('.popup_list ul').slick({
    infinite: true,
    dots:true,
    slidesToShow : 1,
    speed:1000,
    cssEase: 'linear',
    autoplay: true,
    fade : true,
    arrows : false,
    zindex : 1,
    })
}
popupList()
var mainSlider = function(){
  $('.visual_slide .asd').slick({
    infinite: true,
    dots:true,
    slidesToShow : 1,
    speed:500,
    autoplaySpeed:3000,
    cssEase: 'linear',
    autoplay: false,
    fade : true,
    arrows : false
    })
    $('.top_banner_contents ul').slick({
      infinite: true,
      dots:true,
      slidesToShow : 1,
      speed:1000,
      autoplaySpeed:2000,
      cssEase: 'linear',
      autoplay: false,
      arrows : true
      })
}
mainSlider()
