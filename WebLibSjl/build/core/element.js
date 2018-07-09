Sjl.core.element._elements = {};

Sjl.core.element.init = function() {
    Sjl.core.element._scopeName = "core.element";

    // map public functions
    Sjl.createElement = Sjl.core.element.createElement;
    Sjl.removeElement = Sjl.core.element.removeElement;
    Sjl.getElement = Sjl.core.element.getElement;
    Sjl.showElement = Sjl.core.element.showElement;
    Sjl.hideElement = Sjl.core.element.hideElement;
};

Sjl.core.element._getNextElementId = function() {
    var scope = Sjl.core.element;
    var id = 1;

    for(var key in scope._elements) {
        if(scope._elements.hasOwnProperty(key)) {
            id = id < parseInt(key) ? parseInt(key) : id;
        }
    }

    return id+1;
};

Sjl.core.element._hasElementId = function(id) {
    var scope = Sjl.core.element;

    for(var key in scope._elements) {
        if(id == key) {
            return true;
        }
    }

    return false;
};

Sjl.core.element._addElement = function(element) {
    var scope = Sjl.core.element;

    if(scope._elements.hasOwnProperty(element.id)) {
        console.warn('element already exists in map: ' + element.id);
        return;
    }

    scope._elements[element.id] = element;
};

Sjl.core.element._removeElement = function(element) {
    var scope = Sjl.core.element;

    if(!scope._elements.hasOwnProperty(element.id)) {
        console.warn('element not found in map for remove: '+element.id);
        return;
    }

    scope._removeElementsWithParentId(element.id);
    delete scope._elements[element.id];
};

Sjl.core.element._removeElementsWithParentId = function (parentId)  {
    var scope = Sjl.core.element;

    for( var key in scope._elements ) {
        if(scope._elements.hasOwnProperty(key)) {
            if(scope._elements[key].id != parentId && scope._elements[key].parent === parentId) {
                scope._removeElement(scope._elements[key]);
            }
        }
    }
};

Sjl.core.element._optimizeConfig = function (config)  {
    var scope = Sjl.core.element;
    config = config || {};
    config.id = config.id || scope._getNextElementId();

    if(scope._hasElementId(config.id)) {
        console.error('element id is already used: '+config.id);
        return;
    }

    config.type = config.type || "element";
    config.name = config.name || config.type;
    var autoClass = config.type != "element" ? config.type : config.name;
    config.class = config.class || autoClass;

    if(config.class.indexOf("tool-button") != -1) {
        config.class = "tool-button " + config.class;
    }

    config.domType = config.domType || "div";
};

Sjl.core.element._setStyling = function(config, element) {
    var scope = Sjl.core.element;

    for( var key in config.style ) {
        if(config.style.hasOwnProperty(key) && element.dom.style.hasOwnProperty(key)) {
            element.dom.style[key] = config.style[key].constructor === Number && scope._isMeasureStyle(key) ?  config.style[key] + "px" : config.style[key];
        }
    }
};

Sjl.core.element._isMeasureStyle = function(style) {
    return style == "width" || style == "height" || style == "top" || style == "left" || style.indexOf("margin") != -1 || style.indexOf("padding") != -1;
};

Sjl.core.element._addDomEvents = function(events, callbacks, element, scope, componentScope) {
    if(!scope) {
        console.warn('could not add dom events, scope undefined for dom: '+element.dom.id);
        return;
    }

    if(!componentScope) {
        console.warn('could not add dom events, componentScope undefined for component: '+element.type);
        return;
    }

    var key;

    if(events) {
        for( key in events ) {
            if(events.hasOwnProperty(key)) {
                var fnName = events[key];

                if(!componentScope._eventFunctions.hasOwnProperty(fnName)) {
                    console.warn('no function defined for component scope: '+componentScope.toString()+' fn: '+fnName);
                    continue;
                }

                scope._addDomEvent(element, key, componentScope._eventFunctions[fnName]);
            }
        }
    }

    if(callbacks) {
        for( key in callbacks ) {
            if(callbacks.hasOwnProperty(key)) {
                var currentScope = Sjl;
                var packages = callbacks[key].split(".");

                for( var i = 0; i < packages.length; i++ ) {
                    if(packages[i] === "Sjl") {
                        continue;
                    }

                    currentScope = currentScope[packages[i]];
                }

                if(currentScope.constructor === Function) {
                    scope._addDomEvent(element, key, currentScope);
                }
            }
        }
    }
};

Sjl.core.element._addDomEvent = function(element, event, fn) {
    if(event === 'click') {
        element.dom.onclick = function(e) {
            fn(element, e);
        }
    } else if(event === 'change') {
        element.dom.onkeyup = function(e) {
            fn(element, e);
        }
    }
};

Sjl.core.element.createElement = function(config, isComponent) {
    var componentScope = Sjl.component.hasOwnProperty(config.componentType) ? Sjl.component[config.componentType] : Sjl.component[config.type];
    var scope = Sjl.core.element;
    scope._optimizeConfig(config);

    var element = config;
    element.dom = document.createElement(config.domType);
    element.dom.id = config.name + "-" + config.id;
    element.dom.className = config.class;

    if(isComponent === true) {
        element.componentId = element.id;
        element.componentType = element.type;
        element.mainComponentId = element.mainComponentId || element.id;
    }

    if(config.style) {
       scope._setStyling(config, element);
    }
    
    if(config.text) {
        element.dom.innerText = config.text;
    }

    scope._addElement(element);

    if(config.parent) {
        var parentElement = scope._hasElementId(config.parent) ? scope.getElement(config.parent) : null;
        var parentElementDom = parentElement ? parentElement.dom : null;
        var parentDom = config.parent === 'body' ? top.document.body : parentElementDom;

        if(parentDom) {
            parentDom.appendChild(element.dom);
        }
    }

    if(componentScope) {
        scope._addDomEvents(config.events, config.callbacks, element, scope, componentScope);
    }

    // console.info("created element: "+element.type+" id: "+element.id);
    return element;
};

Sjl.core.element.getElement = function(id) {
    var scope = Sjl.core.element;

    if(!scope._elements.hasOwnProperty(id)) {
        console.warn('element not found in map for get: '+id);
        return null;
    }

    return scope._elements[id];
};

Sjl.core.element.removeElement = function(id) {
    var scope = Sjl.core.element;
    var element = scope.getElement(id);

    if(element == null) {
        console.warn('element not found for remove: '+id);
        return;
    }

    if(element.dom.parentNode) {
        element.dom.parentNode.removeChild(element.dom);
    }

    // console.info("removed element: "+element.type+" id: "+element.id);
    scope._removeElement(element);
};

Sjl.core.element.showElement = function(id) {
    var scope = Sjl.core.element;
    var element = scope.getElement(id);

    if(element == null) {
        console.warn('element not found to show: '+id);
        return;
    }

    element.dom.style.display = "block";
};

Sjl.core.element.hideElement = function(id) {
    var scope = Sjl.core.element;
    var element = scope.getElement(id);

    if(element == null) {
        console.warn('element not found to hide: '+id);
        return;
    }

    element.dom.style.display = "none";
};

Sjl.core.element.init();