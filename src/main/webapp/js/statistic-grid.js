function initDatagrid() {
    let mealStatisticOption = {
        dataSource: {
            cols: [
                {name: 'foodname', label: __res.foodname, width: 400},
                //{name: 'weight', label: resWeight, width: 120, valueType: 'double'},
                {name: 'calorie', label: __res.calorie + "(kcal)", width: 120, valueType: 'double'},
                {name: 'protein', label: __res.protein + "(g)", width: 120, valueType: 'double'},
                {name: 'fat', label: __res.fat + "(g)", width: 120, valueType: 'double'},
                {name: 'carbohydrate', label: __res.carbohydrate + "(g)", width: 120, valueType: 'double'},
                {name: 'fid', label: "fid"},
            ],
            array: breakfastData
        },
        valueOperator: {
            double: {
                getter: function (value, cell, dataGrid) {
                    return $.formatNum(value);
                }
            }
        },
        configs: {
            C6: {
                style: {
                    display: "none",
                },
            },
            C1: {
                style: {
                    textAlign: "right",
                },
            },
        },
        states: {
            fixedLeftUntil: 0,
            fixedTopUntil: 0,
        },
        showMessage: false,
        height: 200,
    };

    mealStatisticOption.dataSource.array = breakfastData;
    $('#datagrid-breakfast').datagrid(mealStatisticOption);
    mealStatisticOption.dataSource.array = lunchData;
    $('#datagrid-lunch').datagrid(mealStatisticOption);
    mealStatisticOption.dataSource.array = dinnerData;
    $('#datagrid-dinner').datagrid(mealStatisticOption);

    function onTouchStart(e) {
        startX = e.originalEvent.changedTouches[0].pageX;
        startY = e.originalEvent.changedTouches[0].pageY;
    }

    $("#datagrid-container-breakfast").on("touchstart", onTouchStart);

    $("#datagrid-container-breakfast").on("touchmove", function (e) {
        e.preventDefault();
        let _d = $("#datagrid-container-breakfast");
        let x = _d.scrollLeft() + (startX - e.originalEvent.changedTouches[0].pageX) * sensitivity;
        let y = _d.scrollTop() + (startY - e.originalEvent.changedTouches[0].pageY) * sensitivity;
        _d.scrollLeft(x);
        _d.scrollTop(y);
    });

    $("#datagrid-container-lunch").on("touchstart", onTouchStart);

    $("#datagrid-container-lunch").on("touchmove", function (e) {
        e.preventDefault();
        let _d = $("#datagrid-container-lunch");
        let x = _d.scrollLeft() + (startX - e.originalEvent.changedTouches[0].pageX) * sensitivity;
        let y = _d.scrollTop() + (startY - e.originalEvent.changedTouches[0].pageY) * sensitivity;
        _d.scrollLeft(x);
        _d.scrollTop(y);
    });

    $("#datagrid-container-dinner").on("touchstart", onTouchStart);

    $("#datagrid-container-dinner").on("touchmove", function (e) {
        e.preventDefault();
        let _d = $("#datagrid-container-dinner");
        let x = _d.scrollLeft() + (startX - e.originalEvent.changedTouches[0].pageX) * sensitivity;
        let y = _d.scrollTop() + (startY - e.originalEvent.changedTouches[0].pageY) * sensitivity;
        _d.scrollLeft(x);
        _d.scrollTop(y);
    });
}