Sjl.component.button._eventFunctions = {};
Sjl.component.button._templates = {};

Sjl.component.button.init = function() {
    Sjl.core.element._scopeName = "component.button";
    Sjl.applyConfig(Sjl.component.button, Sjl.component.base);
};

Sjl.component.button._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "input";
    config.height = config.height || 30;
    config.callbacks = config.callbacks || {};
};

Sjl.component.button.create = function(config, scope) {
    scope = scope || Sjl.component.button;
    var element = Sjl.component.base.create(config, scope);
    return element;
};

Sjl.component.button.init();