// JavaScript Document

$(document).ready(

function default_404 (){

	$(".default_shield").animate({opacity:"0"},300,function(){
	$(".default_shield").animate({opacity:"1"},300,default_404);
	}
	);

	$(".default_star_1").animate({opacity:"0"},600,function(){
	$(".default_star_1").animate({opacity:"1"},600,default_404); 
	}
	);

	$(".default_star_2").animate({opacity:"0"},750,function(){
	$(".default_star_2").animate({opacity:"0.7"},750,default_404);
	}
	);

	$(".default_star_3").animate({opacity:"0"},500,function(){
	$(".default_star_3").animate({opacity:"0.7"},500,default_404);
	}
	);
	
}
)


