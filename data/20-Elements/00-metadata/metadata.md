```
title: Metadata
tags:
 - template
```

**A note should always start by metadata field.**

This field is marked using the markdown unofficial but commun syntax:
```md
	```
	title: This is a title
	id: this-is-an-id
	toc: 2
	tags:
	 - a-tag
	 - a-second-tag

	date: dd/MM/yyyy
	author: Thomas Auriel
	version: version 0.4

	activity: 2017-02-01
	```
```
In this textbox, several field can be defined.
+ **title**: Title element define the title to display at the begging of the note in the final handbook.

```md
	```
	title: This is a title
	```
```

+ **id**: The id represents a text used to link the element through the whole document. If this element is not specified, a standard id is generated based on the title. Whitespaces and non-ASCII characters are replaced by a dash. This does not prevent unicity of id and it recommanded to wrote its one id.

```md
	```
	id: this-is-an-id
	```
```

+ **toc**: The toc element define the size of the *table of content* to display. The value required is a integer. If this element is not provided, then the level is supposed to be 0 and no table of content are displayed.

```md
	```
	toc: 2
	```
```

+ **tags**: Tags is a list of id refering to notes contained in the current handbook. This allow to display a list of tag at the begging of a note (after the title) and allow to navigate easily in the document.
```md
	```
	tags:
	 - a-tag
	 - a-second-tag
	```
```

+ **date**: The requested value is a date (in any format) or a date formater pattern according to SimpleDateFormat java class. This means to by providing to text 'yyyy-MM-dd' this will display the date when the final handbook is generated.
```md
	```
	date: dd/MM/yyyy
	```
```

+ **author**: The text provided in the field will be display on the first page inthe author field.
```md
	```
	author: Thomas Auriel
	```
```

+ **version**: The text provided in the field will be display on the first 
page inthe version field.
```md
	```
	version: version 0.4
	```
```

+ **activity**: activity define a note a temporal event. This can be usefull to write a journal and keep informations on past event. The required value must follow the format yyyy-MM-dd.
```md
	```
	activity: 2017-02-01
	```
```

+ **template**: **NOT YET IMPLEMENTED, BUT SOON!** This field defines which template to use. If it is not specify, then the standard template is used.
```md
	```
	template: standard.xml
	```
```
