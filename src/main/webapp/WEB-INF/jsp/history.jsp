<%@page import="ru.naumen.sd40.log.parser.DataType"%>
<%@page import="ru.naumen.sd40.log.parser.modes.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.influxdb.dto.QueryResult.Series" %>

<html>

<head>
    <title>SD40 Performance indicator</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css"
          integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"
            integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/style.css"/>
</head>

<body>

<script src="http://code.highcharts.com/highcharts.js"></script>

<div class="container">
    <br>
    <h1>Performance data for "${client}"</h1>
    <h3><a class="btn btn-success btn-lg" href="/">Client list</a></h3>
    <h4 id="date_range"></h4>
    <p>
        Feel free to hide/show specific data by clicking on chart's legend
    </p>
</div>

<div id="chart-container" style="height: 600px"></div>

<script type="text/javascript">
    <%
        Map<String, Number[]> values = (Map<String, Number[]>)request.getAttribute("statdata");
        Number times[] = values.remove(Constants.TIME);
        String names[] = values.keySet().toArray(new String[values.keySet().size()]);
        List<Number[]> entries = new ArrayList<>(values.values());
    %>

    var times = [];
    var names = [];
    var dataEntries = [];

    Highcharts.setOptions({
    global: {
        useUTC: false
    }
    });

    <% for(int i=0; i < names.length; i++) {%>
        dataEntries[<%=i%>] = [];
        names[<%=i%>] = "<%=names[i]%>";
    <%}%>

    <% for(int i=0; i<times.length; i++) {%>
        times.push((<%=times[i]%>));
            <% for(int j = 0; j<names.length;j++) {%>
                dataEntries[<%=j%>].push([new Date(<%= times[i] %>), <%= java.lang.Math.round(entries.get(j)[i].intValue()) %>]);
            <%}%>
    <% } %>

    var series = [];
    for (var i = 0; i < names.length; i++) {
            series.push({
                name: names[i],
                data: dataEntries[i],
                turboThreshold: 10000
        })
    }

    var myChart = Highcharts.chart('chart-container', {
        chart: {
            zoomType: 'x,y'
        },
        title: {
            text: '<%=request.getAttribute("client")%> chart'
        },
        tooltip: {
            formatter: function() {
                var index = this.point.index;
                var date =  new Date(times[index]);
                return Highcharts.dateFormat('%a %d %b %H:%M:%S', date)
                    + '<br/> <b>'+this.series.name+'</b> - '+ this.y + ' times<br/>'
            }
        },
        xAxis: {
            labels:{
                formatter:function(obj){
                    return Highcharts.dateFormat('%a %d %b %H:%M:%S', new Date(times[this.value]));
                }
            },
            reversed: true
        },
        yAxis: {
            title: {
                text: '<%=request.getAttribute("requested_datatype")%>'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        plotOptions: {
            line: {
                marker: {
                    enabled: false
                }
            }
        },
        series: series
    });
    document.getElementById('date_range').innerHTML += 'From: '+new Date(times[<%=times.length%>-1])+'<br/>To:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +new Date(times[0])
</script>

<div class="container scroll-container">
    <table class="table table-fixed header-fixed">
        <thead class="thead-inverse">
            <th class="col-xs-2">Time</th>
            <%for (int i = 0; i < names.length; i++) {%>
            <th class="col-xs-1"><%=names[i]%></th>
            <%}%>
        </thead>
        <tbody>
        <% for(int i=0;i<times.length;i++) {%>
            <tr class="row">
                <td class="col-xs-2" style="text-align:center;">
                    <%= new java.util.Date(times[i].longValue()).toString() %>
                </td>
                <% for(int j=0;j<names.length;j++) {%>
                <td class="col-xs-1">
                    <%= java.lang.Math.round(entries.get(j)[i].intValue()) %>
                </td>
                <%}%>
            </tr>
        <%}%>
        </tbody>
    </table>
</div>

</body>

</html>