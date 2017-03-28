function convertCSVtoHTML(element){

	var input = element.innerHTML;
	$(element).empty()

	//Define the table
	var table = $('<table>')
	$(element).append(table)

	var inputLines = input.trim().split('\n')
	var i, j
	for(i = 0; i<inputLines.length; i++){
		//Define the line to edit.
		var htmlLine = $('<tr>')
		table.append(htmlLine)

		var inputCells = inputLines[i].trim().split(';')
		for(j = 0; j < inputCells.length; j++){
			//Define the cell to edit.
			var htmlCell = $('<td>')
			htmlCell.append(inputCells[j].trim())
			htmlLine.append(htmlCell)
		}
	}

}