# HTML Handbook

HTML Handbook is a way to organise notes inside handbooks. They are linked together through tags. All notes are aggregated in a unique vertical column allowing to display and print it similarly to a true handbook.

## New version
The new version 0.3 use the markdown syntaxe to write content and is way simpler to use. **[See documentation](https://github.com/ThomasAuriel/HTML-Handbook/blob/master/formatedHandbook.md)**

## Motivation

I created this tool since I was not able to find a similar and simple method to show information. There are different interesting tools as wikis (such as TiddlyWiki or Zim). However, the information is displayed by elements and not in a stream. The stream view allows to move around those elements without following a precise path and find easier information (in addition of tag navigation).

The first is a Latex template associated to scripts to compile and load automatically content. I appreciate it since it is easy to use it. But it requires a Latex compiler.

This second tools need only a Firefox browser (or the Tiddly Desktop software). Using only Firefox as renderer software makes this tool cross-platform and portable. I did not develop this tool for other browser since they have limitations access to local files.

## How to Use
I provide documentation in the sources. Since this documentation use the tool, it is also a complet example.

## Tips
Use only ASCII characters in the folder and file names (no special characters). Otherwise, the handbook could be loaded incorectly. Do the same for ID tags.

## Issues

The MathJax library is unnecessary too large (51MB). Must be reduced. If you do _not_ need MathJax you can remove it and remove associated code in the Note.html file.
