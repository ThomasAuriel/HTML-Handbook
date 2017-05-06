<note id="structures" title="Structures" tags='["balises"]'>

<headline/>
<content>
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
	<toc level='...'/>
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

</content>
<subcontent/>
<contentList/>

</note>
