// solve drillupall in one call
drillupall

// change colors in Highcharts 6
<style type="text/css">
    #container {
        height: 400px;
        max-width: 800px;
        margin: 0 auto;
    }
    .highcharts-color-0 {
        fill: #058DC7;
        stroke: #058DC7;
    }
    .highcharts-axis.highcharts-color-2 .highcharts-axis-line {
        stroke: #7cb5ec;
    }
    .highcharts-axis.highcharts-color-2 text {
        fill: #7cb5ec;
    }
    .highcharts-yaxis .highcharts-axis-line {
        stroke-width: 2px;
    }
</style>
            
// change 
e.point.colorIndex = i;

// scroll 
xAxis: {
    type: 'category',
    title: {
        text: null
    },
    min: 0,
    max: 4,
    scrollbar: {
        enabled: true
    },
    tickLength: 0
},
