module.exports = {

	renderMarkdown: function(){
		var md = require('markdown-it')({
			  html:         true,        // Enable HTML tags in source
			  xhtmlOut:     false,        // Use '/' to close single tags (<br />).
			                              // This is only for full CommonMark compatibility.
			  breaks:       true,        // Convert '\n' in paragraphs into <br>
			  langPrefix:   '',           // CSS language prefix for fenced blocks. Can be
			                              // useful for external highlighters.
			  linkify:      true,         // Autoconvert URL-like text to links
                          typography: true
			})

		//render math
		md.use(require('markdown-it-math'), {
			inlineOpen: '_$',
			inlineClose: '$_',
			blockOpen: '_$$',
			blockClose: '$$_'
		});

		// Checkboxes
		md.use(require('markdown-it-checkbox'));
		// Footnotes
		md.use(require('markdown-it-footnote'));

		//Highlight
		md.use(require('markdown-it-highlightjs'));

		//Images
		// md.use(require('markdown-it-imsize'), { autofill: false });
		//Video
		md.use(require('markdown-it-video'));

		//Graph
		md.use(require('markdown-it-mermaid'));


		const env = {}
		$('.markdown').each(function(index, element){
			var text = element.textContent;
			var html = md.render(text, env);
			env.highlighted === true;
			element.innerHTML = html;
		})
	}
}
