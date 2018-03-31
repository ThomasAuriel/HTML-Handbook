
// Default values.
var width = 960,
    height = 136,
    cellSize = 17;

var color = d3.scaleQuantize()
    .range(["#f0f0f0", "#66bd63"]);


function formatCalendar(){

    var formatPercent = d3.format(".1%");

    $('calendar').each(function(index, calendar){
        var rangeStarting = calendar.getAttribute('starting');
        var rangeEnding = calendar.getAttribute('ending');

        rangeStarting = parseInt(rangeStarting)
        rangeEnding = parseInt(rangeEnding)+1

        // Parse data
        var calendarData = calendar.getAttribute('data');
        calendarData = JSON.parse(calendarData)

        //Define areas for each calendar
        var svg = d3.select("calendar")
                    .selectAll("svg")
                    .data(d3.range(rangeStarting, rangeEnding))
                    .enter().append("svg")
                    .attr("width", width)
                    .attr("height", height)
                    .append("g")
                    .attr("transform", "translate(" + ((width - cellSize * 53) / 2) + "," + (height - cellSize * 7 - 1) + ")");

        //Define the date label.
        svg.append("text")
           .attr("transform", "translate(-6," + cellSize * 3.5 + ")rotate(-90)")
           .attr("font-family", "sans-serif")
           .attr("font-size", 10)
           .attr("text-anchor", "middle")
           .text(function(d) { return d; });

        // Define days rectangles
        var rect = svg.append("g")
                      .attr("fill", "none")
                      .attr("stroke", "#ccc")
                      .selectAll("rect")
                      .data(function(d) { return d3.timeDays(new Date(d, 0, 1), new Date(d + 1, 0, 1)); })
                      .enter().append("rect")
                      .attr("width", cellSize)
                      .attr("height", cellSize)
                      .attr("x", function(d) { return d3.timeWeek.count(d3.timeYear(d), d) * cellSize; })
                      .attr("y", function(d) { return d.getDay() * cellSize; })
                      .datum(d3.timeFormat("%Y-%m-%d"));
    
        // Define month separator
        svg.append("g")
           .attr("fill", "none")
           .attr("stroke", "#000")
           .selectAll("path")
           .data(function(d) { return d3.timeMonths(new Date(d, 0, 1), new Date(d+1, 0, 1)); })
           .enter().append("path")
           .attr("d", pathMonth);

        //Format data for d3
        var data = d3.nest()
                     .key(function(d) { return d.date })
                     .object(calendarData);

        //Set days colors
          //Fill with the default color
        rect.attr("fill", color(0))
          //Fill with the color depending values.
        rect.filter(function(d) { return d in data; })
            .attr("fill", function(d) {
              return color(Object.keys(data[d]).length)
            })
            .append("title")
            .text(function(d) { return d + " : " + Object.keys(data[d]).length + " activity"});

    })

}

function pathMonth(t0) {
  var t1 = new Date(t0.getFullYear(), t0.getMonth() + 1, 0),
      d0 = t0.getDay(), w0 = d3.timeWeek.count(d3.timeYear(t0), t0),
      d1 = t1.getDay(), w1 = d3.timeWeek.count(d3.timeYear(t1), t1);
  return "M" + (w0 + 1) * cellSize + "," + d0 * cellSize
      + "H" + w0 * cellSize + "V" + 7 * cellSize
      + "H" + w1 * cellSize + "V" + (d1 + 1) * cellSize
      + "H" + (w1 + 1) * cellSize + "V" + 0
      + "H" + (w0 + 1) * cellSize + "Z";
}

