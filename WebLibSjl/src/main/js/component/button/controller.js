Sjl.component.button.init = function() {
    Sjl.core.element._scopeName = "component.button";
    Sjl.applyConfig(Sjl.component.button, Sjl.component.base);
};

Sjl.component.button._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "button";
    config.height = config.height || 30;
    config.width = config.width || 120;
    config.callbacks = config.callbacks || {};
};

Sjl.createButton = Sjl.component.button.create = function(config, scope) {
    scope = scope || Sjl.component.button;
    return Sjl.component.base.create(config, scope);
};

Sjl.component.button.init();