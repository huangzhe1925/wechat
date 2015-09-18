function ScrollComJs() {
	this.initScrollCompo = function(opt) {
		this.comId = opt.comId;
		this.comDivId = opt.comId + '_ScrollCompo_DIV';
		this.wapperDivSel = '#' + this.comDivId + " > #warpper";
		this.scrollDivSel = '#' + this.comDivId + " > #warpper > #scroller ";
		this.afterPullUpSpaceDivSel = '#' + this.comDivId + " > #warpper > #scroller > #afterPullUpSpace";
		this.beforePullUpSpaceDivSel = '#' + this.comDivId + " > #warpper > #scroller > #beforePullUpSpace";
		this.mode = parseInt(opt.mode);
		this.scrollUpAction=opt.scrollUpAction;
		this.scrollDownAction=opt.scrollDownAction;
		this.scrollerBottomHeight=parseInt(opt.scrollerBottomHeight);
		this.scrollEndAction=opt.scrollEndAction;

		// parameters for page scroll
		this.myScroll = null;

		 this.pullDownEl = $(this.scrollDivSel + ' > .pullDown');
		 this.pullUpEl = $(this.scrollDivSel + ' > .pullUp');
		 this.afterPullUpSpaceDiv=$(this.afterPullUpSpaceDivSel);
		 this.beforePullUpSpaceDiv=$(this.beforePullUpSpaceDivSel);
		 
		 //some private parameters 
		 this.hideLoadMoreFlag=false;

	};

	var that = this;

	this.showSomething = function(something) {
		//console.log(something);
	};

	this.setWapperHeight = function(warpperMode,bottomHeight) {
		if (typeof (bottomHeight) == "undefined") {
			bottomHeight = 1;// need to change if style changed
		}
		this.beforePullUpSpaceDiv.height(0);
		this.beforePullUpSpaceDiv.width(0);
		this.afterPullUpSpaceDiv.height(0);
		this.afterPullUpSpaceDiv.width(0);
		if (warpperMode == 1) {
			var clientHeight = document.body.clientHeight;
			this.showSomething('bodyHeight ' + clientHeight);
			var headHeight = $(this.wapperDivSel).offset().top;
			this.showSomething('headHeight ' + headHeight);
			var wapperWidth = $(this.wapperDivSel).width();
			var wapperHeight = $(this.wapperDivSel).height();
			wapperHeight=clientHeight-headHeight-bottomHeight;
			$(this.wapperDivSel).height(wapperHeight)
			this.showSomething('wapperHeight ' + wapperHeight);
			var scrollHeight = $(this.scrollDivSel).height();
			this.showSomething('scrollHeight ' + scrollHeight);
			if (wapperHeight >= scrollHeight) {
				this.beforePullUpSpaceDiv.height(wapperHeight - scrollHeight + 1);
				this.beforePullUpSpaceDiv.width(wapperWidth);
				this.showSomething('beforePullUpSpaceDivSel ' + this.beforePullUpSpaceDiv.height());
			}
			this.showSomething('wapperHeight ' + $(this.wapperDivSel).height());
			this.showSomething('scrollHeight ' + $(this.scrollDivSel).height());
		}else if(warpperMode == 2){
			var clientHeight = document.body.clientHeight;
			this.showSomething('bodyHeight ' + clientHeight);
			var headHeight = $(this.wapperDivSel).offset().top;
			this.showSomething('headHeight ' + headHeight);
			var wapperWidth = $(this.wapperDivSel).width();
			var wapperHeight = $(this.wapperDivSel).height();
			wapperHeight=clientHeight-headHeight-bottomHeight;
			$(this.wapperDivSel).height(wapperHeight)
			this.showSomething('wapperHeight ' + wapperHeight);
			var scrollHeight = $(this.scrollDivSel).height();
			this.showSomething('scrollHeight ' + scrollHeight);
		}
	};

	this.hidePullDownDiv = function(hideMode) {
		if(hideMode==1){
			var pullDownHeight = this.pullDownEl.height();
			this.showSomething('pullDownEl.height ' + pullDownHeight);
			that.myScroll.scroller.style.marginTop = "-" + pullDownHeight + "px";
		}else if(hideMode==2){
			var wapperHeight = $(this.wapperDivSel).height();
			var scrollHeight = $(this.scrollDivSel).height();
			if(scrollHeight>wapperHeight){
				var pullDownHeight = this.pullDownEl.height();
				this.showSomething('pullDownEl.height ' + pullDownHeight);
				that.myScroll.scroller.style.marginTop = "-" + pullDownHeight + "px";
			}else{
				this.pullDownEl.hide();
			}
		}else if(hideMode == 3){
			
		}
	};

	this.hidePullUpDiv = function(hideMode,hideHeight,needToHidePullUp) {
		if (hideMode == 1) {
			var wapperHeight = $(this.wapperDivSel).height();
			var scrollHeight = $(this.scrollDivSel).height();
			var beforeSpaceHeight = $(this.beforePullUpSpaceDivSel).height();
			//warpper is smaller then scroller, means this component cant scroll, just hide pullUpEl
			if (scrollHeight<=wapperHeight) {
				if(typeof(needToHidePullUp) == "undefined"){
					needToHidePullUp = true;
				}
				if(needToHidePullUp){
					this.pullUpEl.hide();
				}
			}//scroller is bigger then warpper, but scroller is not big enough to hide hideHeight
			else if(scrollHeight>wapperHeight && scrollHeight-hideHeight>wapperHeight) {
				this.myScroll.maxScrollY += hideHeight;
			} else if(scrollHeight>wapperHeight && scrollHeight-hideHeight<=wapperHeight){
				beforeSpaceHeight += hideHeight;
				this.beforePullUpSpaceDiv.height(beforeSpaceHeight);
				this.showSomething('beforePullUpSpaceDivSel ' + beforeSpaceHeight);
			}
		}else if(hideMode == 2){
			var wapperHeight = $(this.wapperDivSel).height();
			var scrollHeight = $(this.scrollDivSel).height();
			if(scrollHeight>wapperHeight && scrollHeight-hideHeight>wapperHeight){
				this.myScroll.maxScrollY += hideHeight;
			}else{
				this.pullUpEl.hide();
			}
		}else if(hideMode == 3){
			if(this.hideLoadMoreFlag){
				return;
			}
			var wapperHeight = $(this.wapperDivSel).height();
			var wapperWidth = $(this.wapperDivSel).width();
			var scrollHeight = $(this.scrollDivSel).height();
			var beforeSpaceHeight = $(this.beforePullUpSpaceDivSel).height();
			//warpper is smaller then scroller, means this component cant scroll, just hide pullUpEl
			if (wapperHeight >= scrollHeight) {
				beforeSpaceHeight += (wapperHeight-scrollHeight+hideHeight);
				this.beforePullUpSpaceDiv.height(beforeSpaceHeight);
				this.beforePullUpSpaceDiv.width(wapperWidth);
				this.showSomething('beforePullUpSpaceDivSel ' + this.beforePullUpSpaceDiv.height());
			}//scroller is bigger then warpper, but scroller is not big enough to hide hideHeight
			else if(scrollHeight>wapperHeight && scrollHeight-hideHeight>wapperHeight) {
				this.myScroll.maxScrollY += hideHeight;
			} else if(scrollHeight>wapperHeight && scrollHeight-hideHeight<=wapperHeight){
				beforeSpaceHeight += (wapperHeight-scrollHeight+hideHeight);
				$(this.beforePullUpSpaceDivSel).height(beforeSpaceHeight);
				this.showSomething('beforePullUpSpaceDivSel ' + beforeSpaceHeight);
			}
		}else if(hideMode == 4){
//			if(this.hideLoadMoreFlag){
//				return;
//			}
			var wapperHeight = $(this.wapperDivSel).height();
			var scrollHeight = $(this.scrollDivSel).height();
			var beforeSpaceHeight = $(this.beforePullUpSpaceDivSel).height();
			//warpper is smaller then scroller, means this component cant scroll, just hide pullUpEl
			if (scrollHeight<=wapperHeight) {
				if(typeof(needToHidePullUp) == "undefined"){
					needToHidePullUp = true;
				}
				if(needToHidePullUp){
					this.pullUpEl.hide();
				}
			}//scroller is bigger then warpper, but scroller is not big enough to hide hideHeight
			else if(scrollHeight>wapperHeight && scrollHeight-hideHeight>wapperHeight) {
				this.myScroll.maxScrollY += hideHeight;
			} else if(scrollHeight>wapperHeight && scrollHeight-hideHeight<=wapperHeight){
				beforeSpaceHeight += hideHeight;
				this.beforePullUpSpaceDiv.height(beforeSpaceHeight);
				this.showSomething('beforePullUpSpaceDivSel ' + beforeSpaceHeight);
			}
		}
	};
	
	this.onRefreshScroll = function(onRefreshScrollMode) {
		if(onRefreshScrollMode == 1){
			if (this.pullDownEl.attr('data-state').match('loading')) {
				this.pullDownEl.attr('data-state','');
				this.pullDownEl.children('.pullDownLabel').html('下拉刷新...');
			} else if (this.pullUpEl.attr('data-state').match('loading')) {
				this.pullUpEl.attr('data-state','');
				this.pullUpEl.children('.pullUpLabel').html('上拉加载更多...');
			}
		}else if(onRefreshScrollMode == 2){
			if (this.pullUpEl.attr('data-state').match('loading')) {
				this.pullUpEl.attr('data-state','');
				this.pullUpEl.children('.pullUpLabel').html('上拉加载更多...');
			}
		}
	};

	this.onScroll = function(onScrollMode) {
		var pullUpHeight = this.pullUpEl.height();
		var pullDownHeight = this.pullDownEl.height();
		if (onScrollMode == 1) {

			if (pullDownHeight < parseInt(this.myScroll.y) && !this.pullDownEl.attr('data-state').match("flip")) {
				this.pullDownEl.attr('data-state','flip');
				this.pullDownEl.children(".pullDownLabel").html('松手开始更新...');
			}
			
			if (this.myScroll.maxScrollY - pullUpHeight >= parseInt(this.myScroll.y) && !this.pullUpEl.attr('data-state').match("flip")) {
				// Adjust max height to show loading style
				this.myScroll.maxScrollY -= pullUpHeight;
				this.pullUpEl.attr('data-state','flip');
				this.pullUpEl.children('.pullUpLabel').html('松手开始更新...');
			}
			
		}else if(onScrollMode == 2){
			var wapperHeight = $(this.wapperDivSel).height();
			var scrollHeight = $(this.scrollDivSel).height();
			if(scrollHeight>wapperHeight){
				if(scrollHeight-wapperHeight>pullUpHeight){
					if (this.myScroll.maxScrollY - pullUpHeight >= parseInt(this.myScroll.y) && !this.pullUpEl.attr('data-state').match("flip")) {
						// Adjust max height to show loading style
						this.myScroll.maxScrollY -= pullUpHeight;
						this.pullUpEl.attr('data-state','flip');
						this.pullUpEl.children('.pullUpLabel').html('松手开始更新...');
					}
				}
				if (pullDownHeight < parseInt(this.myScroll.y) && !this.pullDownEl.attr('data-state').match("flip")) {
					this.pullDownEl.attr('data-state','flip');
					this.pullDownEl.children(".pullDownLabel").html('松手开始更新...');
				}
			}
		}else if(onScrollMode == 3){
			if(!this.hideLoadMoreFlag){
				if (this.myScroll.maxScrollY - pullUpHeight >= parseInt(this.myScroll.y) && !this.pullUpEl.attr('data-state').match("flip")) {
					// Adjust max height to show loading style
					this.myScroll.maxScrollY -= pullUpHeight;
					this.pullUpEl.attr('data-state','flip');
					this.pullUpEl.children('.pullUpLabel').html('松手开始更新...');
				}
			}
		}else if(onScrollMode == 4){
			if (pullDownHeight < parseInt(this.myScroll.y) && !this.pullDownEl.attr('data-state').match("flip")) {
				this.pullDownEl.attr('data-state','flip');
				this.pullDownEl.children(".pullDownLabel").html('松手开始更新...');
			}
			
			if(!this.hideLoadMoreFlag){
				if (this.myScroll.maxScrollY - pullUpHeight >= parseInt(this.myScroll.y) && !this.pullUpEl.attr('data-state').match("flip")) {
					// Adjust max height to show loading style
					this.myScroll.maxScrollY -= pullUpHeight;
					this.pullUpEl.attr('data-state','flip');
					this.pullUpEl.children('.pullUpLabel').html('松手开始更新...');
				}
			}
		}
	};
	
	this.onScrollEnd=function(onScrollEndMode){
		if(onScrollEndMode == 1){
			if (this.pullDownEl.attr('data-state').match("flip")) {
				// To show refreshing style
				var pullDownHeight=this.pullDownEl.height();
				this.myScroll.scroller.style.marginTop = "0";
				this.pullDownEl.attr('data-state','loading');
				this.pullDownEl.children('.pullDownLabel').html('加载中...');
				setTimeout(function(){that.processScrollDownCallBackFunc()},50);
			}
			
			if (this.pullUpEl.attr('data-state').match("flip")) {
				this.pullUpEl.attr('data-state','loading');
				this.pullUpEl.children('.pullUpLabel').html('加载中...');
				setTimeout(function(){that.processScrollUpCallBackFunc()},50);
			}
		}else if(onScrollEndMode == 2){
			var wapperHeight = $(this.wapperDivSel).height();
			var scrollHeight = $(this.scrollDivSel).height();
			if(scrollHeight>wapperHeight){
				if (this.pullDownEl.attr('data-state').match("flip")) {
					// To show refreshing style
					var pullDownHeight=this.pullDownEl.height();
					this.myScroll.scroller.style.marginTop = "0";
					this.pullDownEl.attr('data-state','loading');
					this.pullDownEl.children('.pullDownLabel').html('加载中...');
					setTimeout(function(){that.processScrollDownCallBackFunc()},50);
				}
				
				if (this.pullUpEl.attr('data-state').match("flip")) {
					this.pullUpEl.attr('data-state','loading');
					this.pullUpEl.children('.pullUpLabel').html('加载中...');
					setTimeout(function(){that.processScrollUpCallBackFunc()},50);
				}
			}
		}else if(onScrollEndMode == 3){
			if (this.pullUpEl.attr('data-state').match("flip")) {
				this.pullUpEl.attr('data-state','loading');
				this.pullUpEl.children('.pullUpLabel').html('加载中...');
				setTimeout(function(){that.processScrollUpCallBackFunc()},50);
			}
			that.processScrollEndAction();
		}
	};
	
	
	this.processScrollDownCallBackFunc=function(){
		var actStr=this.scrollDownAction.replace('options',JSON.stringify(this.getReturnOptions()));
		eval(actStr);
	};
	
	this.processScrollUpCallBackFunc=function(){
		var actStr=this.scrollUpAction.replace('options',JSON.stringify(this.getReturnOptions()));
		eval(actStr);
	};
	
	this.processScrollEndAction=function(){
		var actStr=this.scrollEndAction.replace('options',JSON.stringify(this.getReturnOptions()));
		eval(actStr);
	};
	
	this.getReturnOptions=function(){
		var options = new Object();
		options.maxScrollY=this.myScroll.maxScrollY;
		options.y=this.myScroll.y;
		options.warpperHeight=$(this.wapperDivSel).height();
		options.scrollHeight = $(this.scrollDivSel).height();
		return options;
	};
	
	this.refreshScroll=function(){
		if (this.mode == 1) {
			this.setWapperHeight(1,that.scrollerBottomHeight);
		}else if(this.mode == 2){
			this.setWapperHeight(2,that.scrollerBottomHeight);
		}else if(this.mode == 3){
			this.pullDownEl.hide();
			this.setWapperHeight(1,that.scrollerBottomHeight);
		}else if(this.mode == 4){
			this.setWapperHeight(1,that.scrollerBottomHeight);
		}
		this.myScroll.refresh();
	};
	
	this.destroyScroll=function(){
		if(this.myScroll==null){
			return;
		}
		this.beforePullUpSpaceDiv.height(0);
		this.beforePullUpSpaceDiv.width(0);
		this.afterPullUpSpaceDiv.height(0);
		this.afterPullUpSpaceDiv.width(0);
		
		this.myScroll=null;
	};
	
	this.hideLoadMore=function(){
		if(this.mode == 3){
			this.hideLoadMoreFlag=true;
			this.pullUpEl.hide();
            this.showSomething('hideLoadMore ' + this.hideLoadMoreFlag);
		}else if(this.mode == 4){
			this.hideLoadMoreFlag=true;
			this.pullUpEl.hide();
            this.showSomething('hideLoadMore ' + this.hideLoadMoreFlag);
		}else{
			alert('HideLoadMore only support mode 3 and 4!');
			return;
		}
	};
	
	this.showLoadMore=function(){
		if(this.mode == 3){
			this.hideLoadMoreFlag=false;
			this.pullUpEl.show();
            this.showSomething('hideLoadMore ' + this.hideLoadMoreFlag);
		}else if(this.mode == 4){
			this.hideLoadMoreFlag=false;
			this.pullUpEl.show();
            this.showSomething('hideLoadMore ' + this.hideLoadMoreFlag);
		}else{
			alert('ShowLoadMore only support mode 3 and 4!');
			return;
		}
	};
	
	this.clearDiv=function(){
		this.destroyScroll();
		$('#'+this.comDivId).hide();
	}
	
	this.scrollTo=function(x,y,time,easing){
		this.myScroll.scrollTo(x,y,time,easing);
	}
	
	
	/*
	 * 初始化滑动分页控件
	 */
	this.initScroller = function(opts) {
		
		this.pullDownEl.show();
		 this.pullUpEl.show();
		
		// default input parameters
		var inputOptions = Object.create({
			probeType : 1,
			preventDefault : true,
			click : true,
			scrollbars : true,
			mouseWheel : true,
			interactiveScrollbars : true,
			shrinkScrollbars : 'scale',
			fadeScrollbars : true
		});// scrollbars:true
		for ( var i in opts) {
			inputOptions[i] = opts[i];
		}

		// before init myScroll need to set page height first, no needed because refresh activited each time refresh
//		if(this.mode==3){
//			this.setWapperHeight(1,this.scrollerBottomHeight);
//		}

		this.myScroll = new IScroll('#' + this.comDivId + " > #warpper", inputOptions);

		// make the content always scrollable, set height to
		this.myScroll.on("refresh", function() {
			if (that.mode == 1) {
				//set before the refresh start 
				//that.setWapperHeight(1,that.scrollerBottomHeight);
				that.hidePullDownDiv(1);
				that.hidePullUpDiv(1,that.pullDownEl.height()+that.pullUpEl.height());
				that.onRefreshScroll(1);
			}else if(that.mode == 2){
				//set before the refresh start 
				//that.setWapperHeight(2,that.scrollerBottomHeight);
				that.hidePullDownDiv(2);
				that.hidePullUpDiv(2,that.pullDownEl.height()+that.pullUpEl.height());
				that.onRefreshScroll(1);
			}else if(that.mode == 3){
				that.hidePullDownDiv(3);
				that.hidePullUpDiv(3,that.pullUpEl.height());
				that.onRefreshScroll(2);
			}else if(that.mode == 4){
				that.hidePullDownDiv(1);
				that.hidePullUpDiv(4,that.pullUpEl.height(),false);
				that.onRefreshScroll(1);
			}
		});

		this.myScroll.on("scroll", function() {
			if (that.mode == 1) {
				that.onScroll(1);
			}else if(that.mode == 2){
				that.onScroll(2);
			}else if(that.mode == 3){
				that.onScroll(3);
			}else if(that.mode == 4){
				that.onScroll(4);
			}
		});

		 this.myScroll.on("scrollEnd",function() {
			 if (that.mode == 1) {
				that.onScrollEnd(1);
			}else if(that.mode == 2){
				that.onScrollEnd(2);
			}else if(that.mode == 3){
				that.onScrollEnd(3);
			}else if(that.mode == 4){
				that.onScrollEnd(1);
			}
		 });

        this.refreshScroll();

	}
};

function ScrollContainerJsUtil() {
	this.createScrollObj = function(opt) {
		var comObjid = opt.comId;
		var comObj = comObjid + "_ScrollCompo";
		var cmd = 'window.' + comObj + '=new ScrollComJs();';
		eval(cmd);
		var options = new Object();
		for ( var i in opt) {
			options[i] = opt[i];
		}
		options['comId'] = comObjid
		eval(comObj + '.initScrollCompo(options);');
	};

	this.getSccollObj = function(comId) {
		return eval(comId + '_ScrollCompo');
	}
}

window.scrContJsUtil = new ScrollContainerJsUtil();

$(function() {
	$('input[name="scrollComInfo"]').each(function(index) {
		// 0:comId 1:mode 2.scrollUpAction 3.scrollDownAction 4.scrollerBottomHeight 5.scrollEndAction
		var infos = $(this).val().split('|');
		scrContJsUtil.createScrollObj({
			comId : infos[0],
			mode : infos[1],
			scrollUpAction:infos[2],
			scrollDownAction:infos[3],
			scrollerBottomHeight:infos[4],
			scrollEndAction:infos[5]
		});
		
	});
	//console.log(scrContJsUtil.getSccollObj('anyId'));
});
