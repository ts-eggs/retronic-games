Sjl.component.button.init = function() {
    Sjl.applyConfig(Sjl.component.button, Sjl.component.base);
};

Sjl.component.button._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "input";
    config.height = config.height || 30;
};

Sjl.component.button.create = function(config, scope) {
    scope = scope || Sjl.component.button;
    var element = Sjl.component.base.create(config, scope);

    if(config.text) {
        element.dom.innerText = config.text;
    }

    return element;
};

Sjl.component.button.init();