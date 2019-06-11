function fillTable(data) {
    var maxRowspan = getMaxRowspan(data.columnList);
    var columsHead = [];
    var columsHead1 = [];
    var columsHead2 = [];
    columsHead1.push({title: data.indexColumn.name, field: 'date', rowspan: maxRowspan, align: 'center', valign: 'middle'});
    // 解析head1
    for (var i = 0; i < data.columnList.length; i++) {
        var column = data.columnList[i];
        columsHead1.push({
            title: column.name,
            field: column.field,
            rowspan: isMultiColumn(column) ? 1 : maxRowspan,
            colspan: getMaxColspan(column),
            align: 'center',
            valign: 'middle'
        });
    }
    // 解析head2
    for (var i = 0; i < data.columnList.length; i++) {
        var column = data.columnList[i];
        if (!isMultiColumn(column)) {
            continue;
        }
        for (var j = 0; j < column.columnList.length; j++) {
            var subColumn = column.columnList[j];
            columsHead2.push({
                title: subColumn.name,
                field: column.field + "_" + subColumn.field,
                align: 'center',
                valign: 'middle',
                class: column.type,
                formatter: function(cellValue, row, index) {
            		if (("" + cellValue).indexOf("%") == -1) {
            			return formatPerfectNumber(cellValue, 2);
            		} else {
            			return cellValue;
            		}
            	}
            });
        }
    }
    columsHead.push(columsHead1);
    if (columsHead2.length != 0) {
        columsHead.push(columsHead2);
    }

    // 解析数据
    var dataArray = [];
    for (var i = 0; i < data.indexColumn.dataList.length; i++) {
        var obj = {};
        obj['date'] = data.indexColumn.dataList[i];
        for (var j = 0; j < data.columnList.length; j++) {
            var column = data.columnList[j];
            if (!isMultiColumn(column)) {
                if (isScaleColumn(column)) {
                    obj[column.field] = column.scaleFmtList[i];
                } else if (isCountColumn(column)||isStringColumn(column)) {
                    obj[column.field] = column.dataList[i];
                } else if (isAlertColumn(column)) {
                    var contents = column.dataList[i];
                    var html = "";
                    for (var l = 0; l < contents.length; l++) {
                        var content = contents[l];
                        html += "<a href='javascript:void(0);' onclick=alertMsg(\""+encodeURIComponent(content.link)+"\")>"+content.name+"</a>";
                        obj[column.field] = html;
                    }
                } else if (isEventColumn(column)) {
                	var options = column.dataList[i];
                	var html = "";
                	for (var l = 0; l < options.length; l++) {
                		var option = options[l];
                		html += "<a class='m-left-10' href='" + option.link + "' target='_blank'>" + option.name + "</a>";
                		obj[column.field] = html;
                	}
                }
                continue;
            }
            for (var k = 0; k < column.columnList.length; k++) {
                var subColumn = column.columnList[k];
                if (isScaleColumn(subColumn)) {
                    obj[column.field + "_" + subColumn.field] = subColumn.scaleFmtList[i];
                } else {
                    obj[column.field + "_" + subColumn.field] = subColumn.dataList[i];
                }
            }
        }
        dataArray.push(obj);
    }

    initTable({pagination: true}, dataArray, columsHead);
}

function getMaxRowspan(columnList) {
    for (var i = 0; i < columnList.length; i++) {
        if (isMultiColumn(columnList[i])) {
            return 2;
        }
    }
    return 1;
}

function getMaxColspan(column) {
    return isMultiColumn(column) ? column.columnList.length : 1;
}

function maxRowSpan(column, maxRowSpan) {
    return Math.max(getCurrentRowSpan(column), maxRowSpan);
}

function getCurrentRowSpan(column) {
    return isMultiColumn(column) ? 2 : 1;
}

function isMultiColumn(column) {
    return column.type == "MULTI";
}

function isScaleColumn(column) {
    return column.type == "SCALE";
}

function isCountColumn(column) {
    return column.type == "COUNT";
}

function isEventColumn(column) {
    return column.type == "EVENT";
}

function isStringColumn(column) {
    return column.type == "STRING";
}

function isAlertColumn(column) {
    return column.type == "ALERT";
}

function plotLine(data) {
    var lineArray = [];
    for (var i = 0; i < data.columnList.length; i++) {
        var lines = parseColumnLine(data.columnList[i], data.chartReverse);
        if (lines != null) {
            lineArray = lineArray.concat(lines);
        }
    }
    initChart({
        title: "",
        needRateYAxis: true,
        series: lineArray,
        xAxis: data.chartReverse ? data.indexColumn.dataList.concat().reverse() : data.indexColumn.dataList,
        tooltip: true
    });
}

function parseColumnLine(column, reverse, prefix) {
    if (isCountColumn(column)) {
        return parseCountLine(column, reverse, prefix);
    } else if (isScaleColumn(column)) {
        return parseScaleLine(column, reverse, prefix);
    } else if (isMultiColumn(column)) {
        return parseMultiColumn(column, reverse);
    }
    return null;
}

function parseCountLine(column, reverse, prefix) {
    if (!column.line) {
        return null;
    }
    var lineObj = {
        name: prefix != null ? prefix + "_" + column.name : column.name,
        data: reverse ? column.dataList.concat().reverse() : column.dataList,
        visible: true
    };
    return [lineObj];
}

function parseScaleLine(column, reverse, prefix) {
    if (!column.line) {
        return null;
    }
    var lineObj = {
        name: prefix != null ? prefix + "_" + column.name : column.name,
        data: reverse ? column.dataList.concat().reverse() : column.dataList,
        visible: true,
        yAxis: 1,
        tooltip: {
            valueSuffix: '%'
        }
    };
    return [lineObj];
}

function parseMultiColumn(column, reverse) {
    var lineArr = [];
    for (var i = 0; i < column.columnList.length; i++) {
        var lines = parseColumnLine(column.columnList[i], reverse, column.name);
        if (lines != null) {
            lineArr = lineArr.concat(lines);
        }
    }
    return lineArr;
}

function alertMsg(value){
    alert(decodeURIComponent(value));
}

