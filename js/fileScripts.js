/**
Synchronous load
*/
function getFileContent(url, asynchronous){

	// Get API manual page : https://api.jquery.com/jquery.get/
	// https://stackoverflow.com/questions/933713/is-there-a-version-of-getjson-that-doesnt-use-a-call-back
	var toReturn
	$.ajax({
		type: 'GET',
		url: url,
		dataType: 'text',
		success: function(data) {toReturn = data},
		data: {},
		async: asynchronous
	});

	return  toReturn
}

function parseJSON(){
	var jsonContentFile = getFileContent(urlJSON, "json", false)
	var json = jQuery.parseJSON(jsonContentFile);
}
