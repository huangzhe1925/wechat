var num = 0;
var spaceDeg = 4;
var itemDeg = 30;
var maxItems = 7;

var lastPosX = 0;
var lastPosY = 0;
var posX = 0;
var posY = 0;
var centerBtn=null;

var transitionEvent;
var calculatedObj;

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
	}, {
		'id' : '1_3',
		'content' : 'List3',
		'sub' : [ {
			'id' : '1_3_1',
			'content' : 'List31',
			'sub' : []
		}, {
			'id' : '1_3_2',
			'content' : 'List32',
			'sub' : []
		} ]
	} ]
} ]);



$(function() {
	transitionEvent = whichTransitionEvent();
	var menuArr = getSubMenuArray('1', navObj);
	$.each(menuArr, function(ky, vl) {
		var index = ky + 1;
		$('.box').append(
				'<div id="navItem' + index + '" class="navItem"><a date-id="'
						+ vl.id + '" href="javascript:void(0);"><span>'
						+ vl.content + '</span></a></div>');
	});

	var itemCnt = $('.box').children().length - 2;
	calculatedObj = calculateAngle(itemCnt, spaceDeg, itemDeg);
	shrinkItems(0, calculatedObj);

	$('.box').show();

//	$('.centerbtn a').click(function() {
//		if ($('.box').attr('data-status') == 'close') {
//			$('.box').attr('data-status', 'open');
//			$(this).attr('data-id', '1');
//			extendItems(0.5, calculatedObj);
//		} else {
//			if ($(this).attr('data-id') == '1') {
//				shrinkItems(0.5, calculatedObj);
//				$('.box').attr('data-status', 'close');
//			} else {
//				var parId = getParentId($(this).attr('data-id'));
//				createNewItems(parId);
//				changeCenterButton(parId, getContentFromId(parId, navObj));
//			}
//
//		}
//	});
	
	bindItemClickEvent(calculatedObj);
	bindTranEndEvent(calculatedObj);
	
	centerBtn =new Hammer(document.getElementById('draggable'));

	centerBtn.get('pan').set({direction : Hammer.DIRECTION_ALL});

	centerBtn.on("pan panend", function(ev) {
		manageMultitouch($('#box'),ev);
	});
	
	centerBtn.on("tap pressup", function(ev) {
		if ($('.box').attr('data-status') == 'close') {
			$('.box').attr('data-status', 'open');
			$('.centerbtn a').attr('data-id', '1');
			extendItems(0.5, calculatedObj);
		} else {
			if ($('.centerbtn a').attr('data-id') == '1') {
				shrinkItems(0.5, calculatedObj);
				$('.box').attr('data-status', 'close');
			} else {
				var parId = getParentId($('.centerbtn a').attr('data-id'));
				createNewItems(parId);
				changeCenterButton(parId, getContentFromId(parId, navObj));
			}

		}
	});

});

function manageMultitouch(divObj,ev) {
	switch (ev.type) {
	case 'pan':
		posX = ev.deltaX + lastPosX;
		posY = ev.deltaY + lastPosY;
		break;
	case 'panend':
		lastPosX = posX;
		lastPosY = posY;
		break;
	}
	divObj.css('transition','all 0s');
	divObj.css('transform', 'translate(' + posX + 'px, ' + posY + 'px)');
}

function createNewItems(id) {
	var menuArr = getSubMenuArray(id, navObj);
	$('.box .navItem').remove();

	$.each(menuArr,function(ky, vl) {
		var index = ky + 1;
		$('.box').append('<div id="navItem'+ index+ '" class="navItem" style="display:none;"><a date-id="'+ vl.id+ '" href="javascript:void(0);"><span>'
				+ vl.content+ '</span></a></div>');
	});
	var itemCnt = $('.box').children().length - 2;
	calculatedObj = calculateAngle(itemCnt, spaceDeg, itemDeg);
	shrinkItems(0, calculatedObj);
	$('.box .navItem').show();
	extendItems(0.5, calculatedObj);
	bindItemClickEvent(calculatedObj);
	bindTranEndEvent(calculatedObj);
}

