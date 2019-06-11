/**
 * Created by ke.gong on 2017/1/28.
 */
jQuery(document).ready(function () {
    function fillTable(data) {
        initTable({pagination: true}, data, [{
            title: 'id',
            field: 'id',
            align: 'center',
            width: '2%'
        }, {
            title: '权益编码',
            field: 'privilegeCode',
            align: 'center'
        }, {
            title: '权益名称',
            field: 'privilegeName',
            align: 'center'
        }, {
            title: '权益类型',
            field: 'fmtPrivilegeType',
            align: 'center',
            width: '10%'
        }, {
            title: '关联系统编码',
            field: 'associationCode',
            align: 'center',
            width: '10%'
        }, {
            title: '权益结算价格',
            field: 'fmtSettlePrice',
            align: 'center',
            width: '10%'
        }, {
            title: '权益简介',
            field: 'privilegeBrief',
            align: 'center',
            width: '15%'
        }, {
            title: '权益详情简介',
            field: 'privilegeShort',
            align: 'center',
            width: '10%'
        }, {
        //    title: '使用说明',
        //    field: 'privilegeDesc',
        //    align: 'center',
        //    width: '15%'
        //},{
            title: '用户中心的展示icon',
            field: 'iconUrl',
            align: 'center',
            width: '15%'
        },{
            title: '灰色icon',
            field: 'blackIconUrl',
            align: 'center',
            width: '15%'
        },{
            title: '特权详情图片',
            field: 'detailPicUrl',
            align: 'center',
            width: '15%'
        },{
            title: '更新时间',
            field: 'fmtLastUpdateTime',
            align: 'center',
            width: '15%'
        },{
            title: '创建日期',
            field: 'fmtCreateTime',
            align: 'center',
            width: '15%'
        }, {
            title: '操作',
            field: 'operate',
            align: 'center',
            events: operateEvents,
            formatter: operateFormatter,
            width: '15%'
        }
        ]);
    };

    window.operateEvents = {
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
        options.push(
            '<a class="edit" href="javascript:void(0)" style="margin-right: 20px;">修改</a>',
            '<a class="delete" href="javascript:void(0)">删除</a>'
        );
        return options.join('');
    }

    jQuery("#submitbutton").on("click", function () {
        ajaxSubmit("./query", $('#searchForm').serialize(), function (data) {
            fillTable(data);
        });
    });

    $(function () {
        focusMenu();
        $('#submitbutton').trigger("click");
    });
});