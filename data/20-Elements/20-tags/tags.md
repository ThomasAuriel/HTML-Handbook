<note id="tags" title="Tags" tags='[structures]'>

<headline/>
<content>

Different tags exist.

- **title**: A string which represents the title of the current note. It is used with the [note](#note) and [handbook](#headline)

- **author**: A string which is used to represent the author of a [handbook](#headline).

- **id**: The id is used with [note](#note) and [handbook](#headline). This is obligatory tag. Id must be unique, contain standard characters (no accents, no spaces, no punctuation excepted dash).
- **level**: Level is used in [toc](#toc). It represents the max depth of Table of Content. If it is not present, then it is assumed to be 1.
- **tags**: The tags is used with [note](#note). This is not an obligation to use it. Tags are a [JSON structure](http://json.org/example.html) containing notes id. Each id will be replaced by a link to the corresponding note and display in the note header line.

```json
tags="[id1, id2]"
```

- **version**: The version is used in the [handbook](#handbook) structure. The version tag contains the version to display on the first page. It is interpreted as a regular text. So you can add any text.

- **date**: The date is used in the [handbook](#handbook) structure. The date tag contain the date to display on the first page. It is interpreted as a regular text. So you can add any text.

- **dateformat**: The date format is used in the [handbook](#handbook) structure. This tag is used if date tag is not used. The date format allows to display the date when the handbook is generated. Allow formats are defined by the [SimpleDateFormat format](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html). For instance dd/MM/YYYY or MM/dd/YYYY. This date format is used to format the current date and display it on the first page.

- **activity**: Activity is a tag used with notes elements. It allows users to define a note as an activity. This is used full to add a note which represents a temporal event.

</content>
<subcontent/>
<contentList/>

</note>
