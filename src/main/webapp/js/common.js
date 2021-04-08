let sensitivity = 0.2;
let __cmpKeys = ["calorie", "protein", "fat", "carbohydrate", "sodium", "salt", "cholesterol", "sugar", "vitaminA", "vitaminD", "vitaminE", "vitaminK", "vitaminB1", "vitaminB2", "vitaminB6", "vitaminB12", "vitaminC", "calcium", "iron", "magnesium", "zinc", "potassium"];
let __frictionDigits = 2;
let __shortUrlBase = "https://zhuangcloud.cn/s/";

(function ($) {

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    $.ok = function (msg, callback) {
        $.msg(msg, "", callback);
    };

    $.error = function (msg, callback) {
        $.msg(msg, "", callback);
    };


    $.msg = function (msg, type, callback) {
        bootbox.alert({
            size: "small",
            message: msg,
            callback: callback
        });
    };

    $.tip = function (msg, type, callback) {
        new $.zui.Messager({
            placement: 'center',
            type: type
        }).show(msg, callback);
    };

    $.confirm = function (msg, buttonOK, buttonCancel, callback) {
        bootbox.confirm({
            size: "small",
            message: msg,
            buttons: {
                confirm: {
                    label: buttonOK
                },
                cancel: {
                    label: buttonCancel
                }
            },
            callback: callback
        });
    };

    $.prompt = function (title, inputType, callback) {
        bootbox.prompt({
            title: title,
            inputType: inputType,
            callback: callback
        });
    };

    /**
     * @return {string}
     */
    $.getPara = function (name) {
        let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        let r = window.location.search.substr(1).match(reg);
        if (r !== null) {
            return decodeURIComponent(r[2]);
        }
        return "";
    };

    $.getJFinalPara = function () {
        let url = window.location.href;
        return url.substring(url.lastIndexOf("\/") + 1, url.length);
    }

    $.delPara = function (name) {
        let url = location.href;
        let para = location.search.substr(1);
        let base = url.replace(para, "");
        let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        let r = para.match(reg);
        if (r !== null) {
            para = para.replace(r[0], (r[1] === "" || r[3] === "") ? "" : "&");
            url = base + para;
        }
        return url;
    };

    $.addSumRow = function (data) {
        let sum = {};
        __cmpKeys.forEach((key) => {
            sum[key] = 0;
        });
        sum.foodname = __res.total;
        data.forEach((row) => {
            for (let k in row) {
                if (null === row[k]) {
                    row[k] = 0;
                } else if ("number" === typeof row[k]) {
                    sum[k] += $.formatNum(row[k]);
                }
            }
        });
        sum.fid = 0;
        sum.weight = 0;
        sum.type = 0;
        if (data) {
            data.push(sum);
        } else {
            data = [sum];
        }
        return data;
    };

    $.post1 = function (url, data, success, button) {
        let error = function () {
            $.error(__res.networkError);
            if (button)
                button.prop("disabled", false);
        };
        $.ajax({
            type: 'post',
            url: url,
            data: data,
            success: success,
            error: error
        });

    };

    $.post2 = function (url, form, success) {
        let error = function () {
            $.error(__res.networkError);
        };
        $.ajax({
            type: 'post',
            url: url,
            data: form.serializeObject(),
            success: success,
            error: error
        });

    };

    $.post3 = function (url, data, success, complete) {
        let error = function () {
            $.error(__res.networkError);
        };
        $.ajax({
            type: 'post',
            url: url,
            data: data,
            success: success,
            error: error,
            complete: complete
        });

    };

    $.post4 = function (url, data, success, error, complete) {
        $.ajax({
            type: 'post',
            url: url,
            data: data,
            success: success,
            error: error,
            complete: complete
        });

    };

    $.jump = function (url) {
        location.href = url;
    };

    $.sleep = function (d) {
        for (let t = Date.now(); Date.now() - t <= d;) {
        }
    };

    $.formatNum = function (num, frictionDigits) {
        function parse(num, digits) {
            if (digits < 1) {
                return Math.round(num);
            }
            return parseFloat(num.toFixed(digits));
        }

        if (!num) {
            return 0;
        }
        if ("number" === typeof frictionDigits) {
            return parse(num, frictionDigits);
        }
        return parse(num, __frictionDigits);
    };

    $.buildShareLink = function (token, date, locale) {
        if (!token)
            return "";
        let shareLink = "https://zhuangcloud.cn/share?token=";
        shareLink += token;
        if (date) {
            shareLink += "&date=" + date;
        }
        if (locale) {
            shareLink += "&_locale=" + locale;
        }
        return shareLink;
    };

    $.formatDate = function (date) {
        let dd = date.getDate();
        let mm = date.getMonth() + 1; //January is 0!
        let yyyy = date.getFullYear();
        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        return yyyy + '-' + mm + '-' + dd;

    }

    $.replace = function (str, a, b) {
        let reg = new RegExp(a, "g");
        return str.replace(reg, b);
    }

    $.getJsData = function (url){
        if(!document.getElementById("ScriptBox")){
            let DivObj = document.createElement("div");
            DivObj.id = "ScriptBox";
            DivObj.style.display = "none";
            document.body.appendChild(DivObj);
        }
        let Snode = document.createElement("script");
        Snode.setAttribute("type", "text/javascript");
        Snode.setAttribute("language", "javascript");
        Snode.setAttribute("src", url);
        document.getElementById("ScriptBox").innerHTML = "";
        document.getElementById("ScriptBox").appendChild(Snode);
    }

})(jQuery);

const ptr = PullToRefresh.init({
    mainElement: 'body',
    distThreshold: 150,
    distMax: 200,
    instructionsPullToRefresh: __res.hereNothing,
    instructionsReleaseToRefresh: __res.reallyNothing,
    instructionsRefreshing: __res.seeNothing,
    onRefresh() {
        console.log("Thanks for finding me ~");
        $.sleep(3000);
    }
});