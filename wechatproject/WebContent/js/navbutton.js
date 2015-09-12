var num = 0;
var spaceDeg = 4;
var itemDeg=30;
var maxItems=7;

var navObj = Object.create([ {
	'id' : '1',
	'content' : 'Menu',
	'sub' : [ {
		'id' : '1_1',
		'content' : 'List1',
		'sub' : []
	}, {
		'id' : '1_2',
		'content' : 'List2',
		'sub' : []
	},{
		'id' : '1_3',
		'content' : 'List3',
		'sub' : [{
			'id' : '1_3_1',
			'content' : 'List31',
			'sub' : []
		}, {
			'id' : '1_3_2',
			'content' : 'List32',
			'sub' : []
		}]
	} ]
} ]);

$(function() {
	var menuArr=getSubMenuArray('1',navObj);
	$.each(menuArr, function(ky, vl) {
		var index=ky+1;
		$('.box').append('<div id="navItem'+index+'" class="navItem"><a date-id="'+vl.id+'" href="#"><span>'+vl.content+'</span></a></div>');
	});
	
	var itemCnt=$('.box').children().length-2;
	
	var calObj=calculateAngle(itemCnt,spaceDeg,itemDeg);
//	$('.box div[id^="navItem"]').each(function(i){
//		 $(this).attr("class", 'navItem');
//		 $(this).attr('style',getTransStyle(0, 100, 100, calObj[i].rotate, calObj[i].skew));
//		 var revertRotate='-'+(calObj[i].skew+parseInt(itemDeg/2));
//		 $(this).children('a').attr('style',getRevertTransStyle(0, 50, 50, '-'+calObj[i].skew , revertRotate));
//	}); 
	shrinkItems(calObj);
	
	$('.box').show();
	
	
	$('.centerbtn a').click(function(){
		if($('.box').attr('data-status')=='close'){
			$('.box').attr('data-status','open');
			extendItems(calObj);
		}else{
			$('.box').attr('data-status','close');
			shrinkItems(calObj);
		}
	});
	
	$('.box .navItem').each(function(i){
		$(this).click(function(){
			$(this).children('a').attr('date-status','clicked');
			$(this).attr('style',getTransStyle(1, 100, 100, calObj[i].rotate, calObj[i].skew,0,0));
			$(this).siblings(".navItem").css({'opacity':'0'});
//			alert($(this).children('a').attr('date-id'));
		});
		//animationend transitionend
		$(this).bind('transitionend',function(){
			if($(this).children('a').attr('date-status')=='clicked'){
				$(this).children('a').attr('date-status','');
				extendItems(calObj);
				console.log($(this));
			}
		});
		
	});
	
	
	
	
//	$('#step').click(function() {
//		var calObj=calculateAngle(itemCnt,spaceDeg,itemDeg);
//		if (num == 0) {
//			$('.box div[id^="navItem"]').each(function(i){  
//				 $(this).attr("class", 'navItem');
//				 $(this).attr('style',getTransStyle(1, 100, 100, calObj[i].rotate, calObj[i].skew));
//				 var revertRotate='-'+(calObj[i].skew+parseInt(itemDeg/2));
//				 $(this).children('a').attr('style',getRevertTransStyle(1, 50, 50, '-'+calObj[i].skew , revertRotate));
//			}); 
//		} else if (num == 1) {
//			$('#navItem1').attr("class", 'demo11');
//		} else {
//			$('#navItem1').attr("class", '');
//			$('#navItem1').attr("style", '');
//			num = -1;
//		}
//
//		num++;
//	});
//
//	$('#demo2').bind('transitionend', function() {
//		if ($(this).hasClass('demo21')) {
//			show('demo2');
//		}
//	});
//	
//	console.log(getSubMenuArray('1_2',navObj));
});

function shrinkItems(calObj){
	$('.box div[class="navItem"]').each(function(i){
		$(this).attr('style',getTransStyle(1, 100, 100, calObj[i].rotate, calObj[i].skew,0,0));
		var revertRotate='-'+(calObj[i].skew+parseInt(itemDeg/2));
		$(this).children('a').attr('style',getRevertTransStyle(1, 50, 50, '-'+calObj[i].skew , revertRotate,0,0));
	});
}

function extendItems(calObj){
	$('.box div[class="navItem"]').each(function(i){
		$(this).attr('style',getTransStyle(1, 100, 100, calObj[i].rotate, calObj[i].skew,1,1));
		var revertRotate='-'+(calObj[i].skew+parseInt(itemDeg/2));
		$(this).children('a').attr('style',getRevertTransStyle(1, 50, 50, '-'+calObj[i].skew , revertRotate,1,1));
	}); 
}

function bindEvent(){
	$('.box .navItem').each(function(i){
		$(this).children('a').unbind();
		$(this).children('a').click(function(){
			
		});
	}); 
}

function calculateAngle(itemCnt,spaDeg,iteDeg){
	var retVal=new Array();
	if(itemCnt<=1){
		var skew=90-iteDeg;
		var rotate=90-parseInt(iteDeg/2);
		retVal.push(Object.create({'skew':skew,'rotate':rotate}));
	}else{
		var totalDeg=itemCnt*iteDeg+(itemCnt-1)*spaDeg;
		var skew=90-iteDeg;
		for(var index=1;index<=itemCnt;index++){
			var rotate=90-parseInt(totalDeg/2)+(index-1)*(spaDeg+iteDeg);
			retVal.push(Object.create({'skew':skew,'rotate':rotate}));
		}
	}
	
	
	return retVal;
	
}

function getSubMenuArray(id, json) {
	if(json==null){
		return;
	}
	
	var retVal=null;
	$.each(json, function(ky, vl) {
		if(id.startWith(vl.id)){
			if(id==vl.id){
				retVal= vl.sub;
				return;
			}else{
				retVal= getSubMenuArray(id,vl.sub);
				return;
			}
		}
		
	});
	
	return retVal;
}

function show(msg) {
	alert(msg);
}

function getTransStyle(duration, orgX, orgY, rotate, skew,scaleX,scaleY) {
	return 'transition: all ' + duration + 's;transform-origin:' + orgX + '% '
			+ orgY + '%;transform: scale('+scaleX+','+scaleY+') rotate(' + rotate + 'deg) skew(' + skew
			+ 'deg);';
}

function getRevertTransStyle(duration, orgX, orgY,skew , rotate,scaleX,scaleY) {
	return 'transition: all ' + duration + 's;transform-origin:' + orgX + '% '
			+ orgY + '%;transform:scale('+scaleX+','+scaleY+') skew(' + skew + 'deg) rotate(' + rotate + 'deg);';
}

String.prototype.startWith=function(str){  
    if(str==null||str==""||this.length==0||str.length>this.length)  
      return false;  
    if(this.substr(0,str.length)==str)  
      return true;  
    else  
      return false;  
    return true;  
}  
String.prototype.endWith=function(str){  
    if(str==null||str==""||this.length==0||str.length>this.length)  
      return false;  
    if(this.substring(this.length-str.length)==str)  
      return true;  
    else  
      return false;  
    return true;  
}  