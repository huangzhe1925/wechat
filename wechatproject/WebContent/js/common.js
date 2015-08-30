function is_wechat_client() {
	var ua = navigator.userAgent.toLowerCase();
	if (ua.match(/MicroMessenger/i) == "micromessenger") {
		return true;
	} else {
		return false;
	}
}

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

if(!is_wechat_client()){
	window.location.href=getContextPath()+'/errorpages/wchatbrowseerror';
}

$(function(){
	
});




