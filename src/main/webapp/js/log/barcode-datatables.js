function search() {
    let ean = $("#input-ean").val();
    datagridFood.search(ean);
}

$("#input-ean").change(search);

$(document).ready(function () {
    $('#datagrid-food').DataTable({
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/api/v1/food/paginate/barcode/datatables',
            type: 'POST'
        },
        columns: [
            {data: 'ean'},
            {data: 'foodname'},
            {data: 'calorie'},
            {data: 'protein'},
            {data: 'fat'},
            {data: 'carbohydrate'},
            {data: 'uploader'},
        ]
    });
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
