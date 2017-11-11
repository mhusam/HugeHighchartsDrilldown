<%-- 
    Document   : index
    Created on : Nov 10, 2017, 5:12:32 AM
    Author     : Toshiba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Highcharts</title>
        
        <script src="resources/js/jquery-3.2.1.js" type="text/javascript"></script>
        <link href="resources/highchart/css/highcharts.css" rel="stylesheet" type="text/css"/>
        <script src="resources/highchart/js/highcharts.js" type="text/javascript"></script>
        <script src="resources/highchart/js/drilldown.js" type="text/javascript"></script>
        <script src="resources/js/highchart.config.js" type="text/javascript"></script>
        <script src="resources/js/highchart.data.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>Welcome to Huge Drilldowns Data</h1>
        
        <div id="chartContainer" style="min-width: 310px; height: 400px; margin: 0 auto;"></div>
        
        <script type="text/javascript">            
            $(document).ready(function(){
                
//                var sample1 = ;
//                var sample2 = ;
//                var sample3 = ;
//                var sample4 = ;
                
                
                $.get("http://localhost:8084/MasterHighcharts/GetDataFromServer", function(data){
                    data = Function("return " + data + "")();
                    console.log(data);
                    var master = data.master;
                    var detail = data.detail;
                    var dataChart = new HighchartData(master, detail, 'chartContainer');
                });
            });
        </script>
    </body>
</html>
