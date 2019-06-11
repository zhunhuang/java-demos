window.tableHeight = 550;
window.pageList = [10, 30, 50, 100];

window.typeLink = [
    ["saleCount", "订单"],
    ["o2pRate", "比率"]
];

// 选中的chart指标
window.selectedChartSeriesIndex = [];
window.selectedChartSeriesName = [];

window.heightChart = {};
window.heightChartAddedLabel = [];

String.prototype.endWith = function(str) {
	var reg = new RegExp(str + "$");
	return reg.test(this);
}

var numberReg = new RegExp("^[0-9.]*$");

function focusMenu() {
	var link = window.location.href;
	$("ul.navbar-nav").find("li").each(function() {
		if (link.endWith($(this).attr("name"))) {
			$(this).addClass("active");
			
			var parentLi = $(this).parent().parent();
			if ($(parentLi).hasClass("subMenu")) {
				$(parentLi).addClass("active");
			}
			return;
		}
	});
}

/**
 * 创建表格
 * @param tableParams
 * @param data
 * @param columns
 * @return
 */
function initTable(tableParams, data, columns) {
    var $table = $('#table');
    
    for (var i = 0; i < columns.length; i++) {
    	columns[i].formatter = columns[i].formatter || function(cellValue, row, index) {
    		if (numberReg.test(cellValue)) {
    			return formatPerfectNumber(cellValue, 2);
    		} else {
    			return cellValue;
    		}
    	}
    }

    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        data: data,
        fixedColumns: true,
        fixedNumber: tableParams.fixedNumber || 1,
        exportDataType: 'all', //导出所有数据
        showExport: true,
        exportTypes: ['csv', 'txt', 'excel'],
        showPaginationSwitch: true,
        pagination: tableParams.pagination, //分页
        pageNumber: 1,
        pageSize: window.pageList[0],                       //每页的记录行数（*）
        pageList: window.pageList,        //可供选择的每页的行数（*）
        height: window.tableHeight,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        striped: true,
        cache: false,
        search: true, //显示搜索框
        sidePagination: tableParams.sidePagination || "client", // server服务端处理分页 client客户端分页
        url: tableParams.url || "",
        queryParamsType: '', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                            // 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
        columns: columns
    });
    
    $(window).resize(function () {
    	$table.bootstrapTable('resetView');
    });
    // 修正bootstrapTable错误
    $(".fixed-table-header-columns tr:first th:last").removeAttr("rowspan");
}

/**
 * 日期选择
 * @param start
 * @param end
 * @return
 */
function fillDate(start, end) {
    $("#startDate").val(laydate.now(start, "YYYY-MM-DD"));
    $("#endDate").val(laydate.now(end, "YYYY-MM-DD"));
};

/**
 * 统一查询数据提交
 * @param url
 * @param callback
 * @return
 */
function ajaxSubmit(url, params, callback) {
    AjaxUtils.sendAjaxJson(url, params, function (data) {
        if (data.status == 0) {
        	callback(data.data);
        } else {
            Prompt.error({
                content: data.message
            });
        }
    }, function() {
    	// window.location.reload();
    });
}

function formatPerfectNumber(value, scale) {
	if (value == 0) return value;
	var formatValue = formatNumber2(value, scale);
	var tmp = ("" + formatValue).split(".");
	if (tmp.length == 1) {
		return formatNumber(tmp[0]);
	} else {
		return formatNumber(tmp[0]) + "." + tmp[1];
	}
}

function showPercentRate(data){
    var index = $('#indexName option:selected') .val();
    if (data != null&& window.showRate.indexOf(index)>=0) {
        data = data.toFixed(2) + "%";
    }
    return data;
}

/**
 * format数字
 * @param num
 * @return
 */
function formatNumber(num) {
    if (num == 0) return 0;
    var text = "";
    while (num > 0) {
        var lowNum = (num % 1000).toFixed();
        var num = parseInt(num / 1000);
        if (num > 0) {
            while (lowNum.length < 3) {
                lowNum = "0" + lowNum;
            }
            lowNum = "," + lowNum;
        }
        text = lowNum + text;
    }
    return text;
};

/**
 * 格式化数字
 * @param value 原值
 * @param scale 保留小数位个数
 * @return
 */
function formatNumber2(value, scale) {  
    return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
}

/**
 * 创建chart
 * @param chartJson
 * @param params
 * params.type chart类型
 * params.title 标题
 * params.tooltip true/false 是否需要tooltip
 * params.needRateYAxis true/false 是否需要百分比y轴
 * params.xAxis x轴数据 数组
 * params.selectByIndex 按序号选择指标
 * params.selectSeriesByName 按名称选择指标
 * @return
 */
