(function (window, document) {
    'use strict';

    var list = document.querySelector('.list');
    var input = document.querySelector('input');

    window.send = function() {
        var value = input.value;

        input.value = '';

        window.Chat.send(value)
    };

    window.onmessage = function(e) {
        if (!e.data) {
            return;
        }

        var element = document.createElement('div');

        element.innerText = e.data;

        list.appendChild(element);
    };
})(window, document)
