```
title: Data folder structure
id: datastructure
tags:
 - markdown
```
	
The data folder contains all the data to display in the final handbook file.
**Each notes are wrote using the [markdown] syntaxe and must use the extension associated : .md.**

*The first folder must contain one unique [markdown] file (.md).* This file represents the handbook first page. Once you defined this file you can add multiple folders in it. They will be include in the handbook after the first page as subnotes.
Sub-folders can contain multiple [markdown] files. They will be regarded as note in the same level during the generation. This mean that the they will be include in their parent as sub-notes. However, if a folder containing other sub-folder has multiple markdown files, then each markdown file will contain the subnotes. This create a confusing duplication of notes. This structure is to avoid.

Example of a **correct data structure**
```nohighlight
data + handbook.md
     |
     + subnote1 + subnote1.md
     |          + subnote2.md
     |
     + subnote2 + subnote3.md
                + img.jpg
```

Example of a **data structure to avoid**:
```nohighlight
data + handbook.md
     |
     + subnote1 + subnote1.md
     |          + subnote2.md
     |          |
     |          + subnote11 + subnote11.md
     |                      + subnote21.md
     |
     + subnote2 + subnote3.md
                + img.jpg
```

[markdown]: #markdown
