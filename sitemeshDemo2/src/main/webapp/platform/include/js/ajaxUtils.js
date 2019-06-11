AjaxUtils = function () {

    var AjaxUtils = {

        /**
         * send ajax request with the feedback json data type
         */
        sendAjax: function (postUrl, postData, callback, callbackError, block) {
            var isBlock = typeof block == 'undefined' ? true : block;
            try {
                if (isBlock) $.blockUI();
            } catch (e) {
            }
            $.ajax({
                type: "POST",
                url: postUrl,
                cache: false,
                dataType: "html",
                data: postData,
                success: callback,
                error: callbackError || function () {
                },
                complete: function () {
                    try {
                        if (isBlock) $.unblockUI();
                    } catch (e) {
                    }
                }
            });
        },

        sendAjaxJson: function (postUrl, postData, callback, callbackError, block) {
            var isBlock = typeof block == 'undefined' ? true : block;
            try {
                if (isBlock) $.blockUI();
            } catch (e) {
            }
            $.ajax({
                type: "POST",
                url: postUrl,
                cache: false,
                data: postData,
                dataType: "json",
                success: callback,
                error: callbackError || function () {
                },
                complete: function () {
                    try {
                        if (isBlock) $.unblockUI();
                    } catch (e) {
                    }
                }
            });
        },

        sendPerfectAjaxJson: function (postUrl, postData, callback, callbackError, block) {
            var isBlock = typeof block == 'undefined' ? true : block;
            try {
                if (isBlock) $.blockUI();
            } catch (e) {
            }
            $.ajax({
                type: "POST",
                url: postUrl,
                cache: false,
                dataType: "json",
                data: postData,
                success: function (result) {
                    if (result.info.status == "ERROR") {
                        Prompt.error({
                            content: result.info.msg
                        });
                    } else if (result.info.status == "WARNING") {
                        Prompt.warning({
                            content: result.info.msg
                        });
                    } else if (result.info.status == "RELOGIN") {
                        Prompt.warning({
                            content: result.info.msg,
                            callback: function () {
                                var dialog = window.parent ? window.parent : window;
                                dialog.location.reload();
                                return true;
                            }
                        });
                    } else if (result.info.status == "FAILURE") {
                        Prompt.error({
                            content: result.info.msg || "操作失败"
                        });
                    } else if (result.info.status == "TIMEOUT") {
                        Prompt.warning({
                            content: result.info.msg || "会话超时，请重新登录"
                        });
                    } else if (result.info.status == "DENY") {
                        Prompt.warning({
                            content: result.info.msg || "访问受限，请联系管理员"
                        });
                    } else if (result.info.status == "SUCCESS") {
                        callback(result) || function () {
                        };
                    }
                },
                error: callbackError || function () {
                },
                complete: function () {
                    try {
                        if (isBlock) $.unblockUI();
                    } catch (e) {
                    }
                }
            });
        },

        ajaxSubmitJson: function (formId, postUrl, callback, callbackError) {
            $('#' + formId).ajaxSubmit({
                type: "POST",
                url: postUrl,
                dataType: 'json',
                success: function (result) {
                    if (result.info.status == "ERROR") {
                        Prompt.error({
                            content: result.info.msg
                        });
                    } else if (result.info.status == "WARNING") {
                        Prompt.warning({
                            content: result.info.msg
                        });
                    } else if (result.info.status == "RELOGIN") {
                        Prompt.warning({
                            content: result.info.msg,
                            callback: function () {
                                var dialog = window.parent ? window.parent : window;
                                dialog.location.reload();
                                return true;
                            }
                        });
                    } else if (result.info.status == "SUCCESS") {
                        callback(result) || function () {
                        };
                    }
                },
                error: callbackError || function () {
                }
            });
        }
    };
    return AjaxUtils;
}();