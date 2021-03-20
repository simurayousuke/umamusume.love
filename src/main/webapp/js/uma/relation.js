let span_relation_result = document.getElementById("span-relation-result");
let input_horse1 = document.getElementById("input-horse1");
let input_horse2 = document.getElementById("input-horse2");
let value1 = "1001", value2 = "1002";

$("#form-relation-query").submit(function (e) {
    e.preventDefault();
    let button = $("#button-relation");
    button.prop("disabled", true);
    let data = $(this).serializeObject();
    if (data.id1 === data.id2) {
        $.error('请不要选择相同的马');
        button.prop("disabled", false);
        return;
    }
    $.post1("/api/v1/succession/relation", data, function (data) {
        if ("ok" === data.state) {
            span_relation_result.innerText = '相性值为：' + data.relation;
            button.prop("disabled", false);
        } else {
            $.error(__res.fail);
            button.prop("disabled", false);
        }
    }, button);
});

function getIndex(value) {
    for (let i = 0; i < input_horse1.options.length; ++i) {
        if (input_horse1.options[i].value === value) {
            return i;
        }
    }
    return -1;
}

function init() {
    let flag = 0;
    for (let i = 0; i < input_horse1.options.length; ++i) {
        if (input_horse1.options[i].value === "1001") {
            input_horse1.selectedIndex = i;
            flag++;
        } else if (input_horse1.options[i].value === "1002") {
            input_horse2.selectedIndex = i;
            flag++;
        }
        if (flag === 2) {
            break;
        }
    }
    $("#form-relation-query").submit();
}

function inputChange() {
    if (input_horse1.value !== input_horse2.value) {
        value1 = input_horse1.value;
        value2 = input_horse2.value;
        $("#form-relation-query").submit();
    } else {
        let a = getIndex(value1), b = getIndex(value2);
        if (a < 0 || b < 0) {
            init();
            return;
        }
        input_horse1.selectedIndex = a;
        input_horse2.selectedIndex = b;
        $.error('请不要选择相同的马');
    }
}

init();
init_datatable({sortable:false,fixedHeader:true});