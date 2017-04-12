function loadHandbook(handbook, json){

	var volumeList = $('.volume-list', handbook)
	for(var i = 0; i < json.volumes.length; i++){
		loadVolume(volumeList, json.volumes[i], json)
	}
}

function loadVolume(volumeList, jsonVolume, json){

	//Load the volume
	$(volumeList).append(getFileContent(jsonVolume.path, 'html', false))

	//get the loaded volume
	var volume = $('#'+jsonVolume.id)

	//Define the title of the volume
	$('.volume-title', volume)
		.append(
			$('<h1>')
				.append(
					$('<a>')
						.attr('href',"#" + jsonVolume.id)
						.attr('class','fade-link-title')
						.append('#'))
				.append(jsonVolume.title)
			)

	var chapterList = $('.volume-chapter-list', volume)
	for(var i = 0; i < jsonVolume.chapters.length; i++){
		loadChapter(chapterList, jsonVolume.chapters[i], json)
	}
	
}

function loadChapter(chapterList, jsonChapter, json){

	//Load the chapter
	$(chapterList).append(getFileContent(jsonChapter.path, 'html', false))

	//get the loaded chapter
	var chapter = $('#'+jsonChapter.id)

	//Define the title of the chapter
	$('.chapter-title', chapter)
		.append(
			$('<h2>')
				.append(
					$('<a>')
						.attr('href',"#" + jsonChapter.id)
						.attr('class','fade-link-title')
						.append('#'))
				.append(jsonChapter.title)
			)

	var sectionList = $('.chapter-section-list', chapter)
	for(var i = 0; i < jsonChapter.sections.length; i++){
		loadSection(sectionList, jsonChapter.sections[i], json)
	}

}

function loadSection(sessionList, jsonSection, json){

	//Load the section
	$(sessionList).append(getFileContent(jsonSection.path, 'html', false))

	//get the loaded chapter
	var section = $('#'+jsonSection.id)

	//Define the title of the chapter
	$('.section-title', section)
		.append(
			$('<h2>')
				.append(
					$('<a>')
						.attr('href',"#" + jsonSection.id)
						.attr('class','fade-link-title')
						.append('#'))
				.append(jsonSection.title)
			)

	//Define the tag list
	var tagList = $('<ul>')
	tagList.attr('class', 'section-tags-list')
	tagList.attr('id', 'section-tags-list-'+jsonSection.id)
	$('.section-tags', section).append(tagList)

	// Update tags
	for(var i = 0; i < jsonSection.referencedSections.length; i++){
		var idReferencedSection = jsonSection.referencedSections[i]
		var currentreferencedSection = json.mapId[idReferencedSection]
		
		//If the element does not exist in the document, juste use the id.
		if(currentreferencedSection && currentreferencedSection !== 'null' && currentreferencedSection !== 'undefined'){
			tagList.append(
					$('<li>')
					.append(
						$('<a>')
						.attr('href',"#" + currentreferencedSection.id)
						.attr('class','section-tags-list-element')
						.append(currentreferencedSection.shortTitle)
						)
					)
		} else {
			tagList.append(
					$('<li>')
					.append(
						$('<a>')
						.attr('href',"#" + idReferencedSection)
						.attr('class','section-tags-list-element')
						.append('!'+idReferencedSection+'!')
						)
					)
		}
	}


	//Define section feet
	//Define the wiki content list
	$('.section-foot', section).append($('<h3>').append('Content in the wiki refering to this section'))
	var contentList = $('<ul>');
	contentList.attr('class', 'section-foot-list-content')
	contentList.attr('id', 'section-foot-list-content-' + jsonSection.id)
	$('.section-foot', section).append(contentList)
	//Define the wiki activity list
	$('.section-foot', section).append($('<h3>').append('Acitivity in the wiki refering to this section'))
	var activityList = $('<ul>');
	activityList.attr('class', 'section-foot-list-activity')
	activityList.attr('id', 'section-foot-list-activity-' + jsonSection.id)
	$('.section-foot', section).append(activityList)

	//find all contents refering this section
	var referingSections = json.mapRefering[jsonSection.id]
	if(referingSections && referingSections !== 'null' && referingSections !== 'undefined'){

		//Iterate over a key/value structure.
		for (var key in referingSections){

			var referingSection = referingSections[key]

			//If the section is an activity section
			if(referingSection.classElement.includes('activity')){
				if($('li', activityList).length < 10){
					activityList.append(
						$('<li>')
						.append(
							$('<a>')
							.attr('href',"#" + referingSection.id)
							.attr('class','section-foot-list-element')
							.append(referingSection.title)
							)
						)
				}
			} else {
				//If the section is a classic section
				contentList.append(
					$('<li>')
					.append(
						$('<a>')
						.attr('href',"#" + referingSection.id)
						.attr('class','section-foot-list-element')
						.append(referingSection.title)
						)
					)
			}
		}
	}
}

