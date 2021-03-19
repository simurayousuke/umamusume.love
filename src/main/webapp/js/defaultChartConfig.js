let __defaultChartConfig = {
    // Boolean - 是否执行动画效果
    animation: false,
    // Number - 动画执行的步数，数值越大，动画执行的时间越长
    animationSteps: 60,
    // String - 动画效果
    // 可以选用的动画效果包括:
    // [easeInOutQuart, linear, easeOutBounce, easeInBack, easeInOutQuad,
    //  easeOutQuart, easeOutQuad, easeInOutBounce, easeOutSine, easeInOutCubic,
    //  easeInExpo, easeInOutBack, easeInCirc, easeInOutElastic, easeOutBack,
    //  easeInQuad, easeInOutExpo, easeInQuart, easeOutQuint, easeInOutCirc,
    //  easeInSine, easeOutExpo, easeOutCirc, easeOutCubic, easeInQuint,
    //  easeInElastic, easeInOutSine, easeInOutQuint, easeInBounce,
    //  easeOutElastic, easeInCubic]
    animationEasing: "easeOutQuart",
    // Boolean - 是否显示坐标网格
    showScale: true,
    // Boolean - 是否使用自定义的策略绘制坐标网格来覆盖默认方案
    scaleOverride: false,
    // ** 以下选项在 scaleOverride 设置为 true 的情况下为必填项 **
    // Number - 自定义坐标网格时的格子数目
    scaleSteps: null,
    // Number - 自定义坐标网格每个格子的大小
    scaleStepWidth: null,
    // Number - 自定义坐标网格时起始刻度值
    scaleStartValue: null,
    // String - 坐标网格线条颜色
    scaleLineColor: "rgba(0,0,0,.1)",
    // Number - 坐标网格线条宽度
    scaleLineWidth: 1,
    // Boolean - 是否为坐标网格显示刻度标签文本
    scaleShowLabels: true,
    // Interpolated JS string - 坐标刻度格式化文本
    scaleLabel: "<%=value%>",
    // Boolean - 是否仅仅在坐标为整数值时显示刻度，分数刻度会被跳过
    scaleIntegersOnly: true,
    // Boolean - 坐标的刻度值是否必须从0开始，否则会以数据中的最小值开始
    scaleBeginAtZero: false,
    // String - 坐标刻度文本字体
    scaleFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
    // Number - 坐标刻度文本字体大小
    scaleFontSize: 12,
    // String - 坐标刻度文本样式
    scaleFontStyle: "normal",
    // String - 坐标刻度文本颜色
    scaleFontColor: "#666",
    // Boolean - 是否启用响应式设计，在窗口尺寸变化时进行重绘
    responsive: true,
    // Boolean - 当启用响应式设计时，是否在缩放时保持原始比例，如果设置为 false，则重新以新的容器大小进行绘制
    maintainAspectRatio: true,
    // Boolean - 是否在触摸或鼠标移动时显示工具提示文本
    showTooltips: true,
    // Boolean - 是否在绘制工具提示文本时使用自定义的函数
    customTooltips: false,
    // Array - 显示工具提示的触发事件
    tooltipEvents: ["mousemove", "touchstart", "touchmove", "mouseout"],
    // String - 工具提示背景颜色
    tooltipFillColor: "rgba(0,0,0,0.8)",
    // String - 工具提示字体
    tooltipFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
    // Number - 工具提示字体大小
    tooltipFontSize: 14,
    // String - 工具提示字体样式
    tooltipFontStyle: "normal",
    // String - 工具提示字体颜色
    tooltipFontColor: "#fff",
    // String - 工具提示标题字体
    tooltipTitleFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
    // Number - 工具提示标题字体大小
    tooltipTitleFontSize: 14,
    // String - 工具提示标题字体样式
    tooltipTitleFontStyle: "bold",
    // String - 工具提示标题字体颜色
    tooltipTitleFontColor: "#fff",
    // Number - 工具提示在垂直方向上的内边距大小
    tooltipYPadding: 6,
    // Number - 工具提示在水平方向上的内边距大小
    tooltipXPadding: 6,
    // Number - 工具提示三角箭头大小
    tooltipCaretSize: 8,
    // Number - 工具提示边框圆角大小
    tooltipCornerRadius: 6,
    // Number - 工具提示在 X 坐标上的偏移量
    tooltipXOffset: 10,
    // String - 工具提示模板字符串
    tooltipTemplate: "<%if (label){%><%=label%>: <%}%><%= value %>",
    // String - 多条目工具提示的模板字符串
    multiTooltipTemplate: "<%if (datasetLabel){%><%=datasetLabel%>: <%}%><%= value %>",
    // String - 多条目工具提示的背景色
    multiTooltipKeyBackground: '#fff',
    // Function - 当动画执行过程中的回调函数
    onAnimationProgress: function () {
    },
    // Function - 当动画执行完成时的回调函数
    onAnimationComplete: function () {
    }
};