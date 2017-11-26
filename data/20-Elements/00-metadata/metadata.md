```
title: Metadata
tags:
 - template
```

**A note should always start by metadata field.**

A metadata field is defined by using the non-official but quite common following syntax:
```md
​```
title: This is a title
id: this is an id
toc: 2
tags:
 - a tag
 - a-second-tag
date: dd/MM/yyyy
author: Thomas Auriel
version: version 0.5
activity: 2017-02-01
​```
```

The box defined by:
```md
​```

​```
```
Is the way to defined code area in Markdown files. The text inside is not interpreted by Markdown and display as it.

In this textbox, several field can be defined.
+ **title**: Title elements defines the title to display at the begging of the note in the final handbook. It is an obligation for a note to have a title. This is the minimal requirement.
```md
​```
title: This is a title
​```
```

+ **id**: The id represents a text used to link the element through the whole document (see [tag mechanism](#tags)). If this element is not specified, the title is used an ID. IDs shall be unique otherwise it is not possible to navigate correctly through the document.
```md
​```
id: this is an id
​```
```

+ **toc**: The ToC element defines the size of the *table of content* to display in the current note. The value required is an integer. If this element is not provided, then the level is supposed to be 0 and no table of content are displayed.
```md
​```
toc: 2
​```
```

+ **tags**: The tag element is a list of ID referring to notes contained in the current handbook. This allows to display a list of tags at the begging of a note (after the title) and allow to navigate easily in the document.
```md
​```
tags:
- a-tag
- a-second-tag
​```
```

+ **date**: The requested value is a date (in any format) or a date format pattern according to `SimpleDateFormat` java class. This means to by providing to text `yyyy-MM-dd` this will display the date when the final handbook is generated.
```md
​```
date: dd/MM/yyyy
​```
```

+ **author**: The text provided in the field will be displayed on the first page in the author field.
```md
​```
author: Thomas Auriel
​```
```

+ **version**: The text provided in the field will be displayed on the first    page in the version field.
```md
​```
version: version 0.4
​```
```

+ **activity**: Activity field defines a note a temporal event. This can be useful to write a journal and keep information on past event. The required value must be a date following the format `yyyy-MM-dd`.
```md
​```
activity: 2017-02-01
​```
```

+ **template**: This field defines the template to use. If it is not specified, then the standard template is used.
```md
​```
template: standard.xml
​```
```