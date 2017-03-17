** HTML Notebook **

HTML Notebook is a way to organise notes. They are linked together through tags allowing to navigate easily in.
Notes are grouped by chapters and by volume. When displaying the document, all notes are aggregated in a unique vertical column. This representation is close to what you could have by printing it.

I created this tool since I was not able to find a similar and simple method to show information. There are different interesting tools as wikis (such as TiddlyWiki or Zim). However, the information is displayed by elements (as a tiddly) and not following a stream. So I developed two tools to do so.
The first is a Latex template associated to scripts to compile and load automatically content. I appreciate it since it is easy to use it. But it requires a Latex compiler.
This second tools need only a Firefox browser (or the Tiddly Desktop software). I could make this notebook compatible with more browsers. Using only Firefox as renderer software makes this tool cross-platform and portable.

** HOW TO USE **

First thing first, an example is provided.

You do not need server to run it. Open it directly in firefox or Tiddy Desktop Software.

Notebooks are divided in several parts:
    -The root HTML file and JavaScript are the engine of this tool. They manage to collect all the fragments and assemble them.
    -The CSS folder contains the document style for screens and paper.
    -The last part is the data. It represents all the notes and are divided between volume, chapter, sections. This last element represents notes. 

Chapter and volume have JSON files associated. Each one represents the list of elements to contain. The volume JSON will refer to chapters and the chapter JSON will refer to sections. So when you add a section or a chapter, don’t forget to update the JSON of the parent. To assemble multiple volumes in one document, you need to update, ‘Notes With Volume.html’.
