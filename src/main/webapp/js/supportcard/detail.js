let div_status=document.getElementById("div-card-status");
$("#form-calc").submit(function (e) {
    e.preventDefault();
    let button = $("#button-calc");
    button.prop("disabled", true);
    let data = $(this).serializeObject();
    data.id=id;
    $.post1("/api/v1/supportcard/calculate", data, function (data) {
        if ("ok" === data.state) {
            let html="<table  class=\"table datatable\" style='margin: 20px 0;'><thead><th>效果</th><th>数值</th></thead><tbody>";
            for (var key in data.status) {
                html+="<tr><td>";
                html+=key;
                html+="</td><td>";
                html+=data.status[key];
                html+="</td></tr>";
            }
            html+="</tbody></table>"
            div_status.innerHTML=html;
            console.log(data.status);
            button.prop("disabled", false);
        }else{
            $.error(__res.fail);
            button.prop("disabled", false);
        }
    }, button);
});
$("#form-calc").submit();