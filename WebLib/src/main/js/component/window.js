top.Sjl.component.window._windows = {};
top.Sjl.component.window._windowConfigFns = {};

top.Sjl.component.window.init = function() {
    console.info('init window component');
    var scope = top.Sjl.component.window;

    // map window config templates
    scope._windowConfigFns['default'] = scope._defaultWindow;

    // map public functions
    top.Sjl.createWindow = top.Sjl.component.window.createWindow;
    top.Sjl.removeWindow = top.Sjl.component.window.removeWindow;
    top.Sjl.centerWindow = top.Sjl.component.window.centerWindow;
};

top.Sjl.component.window._getWindowConfig = function(config) {
    var scope = top.Sjl.component.window;

    if(scope._windowConfigFns.hasOwnProperty(config.type)) {
        return scope._windowConfigFns[type](config);
    }

    return scope._windowConfigFns['default'](config);
};

top.Sjl.component.window._defaultWindow = function(config) {
    config.width = config.width || 400;
    config.height = config.height || 400;
    config.closeImg = config.closeImg || 'images/base/close.png';

    return {
        name: 'window',
        parent: config.parent,
        style: {
            width: config.width,
            height: config.height
        },
        items: [{
            name: 'window-header',
            items: [{
                name: 'window-header-title',
                text: config.title
            }, {
                name: 'window-header-close'
            }]
        }, {
            name: 'window-content'
        }]
    }
};

top.Sjl.component.window.createWindow = function(config) {
    var scope = top.Sjl.component.window;
    config = config || {};
    config.parent = config.parent || top.document.body;
    var view = top.Sjl.createElement(scope._getWindowConfig(config));
    scope._windows[view.id] = view;
    scope.centerWindow(view);
    top.Sjl.showElement(view.id);
    console.info('created window: '+view.id);
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