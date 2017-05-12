<note id="note-type" title="Note Types" tags='[structures, balises]'>

<headline/>
<content>

There are three kinds of notes :

- **Simple Note**

	A simple note is the basic element. A note is defined by:
	- A **id**: This id allows users to navigate in the document through tags. It must be **defined and unique**.
	- A **title** (must be **defined** but not necessarily unique.
	- A **list** of [tags](#tags). It is not an obligation to define it.

	```md
		<note id="..." title="..." tags="...">
			...
		</note>
	```


- **Activity note**
	An activity note is a simple note with the attribute activity defined to true.

	```md
		<note id="..." title="..." tags="..." activity="true">
			...
		</note>
	```

- **Handbook**

	A handbook is the root element. It should be used as this but it is not an obligation. 	Each time you use a handbook, a first page is used in the final handbook. This can be useful to provide separations between sections.

	A handbook is a defined by:
	- A **id**: This id allows users to navigate in the document through tags. It must be **defined and unique**.
	- A **title** (must be **defined** but not unique.
	- A **author**
	- A **version**
	- A **date** or **dateformat** It is possible to define a date. It is interpreted as textual date. You can then write anything in this field. Instead a date, you can use the dateformat tag. This date format should follow the format of [SimpleDateFormat](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html). For instance: dd/MM/YYYY or MM/dd/YYYY. This date format is used to format the current date and display it on the first page.

	Example of a handbook : 
	```md
		<handbook id="..." title="..." author="..." date="..." version="...">
			...
		</handbook>
	```
	Or
	```md
		<handbook id="..." title="..." author="..." dateformat="..." version="...">
			...
		</handbook>
	```

</content>
<subcontent/>
<contentList/>

</note>
