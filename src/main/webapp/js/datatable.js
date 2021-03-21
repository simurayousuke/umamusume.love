let startx;
let table;

function get_last_table(self) {
    for (var i = 0; i < table.length; ++i) {
        if (self === table[i])
            return i - 1;
    }
    return -1;
}

function init_datatable(config) {
    $('table.datatable').datatable(config);
    table = document.getElementsByTagName("table");
    for (var i = 0; i < table.length; ++i) {
        table[i].addEventListener("touchstart", function (e) {
            startx = e.touches[0].pageX;
        }, false);
        table[i].addEventListener("touchmove", function (e) {
            if (e.targetTouches.length > 1 || e.scale && e.scale !== 1) return;
            let dx = (e.changedTouches[0].pageX - startx) ;
            let left = parseInt(this.style.left) + dx;
            let min = parseInt(document.body.offsetWidth) - parseInt(this.offsetWidth);
            if (left < min) {
                left = min;
            } else if (left > 0) {
                left = 0;
            }
            let last_index=get_last_table(this);
            if(last_index>=0)
                table[last_index].style.left = left + "px";
            this.style.left = left + "px";
            startx = e.changedTouches[0].pageX;
        }, false);
    }

    /*window.addEventListener("touchstart", function (e) {
        startx = e.touches[0].pageX;
    }, false);
    window.addEventListener("touchmove", function (e) {
        if (e.targetTouches.length > 1 || e.scale && e.scale !== 1) return;
        let dx = (e.changedTouches[0].pageX - startx)*2;
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
        startx=e.changedTouches[0].pageX;
    }, false);*/
}