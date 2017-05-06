<note id="balises" title="Balises" tags='[structures]'>

<headline/>
<content>
Different balises exist.

- **title**
	A string which represent the title of the current note. It is used with the [note](#note) and [handbook](#headline)
- **author**
	A string which is used to represent the author of an **[handbook](#headline)**
- **id**
	The id is used with **[note](#note)** and **[handbook](#headline)**. This is **obligatory balise**. Id must be **unique**, contain **standard characters** (no accents, no spaces, no punctuation excepted dash).
- **level**
	Level is used in **[toc](#toc)**. It represent the max depth of Table of Content. If it is not present, then it is assumed to be 1.
- **tags**
	The tags is used with **[note](#note)**. This is **not an obligation** to use it.
	Tags is a [json structure](http://json.org/example.html) containing notes id. Each id will be replace by a link to the corresponding note and display in the note headerline.

	```json
	tags="[id1, id2]"
	```

- **version**
	The version is used in the [handbook](#handbook) structure.
	The version balise contain the version to display on the first page. It is interpreted as a regular text. So you can add any text.

- **date**
	The date is used in the [handbook](#handbook) structure.
	The date balise contain the date to display on the first page. It is interpreted as a regular text. So you can add any text.

- **dateFormat**
	The dateFormat is used in the [handbook](#handbook) structure.
	This balise is used if date balise is not used. The dateformat allow to display the date when the handbook is generated. The allow format is defined by the [SimpleDateFormat format](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html). For instance: dd/MM/YYYY or MM/dd/YYYY. This date format is used to format the current date and display it on the first page.

- **activity**
	Activity is a balise used with notes elements. It allows to define a note as an activity. This is use full to add a note which represent a temporal event.

</content>
<subcontent/>
<contentList/>

</note>
