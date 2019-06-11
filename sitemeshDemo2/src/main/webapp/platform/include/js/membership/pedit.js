/**
 * Created by ke.gong on 2017/1/28.
 */
jQuery(document).ready(function () {
    var id = $("#id").val();
    var url = id != 0 ? CTX + "/privilege/" + id + "/update" : CTX + "/privilege/create";

    jQuery("#submitbutton").on("click", function () {
        ajaxSubmit(url, $('#dataForm').serialize(), function (data) {
            Prompt.success({
                content: "操作成功",
                callback: function () {
                    window.location.href = CTX + "/privilege/";
                }
            });
        });
    });

    jQuery("#backbutton").on("click", function () {
        window.location.href = CTX + "/privilege/";
    });

    $(function () {
        focusMenu();
    });

    $('.peditSubmit').on('click', function () {
        var _self = $(this),
            key = _self.parent().prev().prev().prev().find('input').val(),
            desc = _self.parent().prev().prev().find('input').val(),
            value = _self.parent().prev().find('input').val();

        $.ajax({
            url: CTX + "/privilege/updateExt",
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                id: _self.data('id'),
                extValue: value,
                extKey: key,
                extKeyDesc: desc,
                privilegeCode: _self.data('code')
            })
        }).done(function (res) {
            alert('修改成功！');
            location.href = location.href;
        }).fail(function () {
            alert('修改失败！');
        });
    });

    $('.pDeleteSubmit').on('click', function () {
        var _self = $(this);

        $.ajax({
            url: CTX + "/privilege/deleteExt",
            type: 'POST',
            data: {
                id: _self.data('id')
            }
        }).done(function (res) {
            alert('删除成功！');
            location.href = location.href;
        }).fail(function () {
            alert('删除失败！');
        });
    });

    $('.pAddSubmit').on('click', function () {
        var _self = $(this),
            key = _self.parent().prev().prev().prev().find('input').val(),
            desc = _self.parent().prev().prev().find('input').val(),
            value = _self.parent().prev().find('input').val();

        $.ajax({
            url: CTX + "/privilege/createExt",
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                extValue: value,
                extKey: key,
                extKeyDesc: desc,
                privilegeCode: _self.data('code')
            })
        }).done(function (res) {
            alert('新增成功！');
            location.href = location.href;
        }).fail(function () {
            alert('新增失败！');
        });
    });

    $('.table').find('.updatevalue')

});