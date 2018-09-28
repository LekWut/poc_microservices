/*Prevent the backspace key from navigating back.*/
$(document).unbind('keydown').bind('keydown', function (event) {
    var doPrevent = false;
    if (event.keyCode === 8) {
        var d = event.srcElement || event.target;
        if ((d.tagName.toUpperCase() === 'INPUT' && 
             (
                 d.type.toUpperCase() === 'TEXT' ||
                 d.type.toUpperCase() === 'PASSWORD' || 
                 d.type.toUpperCase() === 'FILE' || 
                 d.type.toUpperCase() === 'EMAIL' || 
                 d.type.toUpperCase() === 'SEARCH' || 
                 d.type.toUpperCase() === 'DATE' )
             ) || 
             d.tagName.toUpperCase() === 'TEXTAREA') {
            doPrevent = d.readOnly || d.disabled;
        }
        else {
            doPrevent = true;
        }
    }
    /*-- เพิ่มเติมป้องกัน Enter ที่ input --*/
    if (event.keyCode === 13) {
		var d = event.srcElement || event.target;
		var notDisableEnterClass = (' ' + d.className + ' ').indexOf(' notDisableEnter ') > -1;
    	if (d.tagName.toUpperCase() === 'INPUT' && notDisableEnterClass === false &&
	        (
	        	d.type.toUpperCase() === 'TEXT' ||
	            d.type.toUpperCase() === 'PASSWORD' || 
	            d.type.toUpperCase() === 'FILE' || 
	            d.type.toUpperCase() === 'EMAIL' || 
	            d.type.toUpperCase() === 'SEARCH' || 
	            d.type.toUpperCase() === 'DATE' 
	         )){
    		doPrevent = true;
    	}
    }
    /*-- End เพิ่มเติมป้องกัน Enter ที่ input --*/
	if (doPrevent) {
		event.preventDefault();
	}
});