top.Sjl.component.window._windows = {};

top.Sjl.component.window.init = function() {
    console.info('init window component');

    // map public functions
    top.Sjl.createWindow = top.Sjl.component.window.createWindow;
    top.Sjl.getWindow = top.Sjl.component.window.getWindow;
    top.Sjl.removeWindow = top.Sjl.component.window.removeWindow;
    top.Sjl.centerWindow = top.Sjl.component.window.centerWindow;
};

top.Sjl.component.window._getWindowConfig = function(config) {
    var scope = top.Sjl.component.window;

    if(scope._templates.hasOwnProperty(config.templateName)) {
        return scope._templates[config.templateName];
    }

    return scope._templates['default'];
};

top.Sjl.component.window._optimizeConfig = function (config)  {
    config = config || {};
    config.type = "window";
    config.parent = config.parent || 'body';
    config.width = config.width || 400;
    config.height = config.height || 400;
    config.hidden = config.hidden || false;
    config.templateName = config.templateName || 'default';
};

top.Sjl.component.window.createWindow = function(config) {
    var scope = top.Sjl.component.window;
    scope._optimizeConfig(config);

    var elementConfig = top.Sjl.generateElementConfig(scope._getWindowConfig(config), config);
    var element = top.Sjl.createElement(elementConfig, true);
    scope._windows[element.id] = element;
    scope.centerWindow(element);

    if(config.hidden != true) {
        top.Sjl.showElement(element.id);
    }

    console.info('created window: '+element.id);
};

top.Sjl.component.window.getWindow = function(id) {
    var scope = top.Sjl.component.window;

    if(!scope._windows.hasOwnProperty(id)) {
        console.error("no window found to get: "+id);
        return null;
    }

    return this._windows[id];
};

top.Sjl.component.window.removeWindow = function(id) {
    var scope = top.Sjl.component.window;

    if(!scope._windows.hasOwnProperty(id)) {
        console.error("no window found to remove: "+id);
    }

    top.Sjl.removeElement(id);
    delete scope._windows[id];
    console.info('removed window: '+id);
};

top.Sjl.component.window.centerWindow = function(view) {
    var scope = top.Sjl.component.window;
    view = typeof view === 'object' ? view : scope.getWindow(view);
    view.dom.style.left = ((top.document.body.offsetWidth - view.style.width) / 2) + "px";
    view.dom.style.top = ((top.document.body.offsetHeight - view.style.height) / 2) + "px";
};

top.Sjl.component.window.init();