let startx;
function init_datatable(config) {
    $('table.datatable').datatable(config);
    let table = document.getElementsByTagName("table");
    window.addEventListener("touchstart", function (e) {
        startx = e.touches[0].pageX;
    }, false);
    window.addEventListener("touchmove", function (e) {
        if (e.targetTouches.length > 1 || e.scale && e.scale !== 1) return;
        let dx = (e.changedTouches[0].pageX - startx) / 20;
        for (var i = 0; i < table.length; ++i) {
            let left = parseInt(table[i].style.left) + dx;
            let min = parseInt(document.body.offsetWidth) - parseInt(table[i].offsetWidth);
            if (left < min) {
                left = min;
            } else if (left > 0) {
                left = 0;
            }
            table[i].style.left = left + "px";
        }

    }, false);
}