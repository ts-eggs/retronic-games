Scl._components = {};

Scl.core.component.init = function() {
    Scl.core.component._scopeName = "core.component";
};

Scl.createComponent = function(type, parent) {
    var component = document.createElement(type);
    parent = parent || window.document.body;

    if(parent) {
        parent.appendChild(component);
    }
};

Scl.addComponent = function(component) {
    var id = Scl._getNextComponentId();
    Scl._components[id] = component;
    return id;
};

Scl.removeComponent = function(component, componentId) {
    if(component.parentNode) {
        component.parentNode.removeChild(component);
    }

    componentId = componentId || component._componentId;

    if(!Scl._components.hasOwnProperty(componentId)) {
        console.warn('component not found for remove with id: '+componentId);
        return;
    }

    delete Scl._components[componentId];
};

Scl._getNextComponentId = function() {
    var id = 1;

    while(Scl._components.hasOwnProperty(id.toString())) {
        id++;
    }

    return id+1;
};

Scl.core.component.init();