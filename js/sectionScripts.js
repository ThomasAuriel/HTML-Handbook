function formatSection(section){

	//Define the title of the section
	$('.section-title', section).append($('<h2>').append(section.title).attr('id', section.getAttribute('referenceTag')))


	//Define the tag list
	var tagList = $('<ul>');
	tagList.attr('class', 'section-tags-list')
	tagList.attr('id', 'section-tags-list-'+section.id)
	$('.section-tags', section).append(tagList)

	// Update tags
	var json = jQuery.parseJSON(section.getAttribute('referencedSection'));
	for (var i = 0; i < json.tags.length; i++){

		var referedSection = getSectionById(json.tags[i])
		if(referedSection != null && referedSection != undefined && referedSection != null){
			tagList.append(
				$('<li>')
				.append(
					$('<a>')
					.attr('href',"#" + json.tags[i])
					.attr('class','section-tags-list-element')
					.append(referedSection.getAttribute('tag'))
					)
				)
		} else {
			tagList.append(
				$('<li>')
				.append(
					$('<a>')
					.attr('href',"#" + json.tags[i])
					.attr('class','section-tags-list-element')
					.append(json.tags[i])
					)
				)
		}
	}

}

function sectionFoot(section){

	

	//Define the wiki content list
	$('.section-foot', section).append($('<h3>').append('Content in the wiki refering to this section'))
	var contentList = $('<ul>');
	contentList.attr('class', 'section-foot-list-content')
	contentList.attr('id', 'section-foot-list-content-' + section.id)
	$('.section-foot', section).append(contentList)
	//Define the wiki activity list
	$('.section-foot', section).append($('<h3>').append('Acitivity in the wiki refering to this section'))
	var activityList = $('<ul>');
	activityList.attr('class', 'section-foot-list-activity')
	activityList.attr('id', 'section-foot-list-activity-' + section.id)
	$('.section-foot', section).append(activityList)




	// Update tags
	//Contain all section refered
	var sectionMap = makeOrderedHash();
	var referenceTag = section.getAttribute('tag')
	$('.section').each(function (index, el){
		var json = jQuery.parseJSON(el.getAttribute('referencedSection'));
		for (var i = 0; i < json.tags.length; i++){
			var referedSection = getSectionById(json.tags[i])
			if(referedSection != null && referedSection != undefined && referedSection != null){
				var tag = referedSection.getAttribute('tag')
				if( tag === referenceTag ){
					sectionMap.push(el.getAttribute('title'), el)
					break
				}
			}
		}
		
	})

	for (var i = 0; i < sectionMap.length(); i++){

		var key = sectionMap.key(i)
		var value = sectionMap.val(key)

		var attr = value.getAttribute('activity')
		console.log(value)
		// For some browsers, `attr` is undefined; for others,
		// `attr` is false.  Check for both.
		if (attr == undefined || attr == null) {
			contentList.append(
				$('<li>')
				.append(
					$('<a>')
					.attr('href',"#" + value.id)
					.attr('class','section-foot-list-element')
					.append(value.getAttribute('tag'))
					)
				)
		} else {
			if($('li', activityList).length < 10){
				activityList.append(
					$('<li>')
					.append(
						$('<a>')
						.attr('href',"#" + value.id)
						.attr('class','section-foot-list-element')
						.append(value.getAttribute('tag'))
						)
					)
			}
		}


	}
}

function getSectionById(id){
	return $('#'+id+'.section')[0]
}

function makeOrderedHash() {
	var keys = [];
	var vals = {};
	return {
		push: function(k,v) {
			if (!vals[k]) keys.push(k);
			vals[k] = v;
		},
		insert: function(pos,k,v) {
			if (!vals[k]) {
				keys.splice(pos,0,k);
				vals[k] = v;
			}
		},
		key: function(i) {return keys[i]},
		val: function(k) {return vals[k]},
		length: function(){return keys.length},
		keys: function(){return keys},
		values: function(){return vals}
	};
};