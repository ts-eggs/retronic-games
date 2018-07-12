Sjl.component.selection.init = function() {
    Sjl.core.element._scopeName = "component.selection";
    Sjl.applyConfig(Sjl.component.selection, Sjl.component.input);
};

Sjl.component.selection._optimizeConfig = function (config)  {
    Sjl.component.input._optimizeConfig(config);
    config.type = "selection";
    config.readOnly = config.readOnly || false;
};

Sjl.component.selection.create = function(config, scope) {
    scope = scope || Sjl.component.selection;
    var element = Sjl.component.input.create(config, scope);
    var value = element.findElementByName("value");

    if(config.readOnly === true && value) {
        value.dom.readOnly = config.readOnly;
    }

    return element;
};

Sjl.component.selection.init();