```
title: Note Connection
id: tags
tags:
 - datastructure
 - Markdown
```

The interest of this tool is to connect notes together. This is done thanks to their id.

## IDs
Each notes have an ID. This ID is an arbitrary text. The only condition is to respect the Markdown limitation on links.
There is still a limitation due to the Markdown syntax. When defining a link, it is possible to associate it a tooltip text. This done using a space after the URL of the link and before the tooltip text. Hence, if you want to point to a note from your note texts, you need to remove the spaces from its ID. I will not solve this limitation since it is due to the Markdown syntax and I wish my tool stick to this syntax.
Moreover, ID shall be unique to disambiguate references.

Finally, it is possible to refer to a note through a link in the markdown syntax. To do so use one of the next structures.

```md
1. [Presentation note](#presentation)
1. [Presentation note](#presentation "Define tooltips text")
1. [You can use an arbitrary case-insensitive reference text][Arbitrary case-insensitive reference text]
1. [You can use numbers for reference-style link definitions][1]
1. Or leave it empty and use the [link text itself].
1. Finally, you can also use an html tag : <a href="#presentation" class="tag">Which is represented as a tag.</a>

[arbitrary case-insensitive reference text]: #presentation
[1]: #presentation
[link text itself]: #presentation
```

Which show becomes :

1. [Presentation note](#presentation)
2. [Presentation note](#presentation "Define tooltips text")
3. [You can use an arbitrary case-insensitive reference text][Arbitrary case-insensitive reference text]
4. [You can use numbers for reference-style link definitions][1]
5. Or leave it empty and use the [link text itself].
6. Finally, you can also use an html tag : <a href="#presentation" class="tag">Which is represented as a tag.</a>

[arbitrary case-insensitive reference text]: #presentation
[1]: #presentation
[link text itself]: #presentation

## Tags
A second referring mechanism exists.
By tagging the note in its header with IDs from other note, a tag list is displayed in the handbook under the note title. This reference also appears in the targeted note at the bottom in the reference section. This mechanism is very handy and the heart of the tool.