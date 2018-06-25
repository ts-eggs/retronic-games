Sjl.component.window._windows = {};

Sjl.component.window.init = function() {
    console.info('init window component');

    // map public functions
    Sjl.createWindow = Sjl.component.window.createWindow;
    Sjl.getWindow = Sjl.component.window.getWindow;
    Sjl.removeWindow = Sjl.component.window.removeWindow;
    Sjl.centerWindow = Sjl.component.window.centerWindow;
};

Sjl.component.window._getWindowConfig = function(config) {
    var scope = Sjl.component.window;

    if(scope._templates.hasOwnProperty(config.templateName)) {
        return scope._templates[config.templateName];
    }

    return scope._templates['default'];
};

Sjl.component.window._optimizeConfig = function (config)  {
    config = config || {};
    config.type = "window";
    config.parent = config.parent || 'body';
    config.width = config.width || 400;
    config.height = config.height || 400;
    config.hidden = config.hidden || false;
    config.templateName = config.templateName || 'default';
};

Sjl.component.window.createWindow = function(config) {
    var scope = Sjl.component.window;
    scope._optimizeConfig(config);

    var elementConfig = Sjl.generateElementConfig(scope._getWindowConfig(config), config);
    var element = Sjl.createElement(elementConfig, true);
    scope._windows[element.id] = element;
    scope.centerWindow(element);

    if(config.hidden != true) {
        Sjl.showElement(element.id);
    }

    console.info('created window: '+element.id);
};

Sjl.component.window.getWindow = function(id) {
    var scope = Sjl.component.window;

    if(!scope._windows.hasOwnProperty(id)) {
        console.error("no window found to get: "+id);
        return null;
    }

    return this._windows[id];
};

Sjl.component.window.removeWindow = function(id) {
    var scope = Sjl.component.window;

    if(!scope._windows.hasOwnProperty(id)) {
        console.error("no window found to remove: "+id);
    }

    Sjl.removeElement(id);
    delete scope._windows[id];
    console.info('removed window: '+id);
};

Sjl.component.window.centerWindow = function(view) {
    var scope = Sjl.component.window;
    view = typeof view === 'object' ? view : scope.getWindow(view);
    view.dom.style.left = ((top.document.body.offsetWidth - view.style.width) / 2) + "px";
    view.dom.style.top = ((top.document.body.offsetHeight - view.style.height) / 2) + "px";
};

Sjl.component.window.init();