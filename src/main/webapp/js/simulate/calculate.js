$("#form-simulate-calculate").submit(function (e) {
    e.preventDefault();
    let button = $("#button-submit");
    button.prop("disabled", true);
    let data = $(this).serializeObject();
    $.post1("/api/v1/simulate/calculate", data, function (data) {
        if ("ok" === data.state) {
            console.log(data);
            for (let key in data.data) {
                let temp = document.getElementById(key);
                if (temp) {
                    temp.innerText = data.data[key];
                }
            }
            button.prop("disabled", false);
        } else {
            if(data.msg!==403) {
                $.error(data.msg);
            }else{
                $.error("计算功能需要登陆后才可使用！");
            }
            button.prop("disabled", false);
        }
    }, button);
})
;