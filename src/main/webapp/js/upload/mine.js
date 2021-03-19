$('#datagrid-food').datagrid({
    dataSource: {
        cols: [
            {name: 'ean', label: __res.ean, width: 120},
            {name: 'foodname', label: __res.foodname, width: 250},
            {name: 'calorie', label: __res.calorie, width: 120, valueType: 'double'},
            {name: 'protein', label: __res.protein, width: 120, valueType: 'double'},
            {name: 'fat', label: __res.fat, width: 120, valueType: 'double'},
            {name: 'carbohydrate', label: __res.carbohydrate, width: 120, valueType: 'double'},
            {name: 'uploader', label: __res.uploader, width: 120},
            {name: 'fid', label: "fid"},
        ],
        remote: (params) => {
            return {
                url: '/api/v1/food/paginate/uid/zui',
                type: 'POST',
                dataType: 'json'
            };
        },
    },
    valueOperator: {
        double: {
            getter: function (value, cell, dataGrid) {
                return parseFloat(value.toFixed(__frictionDigits));
            }
        }
    },
    onClickCell: function (event, cell, $cell) {
        let fid = datagridFood.getCell(cell.rowIndex, 8).value;
        // $.jump("/log/post?fid=" + fid);
    },
    configs: {
        C8: {
            style: {
                display: "none",
            },
        },
    },
    states: {
        fixedLeftUntil: 0,
        fixedTopUntil: 0,
        pager: {page: 1, recPerPage: 10},
    },
    height: 'page',
    showMessage: false
});
var datagridFood = $('#datagrid-food').data('zui.datagrid');
var _d = $(".datagrid-container");
var startX;

$("#datagrid-food-main").on("touchstart", function (e) {
    startX = e.originalEvent.changedTouches[0].pageX;
});
$("#datagrid-food-main").on("touchmove", function (e) {
    e.preventDefault();
    let x = _d.scrollLeft() + (startX - e.originalEvent.changedTouches[0].pageX) * sensitivity;
    _d.scrollLeft(x);
});