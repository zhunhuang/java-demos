/**
 * Created by ke.gong on 2017/1/28.
 */
jQuery(document).ready(function () {
    function fillTable(data) {
        initTable({pagination: true}, data, [{
            title: 'date',
            field: 'date',
            align: 'center',
            width: '20%'
        }, {
            title: '订单量',
            field: 'saleCount',
            align: 'center'
        }, {
            title: '比率',
            field: 'o2pRate',
            align: 'center'
        }
        //, {
        //    title: '权益卡名',
        //    field: 'cardName',
        //    align: 'center'
        //}, {
        //    title: '权益卡类型',
        //    field: 'fmtCardType',
        //    align: 'center',
        //    width: '10%'
        //}, {
        //    title: '售价',
        //    field: 'fmtSalePrice',
        //    align: 'center',
        //    width: '10%'
        //}, {
        //    title: '卡参考价格',
        //    field: 'fmtReferencePrice',
        //    align: 'center',
        //    width: '10%'
        //}, {
        //    title: '库存',
        //    field: 'totalStock',
        //    align: 'center',
        //    width: '15%'
        //},{
        //    title: '使用量',
        //    field: 'useStock',
        //    align: 'center',
        //    width: '15%'
        //},{
        //    title: '卡套餐公式',
        //    field: 'cardContent',
        //    align: 'center',
        //    width: '15%'
        //},{
        //    title: '卡有效日期',
        //    field: 'validityTime',
        //    align: 'center',
        //    width: '15%'
        //},{
        //    title: '活动开始时间',
        //    field: 'fmtBeginTime',
        //    align: 'center',
        //    width: '15%'
        //},{
        //    title: '活动截止时间',
        //    field: 'fmtEndTime',
        //    align: 'center',
        //    width: '15%'
        //},{
        //    title: '卡状态',
        //    field: 'fmtCardStatus',
        //    align: 'center',
        //    width: '15%'
        //},  {
        //    title: '卡描述',
        //    field: 'cardDescribe',
        //    align: 'center',
        //    width: '15%'
        //},  {
        //    title: '用卡限制',
        //    field: 'useLimit',
        //    align: 'center',
        //    width: '15%'
        //},
        //    {
        //    title: '免责声明',
        //    field: 'disclaimer',
        //    align: 'center',
        //    width: '15%'
        //},
        //    {
        //    title: '更新时间',
        //    field: 'fmtLastUpdateTime',
        //    align: 'center',
        //    width: '15%'
        //},{
        //    title: '创建日期',
        //    field: 'fmtCreateTime',
        //    align: 'center',
        //    width: '15%'
        //}, {
        //    title: '操作',
        //    field: 'operate',
        //    align: 'center',
        //    events: operateEvents,
        //    formatter: operateFormatter,
        //    width: '15%'
        //}
        ]);
    };

    //画线
    function plotTotalLine(data, reverse) {
        var xAxisData = [];
        for (var i = 0; i < data.length; i++) {
            xAxisData.push(reverse ? data[data.length - 1 - i].date : data[i].date);
        }

        var lineArray = [];
        for (var i in window.typeLink) {
            //if (window.typeLink[i][0].indexOf("ticketPerOrder") >= 0 ||
            //    window.typeLink[i][0].indexOf("bookingPerTicket") >= 0) {
            //    continue;
            //}
            var lineData = [];
            for (var j = 0; j < data.length; j++) {
                var obj = data[j];
                if (reverse) {
                    obj = data[data.length - 1 - j];
                }
                lineData.push(obj[window.typeLink[i][0]]);
            }
            if (window.typeLink[i][0].indexOf("Rate") < 0) {
                lineArray.push({
                    name: window.typeLink[i][1],
                    data: lineData,
                    visible: false
                });
            } else {
                lineArray.push({
                    name: window.typeLink[i][1],
                    data: lineData,
                    yAxis: 1,
                    visible: false,
                    valueSuffix: '%'
                });
            }
        }

        initChart({
            title: "",
            needRateYAxis: true,
            xAxis: xAxisData,
            series: lineArray,
            selectByIndex: [0]
        });
    };

    window.operateEvents = {
        'click .offline': function (e, value, row, index) {
            Prompt.confirm({
                title: "确认操作",
                content: "是否确认下线",
                okCallback: function () {
                    ajaxSubmit("./" + row.id + "/offline", {}, function (data) {
                        Prompt.success({
                            content: "操作成功",
                            callback: function () {
                                $('#submitbutton').trigger("click");
                            }
                        });
                    });
                }
            });
        },
        'click .online': function (e, value, row, index) {
            Prompt.confirm({
                title: "确认操作",
                content: "是否确认上线",
                okCallback: function () {
                    ajaxSubmit("./" + row.id + "/online", {}, function (data) {
                        Prompt.success({
                            content: "操作成功",
                            callback: function () {
                                $('#submitbutton').trigger("click");
                            }
                        });
                    });
                }
            });
        },
        'click .delete': function (e, value, row, index) {
            Prompt.confirm({
                title: "确认操作",
                content: "是否确认删除",
                okCallback: function () {
                    ajaxSubmit("./" + row.id + "/delete", {}, function (data) {
                        Prompt.success({
                            content: "操作成功",
                            callback: function () {
                                $('#submitbutton').trigger("click");
                            }
                        });
                    });
                }
            });
        },
        'click .edit': function (e, value, row, index) {
            window.location.href = "./" + row.id + "/edit";
        }
    };

    function operateFormatter(value, row, index) {
        var options = [];
        if (row.fmtCardStatus == "上线") {
            options.push('<a class="offline" href="javascript:void(0)" style="margin-right: 20px;">下线</a>');
        } else {
            options.push('<a class="online" href="javascript:void(0)" style="margin-right: 20px;">上线</a>');
        }
        options.push(
            '<a class="edit" href="javascript:void(0)" style="margin-right: 20px;">修改</a>',
            '<a class="delete" href="javascript:void(0)">删除</a>'
        );
        return options.join('');
    }

    jQuery("#submitbutton").on("click", function () {
        ajaxSubmit("./query", $('#searchForm').serialize(), function (data) {
            plotTotalLine(data, true);
            addPercentRate(data);
            fillTable(data);
        });
    });

    $(function () {
        focusMenu();
        $('#submitbutton').trigger("click");
    });
});