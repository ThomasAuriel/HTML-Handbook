```
title: Relative Path
id: relative-path
```

If you share note, or reorganize your notes, it can be convenient to define relative path to resources.
To do this, a unique word is require `$NotePath` (in full uppercase). This expression will be replaced by the path of the folder containing the current note. Then it is possible to define a link to a image like:
```md
![an image with a convinent path]($NotePath/resources/img.jpg)
```

The word is then replaced by 
```md
![an image with a convinent path]($NOTEPATH/resources/img.jpg)
```
