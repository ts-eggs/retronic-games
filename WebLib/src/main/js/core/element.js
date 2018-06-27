Sjl.core.element._elements = {};

Sjl.core.element.init = function() {
    console.info('init element core');

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

//TODO: geht ned gscheid
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
    config.class = config.class || config.name;
    config.domType = config.domType || "div";
};

Sjl.core.element._setStyling = function(config, element) {
    if(config.style.width) {
        element.dom.style.width = config.style.width + "px";
    }
    if(config.style.height) {
        element.dom.style.height = config.style.height + "px";
    }
    if(config.style.float) {
        element.dom.style.float = config.style.float;
    }
};

Sjl.core.element._addDomEvents = function(config, element, scope, componentScope) {
    if(!scope) {
        console.warn('could not add dom events, scope undefined for dom: '+element.dom.id);
        return;
    }

    if(!componentScope) {
        console.warn('could not add dom events, componentScope undefined for component: '+element.type);
        return;
    }

    for( var key in config.events ) {
        if(config.events.hasOwnProperty(key)) {
            scope._addDomEvent(element, key, config.events[key], componentScope);
        }
    }
};

Sjl.core.element._addDomEvent = function(element, event, fnName, componentScope) {
    if(!componentScope._eventFunctions.hasOwnProperty(fnName)) {
        console.warn('no function defined for component scope: '+componentScope.toString()+' fn: '+fnName);
        return;
    }

    if(event === 'click') {
        element.dom.onclick = function(e) {
            componentScope._eventFunctions[fnName](element, e);
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
        /*
        if(parentElement) {
            var existingElement = Sjl.findElementById(config.id, parentElement);

            if(!existingElement) {
                parentElement.items = parentElement.items || [];
                parentElement.items.push(element);
            }
        }
        */
        if(parentDom) {
            parentDom.appendChild(element.dom);
        }
    }

    if(config.events && componentScope) {
        scope._addDomEvents(config, element, scope, componentScope);
    }

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