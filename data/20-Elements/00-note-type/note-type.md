<note id="note-type" title="Note Types" tags='[structures, balises]'>

<headline/>
<content>

Their is three kind of notes :

- **Simple Note**

	A simple note is a the basic element. A note is defined by:
	- an **id**: This id allow to navigate in the document through tags. It must be **defined and unique**
	- a **title** (must be **defined** but not necessary unique.
	- a **list** of [tags](#tags). It is not an obligation to define it.

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

	An handbook is the root element. It should be used as this but it is not an obligation. 	Each time you use an handbook, a first page is used in the final handbook. This can be usefull to provide separations between sections.

	A n handbook is a defined by:
	- an **id**: This id allow to navigate in the document through tags. It must be **defined and unique**
	- a **title** (must be **defined** but not necessary unique.
	- an **author**
	- a **version**
	- a **date** or **dateFormat** It is possible to define a date. It is interpreted as textual date. You can then write anything in this field. Instead a date, you can define a dateFormate. This date format should follow the format of [SimpleDateFormat](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html). For instance: dd/MM/YYYY or MM/dd/YYYY. This date format is used to format the current date and display it on the first page.

	Example of an handbook : 
	```md
		<handbook id="..." title="..." author="..." date="..." version="...">
			...
		</handbook>
	```
	or
	```md
		<handbook id="..." title="..." author="..." dateFormat="..." version="...">
			...
		</handbook>
	```

</content>
<subcontent/>
<contentList/>

</note>
