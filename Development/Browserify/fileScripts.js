module.exports = {
	/**
	Synchronous load
	*/
	getFileContent: function(url, asynchronous){

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
	},

	parseJSON: function(){
		var jsonContentFile = getFileContent(urlJSON, "json", false)
		var json = jQuery.parseJSON(jsonContentFile);
	}
}