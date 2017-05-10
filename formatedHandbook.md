<?xml version="1.0" encoding="UTF-8"?><handbook author="Thomas Auriel" dateFormat="dd-MM-YYYY" id="a-simple-handbook" title="A simple Handbook" version="Version 0.3"><headline><div class="first-page"><h1 class="first-page-title">A simple Handbook</h1><h2 class="first-page-author">Thomas Auriel</h2><h2 class="first-page-version">Version 0.3</h2><h2 class="first-page-date">10-05-2017</h2></div></headline><toc level="1"><h2 class="toc-title">Table of Content</h2><ul class="toc-list" level="1"><li class="toc-element"><a href="#presentation">Presentation</a></li><li class="toc-element"><a href="#elements">Elements</a></li><li class="toc-element"><a href="#mechanism">Mechanism</a></li></ul></toc><subcontent><note id="presentation" title="Presentation"><headline><h2><a class="fade-link-title" href="#presentation">#</a>Presentation</h2></headline><content class="markdown">
This handbook is a tool which provide a simple handbook rendered through HTML.

# Website
This tool is publish on github : https://github.com/ThomasAuriel/HTML-Handbook

</content><subcontent/></note><note id="elements" title="Elements"><headline><h2><a class="fade-link-title" href="#elements">#</a>Elements</h2></headline><toc level="2"><h3 class="toc-title">Table of Content</h3><ul class="toc-list" level="1"><li class="toc-element"><a href="#note-type">Note Types</a></li><li class="toc-element"><a href="#structures">Structures</a></li><li class="toc-element"><a href="#balises">Balises</a></li></ul></toc><content class="markdown">
To use this handbook, it is important to know the list of allowed elements.
</content><subcontent><note id="note-type" tags="[structures, balises]" title="Note Types"><headline><h3><a class="fade-link-title" href="#note-type">#</a>Note Types</h3><ul class="tags-list"><li class="tags-list-element"><a class="tag" href="#structures">Structures</a></li><li class="tags-list-element"><a class="tag" href="#balises">Balises</a></li></ul></headline><content class="markdown">

Their is three kind of notes :

