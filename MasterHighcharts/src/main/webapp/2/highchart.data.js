var HighchartData = function (series, drilldowns, divContainer) {
    this.series = series;
    this.drilldowns = drilldowns;
    // if params is not empty then draw chart
    if(divContainer){
        this.drawChart(divContainer);
    }
    return this;
};

HighchartData.prototype = {
    getChart: function(){
        return HighchartData.chart;
    },
    drawChart: function (divContainer) {
        var options = this.options();
        var chart = Highcharts.chart(divContainer, options);
        HighchartData.chart = chart;
        return chart;
    },
    options: function(){
        var _highchartData = this;
        var highchartOptions = { 
                chart: { type: 'column', 
                    events: {
                        drilldown: function (e) {
                            if (!e.seriesOptions) {
                                var chart = this,
                                    drilldowns = _highchartData.drilldowns,
                                    drilldown  = drilldowns[e.point.name],
                                    isLabelClicked = !e.point.state;
                                
                                if(drilldown){
                                    if(isLabelClicked) {
                                        for(var i = 0; i < drilldown.childs.length; i++){
                                            chart.addSingleSeriesAsDrilldown(e.point, drilldown.childs[i]);
                                        }
                                        chart.applyDrilldown();
                                    } else {
                                        for(var i = 0; i < drilldown.status.length; i++){
                                            chart.addSeriesAsDrilldown(e.point, drilldown.status[i]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                title: {text: 'Master Highcharts'},
                xAxis: {type: 'category', reversed: true},
                yAxis: {opposite: true},
                legend: {enabled: true},
                plotOptions: {
                    pie: {showInLegend: true},
                    column: {showInLegend: true},
                    series: {borderWidth: 0, dataLabels: {enabled: true}}
                },
                series: _highchartData.series,
                drilldown: {series: []}
            };
        return highchartOptions;
    }
};
