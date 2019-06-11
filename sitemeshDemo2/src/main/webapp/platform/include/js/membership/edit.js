/**
 * Created by ke.gong on 2017/1/28.
 */
jQuery(document).ready(function () {
    var id = $("#id").val();
    var url = id != 0 ? CTX + "/memberCard/" + id + "/update" : CTX + "/memberCard/create";

    jQuery("#submitbutton").on("click", function () {
        ajaxSubmit(url, $('#dataForm').serialize(), function (data) {
            Prompt.success({
                content: "操作成功",
                callback: function () {
                    window.location.href = CTX + "/memberCard/";
                }
            });
        });
    });

    jQuery("#backbutton").on("click", function () {
        window.location.href = CTX + "/memberCard/";
    });

    $(function () {
        focusMenu();
    });
});