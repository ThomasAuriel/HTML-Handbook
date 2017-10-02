var $ = require('jquery-browserify')

$(document).ready(function(){
	var fileScripts = require('./fileScripts');
	var markdownScripts = require('./markdownScripts.js')

	//Load the handbook
	var formatedHandbook = fileScripts.getFileContent('./formatedHandbook.md', false)
	var handbook = $('#handbook')
	handbook.html(formatedHandbook)

	markdownScripts.renderMarkdown();
});