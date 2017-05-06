<note id="noteConnection" title="Note Connection" tags='[structures]'>

<headline/>
<content>

**IDs**

Note ID allow to create an hyperlink to a note.
This link must be unique to disambiguate notes references. It is possible to referent to a note through a link in the markdown syntaxe. To do so use one of the next structures.

```md
1. [Presentation note](#presentation)
1. [Presentation note](#presentation "Define the the tooltips text")
1. [You can use an arbitrary case-insensitive reference text][Arbitrary case-insensitive reference text]
1. [You can use numbers for reference-style link definitions][1]
1. Or leave it empty and use the [link text itself].
1. Finally you can also use an html balise : <a href="#presentation" class="tag">Which is represented as a tag.</a>

[arbitrary case-insensitive reference text]: #presentation
[1]: #presentation
[link text itself]: #presentation
```

Which show becomes :

1. [Presentation note](#presentation)
1. [Presentation note](#presentation "Define the the tooltips text")
1. [You can use an arbitrary case-insensitive reference text][Arbitrary case-insensitive reference text]
1. [You can use numbers for reference-style link definitions][1]
1. Or leave it empty and use the [link text itself].
1. Finally you can also use an html balise : <a href="#presentation" class="tag">Which is represented as a tag.</a>

[arbitrary case-insensitive reference text]: #presentation
[1]: #presentation
[link text itself]: #presentation


**Tags**
	
Tags are a list of note ids.
This tags are shown in the headline if the _tags_ structure is present.
Tags allow to connect two notes. The note which contain the tags balise, refere targeted note and will appears in the section "Content in the document".

</content>
<subcontent/>
<contentList/>

</note>
