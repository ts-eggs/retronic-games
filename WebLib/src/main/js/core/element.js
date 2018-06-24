top.Sjl.core.element._elements = {};

top.Sjl.core.element.init = function() {
    console.info('init element core');

    // map public functions
    top.Sjl.createElement = top.Sjl.core.element.createElement;
    top.Sjl.removeElement = top.Sjl.core.element.removeElement;
    top.Sjl.getElement = top.Sjl.core.element.getElement;
    top.Sjl.showElement = top.Sjl.core.element.showElement;
    top.Sjl.hideElement = top.Sjl.core.element.hideElement;
};

top.Sjl.core.element._getNextElementId = function() {
    var scope = top.Sjl.core.element;
    var id = 1;

    for(var key in scope._elements) {
        if(scope._elements.hasOwnProperty(key)) {
            id = id < parseInt(key) ? parseInt(key) : id;
        }
    }

    return id+1;
};

top.Sjl.core.element._hasElementId = function(id) {
    var scope = top.Sjl.core.element;

    for(var key in scope._elements) {
        if(id == key) {
            return true;
        }
    }

    return false;
};

top.Sjl.core.element._addElement = function(element) {
    var scope = top.Sjl.core.element;

    if(scope._elements.hasOwnProperty(element.id)) {
        console.warn('element already exists in map: ' + element.id);
        return;
    }

    scope._elements[element.id] = element;
};

top.Sjl.core.element._removeElement = function(element) {
    var scope = top.Sjl.core.element;

    if(!scope._elements.hasOwnProperty(element.id)) {
        console.warn('element not found in map for remove: '+element.id);
        return;
    }

    delete scope._elements[element.id];
};

top.Sjl.core.element._optimizeConfig = function (config)  {
    var scope = top.Sjl.core.element;
    config = config || {};
    config.id = config.id || scope._getNextElementId();

    if(scope._hasElementId(config.id)) {
        console.error('element id is already used: '+config.id);
        return;
    }

    config.type = config.type || "element";
    config.name = config.name || "element";
    config.class = config.class || config.name;
    config.domType = config.domType || "div";
};

top.Sjl.core.element.createElement = function(config) {
    var scope = top.Sjl.core.element;
    scope._optimizeConfig(config);

    var element = config;
    element.dom = document.createElement(config.domType);
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
            childConfig.parent = element.id;
            scope.createElement(childConfig);
        }
    }

    if(config.parent) {
        var parentElementDom = scope._hasElementId(config.parent) ? scope.getElement(config.parent).dom : null;
        var parentDom = config.parent === 'body' ? top.document.body : parentElementDom;

        if(parentDom) {
            parentDom.appendChild(element.dom);
        }
    }

    return element;
};

top.Sjl.core.element.getElement = function(id) {
    var scope = top.Sjl.core.element;

    if(!scope._elements.hasOwnProperty(id)) {
        console.warn('element not found in map for get: '+id);
        return null;
    }

    return scope._elements[id];
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