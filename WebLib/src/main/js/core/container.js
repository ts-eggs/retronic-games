Sjl.core.container.init = function() {
    Sjl.applyConfig(Sjl.core.container, Sjl.core.element);

    // map public functions
    Sjl.findElementById = Sjl.core.container.findElementById;
    Sjl.findElementByName = Sjl.core.container.findElementByName;
    Sjl.createContainer = Sjl.core.container.createElement;
    Sjl.create = Sjl.core.container.create;
};

Sjl.core.container._optimizeConfig = function (config)  {
    Sjl.core.element._optimizeConfig(config);
    config.layout = config.layout || "vertical";
};

Sjl.core.container._setChildren = function(config, parent) {
    for(var i = 0; i < config.items.length; i++) {
        var childConfig = config.items[i];
        childConfig.parent = parent.id;
        childConfig.componentId = parent.componentId;
        childConfig.componentType = parent.componentType;
        childConfig.mainComponentId = parent.mainComponentId;

        if(parent.layout === "horizontal") {
            childConfig.style = childConfig.style || {};
            childConfig.style.float = childConfig.style.float || 'left';
        }

        config.items[i] = Sjl.create(childConfig);
    }
};

Sjl.core.container.create = function(config, isComponent) {
    var componentScope = Sjl.component[config.type];

    if(componentScope) {
        return componentScope.create(config);
    }

    if(config.type === "container") {
        return Sjl.createContainer(config, isComponent);
    } else {
        return Sjl.createElement(config, isComponent);
    }
};

Sjl.core.container.createElement = function(config, isComponent) {
    var scope = Sjl.core.container;
    var element = Sjl.core.element.createElement(config, isComponent);

    if(config.items && config.items.length > 0) {
        scope._setChildren(config, element);
    }

    element.isContainer = true;
    element.findElementById = scope.findElementById;
    element.findElementByName = scope.findElementByName;
    return element;
};

Sjl.core.container.findElementById = function(id, container) {
    container = container || this;

    if(!container.hasOwnProperty('items')) {
        console.warn("cannot find element, because container has not property items: "+container.id);
        return null;
    }

    for( var i = 0; i < container.items.length; i++ ) {
        var item = container.items[i];

        if(item.id == id) {
            return item;
        }

        if(item.isContainer) {
            var foundElement = item.findElementById(id);

            if(foundElement != null) {
                return foundElement;
            }
        }
    }

    return null;
};

Sjl.core.container.findElementByName = function(name, container) {
    container = container || this;

    if(!container.hasOwnProperty('items')) {
        console.warn("cannot find element, because container has not property items: "+container.id);
        return null;
    }

    for( var i = 0; i < container.items.length; i++ ) {
        var item = container.items[i];

        if(item.name == name) {
            return item;
        }

        if(item.isContainer) {
            var foundElement = item.findElementByName(name);

            if(foundElement != null) {
                return foundElement;
            }
        }
    }

    return null;
};

Sjl.core.container.init();