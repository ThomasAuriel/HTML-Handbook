<note id="structures" title="Structures" tags='["balises"]'>

<headline/>
<content>

Structures are the elements used to format notes.
There are six different elements:
- **content**
	The content structure contains the text. This text is formed following the [Markdown](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet).

- **headline**
	Headline is an element which the position where the title of the note and the tag list should be written.

	```html
	<md/>
	```

- **toc**
	The toc tag defined where to write the Table of Content.
	The toc tag accepts one [tag](#tags):
	- **level**: The level represents the max depth of the Table of Content. It must be a **integer** and is **not an obligation**.

	```md
	<toc level='...'/>
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

</content>
<subcontent/>
<contentList/>

</note>
