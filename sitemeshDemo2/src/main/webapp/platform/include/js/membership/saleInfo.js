/**
 * Created by ke.gong on 2017/1/28.
 */
jQuery(document).ready(function () {
    function fillTableToday(data) {
        $('#today').bootstrapTable({
            columns: [{
                title: '权益卡编码',
                field: 'cardCode',
                align: 'center'
            }, {
                title: '权益卡名',
                field: 'cardName',
                align: 'center'
            }, {
                title: '销售量',
                field: 'orderCount',
                align: 'center'
            }, {
                title: '退订量',
                field: 'refundCount',
                align: 'center'
            }, {
                title: '退订率',
                field: 'refundRate',
                align: 'center'
            }],
            data: data,
            height: calcHeight(data.length)
        });
    };

    function fillTableYesterday(data) {
        $('#yesterday').bootstrapTable({
            columns: [{
                title: '权益卡编码',
                field: 'cardCode',
                align: 'center'
            }, {
                title: '权益卡名',
                field: 'cardName',
                align: 'center'
            }, {
                title: '销售量',
                field: 'orderCount',
                align: 'center'
            }, {
                title: '退订量',
                field: 'refundCount',
                align: 'center'
            }, {
                title: '退订率',
                field: 'refundRate',
                align: 'center'
            }],
            data: data,
            height: calcHeight(data.length)
        });
    };

    function fillTableHistory(data) {
        $('#history').bootstrapTable({
            columns: [{
                title: '权益卡编码',
                field: 'cardCode',
                align: 'center'
            }, {
                title: '权益卡名',
                field: 'cardName',
                align: 'center'
            }, {
                title: '销售量',
                field: 'orderCount',
                align: 'center'
            }, {
                title: '退订量',
                field: 'refundCount',
                align: 'center'
            }, {
                title: '退订率',
                field: 'refundRate',
                align: 'center'
            }],
            data: data,
            height: calcHeight(data.length)
        });
    };

    function showData() {
        ajaxSubmit("./query", $('#searchForm').serialize(), function (data) {
            fillTableToday(data.today);
            fillTableYesterday(data.yesterday);
            fillTableHistory(data.history);
        });
    }

    $(function () {
        focusMenu();
        showData();
    });

    function calcHeight(length) {
        if (length == 0) return 80;
        return (length + 1) * 40;
    }

    jQuery("#reset-today").on("click", function () {
        reload($('#today'), 'today', 'false');
    });

    jQuery("#reset-yesterday").on("click", function () {
        reload($('#yesterday'), 'yesterday', 'false');
    });

    jQuery("#reset-history").on("click", function () {
        reload($('#history'), 'history', 'false');
    });


    function reload(table, time, fromCache) {
        table.bootstrapTable('refresh', {url: './refresh?time=' + time + '&fromCache=' + fromCache});
        setTimeout(
            function () {
                table.bootstrapTable('resetView', {height: calcHeight(table.find('tr').length - 1)})
            }, 500);
    }

    /**
     * 每隔1分钟刷新
     * 今天、总量 数据
     */
    setInterval(function () {
        reload($('#today'), 'today', 'true');
        reload($('#yesterday'), 'yesterday', 'true');
        reload($('#history'), 'history', 'true');
    }, 60000);
});