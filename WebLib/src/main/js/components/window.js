top.Sjl.window._windows = {};
top.Sjl.window._windowConfigFns = {};

top.Sjl.window.init = function() {
    this._windowConfigFns['default'] = this._defaultWindow;
    console.info('init window component');
};

top.Sjl.window._getWindowConfig = function(config) {
    config.scope = this;

    if(this._windowConfigFns.hasOwnProperty(config.type)) {
        return this._windowConfigFns[type](config);
    }

    return this._windowConfigFns['default'](config);
};

top.Sjl.window._defaultWindow = function(config) {
    return {
        name: 'window',
        parent: config.parent,
        items: [{
            name: 'window-header',
            items: [{
                name: 'window-header-title',
                text: config.title
            }]
        }, {
            name: 'window-content'
        }]
    }
};

top.Sjl.window.createWindow = function(config) {
    config = config || {};
    config.parent = config.parent || top.document.body;
    var window = top.Sjl.element.createElement(this._getWindowConfig(config));
    this._windows[window.id] = window;
    console.info('open window: '+window.id);
};

top.Sjl.window.removeWindow = function(config) {

};

top.Sjl.window.showWindow = function(config) {

};

top.Sjl.window.closeWindow = function(config) {

};

top.Sjl.window.init();