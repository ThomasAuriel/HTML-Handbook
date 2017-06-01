```
title: Note Connection
tags:
 - datastructure
 - tags
```

# IDs
Note ID allows the browser to create a hyperlink to a note.
This link must be unique to disambiguate notes references. It is possible to referent to a note through a link in the markdown syntax. To do so use one of the next structures.

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
1. [Presentation note](#presentation "Define tooltips text")
1. [You can use an arbitrary case-insensitive reference text][Arbitrary case-insensitive reference text]
1. [You can use numbers for reference-style link definitions][1]
1. Or leave it empty and use the [link text itself].
1. Finally, you can also use an html tag : <a href="#presentation" class="tag">Which is represented as a tag.</a>

[arbitrary case-insensitive reference text]: #presentation
[1]: #presentation
[link text itself]: #presentation

# Tags
Tags are a list of note ids.
This tags are shown in the headline if the _tags_ structure is present.
Tags allow the tool to connect two notes. The note which contains the tags tag, refers targeted notes and will appear in the section "Content in the document".
