Sjl.component.window._windows = {};

Sjl.component.window.init = function() {
    Sjl.core.element._scopeName = "component.window";
    Sjl.applyConfig(Sjl.component.window, Sjl.component.base);
};

Sjl.component.window._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "window";
    config.width = config.width || 400;
    config.height = config.height || 400;
};

Sjl.createWindow = function(config, scope) {
    scope = scope || Sjl.component.window;
    var element = Sjl.component.base.create(config, scope);
    var items = config.items ? Sjl.applyConfig([], config.items) : [];
    var buttons = config.buttons ? Sjl.applyConfig([], config.buttons) : [];
    var i;

    for( i = 0; i < items.length; i++ ) {
        var child = items[i];
        var itemContainer = element.findElementByName("window-content");
        child.parent = itemContainer == null ? null : itemContainer.id;
        Sjl.create(child);
    }

    for( i = 0; i < buttons.length; i++ ) {
        var button = buttons[i];
        button.style = button.style || {};
        button.style.float = 'right';
        var buttonContainer = element.findElementByName("window-buttons");
        button.parent = buttonContainer == null ? null : buttonContainer.id;
        Sjl.create(button);
    }

    scope._windows[element.id] = element;
    Sjl.centerWindow(element);
    return element;
};

Sjl.getWindow = function(id) {
    var scope = Sjl.component.window;

    if(!scope._windows.hasOwnProperty(id)) {
        console.error("no window found to get: "+id);
        return null;
    }

    return this._windows[id];
};

Sjl.removeWindow = function(id) {
    var scope = Sjl.component.window;

    if(!scope._windows.hasOwnProperty(id)) {
        console.error("no window found to remove: "+id);
    }

    Sjl.removeElement(id);
    delete scope._windows[id];
};

Sjl.centerWindow = function(view) {
    var scope = Sjl.component.window;
    view = typeof view === 'object' ? view : scope.getWindow(view);
    view.dom.style.left = ((top.document.body.offsetWidth - view.style.width) / 2) + "px";
    view.dom.style.top = ((top.document.body.offsetHeight - view.style.height) / 2) + "px";
};

Sjl.component.window.init();