function formatSubSection(subSection, json){

	//Define the title of the chapter
	$('.subsection-title', subSection).append(
		$('<h2>')
			.append(
				$('<a>')
					.attr('href',"#" + subSection.id)
					.attr('class','fade-link-title')
					.append('#'))
			.append(subSection.getAttribute('title'))
		)

	//Define the tag list
	var tagList = $('<ul>')
	tagList.attr('class', 'subsection-tags-list')
	tagList.attr('id', 'subsection-tags-list-'+subSection.id)
	$('.subsection-tags', subSection).append(tagList)

	// Update tags
	var jsonReferencedSection = subSection.getAttribute('referencedSection')
	var referencedSection = jQuery.parseJSON(jsonReferencedSection)
	for(var i = 0; i < referencedSection.length; i++){
		var idReferencedSection = referencedSection[i]
		var currentreferencedSection = json.mapId[idReferencedSection]
		
		if(currentreferencedSection && currentreferencedSection !== 'null' && currentreferencedSection !== 'undefined'){
			//Use the element from json
			tagList.append(
					$('<li>')
					.append(
						$('<a>')
						.attr('href',"#" + currentreferencedSection.id)
						.attr('class','subsection-tags-list-element')
						.append(currentreferencedSection.shortTitle)
						)
					)
		} else {
			//If the element is not found in json
			//form the element from the document using jQuerry
			// Not use elseif since this part does not run everytime (save time)
			var element = $('#'+idReferencedSection)[0]
			if(element && element !== 'null' && element !== 'undefined'){
				tagList.append(
					$('<li>')
					.append(
						$('<a>')
							.attr('href',"#" + element.id)
							.attr('class','subsection-tags-list-element')
							.append(element.getAttribute('shortTitle'))
						)
					)
			} else {
				//If the element does not exist in the document neither in the json, juste use the id.
				tagList.append(
					$('<li>')
					.append(
						$('<a>')
						.attr('href',"#" + idReferencedSection)
						.attr('class','subsection-tags-list-element')
						.append('!'+idReferencedSection+'!')
						)
					)
			}

		}
	}
}

function formatSoloTags(soloTag, json){
	var id = soloTag.getAttribute('tag')
	var elementTargeted = json.mapId[id]

	if(elementTargeted != null && elementTargeted != undefined && elementTargeted != null){
		$(soloTag)
			.append($('<a>')
					.attr('href',"#" + elementTargeted.id)
					.append(elementTargeted.shortTitle)
				)
	} else {
		// Not use elseif since this part does not run everytime (save time)
		var elementTargeted = $('#'+id)[0]
		if(elementTargeted && elementTargeted !== 'null' && elementTargeted !== 'undefined'){
			$(soloTag)
				.append($('<a>')
						.attr('href',"#" + elementTargeted.id)
						.append(elementTargeted.getAttribute('shortTitle'))
					)
		} else {
			//If the element does not exist in the document neither in the json, juste use the id.
			$(soloTag)
				.append('!'+id+'!')
		}
	}
}