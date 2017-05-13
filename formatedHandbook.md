<?xml version="1.0" encoding="UTF-8"?><handbook author="Thomas Auriel" dateFormat="dd-MM-YYYY" id="a-simple-handbook" title="A simple Handbook" version="Version 0.3"><headline><div class="first-page"><h1 class="first-page-title">A simple Handbook</h1><h2 class="first-page-author">Thomas Auriel</h2><h2 class="first-page-version">Version 0.3</h2></div></headline><toc level="1"><h2 class="toc-title">Table of Content</h2><ul class="toc-list" level="1"><li class="toc-element"><a href="#presentation">Presentation</a></li><li class="toc-element"><a href="#mechanism">Mechanism</a></li><li class="toc-element"><a href="#elements">Elements</a></li><li class="toc-element"><a href="#equations">Equations</a></li></ul></toc><subcontent><note id="presentation" title="Presentation"><headline><h2><a class="fade-link-title" href="#presentation">#</a>Presentation</h2></headline><content class="markdown">
This handbook is a tool which provides a simple handbook rendered through HTML.

# Website
This tool is published on Github: https://github.com/ThomasAuriel/HTML-Handbook.

</content><subcontent/></note><note id="mechanism" title="Mechanism"><headline><h2><a class="fade-link-title" href="#mechanism">#</a>Mechanism</h2></headline><toc level="2"><h3 class="toc-title">Table of Content</h3><ul class="toc-list" level="1"><li class="toc-element"><a href="#generation">Use the Jar to generate the final Markdown file</a></li><li class="toc-element"><a href="#datastructure">Data folder structure</a></li><li class="toc-element"><a href="#noteConnection">Note Connection</a></li><li class="toc-element"><a href="#markdown">Markdown</a></li></ul></toc><content class="markdown">
</content><subcontent><note id="generation" tags="[datastructure]" title="Use the Jar to generate the final Markdown file"><headline><h2><a class="fade-link-title" href="#generation">#</a>Use the Jar to generate the final Markdown file</h2><ul class="tags-list"><li class="tags-list-element"><a class="tag" href="#datastructure">Data folder structure</a></li></ul></headline><content class="markdown">
	
The generate the final Markdown file, use the CreateHandbook.jar file.
This file require Java 1.5 or higher to run it.

**Windows**
+ Be sure to have java 1.5 or higher.
+ Double click on the file.

**Linux/Mac OS X**
+ Be sure to have java 1.5 or higher.
+ Run the script "run_CreateHandbook.sh" in the same folder (check permissions for the first run).

