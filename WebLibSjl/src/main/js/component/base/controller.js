Sjl.component.base._templates = {};

Sjl.component.base.init = function() {
    Sjl.core.element._scopeName = "component.base";
};

Sjl.component.base._optimizeConfig = function (config)  {
    config = config || {};
    config.parent = config.parent || 'body';
    config.hidden = config.hidden || false;
    config.templateName = config.templateName || 'default';
};

Sjl.component.base.create = function(config, scope) {
    scope = scope || Sjl.component.base;
    scope._optimizeConfig(config);
    var element = Sjl.createElement(Sjl.generateTemplateConfig(scope, config), true);

    if(config.hidden != true) {
        Sjl.showElement(element.id);
    }

    return element;
};

Sjl.component.base.init();