function initChart(params) {
    var chartJson = {};
    chartJson.chart = {
        type: params.type || "spline",
        height: params.height || 500
    };
    chartJson.plotOptions = params.plotOptions || {};
    chartJson.title = {
        text: params.title || "",
        x: -20 //center
    }
    chartJson.legend = {
        layout: 'horizontal',
        align: 'center',
        verticalAlign: 'bottom',
        borderWidth: 0
    };
    if (typeof params.yAxis != "undefined") {
        chartJson.yAxis = params.yAxis;
    } else {
        chartJson.yAxis = [{
            lineWidth: 1,
            title: {
                text: "数量"
            },
            labels: {
                formatter: function () {
                    return formatPerfectNumber(this.value, 2);
                }
            }
        }];
        if (params.needRateYAxis) {
            chartJson.yAxis.push({
                lineWidth: 1,
                opposite: true,
                title: {
                    text: '百分比'
                },
                labels: {
                    formatter: function () {
                        return this.value;
                    }
                }
            });
        }
    }

    chartJson.xAxis = {
        categories: params.xAxis
    };

    chartJson.series = params.series;

    if (params.tooltip) {
        chartJson.tooltip = {
            crosshairs: true,
            shared: true
        };
    }

    // 选中之前选择的指标
    if (params.selectByIndex) {
        selectSeriesByIndex(chartJson, params.selectByIndex);
    } else if (params.selectSeriesByName) {
        selectSeriesByName(chartJson, params.selectSeriesByName);
    } else {
        selectSeriesByIndex(chartJson);
    }

    // 鼠标悬浮 增加最大值 最小值显示
    chartJson.plotOptions.series = chartJson.plotOptions.series || {};
    chartJson.plotOptions.series.events = chartJson.plotOptions.series.events || {};
    chartJson.plotOptions.series.events.mouseOver = function () {
        var points = this.points;
        var min = 0;
        var max = 0;
        var pointsToShow = [0, 0];
        Highcharts.each(points, function (p) {
            if (p.index == 0) {
                min = max = p.y;
            } else {
                if (p.y > max) {
                    pointsToShow[0] = p.index;
                    max = p.y;
                }
                if (p.y < min) {
                    pointsToShow[1] = p.index;
                    min = p.y;
                }
            }
        });
        render(this.chart, this.color, points[pointsToShow[0]]);
        render(this.chart, this.color, points[pointsToShow[1]]);
    }

    // redraw时取消最大最小值显示
    chartJson.chart.events = chartJson.chart.events || {};
    chartJson.chart.events.redraw = function () {
        for (var i = 0; i < window.heightChartAddedLabel.length; i++) {
            window.heightChartAddedLabel[i].fadeOut();
        }
        window.heightChartAddedLabel = [];
    }
    window.heightChart = Highcharts.chart("chart", chartJson);
}

/**
 * 根据顺序选择指标
 * @param chartJson
 * @param defaultSelected
 * @return
 */
function selectSeriesByIndex(chartJson, defaultSelected) {
    var series = chartJson.series;
    if (series.length == 0 || window.selectedChartSeriesIndex.length == 0) {
        selectSeriersByDefault(series, defaultSelected);
    } else {
        for (var i in series) {
            series[i].visible = false;
        }
        for (var i in window.selectedChartSeriesIndex) {
            if (series[window.selectedChartSeriesIndex[i]] == null ||
                series[window.selectedChartSeriesIndex[i]] == "undefined") {
                continue;
            }
            series[window.selectedChartSeriesIndex[i]].visible = true;
        }
    }
}

function selectSeriersByDefault(series, defaultSelected) {
    if (typeof defaultSelected != "undefined") {
        for (var i in defaultSelected) {
            if (typeof series[defaultSelected[i]] != "undefined") {
                series[defaultSelected[i]].visible = true;
            }
        }
    }
}

/**
 * 根据名称选择
 * @param chartJson
 * @param defaultSelected
 * @return
 */
function selectSeriesByName(chartJson, defaultSelected) {
    var series = chartJson.series;
    if (window.selectedChartSeriesName.length == 0) {
        selectSeriersByDefault(series, defaultSelected);
    } else {
        var selected = false
        for (var i in series) {
            series[i].visible = false;
            for (var j in window.selectedChartSeriesName) {
                if (series[i].name == window.selectedChartSeriesName[j]) {
                    series[i].visible = true;
                    selected = true;
                    break;
                }
            }
        }
        // 当根据上次选择都有选中的时候, 根据默认选择
        if (!selected) {
            selectSeriersByDefault(series, defaultSelected);
        }
    }
}

function render(chart, color, point) {
    var addedLabel = chart.renderer.label(formatPerfectNumber(point.y, 2), point.plotX + chart.plotLeft - 20, point.plotY + chart.plotTop - 45, 'callout', point.plotX + chart.plotLeft, point.plotY + chart.plotTop)
        .css({
            color: '#FFFFFF',
            align: 'center'
        }).attr({
            fill: color,
            padding: 8,
            r: 5,
            zIndex: 6
        }).add();

    window.heightChartAddedLabel.push(addedLabel);
    setTimeout(function () {
        addedLabel.fadeOut();
    }, 5000);
}

//显示%的指标类型
window.showRate = ["b2oRate", "o2pRate", "accidentInsRate", "delayInsRate", "combineInsRate", "xRate", "refundRate", "changeRate"];
/**
 * 添加百分比数据
 * @param data
 * @return
 */
function addPercentRate(data) {
    var index = $('#indexName option:selected').val();
    for (var i in data) {
        var obj = data[i];
        for (var j in obj) {
            if (obj[j] == "0") {
                obj[j] = "-";
            } else if (j.indexOf("Rate") >= 0 || j.indexOf("ticketsCompare") >= 0) {
                if (obj[j] != null) {
                    obj[j] = obj[j].toFixed(2) + "%";
                }
            } else if (j.indexOf("Data") >= 0 || j.toLowerCase().indexOf("first") >= 0 || j.toLowerCase().indexOf("second") >= 0) {
                if (obj[j] != null && window.showRate.indexOf(index) >= 0) {
                    obj[j] = obj[j].toFixed(2) + "%";
                }
            }
        }
    }

};