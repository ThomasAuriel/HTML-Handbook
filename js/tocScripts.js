function formatGeneralToC(volume){

	//Title
	$('.nav-ToC', volume).append(
		$('<h1>').addClass('nav-ToC-header')
		.append('Content')
		)

	//Define the ToC
	var elementList = $('<ul>');
	elementList.attr('class', 'nav-ToC-list')
	elementList.attr('id', 'nav-ToC-list')
	$('.nav-ToC', volume).append(elementList)

	// Update the ToC
	var elements = $('.nav-element')
	for (var i = 0; i < elements.length; i++){
		var element = elements[i]
		elementList.append(
			$('<li>').append(
				$('<a>').attr('href',"#" + element.id)
				.attr('class','nav-ToC-list-element')
				.append(element.title)
				)
			)
	}

}