# HTML Handbook

HTML Handbook is a way to organise notes inside handbooks. Notes are linked together through tags. All notes are aggregated in a unique vertical column allowing to display and print it similarly to a true handbook.

# Version

## Version 0.5

The version 0.5 reword the Java code to optimise it (faster and easier to support).

The user will not see large change. The modification noticeable are:

- General structure of the final handbook.
- Easier CSS edition
- Faster compilation
- More explicit error messages

## Version 0.4

The version 0.4 use the markdown syntaxe to write content.
The version is simpler than all previous versions thank to a large rework on data structure. This version is also a lot of lighter. The new version is only 38MB compare to the monstrous 172MB of the previous versions. You can still reduce the size by removing MathJax. Its only purpose is to render math equations.
**[See documentation](https://github.com/ThomasAuriel/HTML-Handbook/blob/master/formatedHandbook.md)**

# Motivation

I created this tool since I was not able to find a similar and simple method to show information. There are different interesting tools as wikis (such as TiddlyWiki or Zim). However, the information is displayed by elements and not in a stream. The stream view allows to move around those elements without following a precise path and find easier information (in addition of tag navigation).

The first is a Latex template associated to scripts to compile and load automatically content. I appreciate it since it is easy to use it. But it requires a Latex compiler.

This second tools need only a _Firefox browser_. Using only Firefox as renderer software makes this tool cross-platform and portable. I did not develop this tool for other browser since they have limitations access to local files. To produce the final handbook Java 1.5 or higher should be used. Today, Java 1.8 is public and most computer can run Java 1.5 code without issue.

# How to Use
I provide documentation in the sources. Since this documentation use the tool, it is also a complete example.

## Tips
1. Use only ASCII characters in the folder and file names (no special characters). Otherwise, the handbook could be loaded incorectly. Do the same for ID tags.
2. To be able to write and use mathematical equation, please unzip MathJax from its zip in `./js` .
