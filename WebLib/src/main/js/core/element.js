top.Sjl.core.element.elements = {};

top.Sjl.core.element.init = function() {
    console.info('init element component');

    // map public functions
    top.Sjl.createElement = top.Sjl.core.element.createElement;
    top.Sjl.removeElement = top.Sjl.core.element.removeElement;
    top.Sjl.getElement = top.Sjl.core.element.getElement;
    top.Sjl.showElement = top.Sjl.core.element.showElement;
    top.Sjl.hideElement = top.Sjl.core.element.hideElement;
};

top.Sjl.core.element._getElementId = function() {
    var scope = top.Sjl.core.element;
    var id = 1;

    for(var key in scope.elements) {
        if(scope.elements.hasOwnProperty(key)) {
            id = id < parseInt(key) ? parseInt(key) : id;
        }
    }

    return id+1;
};

top.Sjl.core.element._hasElementId = function(id) {
    var scope = top.Sjl.core.element;

    for(var key in scope.elements) {
        if(id == key) {
            return true;
        }
    }

    return false;
};

top.Sjl.core.element._addElement = function(element) {
    var scope = top.Sjl.core.element;

    if(scope.elements.hasOwnProperty(element.id)) {
        console.warn('element already exists in map: ' + element.id);
        return;
    }

    scope.elements[element.id] = element;
};

top.Sjl.core.element._removeElement = function(element) {
    var scope = top.Sjl.core.element;

    if(!scope.elements.hasOwnProperty(element.id)) {
        console.warn('element not found in map for remove: '+element.id);
        return;
    }

    delete scope.elements[element.id];
};

top.Sjl.core.element.createElement = function(config) {
    var scope = top.Sjl.core.element;
    config = config || {};
    config.id = config.id || scope._getElementId();

    if(scope._hasElementId(config.id)) {
        console.error('element id is already used: '+config.id);
        return;
    }

    config.type = config.type || "div";
    config.name = config.name || "element";
    config.class = config.class || config.name;

    var element = config;
    element.dom = document.createElement(config.type);
    element.dom.id = config.name + "-" + config.id;
    element.dom.className  = config.name;

    if(config.style) {
        if(config.style.width) {
            element.dom.style.width = config.style.width + "px";
        }
        if(config.style.height) {
            element.dom.style.height = config.style.height + "px";
        }
    }
    
    if(config.text) {
        element.dom.innerText = config.text;
    }

    scope._addElement(element);

    if(config.items && config.items.length > 0) {
        for(var i = 0; i < config.items.length; i++) {
            var childConfig = config.items[i];
            childConfig.parent = element;
            scope.createElement(childConfig);
        }
    }

    if(config.parent) {
        var parent = config.parent.hasOwnProperty("dom") ? config.parent.dom : config.parent;
        parent.appendChild(element.dom);
    }

    return element;
};

top.Sjl.core.element.getElement = function(id) {
    var scope = top.Sjl.core.element;

    if(!scope.elements.hasOwnProperty(id)) {
        console.warn('element not found in map for get: '+id);
        return null;
    }

    return scope.elements[id];
};

top.Sjl.core.element.removeElement = function(id) {
    var scope = top.Sjl.core.element;
    var element = scope.getElement(id);

    if(element == null) {
        console.warn('element not found for remove: '+id);
        return;
    }

    if(element.dom.parentNode) {
        element.dom.parentNode.removeChild(element);
    }

    scope._removeElement(element);
};

top.Sjl.core.element.showElement = function(id) {
    var scope = top.Sjl.core.element;
    var element = scope.getElement(id);

    if(element == null) {
        console.warn('element not found to show: '+id);
        return;
    }

    element.dom.style.display = "block";
};

top.Sjl.core.element.hideElement = function(id) {
    var scope = top.Sjl.core.element;
    var element = scope.getElement(id);

    if(element == null) {
        console.warn('element not found to hide: '+id);
        return;
    }

    element.dom.style.display = "none";
};

top.Sjl.core.element.init();