let startX, startY;
let breakfastData = [], lunchData = [], dinnerData = [];
let breakfastSum = [], lunchSum = [], dinnerSum = [];
let daySum = [];
let __sync = 0;
let _totalCalculated;
let _targetValues = {};

function getDate() {
    let reg = new RegExp("/[0-9]{4}-[0-9]{2}-[0-9]{2}$");
    let ret = reg.exec(location.href);
    if (ret) {
        return ret[0].substring(1);
    } else {
        return $.formatDate(new Date());
    }
}

let dateStatistic = getDate();
let inputDate = $("#input-date-statistic");

inputDate.val(dateStatistic);

$("#input-date-statistic").datetimepicker(
    {
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: "yyyy-mm-dd",
        initialDate: dateStatistic,
        language: __locale.substring(0, 2)
    }).on('changeDate', function (ev) {
    $.jump("/statistic/" + inputDate.val());
});


function initDatagrid() {
    let mealStatisticOption = {
        dataSource: {
            cols: [
                {name: 'foodname', label: __res.foodname, width: 250},
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
                    return parseFloat(value.toFixed(__frictionDigits));
                }
            }
        },
        configs: {
            C6: {
                style: {
                    display: "none",
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

function getSumRow(data) {
    return data[data.length - 1];
}

function getDaySum(bs, ls, ds) {
    let sum = {};
    for (let k in bs) {
        if ("number" === typeof bs[k]) {
            sum[k] = bs[k];
        }
    }
    for (let k in ls) {
        if ("number" === typeof ls[k]) {
            sum[k] += ls[k];
        }
    }
    for (let k in ds) {
        if ("number" === typeof ds[k]) {
            sum[k] += ds[k];
        }
    }
    sum.foodname = __res.total;
    return sum;
}


function initChart() {
    let threeMealChartData = [{
        value: $.formatNum(breakfastSum.calorie),
        color: "#90d7ec",
        label: __res.breakfast
    }, {
        value: $.formatNum(lunchSum.calorie),
        color: "#009ad6",
        label: __res.lunch
    }, {
        value: $.formatNum(dinnerSum.calorie),
        color: "#145b7d",
        label: __res.dinner
    }];
    let threeMealOptions = __defaultChartConfig;
    threeMealOptions.scaleShowLabels = true;
    threeMealOptions.scaleLabel = "<%=label%>: <%= $.formatNum(value/daySum.calorie*100) %>%";
    threeMealOptions.tooltipTemplate = "<%if (label){%><%=label%>: <%}%><%= value %> kcal";
    threeMealOptions.scaleLabelPlacement = "outside";
    $("#chart-threeMeal").pieChart(threeMealChartData, threeMealOptions);
    $("#chart-title-threeMeal").text(__res.threeMealTitle + " (" + __res.total + " " + $.formatNum(daySum.calorie) + " kcal)");

    let threeEnergyChartData = [{
        value: daySum.protein * 4,
        color: "#fab27b",
        label: __res.protein
    }, {
        value: daySum.fat * 9,
        color: "#f58220",
        label: __res.fat
    }, {
        value: daySum.carbohydrate * 4,
        color: "#faa755",
        label: __res.carbohydrate
    }];
    let threeEnergyOptions = __defaultChartConfig;
    threeEnergyOptions.scaleShowLabels = true;
    threeEnergyOptions.scaleLabel = "<%=label%>: <%= $.formatNum(value/_totalCalculated*100) %>%";
    threeEnergyOptions.tooltipTemplate = "<%if (label){%><%=label%>: <%}%><%= value %> kcal";
    threeEnergyOptions.scaleLabelPlacement = "outside";
    $("#chart-threeEnergy").pieChart(threeEnergyChartData, threeEnergyOptions);
    $("#chart-title-threeEnergy").text(__res.threeEnergyTitle + " (" + __res.total + " " + $.formatNum(daySum.calorie) + " kcal)");

    let targetData = {
        labels: [__res.protein, __res.fat, __res.carbohydrate],
        datasets: [
            {
                label: __res.targetValue,
                color: 'blue',
                data: [$.formatNum(_targetValues.target_protein),
                    $.formatNum(_targetValues.target_fat),
                    $.formatNum(_targetValues.target_carbohydrate)]
            }, {
                label: __res.currentValue,
                color: 'green',
                data: [$.formatNum(daySum.protein),
                    $.formatNum(daySum.fat),
                    $.formatNum(daySum.carbohydrate)]
            }
        ]
    };


    $('#chart-target').barChart(targetData, {responsive: true});
    $("#chart-title-target").text(__res.targetValue);
}

function _callback(data) {
    if (data)
        $.addSumRow(data);
    if (++__sync > 3) {
        initDatagrid();
        breakfastSum = getSumRow(breakfastData);
        lunchSum = getSumRow(lunchData);
        dinnerSum = getSumRow(dinnerData);
        daySum = getDaySum(breakfastSum, lunchSum, dinnerSum);
        _totalCalculated = daySum.protein * 4 + daySum.fat * 9 + daySum.carbohydrate * 4;
        initChart();
        $("#main-statistic").removeClass("loading");
    }
}

function initBreakfastData() {
    $.post4("/api/v1/statistic/dateAndType", {date: dateStatistic, type: 1}, (data) => {
        breakfastData = data;
        _callback(breakfastData);
    }, () => {
        $.error(__res.networkError, () => {
            initBreakfastData();
        });
    });
}

function initLunchData() {
    $.post4("/api/v1/statistic/dateAndType", {date: dateStatistic, type: 2}, (data) => {
        lunchData = data;
        _callback(lunchData);
    }, () => {
        $.error(__res.networkError, () => {
            initLunchData();
        });
    });
}

function initDinnerData() {
    $.post4("/api/v1/statistic/dateAndType", {date: dateStatistic, type: 3}, (data) => {
        dinnerData = data;
        _callback(dinnerData);
    }, () => {
        $.error(__res.networkError, () => {
            initDinnerData();
        });
    });
}

function initTargetData() {
    $.post4("/api/v1/target/fetch", {}, (data) => {
        _targetValues = data;
        _callback(null);
    }, () => {
        $.error(__res.networkError, () => {
            initTargetData();
        });
    });
}

function initData() {
    initBreakfastData();
    initLunchData();
    initDinnerData();
    initTargetData();
}

$(document).ready(function () {
    initData();
});