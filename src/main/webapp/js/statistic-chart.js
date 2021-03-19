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
    threeMealOptions.scaleLabel = "<%=label%>: <%= $.formatNum(value/daySum.calorie*100,0) %>%";
    threeMealOptions.tooltipTemplate = "<%if (label){%><%=label%>: <%}%><%= value %> kcal";
    threeMealOptions.scaleLabelPlacement = "outside";
    $("#chart-threeMeal").pieChart(threeMealChartData, threeMealOptions);
    $("#chart-title-threeMeal").text(__res.threeMealTitle + " (" + __res.total + " " + $.formatNum(daySum.calorie) + " kcal)");

    let threeEnergyChartData = [{
        value: $.formatNum(daySum.protein * 4),
        color: "#fab27b",
        label: __res.protein
    }, {
        value: $.formatNum(daySum.fat * 9),
        color: "#f58220",
        label: __res.fat
    }, {
        value: $.formatNum(daySum.carbohydrate * 4),
        color: "#faa755",
        label: __res.carbohydrate
    }];
    let threeEnergyOptions = __defaultChartConfig;
    threeEnergyOptions.scaleShowLabels = true;
    threeEnergyOptions.scaleLabel = "<%=label%>: <%= $.formatNum(value/_totalCalculated*100,0) %>%";
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

    let targetCalorieData = {
        labels: [__res.calorie],
        datasets: [
            {
                label: __res.targetValue,
                color: 'blue',
                data: [$.formatNum(_targetValues.target_calorie)]
            }, {
                label: __res.currentValue,
                color: 'green',
                data: [$.formatNum(daySum.calorie)]
            }
        ]
    };
    $('#chart-target-calorie').barChart(targetCalorieData, {responsive: true});
    $("#chart-title-target-calorie").text(__res.targetValue);
}