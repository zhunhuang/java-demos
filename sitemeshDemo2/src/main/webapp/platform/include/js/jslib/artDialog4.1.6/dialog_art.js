function setDialog(title, htmlId, options, jQueryObj) {
    //	 top, left, resize, zIndex ,artId,
    var art_Dialog;
    if (options.left && options.top) {
        art_Dialog = art.dialog({
            title: title || "消息",
            content: document.getElementById(htmlId),
            id: options.artId || 'MSE34K',
            zIndex: options.zIndex || 10004,
            resize: options.resize || false,//	是否允许用户调节尺寸
            drag: false,
            left: options.left,
            top: options.top,
            lock: options.lock || true,
            close: jQueryObj || function () {
            }
        });
    } else {
        art_Dialog = art.dialog({
            title: title || "消息",
            content: document.getElementById(htmlId),
            id: options.artId || 'MSE34K',
            zIndex: options.zIndex || 10004,
            resize: options.resize || false,//	是否允许用户调节尺寸
            drag: false,
            lock: options.lock || true,
            close: jQueryObj || function () {
            }
        });
    }
    return art_Dialog;
}

function setAjaxDialog(url, title, dialogId, parameter) {
    var addDialog = art.dialog({
        id: dialogId || "M23567",
        title: title || "弹框",
        lock: true
    });
    AjaxUtils.sendAjax(url, parameter, function (data) {
        addDialog.content(data);
    });
    return addDialog;
}
function setConfirmDialog(option, callback) {
    var dialog = art.dialog({
        padding: 0,
        id: option.id || 'testID2',
        title: option.title || '',
        content: option.content || '',
        width: option.width || 'auto',
        height: option.height || 'auto',
        lock: option.lock,
        top: option.top || '38.2%',
        left: option.left || '50%',
        button: [{
            name: '确定',
            callback: callback || function () {
                return false;
            },
            focus: true
        }, {
            name: '取消',
            callback: function () {
                return true;
            }
        }]
    });
    return dialog;
}
function setSuccessDialog(option) {
    art.dialog({
        time: option.time || 1,
        lock: true,
        top: option.top,
        content: option.content || '两秒后关闭'
    });

}