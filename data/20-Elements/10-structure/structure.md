```
title: Structure and Template
id : template
tag: 
 - balises
```

This version of the HTML-handbook separate data from format.
This allows to define a unique template for the whole document [TODO - will change in the futur].

The structure of the standard format is :
``` xml
<element>
	<title/>
	<tags/>
	<toc/>
	<content/>
	<references/>
	<hr/>
	<subcontent/>
</element>
```
## Structure
Their is seven elements. By order of apparition:

+ **element**: This tag is _necessary_ to interprete the template as the structure of an element.
+ **title**: The title tag represents the place that will be used by the title.
+ **headline**: Headline indicates to the handbook where the headline of a note should be placed. This headline will contain the title of the note, the author and the version of the note.
+ **tags**: This balise indicates to the handbook where to display tags. It will contain the list of tags.
+ **toc**: This balise indicates where to place the _table of content_.
+ **content**: The content structure contains the text. This text is formed following the [Markdown syntaxe](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet).
+ **reference**: This balise indicates where the references are displayed.

- **subcontent**: The subcontent structure indicates where sub-notes must be added.

## Modification of the template
By modifying this template you update all the notes. This provide a way to get a WYSIWYM editor. In other words, this architecture allows you to update the whole handbook structure with you need to modify each note to match your new template.
It is possible to reorder the tags and to add text.
