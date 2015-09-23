function NavButtonJs(){
	this.comSel='#naviBtnWarpper ';
	this.boxSel='#naviBtnWarpper .box ';
	this.centerBtnSel='#naviBtnWarpper .centerbtn ';
	this.centerBtnASel='#naviBtnWarpper .centerbtn a ';
	this.centerBtnASpanSel='#naviBtnWarpper .centerbtn a span ';
	this.navItemClass='navItem';
	this.navItemsSel=this.boxSel+'.'+this.navItemClass;
	this.spaceDeg = 4;
	this.itemDeg = 30;
	this.maxItems = 7;
	this.lastPosX = 0;
	this.lastPosY = 0; 
	this.posX = 0;
	this.posY = 0;
	this.centerBtn=null;
	this.fakecenterBtn=null;
	this.transitionEvent=null;
	this.calculatedObj=null;
	this.navItemJson=null;
	
	var that=this;
	
	this.initNavBtnJs=function(opt){
		this.navItemJson=opt.navItemJson;
		
		//--------init component
		this.transitionEvent = this.whichTransitionEvent();
		var menuArr = this.getItemObjFromId('1', that.navItemJson).sub;
		$.each(menuArr, function(ky, vl) {
			//var index = ky + 1;
			var realLink="javascript:void(0)";
			$(that.boxSel).append('<div class="'+that.navItemClass+'"><a data-id="'+ vl.id + '" href="'+realLink+'"><span>'+ vl.content + '</span></a></div>');
		});
		
		
		this.changeCenterButton(parseInt(this.navItemJson[0].id),this.navItemJson[0].content);
		var itemCnt = $(this.navItemsSel).length;
		this.calculatedObj = this.calculateAngle(itemCnt, this.spaceDeg, this.itemDeg);
		this.setItemsAndLinkReady();
		this.shrinkItems(0);

		this.bindItemClickEvent(this.calculatedObj);
		this.bindTranEndEvent(this.calculatedObj);
		
		this.centerBtn =new Hammer($(this.centerBtnASel)[0]);

		this.centerBtn.get('pan').set({direction : Hammer.DIRECTION_ALL});

		this.centerBtn.on("pan panend", function(ev) {
			that.manageMultitouch($(that.comSel),ev);
		});
		
		this.centerBtn.on("tap pressup", function(ev) {
			if ($(that.boxSel).attr('data-status') == 'close') {
				$(that.boxSel).attr('data-status', 'open');
				$(that.centerBtnASel).attr('data-id', '1');
				$(that.boxSel).show();
				setTimeout(function(){
					that.extendItems(0.5);
				},5);
			} else {
				if ($(that.centerBtnASel).attr('data-id') == '1') {
					that.shrinkItems(0.5);
					$(that.boxSel).attr('data-status', 'close');
				} else {
					var parId = that.getParentId($(that.centerBtnASel).attr('data-id'));
					that.createNewItems(parId);
					that.changeCenterButton(parId, that.getItemObjFromId(parId, that.navItemJson).content);
				}

			}
		});
		
	};
	
	
	this.whichTransitionEvent=function(){
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
	};
	
	this.changeCenterButton=function(dataId, content) {
		$(this.centerBtnASpanSel).html(content);
		$(this.centerBtnASel).attr('data-id', dataId);
	};
	
	this.calculateAngle=function(itemCnt, spaDeg, iteDeg) {
		var retVal =[];
		if (itemCnt <= 1) {
			var skew = 90 - iteDeg;
			var rotate = 90 - parseInt(iteDeg / 2);
			retVal.push(Object.create({'skew' : skew,'rotate' : rotate}));
		} else {
			var totalDeg = itemCnt * iteDeg + (itemCnt - 1) * spaDeg;
			var skew = 90 - iteDeg;
			for (var index = 1; index <= itemCnt; index++) {
				var rotate = 90 - parseInt(totalDeg / 2) + (index - 1)
						* (spaDeg + iteDeg);
				retVal.push(Object.create({'skew' : skew,'rotate' : rotate}));
			}
		}

		return retVal;
	};
	
	
	this.setItemsAndLinkReady=function(){
		duration=0;
		$(this.navItemsSel).each(function(i) {
			var revertRotate = '-'+ (that.calculatedObj[i].skew + parseInt(that.itemDeg / 2));
			that.getRevertTransStyle($(this).children('a'),duration, 50, 50, '-'+ that.calculatedObj[i].skew, revertRotate, 1, 1);
			that.getTransStyle($(this),duration, 100, 100, that.calculatedObj[i].rotate,that.calculatedObj[i].skew, 1, 1);
		});
	};
	
	this.shrinkItems=function(duration,index) {
		var totalNum=$(this.navItemsSel).length;
		if(index==null){
			index=0;
		}
		if(index>=totalNum){
			return;
		}
		$(that.navItemsSel).each(function(i) {
			if(i!=index&&duration!=0){
				return;
			}
			that.getTransStyle($(this),duration, 100, 100, that.calculatedObj[i].rotate,that.calculatedObj[i].skew, 0, 0);
			if(duration!=0){
				index++;
				setTimeout(function(){that.shrinkItems(duration,index);},20);
				return false;
			}
		});
	};
	
	
	this.getTransStyle=function(divObj,duration, orgX, orgY, rotate, skew, scaleX, scaleY) {
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
	};

	this.getRevertTransStyle=function(divObj,duration, orgX, orgY, skew, rotate, scaleX, scaleY) {
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
	};
	

	this.bindTranEndEvent=function() {
		$(that.navItemsSel).each(function(i) {
			// animationend transitionend
			$(this).bind(that.transitionEvent,function() {
				if ($(this).children('a').attr('data-status') == 'clicked') {
					$(this).children('a').attr('data-status', '');
					var newDataId = $(this).children('a').attr('data-id');
					that.createNewItems(newDataId);
					that.changeCenterButton(newDataId,that.getItemObjFromId(newDataId,that.navItemJson).content);
				}
				if($(this).parent().attr('data-status')=='close'){
					$(this).parent().hide();
				}
			});
		});
		
	};


	this.bindItemClickEvent=function(calObj) {
		$(that.navItemsSel).each(
			function(i) {
				var thatNav=$(this);
				new Hammer($(this)[0]).on("tap pressup", function(ev) {
					var dataId=thatNav.children('a').attr('data-id');
					var itemObj=that.getItemObjFromId(dataId,that.navItemJson);
					var link=itemObj.link;
					if(link!='#'){
						var realLink=getContextPath()+link;
						window.location.href=realLink;
					}else{
						thatNav.children('a').attr('data-status', 'clicked');
						that.getTransStyle(thatNav,0.5, 100, 100,that.calculatedObj[i].rotate, that.calculatedObj[i].skew,0, 0);
						thatNav.siblings('.'+that.navItemClass).css({'opacity' : '0'});
					}
					
					
				});
		});
	};
	
	this.getParentId=function(id) {
		if (id == null || id == '1') {
			console.log('getParentId error');
			return null;
		}
		return id.substring(0, id.lastIndexOf('_'));
	};

	this.getItemObjFromId=function(id, json) {
		if (json == null) {
			return '';
		}

		var retVal = '';
		$.each(json, function(ky, vl) {
			if (id == vl.id) {
				retVal = vl;
				return false;
			} else {
				var tempretVal = that.getItemObjFromId(id, vl.sub);
				if(tempretVal!=''){
					retVal=tempretVal;
					return false;
				}
			}

		});
		return retVal;
	};
	

	this.extendItems=function(duration,index) {
		var totalNum=$(that.navItemsSel).length;
		if(index==null){
			index=0;
		}
		if(index>=totalNum){
			return;
		}
		$(that.navItemsSel).each(function(i) {
			if(i!=index&&duration!=0){
				return;
			}
			that.getTransStyle($(this),duration, 100, 100, that.calculatedObj[i].rotate,that.calculatedObj[i].skew, 1, 1);
			if(duration!=0){
				index++;
				setTimeout(function(){that.extendItems(duration,index);},20);
				return false;
			}
		});
	};
	
	this.manageMultitouch=function(divObj,ev) {
		switch (ev.type) {
			case 'pan':
				that.posX = ev.deltaX + that.lastPosX;
				that.posY = ev.deltaY + that.lastPosY;
				break;
			case 'panend':
				that.lastPosX = that.posX;
				that.lastPosY = that.posY;
				break;
		}
		divObj.css('transition','all 0s');
		divObj.css('transform', 'translate(' + that.posX + 'px, ' + that.posY + 'px)');
	};



	this.createNewItems=function(id) {
		var menuArr = that.getItemObjFromId(id, that.navItemJson).sub;
		$(that.navItemsSel).remove();

		$.each(menuArr,function(ky, vl) {
			var realLink='javascript:void(0);';
			$(that.boxSel).append('<div class="'+that.navItemClass+'" style="display:none;"><a data-id="'+ vl.id+ '" href="'+realLink+'"><span>'+ vl.content+ '</span></a></div>');
		});
		var itemCnt = $(that.navItemsSel).length;
		that.calculatedObj = that.calculateAngle(itemCnt, that.spaceDeg, that.itemDeg);
		that.setItemsAndLinkReady();
		that.shrinkItems(0);
		$(that.navItemsSel).show();
		that.extendItems(0.5);
		that.bindItemClickEvent();
		that.bindTranEndEvent();
	};

}

