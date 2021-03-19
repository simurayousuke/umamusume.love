function search() {
    let ean = $("#input-ean").val();
    datagridFood.search(ean);
}

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
                url: '/api/v1/food/paginate/barcode/zui',
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
        $.jump("/log/post?fid=" + fid);
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
    // e.preventDefault();
    startX = e.originalEvent.changedTouches[0].pageX;
});
$("#datagrid-food-main").on("touchmove", function (e) {
    e.preventDefault();
    let x = _d.scrollLeft() + (startX - e.originalEvent.changedTouches[0].pageX) * sensitivity;
    _d.scrollLeft(x);
});

var Quagga = window.Quagga;
var App = {
    _scanner: null,
    init: function () {
        this.attachListeners();
    },
    activateScanner: function () {
        var scanner = this.configureScanner('.overlay__content'),
            onDetected = function (result) {
                document.querySelector('#input-ean').value = result.codeResult.code;
                stop();
                search();
            }.bind(this),
            stop = function () {
                scanner.stop();  // should also clear all event-listeners?
                scanner.removeEventListener('detected', onDetected);
                this.hideOverlay();
                this._overlay.remove();
                this._overlay = null;
                this.attachListeners();
            }.bind(this);

        this.showOverlay(stop);
        scanner.addEventListener('detected', onDetected).start();
    },
    attachListeners: function () {
        var self = this,
            button = document.querySelector("#button-scan");

        button.addEventListener("click", function onClick(e) {
            e.preventDefault();
            button.removeEventListener("click", onClick);
            self.activateScanner();
        });
    },
    showOverlay: function (cancelCb) {
        if (!this._overlay) {
            var content = document.createElement('div'),
                closeButton = document.createElement('div');

            closeButton.appendChild(document.createTextNode('X'));
            content.className = 'overlay__content';
            closeButton.className = 'overlay__close';
            this._overlay = document.createElement('div');
            this._overlay.className = 'overlay';
            this._overlay.appendChild(content);
            content.appendChild(closeButton);
            closeButton.addEventListener('click', function closeClick() {
                closeButton.removeEventListener('click', closeClick);
                cancelCb();
            });
            document.body.appendChild(this._overlay);
        } else {
            var closeButton = document.querySelector('.overlay__close');
            closeButton.addEventListener('click', function closeClick() {
                closeButton.removeEventListener('click', closeClick);
                cancelCb();
            });
        }
        this._overlay.style.display = "block";
    },
    hideOverlay: function () {
        if (this._overlay) {
            this._overlay.style.display = "none";
        }
    },
    configureScanner: function (selector) {
        if (!this._scanner) {
            this._scanner = Quagga
                .decoder({readers: ['ean_reader']})
                .locator({patchSize: 'medium'})
                .fromSource({
                    target: selector,
                    constraints: {
                        width: 800,
                        height: 600,
                        facingMode: "environment"
                    }
                });
        }
        return this._scanner;
    }
};
App.init();