The [data folder](#datastructure) must be in the same folder than the jar.

</content><subcontent/><contentList/></note><note id="datastructure" title="Data folder structure"><headline><h2><a class="fade-link-title" href="#datastructure">#</a>Data folder structure</h2></headline><content class="markdown">
	
The **data folder** contains all the data to display in the final Markdown file.

Generally, **each folder should contain one unique Markdown file (.md)**.
So the data folder will contain on Markdown file (.md). This file will be the root note. You can use the [handbook](#note-type) structure in it.
Each sub-notes will be contained in one folder. The name of the folder is not important. You can use it to order notes. The display order of notes will follow the alphabetic order of theses folders.
If you need to add file with a note, then you can add it to its folder. The only constraint is that the added file cannot be a Markdown file.

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

</content><subcontent/><contentList/></note><note id="noteConnection" tags="[structures]" title="Note Connection"><headline><h2><a class="fade-link-title" href="#noteConnection">#</a>Note Connection</h2><ul class="tags-list"><li class="tags-list-element"><a class="tag" href="#structures">Structures</a></li></ul></headline><content class="markdown">

**IDs**

Note ID allows the browser to create a hyperlink to a note.
This link must be unique to disambiguate notes references. It is possible to referent to a note through a link in the markdown syntax. To do so use one of the next structures.

```md
1. [Presentation note](#presentation)
1. [Presentation note](#presentation "Define tooltips text")
1. [You can use an arbitrary case-insensitive reference text][Arbitrary case-insensitive reference text]
1. [You can use numbers for reference-style link definitions][1]
1. Or leave it empty and use the [link text itself].
1. Finally, you can also use an html tag : <a class="tag" href="#presentation">Which is represented as a tag.</a>

[arbitrary case-insensitive reference text]: #presentation
[1]: #presentation
[link text itself]: #presentation
```

Which show becomes :

1. [Presentation note](#presentation)
1. [Presentation note](#presentation "Define tooltips text")
1. [You can use an arbitrary case-insensitive reference text][Arbitrary case-insensitive reference text]
1. [You can use numbers for reference-style link definitions][1]
1. Or leave it empty and use the [link text itself].
1. Finally, you can also use an html tag : <a class="tag" href="#presentation">Which is represented as a tag.</a>

[arbitrary case-insensitive reference text]: #presentation
[1]: #presentation
[link text itself]: #presentation


**Tags**
	
Tags are a list of note ids.
This tags are shown in the headline if the _tags_ structure is present.
Tags allow the tool to connect two notes. The note which contains the tags tag, refers targeted notes and will appear in the section "Content in the document".

</content><subcontent/><contentList/></note><note id="markdown" title="Markdown"><headline><h2><a class="fade-link-title" href="#markdown">#</a>Markdown</h2></headline><content class="markdown">

![Markdown logo](./data/30-Mechanism/30-Markdown/markdownLogo.png =166x102)
	
According to the [official website](#https://daringfireball.net/projects/markdown/) :

	Markdown is a text-to-HTML conversion tool for web writers. Markdown allows you to write using an easy-to-read, easy-to-write plain text format, then convert it to structurally valid XHTML (or HTML).

	Thus, “Markdown” is two things: (1) a plain text formatting syntax; and (2) a software tool, written in Perl that converts the plain text formatting to HTML. See the Syntax page for details pertaining to Markdown’s formatting syntax. You can try it out, right now, using the online Dingus.

**In this handbook you only need to write your text according to the Markdown syntax.**
+ [Official website](https://daringfireball.net/projects/markdown/)
+ [Markdown Cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#links)

For more information, the JavaScript API used in this handbook is **[Showdown](https://github.com/showdownjs/showdown)**

You can also insert HTML tags in the content. However, be warned that it can be unstable since CreateHandbook.jar use the java XML api [javax.xml.parsers.DocumentBuilder](https://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilder.html). The tag img which is usually not closed, should be when you want to use it. But simple and most other common HTML tags should work.

</content><subcontent/><contentList/></note></subcontent></note><note id="elements" title="Elements"><headline><h2><a class="fade-link-title" href="#elements">#</a>Elements</h2></headline><toc level="2"><h3 class="toc-title">Table of Content</h3><ul class="toc-list" level="1"><li class="toc-element"><a href="#note-type">Note Types</a></li><li class="toc-element"><a href="#structures">Structures</a></li><li class="toc-element"><a href="#tags">Tags</a></li></ul></toc><content class="markdown">
To use this handbook, it is important to know the list of allowed elements.
</content><subcontent><note id="note-type" tags="[structures, tags]" title="Note Types"><headline><h2><a class="fade-link-title" href="#note-type">#</a>Note Types</h2><ul class="tags-list"><li class="tags-list-element"><a class="tag" href="#structures">Structures</a></li><li class="tags-list-element"><a class="tag" href="#tags">Tags</a></li></ul></headline><content class="markdown">

There are three kinds of notes :

- **Simple Note**

	A simple note is the basic element. A note is defined by:
	- A **id**: This id allows users to navigate in the document through tags. It must be **defined and unique**.
	- A **title** (must be **defined** but not necessarily unique.
	- A **list** of [tags](#tags). It is not an obligation to define it.

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

	A handbook is the root element. It should be used as this but it is not an obligation. 	Each time you use a handbook, a first page is used in the final handbook. This can be useful to provide separations between sections.

	A handbook is a defined by:
	- A **id**: This id allows users to navigate in the document through tags. It must be **defined and unique**.
	- A **title** (must be **defined** but not unique.
	- A **author**
	- A **version**
	- A **date** or **dateformat** It is possible to define a date. It is interpreted as textual date. You can then write anything in this field. Instead a date, you can use the dateformat tag. This date format should follow the format of [SimpleDateFormat](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html). For instance: dd/MM/YYYY or MM/dd/YYYY. This date format is used to format the current date and display it on the first page.

	Example of a handbook : 
	```md
		<handbook author="..." date="..." id="..." title="..." version="...">
			...
		</handbook>
	```
	Or
	```md
		<handbook author="..." dateformat="..." id="..." title="..." version="...">
			...
		</handbook>
	```

</content><subcontent/><contentList/></note><note id="structures" tags="[&quot;balises&quot;]" title="Structures"><headline><h2><a class="fade-link-title" href="#structures">#</a>Structures</h2><ul class="tags-list"><li class="tags-list-element"><a class="tag" href="#">NoNote&gt;balises</a></li></ul></headline><content class="markdown">

Structures are the elements used to format notes.
There are six different elements:
+ **content**
	The content structure contains the text. This text is formed following the [Markdown syntaxe](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet).

+ **headline**
	Headline is an element which the position where the title of the note and the tag list should be written.

	```html
	<headline/>
	```

+ **toc**
	The toc tag defined where to write the Table of Content.
	The toc tag accepts one [tag](#tags):
	- **level**: The level represents the max depth of the Table of Content. It must be a **integer** and is **not an obligation**.

	```md
	<toc level="..."/>
	```

- **subcontent**
	The subcontent structure indicates where sub-notes must be added.

	```md
	<subcontent/>
	```

- **contentlist**
	The contentlist structure allows to show where the current note is used as a tag in the whole document.

	```md
	<contentlist/>
	```

</content><subcontent/><contentList/></note><note id="tags" tags="[structures]" title="Tags"><headline><h2><a class="fade-link-title" href="#tags">#</a>Tags</h2><ul class="tags-list"><li class="tags-list-element"><a class="tag" href="#structures">Structures</a></li></ul></headline><content class="markdown">

Different tags exist.

- **title**: A string which represents the title of the current note. It is used with the [note](#note) and [handbook](#headline)

- **author**: A string which is used to represent the author of a [handbook](#headline).

- **id**: The id is used with [note](#note) and [handbook](#headline). This is obligatory tag. Id must be unique, contain standard characters (no accents, no spaces, no punctuation excepted dash).
- **level**: Level is used in [toc](#toc). It represents the max depth of Table of Content. If it is not present, then it is assumed to be 1.
- **tags**: The tags is used with [note](#note). This is not an obligation to use it. Tags are a [JSON structure](http://json.org/example.html) containing notes id. Each id will be replaced by a link to the corresponding note and display in the note header line.

```json
tags="[id1, id2]"
```

- **version**: The version is used in the [handbook](#handbook) structure. The version tag contains the version to display on the first page. It is interpreted as a regular text. So you can add any text.

- **date**: The date is used in the [handbook](#handbook) structure. The date tag contain the date to display on the first page. It is interpreted as a regular text. So you can add any text.

- **dateformat**: The date format is used in the [handbook](#handbook) structure. This tag is used if date tag is not used. The date format allows to display the date when the handbook is generated. Allow formats are defined by the [SimpleDateFormat format](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html). For instance dd/MM/YYYY or MM/dd/YYYY. This date format is used to format the current date and display it on the first page.

- **activity**: Activity is a tag used with notes elements. It allows users to define a note as an activity. This is used full to add a note which represents a temporal event.

</content><subcontent/><contentList/></note></subcontent></note><note id="equations" tags="[markdown]" title="Equations"><headline><h2><a class="fade-link-title" href="#equations">#</a>Equations</h2><ul class="tags-list"><li class="tags-list-element"><a class="tag" href="#markdown">Markdown</a></li></ul></headline><content class="markdown">

It is possible to write equations using this tools. To do this, you need to use the LaTeX syntax or the [ASCIIMath syntax].

## Libraries used

### ASCIIMath
[Markdown-it-math]: This library allows to write equations using the light [ASCIIMath syntax]. Since this syntaxe is light and simple, the library renders quickly equations. However, the library is limited and does not allow to write complex equations.
+ `_$` and `$_` define an inline text equation.
+ `_$$` and `$$_` define a block equation.
```md
The determinant _$Delta$_ is:
_$$
Delta = b^2 - 4*a*c
$$_
Second-order polynome solutions are :
_$$x_(1.2) = (-b +- sqrt{b^2 + 4 * a * c})/(2*a)$$_
```

The determinant _$Delta$_ is:
_$$
Delta = b^2 - 4*a*c 
$$_
Second-order polynome solutions are :
_$$x_(1.2) = (-b +- sqrt{b^2 + 4 * a * c})/(2*a)$$_

### LaTeX
[Markdown-it-mathjax] and [MathJax]: These libraries allow to write equations using the complet LaTeX syntax.
+ `$` and `$` define an inline text equation.
+ `$$` and `$$` define a block equation.

```latex
The determinant $\Delta$ is:
$$
\Delta = b^2 - 4 \cdot a \cdot c
$$
Second-order polynome solutions are :
$$x_(1.2) = frac{-b +- \sqrt{b^2 + 4 \cdot a \cdot c}}{2 \cdot a}$$
```

The determinant $\Delta$ is:
$$
\Delta = b^2 - 4 \cdot a \cdot c
$$
Second-order polynome solutions are :
$$x_{1.2} = \frac{-b \pm \sqrt{b^2 + 4 \cdot a \cdot c}}{2 \cdot a}$$

**Source:**
+ [Markdown-it]
+ [Markdown-it-math]
+ [Markdown-it-mathjax]
+ [MathJax]

[Markdown-it]: https://github.com/markdown-it/markdown-it
[Markdown-it-math]: https://github.com/runarberg/markdown-it-math
[Markdown-it-mathjax]: https://github.com/classeur/markdown-it-mathjax
[MathJax]: https://www.mathjax.org/

[ASCIIMath Syntax]: https://runarberg.github.io/ascii2mathml/

</content></note></subcontent></handbook>