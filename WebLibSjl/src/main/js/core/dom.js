Sjl.core.dom.init = function() {
    Sjl.core.dom._scopeName = "core.dom";
};

Sjl.getDomElement = function(id) {
    return document.getElementById(id) || null;
};

Sjl.removeDomElement = function(id) {
    var dom = Sjl.getDomElement(id);

    if(dom == null) {
        console.warn('element not found for remove: '+id);
        return;
    }

    if(dom.parentNode) {
        dom.parentNode.removeChild(dom);
    }
};

Sjl.showDomElement = function(id) {
    var dom = Sjl.getDomElement(id);

    if(dom == null) {
        console.warn('element not found to show: '+id);
        return;
    }

    dom.style.display = "block";
};

Sjl.hideDomElement = function(id) {
    var dom = Sjl.getDomElement(id);

    if(dom == null) {
        console.warn('element not found to hide: '+id);
        return;
    }

    dom.style.display = "none";
};

Sjl.core.dom.init();