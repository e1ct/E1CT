
$(document).ready(function(){ 
	/* hd */
	$('#nav ul li a').click(function(){
		$('#nav ul li').removeClass('active'); 
		$(this).parent().toggleClass('active'); 
	}); 
	$('.nav-open').click(function(){
		$('#nav').show(); 
	}); 
	$('.nav-close').click(function(){
		$('#nav').hide(); 
	});  

	/* ft */
	$("#ft_totop").on("click", function() {
		$("html, body").animate({scrollTop:0}, '500');
		return false;
	}); 
	
	/* sub */
//	$('.sub-nav .sub-depth h3').click(function(){
//		$(this).parent().find('ul').stop().slideToggle(); 
//		$(this).toggleClass('active'); 
//	}); 
	
	$(".sub-nav .sub-depth").hover(function() {
		$(this).children("ul").stop().slideToggle(); 
		$(this).children("h3").addClass('active');
	}, function() {
		$(this).children("ul").stop().slideToggle(); 
		$(this).children("h3").removeClass('active');
	})

	
	/* FAQ */
	
	$('#faq ul li h2').click(function(){
		$('.answer').stop().slideUp();
		$('#faq ul li h2').removeClass('active');
		$(this).parent().find('.answer').stop().slideToggle();
		$(this).toggleClass('active');
	});  

	 
}); 


$(".sub-nav .sub-depth h3").focusout(function() {
	$(".sub-nav .sub-depth h3.active").each(function(idx, item) {
		console.log( item );
		if( $(item).hasClass('active') ) {
			$(item).removeClass('active');
			$(item).siblings("ul").css("display", "none");
		}
	});
});