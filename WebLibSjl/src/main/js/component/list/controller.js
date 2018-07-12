Sjl.component.list.init = function() {
    Sjl.core.element._scopeName = "component.list";
    Sjl.applyConfig(Sjl.component.list, Sjl.component.base);
};

Sjl.component.list._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "list";
    config.width = config.width || 200;
    config.height = config.height || 100;
};

Sjl.component.list.create = function(config, scope) {
    scope = scope || Sjl.component.list;
    var element = Sjl.component.base.create(config, scope);
    return element;
};

Sjl.component.list.init();