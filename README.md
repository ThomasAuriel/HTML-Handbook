## HTML Handbook

HTML Handbook is a way to organise notes inside handbooks. They are linked together through tags. All notes are aggregated in a unique vertical column allowing to display and print it similarly to a true handbook.

Notes are called sections. They are grouped by volumes divided in chapters. Each elements are independent from the parent. For instance, if a section is defined in a chapter, to move it to another chapter, just displace the section file in our target chapter and regenerate the handbook structure. If you wish to display a handbook with a supplementary volume just add it in the handbook and regenerate the folder structure.

## Motivation

I created this tool since I was not able to find a similar and simple method to show information. There are different interesting tools as wikis (such as TiddlyWiki or Zim). However, the information is displayed by elements and not in a stream. The stream view allows to move around those elements without following a precise path and find easier information (in addition of tag navigation).

The first is a Latex template associated to scripts to compile and load automatically content. I appreciate it since it is easy to use it. But it requires a Latex compiler.

This second tools need only a Firefox browser (or the Tiddly Desktop software). Using only Firefox as renderer software makes this tool cross-platform and portable. I did not develop this tool for other browser since they have limitations access to local files.

## How to Use
I provide an example in the sources. This example include all the structure that are handle.

Handbooks are divided in several parts:

    —The root HTML file and JavaScript are the engine of this tool. They manage to collect all the fragments and assemble them.
    —The CSS folder contains the document style for screens and paper.
    —The last part is the data. It represents all the notes and are divided between volumes, chapters and sections. This last element represents notes.

The structure of the handbook is contained in a json file called ‘content.json’ and in the same folder that the handbook. This file can be handled by hand, but I recommend to use the jar file associated to the project. It is easier to use.
