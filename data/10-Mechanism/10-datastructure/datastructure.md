<note id="datastructure" title="Data folder structure">

<headline/>
<content>
	
The **data folder** contains all the data to display in the final Markdown file.

The "data" folder will contain one Markdown file (.md). This file will be the root note. You can use the [handbook](#note-type) structure in it. This file must contain only on Markdown file (.md).
Each sub-notes will be contained in sub-folders. You can store multiple Markdown file in one folder. The name of the folder is not important. You can use it to order notes. The display order of notes will follow the alphabetic order of theses folders.
If you need to add file with a note, then you can add it to its folder. The only constraint is that the added file cannot be a Markdown file.

Example of a correct data structure
```
data + handbook.md
     |
     + subnote1 + subnote1.md
     |          + subnote2.md
     |
     + subnote2 + subnote3.md
                |
                + img.jpg
```

</content>
<subcontent/>
<contentList/>

</note>
