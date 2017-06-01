```
title: Presentation
```

## A handbook
This handbook is a tool which provides a simple handbook rendered through HTML.

## Website
This tool is published on Github: https://github.com/ThomasAuriel/HTML-Handbook. It is **free** and available to download. **You should not pay** to get this tool.

## Motivation
This tool provide a way to display information similarly to a handbook. All information is display on a unique page and each note follow each other. Moreover, a system based on a tags and references allow to navigate in the whole document.
No other tool allow to do this in a light way. To run this handbook you need a modern internet browser, a simple text editor (for instance notepad) and Java 1.5 at least (most computers have already an higher version installed). That's all. You do not need a server to host the notebook, and you do not need to install a software.
Some other softwares exists to provide a way to display and organize information. I propose you a list of few and most notables.
 + **OneNote**: probably the most known. This software allow to display, organize and edit notes, to share them and to read them in a very friendly interface. Their several limitation which are: not free licence (property of Microsoft), need to install it, cannot export all notes in another software easily.
 + **TiddlyWiki**: It is well known tools. It is free (no cost and free to read and modify the code). The community is large and different tools can be installed on it to bring new features. Finally, *it can run entierly in a modern webbrowser* and use a friendly interface (*Markdown syntaxe*). Several disadvantages exist: The whole information is stored in a one html file. This file can be large if you embedded data in it or wrote lot of content. It is possible to share fragment of a handbook but it is not very friendly. Information is not display efficiently. To navigate inside the informations, you need to follow a path similarly to a classic wiki. This does not allow to see the whole information on a unique page.
 + **Zim** and similar softwares: Zim is a kind of software allowing to wrote note similarly to a wiki using the *Markdown syntaxe*. This software have the same limitation as TiddlyWiki conserning the display of information. However, notes are stored in separate file which allow to easily share them. Since it is a software running on Debian (and possibly other Linux OS, I didn't test) it is not cross-platform.

For all this reason, I developed this tools.
This tool must be able to:
 + provide a way to wrote note in a simple syntaxe. I choosed [Markdown] to do so associated to a [metadata] system.
 + display information in a stream. This is done by rendering the whole handbook in a unique html page.
 + provide a way to easily share notes and reorganise the handbook. This is possible by moving notes between handbooks or folders.
 + be able to read the handbook on multiple platform.

The current version of this tools (Version 0.4) match this features.

In the futur and next version I would like to be able to run this in a unique webpage but some limitation due to security in webbrowser does not allow me to do this (see [limitations])

[Markdown]: #markdown
[metadata]: #metadata
[limitations]: #limitations