function NavButtonUtilJs(){
	this.createNavBtn=function(opt){
		window.navButtonJs=new NavButtonJs();
		navButtonJs.initNavBtnJs(opt);
	};

};



window.navBtnUJS=new NavButtonUtilJs();

$(function() {
	var navObj = Object.create([{
		'id' : '1',
		'content' : 'Menu',
		'link':'#',
		'sub' : [ {
			'id' : '1_1',
			'content' : 'List1',
			'link':'#',
			'sub' : [ {
				'id' : '1_1_1',
				'content' : 'Wel',
				'link':'/siteManage/welcomepage',
				'sub' : []
			}, {
				'id' : '1_1_2',
				'content' : 'Scr',
				'link':'/siteManage/testScroll',
				'sub' : []
			},{
				'id' : '1_1_3',
				'content' : 'Htt',
				'link':'/siteManage/testHttpProxy',
				'sub' : []
			} ]
		}, {
			'id' : '1_2',
			'content' : 'Sys',
			'link':'#',
			'sub' : [ {
				'id' : '1_2_1',
				'content' : 'Dep',
				'link':'/siteManage/deploypage',
				'sub' : []
			}, {
				'id' : '1_2_2',
				'content' : 'Fil',
				'link':'/siteManage/systemfiles',
				'sub' : []
			} ]
		}, {
			'id' : '1_3',
			'content' : 'Tes',
			'link':'#',
			'sub' : [ {
				'id' : '1_3_1', 
				'content' : 'List31',
				'link':'#',
				'sub' : []
			}, {
				'id' : '1_3_2',
				'content' : 'List32',
				'link':'#',
				'sub' : []
			} ]
		} ]
	}]);
	
	navBtnUJS.createNavBtn(Object.create({navItemJson:navObj}));
	
});






