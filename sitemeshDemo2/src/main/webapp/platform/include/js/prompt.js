Prompt = function () {
    var promptObj = this;
    this.width = 'auto';
    this.height = 'auto';
    this.lock = true;//开启锁屏。
    this.icon = "succeed";//定义消息图标。
    this.title = "消息";//默认 '消息'	标题内容
    this.content = "操作成功";//消息内容
    this.time = 5;
    var Prompt = {
        success: function (option) {
            promptObj.setIcon("succeed");
            promptObj.setTime(3);
            return promptObj.init(option.title || "操作成功", option.content, option.lock,
                option.time, option.callback);
        },
        error: function (option) {
            promptObj.setIcon("error");
            return promptObj.init(option.title || "操作失败", option.content, option.lock,
                option.time, option.callback);
        },
        warning: function (option) {
            promptObj.setIcon("warning");
            return promptObj.init(option.title || "警告", option.content, option.lock,
                option.time, option.callback);
        },
        info: function (option) {
            promptObj.setIcon("info");
            return promptObj.init(option.title || "提示", option.content, option.lock,
                option.time, option.callback);
        },

        //类confirm提示窗口可以自定义事件
        initConfirm: function (option) {
            var dialog = art.dialog({
                icon: "question",
                content: option.content || promptObj.content,
                width: promptObj.width,
                height: promptObj.height,
                lock: option.lock || promptObj.lock,
                title: option.title || promptObj.title,
                padding: "20px 25px 20px 0px",
                cancel: option.canaelCallback || function () {
                    return true;
                },//Function	null	取消按钮回调函数。
                ok: option.okCallback || function () {
                    return true;
                } //Function	null	确定按钮回调函数。
            });
            return dialog;
        },
        //类confirm提示窗口不可定以事件
        confirm: function (option) {
            var bool = true;
            var dialog = art.dialog({
                icon: "question",
                content: option.content || promptObj.content,
                width: promptObj.width,
                height: promptObj.height,
                lock: option.lock || promptObj.lock,
                title: option.title || promptObj.title,
                padding: "20px 25px 20px 0px",
                cancel: option.canaelCallback || function () {
                    bool = false;
                    return true;
                },
                ok: option.okCallback || function () {
                    bool = true;
                    return true;
                }
            });
            return bool;
        },
        //保留接口
        extended: function () {
            var dialog = art.dialog({
                icon: promptObj.icon,
                content: promptObj.content,
                width: promptObj.width,
                height: promptObj.height,
                lock: promptObj.lock,
                time: promptObj.time,
                title: promptObj.title
            });
            return dialog;
        }
    };
    this.setHeight = function (height) {
        this.height = height;
    };
    this.setWidth = function (width) {
        this.width = width;
    };
    this.setLock = function (lock) {
        this.lock = lock;
    };
    this.setIcon = function (icon) {
        this.icon = icon;
    };
    this.setTitle = function (title) {
        this.title = title;
    };
    this.setContent = function (content) {
        this.content = content;
    };
    this.setTime = function (time) {
        this.time = time;
    };
    //类alert提示窗口
    this.init = function (title, content, lock, time, callback) {
        time = lock ? this.time : time;
        var dialog = art.dialog({
            icon: this.icon,
            content: content || this.content,
            width: this.width,
            height: this.height,
            lock: lock || this.lock,
            time: time || this.time,
            title: title || this.title,
            padding: "20px 25px 20px 0px",
            ok: callback || function () {
                return true;
            },
            close: callback || function () {
                return true;
            }
        });
        return dialog;
    };
    return Prompt;
}();