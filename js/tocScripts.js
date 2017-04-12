function formatGeneralToC(json){

	//Title
	$('.nav-ToC').append(
		$('<h1>')
			.addClass('nav-ToC-header')
			.append('Content')
		)

	//Define the ToC
	var ToCList = $('<ul>');
	ToCList.attr('class', 'nav-ToC-list')
	ToCList.attr('id', 'nav-ToC-list')
	$('.nav-ToC').append(ToCList)

	// Update the ToC
	// Get volume and chapters
	for(var i = 0; i < json.volumes.length; i++){
		//volumes
		var volume = json.volumes[i]
		formatInput(ToCList, volume)

		for(var j = 0; j < volume.chapters.length; j++){
		
			//Chapters
			var chapter = volume.chapters[j]
			formatInput(ToCList, chapter)
		}
	}

}

function formatSubToC(json){

	// section ToCs
	for(var i = 0; i<json.volumes.length; i++){
		var volumeJson = json.volumes[i]
		var volumeElement = $('#'+volumeJson.id)

		var volumeToC = $('<ul>');
		volumeToC.attr('class', 'nav-ToC-list')
		volumeToC.attr('id', 'nav-ToC-list')
		$('.volume-ToC', volumeElement).append(volumeToC)

		//Include chapters and sections
		for(var j = 0; j < volumeJson.chapters.length; j++){
			//chapters
			var chapter = volumeJson.chapters[j]
			formatInput(volumeToC, chapter)

			//Add a ToC to each chapters
			var chapterElement = $('#'+chapter.id)
			//So define a new chapterToC each time
			var chapterToC = $('<ul>');
			chapterToC.attr('class', 'nav-ToC-list')
			chapterToC.attr('id', 'nav-ToC-list')
			$('.chapter-ToC', chapterElement).append(chapterToC)

			for(var k = 0; k < chapter.sections.length; k++){
			
				//sections
				var section = chapter.sections[k]
				// formatInput(volumeToC, section)
				formatInput(chapterToC, section)
			}
		}
	}
	
}

function formatInput(list, jsonElement){
	list.append(
		$('<li>').append(
			$('<a>').attr('href',"#" + jsonElement.id)
				.addClass('nav-ToC-list-element')
				.attr('level', jsonElement.classElement)
				.append(jsonElement.shortTitle)
			)
		)
}