- **Simple Note**

	A simple note is a the basic element. A note is defined by:
	- an **id**: This id allow to navigate in the document through tags. It must be **defined and unique**
	- a **title** (must be **defined** but not necessary unique.
	- a **list** of [tags](#tags). It is not an obligation to define it.

	```md
		<note id="..." tags="..." title="...">
			...
		</note>
	```


- **Activity note**
	An activity note is a simple note with the attribute activity defined to true.

	```md
		<note activity="true" id="..." tags="..." title="...">
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
		<handbook author="..." date="..." id="..." title="..." version="...">
			...
		</handbook>
	```
	or
	```md
		<handbook author="..." dateFormat="..." id="..." title="..." version="...">
			...
		</handbook>
	```

</content><subcontent/><contentList/></note><note id="structures" tags="[&quot;balises&quot;]" title="Structures"><headline><h3><a class="fade-link-title" href="#structures">#</a>Structures</h3><ul class="tags-list"><li class="tags-list-element"><a class="tag" href="#balises">Balises</a></li></ul></headline><content class="markdown">
Structures are the elements used to format notes.
Their is six different elements:
- **content**
	The content structure contain the text. This text is formated following the [Markdown](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)

- **headline**
	Headline is an element which represent where the title of the note and the tag list should be write.

	```html
	<md/>
	```

- **toc**
	The toc balise defined where to write the Table of Content.
	The toc balise accept one [balise](#balises):
	- **level**: The level represent the max depth of the Table of Content. It must be an **integer** and is **not an obligation**.

	```md
	<toc level="..."/>
	```

- **subcontent**
	The subcontent structure indicate where subnotes must be added.

	```md
	<subcontent/>
	```

- **contentList**
	The contentList structure allow to show where the current note is used as tag in the whole document.

	```md
	<contentList/>
	```

</content><subcontent/><contentList><h4 class="refering-notes-title">Content in the document</h4><ul class="refering-notes-list"><li class="refering-notes-list-element"><a href="#note-type">Note Types</a></li><li class="refering-notes-list-element"><a href="#balises">Balises</a></li><li class="refering-notes-list-element"><a href="#noteConnection">Note Connection</a></li></ul></contentList></note><note id="balises" tags="[structures]" title="Balises"><headline><h3><a class="fade-link-title" href="#balises">#</a>Balises</h3><ul class="tags-list"><li class="tags-list-element"><a class="tag" href="#structures">Structures</a></li></ul></headline><content class="markdown">
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

</content><subcontent/><contentList><h4 class="refering-notes-title">Content in the document</h4><ul class="refering-notes-list"><li class="refering-notes-list-element"><a href="#note-type">Note Types</a></li><li class="refering-notes-list-element"><a href="#structures">Structures</a></li></ul></contentList></note></subcontent></note><note id="mechanism" title="Mechanism"><headline><h2><a class="fade-link-title" href="#mechanism">#</a>Mechanism</h2></headline><toc level="2"><h3 class="toc-title">Table of Content</h3><ul class="toc-list" level="1"><li class="toc-element"><a href="#generation">Use the Jar to generate the final Markdown file</a></li><li class="toc-element"><a href="#datastructure">Data folder structure</a></li><li class="toc-element"><a href="#noteConnection">Note Connection</a></li><li class="toc-element"><a href="#markdown">Markdown</a></li></ul></toc><content class="markdown">
</content><subcontent><note id="generation" tags="[datastructure]" title="Use the Jar to generate the final Markdown file"><headline><h3><a class="fade-link-title" href="#generation">#</a>Use the Jar to generate the final Markdown file</h3><ul class="tags-list"><li class="tags-list-element"><a class="tag" href="#datastructure">Data folder structure</a></li></ul></headline><content class="markdown">
	
The generate the final Markdown file, use the CreateHandbook.jar file.
This file require Java 1.5 or higher to run it.

**Windows**
+ Be sure to have java 1.5 or higher.
+ Double click on the file.

**Linux/Mac OS X**
+ Be sure to have java 1.5 or higher.
+ Run the script "run_CreateHandbook.sh" in the same folder (check permissions for the first run).

The [data folder](#datastructure) must be in the same folder than the jar.

</content><subcontent/><contentList/></note><note id="datastructure" title="Data folder structure"><headline><h3><a class="fade-link-title" href="#datastructure">#</a>Data folder structure</h3></headline><content class="markdown">
	
The **data folder** contain all the data to display in the final Markdown file.

Generally, **each folder should contain one unique Markdown file (.md)**.
So the data folder will contain on Markdown file (.md). This file will be the root note. You can use the [handbook](#note-type) structure in it.
Each sub-notes will be contain in one folder. The name of the folder is not important. You can use it to order notes. The display order of notes will follow the alphabetic order of theses folders.
If you need to add file with a note, then you can add it in its folder. The only constraint is that the added file cannot be a Markdown file.

Example of a correct data structure
```
data + handbook.md
     |
     + subnote1 + subnote1.md
     |
	 + subnote2 + subnote2.md
                |
	            + img.jpg
```

</content><subcontent/><contentList><h4 class="refering-notes-title">Content in the document</h4><ul class="refering-notes-list"><li class="refering-notes-list-element"><a href="#generation">Use the Jar to generate the final Markdown file</a></li></ul></contentList></note><note id="noteConnection" tags="[structures]" title="Note Connection"><headline><h3><a class="fade-link-title" href="#noteConnection">#</a>Note Connection</h3><ul class="tags-list"><li class="tags-list-element"><a class="tag" href="#structures">Structures</a></li></ul></headline><content class="markdown">

**IDs**

Note ID allow to create an hyperlink to a note.
This link must be unique to disambiguate notes references. It is possible to referent to a note through a link in the markdown syntaxe. To do so use one of the next structures.

```md
1. [Presentation note](#presentation)
1. [Presentation note](#presentation "Define the the tooltips text")
1. [You can use an arbitrary case-insensitive reference text][Arbitrary case-insensitive reference text]
1. [You can use numbers for reference-style link definitions][1]
1. Or leave it empty and use the [link text itself].
1. Finally you can also use an html balise : <a class="tag" href="#presentation">Which is represented as a tag.</a>

[arbitrary case-insensitive reference text]: #presentation
[1]: #presentation
[link text itself]: #presentation
```

Which show becomes :

1. [Presentation note](#presentation)
1. [Presentation note](#presentation "Define the the tooltips text")
1. [You can use an arbitrary case-insensitive reference text][Arbitrary case-insensitive reference text]
1. [You can use numbers for reference-style link definitions][1]
1. Or leave it empty and use the [link text itself].
1. Finally you can also use an html balise : <a class="tag" href="#presentation">Which is represented as a tag.</a>

[arbitrary case-insensitive reference text]: #presentation
[1]: #presentation
[link text itself]: #presentation


**Tags**
	
Tags are a list of note ids.
This tags are shown in the headline if the _tags_ structure is present.
Tags allow to connect two notes. The note which contain the tags balise, refere targeted note and will appears in the section "Content in the document".

</content><subcontent/><contentList/></note><note id="markdown" title="Markdown"><headline><h3><a class="fade-link-title" href="#markdown">#</a>Markdown</h3></headline><content class="markdown">

![Markdown logo](./data/30-Mechanism/30-Markdown/markdownLogo.png =166x102)
	
According to the [official website](#https://daringfireball.net/projects/markdown/) :

	Markdown is a text-to-HTML conversion tool for web writers. Markdown allows you to write using an easy-to-read, easy-to-write plain text format, then convert it to structurally valid XHTML (or HTML).

	Thus, “Markdown” is two things: (1) a plain text formatting syntax; and (2) a software tool, written in Perl, that converts the plain text formatting to HTML. See the Syntax page for details pertaining to Markdown’s formatting syntax. You can try it out, right now, using the online Dingus.

**In this handbook you only need to write your text according to the Markdown syntaxe.**
+ [Official website](https://daringfireball.net/projects/markdown/)
+ [Markdown Cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#links)

For more information, the javascript API used in this handbook is **[Showdown](https://github.com/showdownjs/showdown)**

You can also insert html balises in the content. However, be wared that it can be instable since CreateHandbook.jar use the java XML api [javax.xml.parsers.DocumentBuilder](https://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilder.html). The balise img which is usually not closed, should be when you want add it. But simple and most commun other html balises should work.

</content><subcontent/><contentList/></note></subcontent></note></subcontent></handbook>