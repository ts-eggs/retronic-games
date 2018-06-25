Sjl.component.list.init = function() {
    console.info('init container component');
};

Sjl.component.list._optimizeConfig = function (config)  {
    config = config || {};
    config.type = "list";
    config.parent = config.parent || 'body';
    config.width = config.width || 200;
    config.height = config.height || 30;
    config.hidden = config.hidden || true;
    config.templateName = config.templateName || 'default';
};

Sjl.component.list.create = function(config) {
    var scope = Sjl.component.list;
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

Sjl.component.list.init();