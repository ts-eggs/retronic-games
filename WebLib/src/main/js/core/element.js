top.Sjl.element.elements = {};

top.Sjl.element.init = function() {
    console.info('init element component');
};

top.Sjl.element._getElementId = function() {
    var id = 1;

    for(var key in this.elements) {
        id = id < parseInt(key) ? parseInt(key) : id;
    }

    return id+1;
};

top.Sjl.element._hasElementId = function(id) {
    for(var key in this.elements) {
        if(id == key) {
            return true;
        }
    }

    return false;
};

top.Sjl.element._addElement = function(element) {
    if(this.elements.hasOwnProperty(element.id)) {
        console.warn('element already exists in map: ' + element.id);
        return;
    }

    this.elements[element.id] = element;
};

top.Sjl.element._removeElement = function(element) {
    if(!this.elements.hasOwnProperty(element.id)) {
        console.warn('element not found in map for remove: '+element.id);
        return;
    }

    delete this.elements[element.id];
};

top.Sjl.element.createElement = function(config) {
    config = config || {};
    config.id = config.id || this._getElementId();

    if(this._hasElementId(config.id)) {
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
    
    if(config.text) {
        element.dom.htmlText = config.text;
    }

    this._addElement(element);

    if(config.items && config.items.length > 0) {
        for(var i = 0; i < config.items.length; i++) {
            var childConfig = config.items[i];
            childConfig.parent = element;
            this.createElement(childConfig);
        }
    }

    if(config.parent) {
        var parent = config.parent.hasOwnProperty("dom") ? config.parent.dom : config.parent;
        parent.appendChild(element.dom);
    }

    return element;
};

top.Sjl.element.getElement = function(id) {
    if(!this.elements.hasOwnProperty(id)) {
        console.warn('element not found in map for get: '+id);
        return null;
    }

    return this.elements[id];
};

top.Sjl.element.removeElement = function(id) {
    var element = this.getElement(id);

    if(element == null) {
        console.warn('element not found for remove: '+id);
        return;
    }

    if(element.dom.parentNode) {
        element.dom.parentNode.removeChild(element);
    }

    this._removeElement(element);
};

top.Sjl.element.showElementById = function(id) {
    var element = this.getElement(id);

    if(element == null) {
        console.warn('element not found to show: '+id);
        return;
    }

    element.style.display = "block";
};

top.Sjl.element.hideElementById = function(id) {
    var element = this.getElement(id);

    if(element == null) {
        console.warn('element not found to hide: '+id);
        return;
    }

    element.style.display = "none";
};

top.Sjl.element.init();