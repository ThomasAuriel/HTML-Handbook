function formatChapter(chapter, urlContentChapter){

	//Define the title of the chapter
	$('.chapter-title', chapter).append($('<h1>').append(chapter.title))


	// Load the JSON chapter structure
	var contentFile = getFileContent(urlContentChapter, "json", false)
	var json = jQuery.parseJSON(contentFile);

	// Load sections
	for (var i = 0; i < json.content.length; i++){
		var contentSection = getFileContent(json.content[i], "html", false)
		$('.chapter-section-list', chapter).append(contentSection)
	}


	//Define the ToC
	$('.chapter-ToC', chapter).append($('<h2>').addClass('chapter-ToC-header').append('Content'))
	var sectionList = $('<ul>');
	sectionList.attr('class', 'chapter-ToC-list')
	sectionList.attr('id', 'chapter-ToC-list-'+ chapter.id)
	$('.chapter-ToC', chapter).append(sectionList)
	// Update the ToC
	var sections = $('.section', chapter) //select element depending their class
	for (var i = 0; i < sections.length; i++){
		var section = sections[i]
		sectionList.append(
			$('<li>').append(
				$('<a>').attr('href',"#" + section.id)
				.attr('class','chapter-ToC-list-element')
				.attr('level', $(section).attr('class').split(' ')[0])
				.append(section.title)
			)
		)
	}
}