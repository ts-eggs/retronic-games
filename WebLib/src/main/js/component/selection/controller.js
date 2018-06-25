Sjl.component.selection.init = function() {
    console.info('init container component');
};

Sjl.component.selection._optimizeConfig = function (config)  {
    config = config || {};
    config.type = "selection";
    config.parent = config.parent || 'body';
    config.width = config.width || 200;
    config.height = config.height || 30;
    config.hidden = config.hidden || false;
    config.templateName = config.templateName || 'default';
};

Sjl.component.selection.create = function(config) {
    var scope = Sjl.component.selection;
    scope._optimizeConfig(config);

    var elementConfig = Sjl.generateElementConfig(scope, config);
    var element = Sjl.createElement(elementConfig, true);

    if(config.hidden != true) {
        Sjl.showElement(element.id);
    }

    console.info('created selection: '+element.id);
};

Sjl.component.selection.init();