function bindTranEndEvent(calObj) {
	$('.box .navItem').each(function(i) {
		// animationend transitionend
		$(this).bind(transitionEvent,function() {
			if ($(this).children('a').attr('date-status') == 'clicked') {
				$(this).children('a').attr('date-status', '');
				// extendItems(calObj);
				var newDataId = $(this).children('a').attr('date-id');
					createNewItems(newDataId);
					changeCenterButton(newDataId,getContentFromId(newDataId,navObj));
			}
		});
	});
}

function changeCenterButton(dataId, content) {
	$('.box .centerbtn a span').html(content);
	$('.box .centerbtn a').attr('data-id', dataId);
}

function bindItemClickEvent(calObj) {
	$('.box .navItem').each(
		function(i) {
//			$(this).click(function() {
//				$(this).siblings(".navItem").unbind();
//				$(this).children('a').attr('date-status', 'clicked');
//				getTransStyle($(this),0.5, 100, 100,calObj[i].rotate, calObj[i].skew,0, 0);
//				$(this).siblings(".navItem").css({'opacity' : '0'});
//			});
			var that=$(this);
			new Hammer($(this)[0]).on("tap pressup", function(ev) {
				//that.siblings(".navItem").unbind();
				that.children('a').attr('date-status', 'clicked');
				getTransStyle(that,0.5, 100, 100,calObj[i].rotate, calObj[i].skew,0, 0);
				that.siblings(".navItem").css({'opacity' : '0'});
			});
	});

}

function shrinkItems(duration, calObj) {
	$('.box div[class="navItem"]').each(
			function(i) {
				getTransStyle($(this),duration, 100, 100, calObj[i].rotate,calObj[i].skew, 0, 0);
				var revertRotate = '-'+ (calObj[i].skew + parseInt(itemDeg / 2));
				getRevertTransStyle($(this).children('a'),duration, 50, 50, '-'+ calObj[i].skew, revertRotate, 0, 0);
			});
};

function extendItems(duration, calObj) {
	$('.box div[class="navItem"]').each(
			function(i) {
				if(i!=0){
					return;
				}
				getTransStyle($(this),duration, 100, 100, calObj[i].rotate,calObj[i].skew, 1, 1);
				var revertRotate = '-'
						+ (calObj[i].skew + parseInt(itemDeg / 2));
				getRevertTransStyle($(this).children('a'),duration, 50, 50, '-'+ calObj[i].skew, revertRotate, 1, 1);
				
			});
}

function calculateAngle(itemCnt, spaDeg, iteDeg) {
	var retVal = new Array();
	if (itemCnt <= 1) {
		var skew = 90 - iteDeg;
		var rotate = 90 - parseInt(iteDeg / 2);
		retVal.push(Object.create({
			'skew' : skew,
			'rotate' : rotate
		}));
	} else {
		var totalDeg = itemCnt * iteDeg + (itemCnt - 1) * spaDeg;
		var skew = 90 - iteDeg;
		for (var index = 1; index <= itemCnt; index++) {
			var rotate = 90 - parseInt(totalDeg / 2) + (index - 1)
					* (spaDeg + iteDeg);
			retVal.push(Object.create({
				'skew' : skew,
				'rotate' : rotate
			}));
		}
	}

	return retVal;
}

function getParentId(id) {
	if (id == null || id == '1') {
		console.log('getParentId error');
		return null;
	}

	return id.substring(0, id.lastIndexOf('_'));
}

function getSubMenuArray(id, json) {
	if (json == null) {
		return;
	}

	var retVal = null;
	$.each(json, function(ky, vl) {
		if (id.startWith(vl.id)) {
			if (id == vl.id) {
				retVal = vl.sub;
				return;
			} else {
				retVal = getSubMenuArray(id, vl.sub);
				return;
			}
		}

	});

	return retVal;
}

function getContentFromId(id, json) {
	if (json == null) {
		return '';
	}

	var retVal = '';
	$.each(json, function(ky, vl) {
		if (id == vl.id) {
			retVal = vl.content;
			return;
		} else {
			retVal = getContentFromId(id, vl.sub);
		}

	});

	return retVal;

}

