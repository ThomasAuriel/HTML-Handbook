```
title: Data folder structure
id: datastructure
tags:
 - Markdown
```

The data folder contains all the data to display in the final handbook file.
**Each notes are written using the [markdown] syntax and must use the extension associated: `.md`.**

*The first folder must contain one unique [markdown] file (.md).* This file represents the handbook first page. Once you defined this file, you can add multiple folders in it. They will be included in the handbook after the first page as sub-notes.
Sub-folders can contain multiple [Markdown] files. They will be regarded as a note in the same level during the generation. This means that they will be included in their parent as sub-notes. However, if a folder containing other sub-folder has multiple markdown files, then each markdown file will contain the sub-notes. This creates a confusing duplication of notes. This structure is to avoid.

Example of a **correct data structure**
```
data + handbook.md
     |
     + subnote1 + subnote1.md
     |          + subnote2.md
     |
     + subnote2 + subnote3.md
                + img.jpg
```

Example of a **data structure to avoid**:
```
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

[Markdown]: #Markdown