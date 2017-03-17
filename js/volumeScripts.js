function formatVolume(volume, urlContentvolume){

	//Define the title of the volume
	$('.volume-title', volume).append($('<h1>').append(volume.title))


	// Load the JSON volume structure
	var contentFile = getFileContent(urlContentvolume, "json", false)
	var json = jQuery.parseJSON(contentFile);

	// Load chapters
	for (var i = 0; i < json.content.length; i++){
		var contentchapter = getFileContent(json.content[i], "html", false)
		$('.volume-chapter-list', volume).append(contentchapter)
	}


	//Define the ToC
	$('.volume-ToC', volume).append($('<h2>').addClass('volume-ToC-header').append('Content'))
	var chapterList = $('<ul>');
	chapterList.attr('class', 'volume-ToC-list')
	chapterList.attr('id', 'volume-ToC-list-'+volume.id)
	$('.volume-ToC', volume).append(chapterList)
	// Update the ToC
	var chapters = $('.chapter', volume) //select element depending their class
	for (var i = 0; i < chapters.length; i++){
		var chapter = chapters[i]
		chapterList.append(
			$('<li>').append(
				$('<a>').attr('href',"#" + chapter.id)
				.attr('class','volume-ToC-list-element')
				.append(chapter.title)
			)
		)
	}
}