function show(msg) {
	alert(msg);
}

function getTransStyle(divObj,duration, orgX, orgY, rotate, skew, scaleX, scaleY) {
	if(divObj==null){
		return;
	}
	divObj.css('transition','all '+duration+'s');
	divObj.css('transform-origin',orgX+'% '+orgY+'%');
	divObj.css('transform','scale('+scaleX+','+scaleY+') rotate('+ rotate + 'deg) skew(' + skew + 'deg)');
	divObj.css('-webkit-transform-origin',orgX+'% '+orgY+'%');
	divObj.css('-webkit-transform','scale('+scaleX+','+scaleY+') rotate('+ rotate + 'deg) skew(' + skew + 'deg)');
	divObj.css('-ms-transform-origin',orgX+'% '+orgY+'%');
	divObj.css('-ms-transform','scale('+scaleX+','+scaleY+') rotate('+ rotate + 'deg) skew(' + skew + 'deg)');
	divObj.css('-moz-transform-origin',orgX+'% '+orgY+'%');
	divObj.css('-moz-transform','scale('+scaleX+','+scaleY+') rotate('+ rotate + 'deg) skew(' + skew + 'deg)');
	divObj.css('-o-transform-origin',orgX+'% '+orgY+'%');
	divObj.css('-o-transform','scale('+scaleX+','+scaleY+') rotate('+ rotate + 'deg) skew(' + skew + 'deg)');
//	return 'transition: all ' + duration + 's;:' + orgX + '% '
//			+ orgY + '%;transform: scale(' + scaleX + ',' + scaleY
//			+ ') rotate(' + rotate + 'deg) skew(' + skew + 'deg);';
}

function getRevertTransStyle(divObj,duration, orgX, orgY, skew, rotate, scaleX, scaleY) {
	if(divObj==null){
		return;
	}
	divObj.css('transition','all '+duration+'s');
	divObj.css('transform-origin',orgX+'% '+orgY+'%');
	divObj.css('transform','scale('+scaleX+','+scaleY+') skew('+ skew + 'deg) rotate(' + rotate + 'deg)');
	divObj.css('-webkit-transform-origin',orgX+'% '+orgY+'%');
	divObj.css('-webkit-transform','scale('+scaleX+','+scaleY+') skew('+ skew + 'deg) rotate(' + rotate + 'deg)');
	divObj.css('-ms-transform-origin',orgX+'% '+orgY+'%');
	divObj.css('-ms-transform','scale('+scaleX+','+scaleY+') skew('+ skew + 'deg) rotate(' + rotate + 'deg)');
	divObj.css('-moz-transform-origin',orgX+'% '+orgY+'%');
	divObj.css('-moz-transform','scale('+scaleX+','+scaleY+') skew('+ skew + 'deg) rotate(' + rotate + 'deg)');
	divObj.css('-o-transform-origin',orgX+'% '+orgY+'%');
	divObj.css('-o-transform','scale('+scaleX+','+scaleY+') skew('+ skew + 'deg) rotate(' + rotate + 'deg)');
//	return 'transition: all ' + duration + 's;transform-origin:' + orgX + '% '
//			+ orgY + '%;transform:scale(' + scaleX + ',' + scaleY + ') skew('
//			+ skew + 'deg) rotate(' + rotate + 'deg);';
}

String.prototype.startWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	if (this.substr(0, str.length) == str)
		return true;
	else
		return false;
	return true;
};

String.prototype.endWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	if (this.substring(this.length - str.length) == str)
		return true;
	else
		return false;
	return true;
};

function whichTransitionEvent(){
    var el = document.createElement('fakeelement');  
    var transitions = {  
      'transition':'transitionend',  
      'OTransition':'oTransitionEnd',  
      'MozTransition':'transitionend',  
      'WebkitTransition':'webkitTransitionEnd',  
      'MsTransition':'msTransitionEnd'  
    };
  
    for(var t in transitions){
        if( el.style[t] !== undefined ){  
            return transitions[t];  
        }  
    }  
}  