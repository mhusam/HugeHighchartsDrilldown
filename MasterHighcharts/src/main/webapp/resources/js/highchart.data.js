var HighchartData = function (series, drilldowns, divContainer) {
    this.series = series;
    this.drilldowns = drilldowns;
    // if params is not empty then draw chart
    if (divContainer) {
        this.drawChart(divContainer);
    }
    return this;
};

HighchartData.prototype = {
    getChart: function () {
        return this.chart;
    },
    drawChart: function (divContainer) {
        var options = this.options();
        this.chart = Highcharts.chart(divContainer, options);
        return this.chart;
    },
    options: function () {
        var _highchartData = this;
        var highchartOptions = {
            chart: {type: 'column', zoomType: 'x', spacingRight: 20, animation: {duration: 100},
                events: {
                    drilldown: function (e) {
                        _highchartData.handleDrilldown(e);
                    }
                }
            },
            credits: {enabled: false},
            title: {text: 'Master Highcharts'},
            xAxis: {type: 'category', reversed: true, crosshair: true},
            yAxis: {opposite: true},
            legend: {enabled: true},
            plotOptions: {
                pie: {showInLegend: true},
                column: {showInLegend: true, pointPadding: 0.1, borderWidth: 7},
                series: {borderWidth: 0, dataLabels: {enabled: true}}
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series: _highchartData.series,
            drilldown: {
                activeDataLabelStyle: {
                    color: '#003399'
                },
                series: []}
        };
        return highchartOptions;
    },
    handleDrilldown: function (e) {
        if (!e.seriesOptions && this.drilldowns) {
            var chart = this.chart,
                    drilldown = this.drilldowns[e.point.name],
                    isLabelClicked = !e.point.state;

            if (drilldown) {
                if (isLabelClicked) {

                    var isEmpty = true;

                    if (drilldown.childs) {
                        for (var i = 0; i < drilldown.childs.length; i++) {
                            if (drilldown.childs[i].data.length !== 0) {
                                isEmpty = false;
                                break;
                            }
                        }
                    }

                    if (!isEmpty) { // drilldown if it's not empty
                        for (var i = 0; i < drilldown.childs.length; i++) {
                            chart.addSingleSeriesAsDrilldown(e.point, drilldown.childs[i]);
                        }
                        chart.applyDrilldown();
                    } else {
//                      chart.renderer.text('لا يوجد معلومات', 140, 120).add();
                    }
                } else {
                    if (drilldown.status) {
                        for (var i = 0; i < drilldown.status.length; i++) {
                            chart.addSeriesAsDrilldown(e.point, drilldown.status[i]);
                        }
                    }
                }
            }
        }
    }
};
