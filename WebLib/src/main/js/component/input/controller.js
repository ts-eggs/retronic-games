Sjl.component.input.init = function() {
    Sjl.applyConfig(Sjl.component.input, Sjl.component.base);
};

Sjl.component.input._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "input";
    config.width = config.width || "100%";
    config.height = config.height || 30;
};

Sjl.component.input.create = function(config, scope) {
    scope = scope || Sjl.component.input;
    var element = Sjl.component.base.create(config, scope);
    var label = element.findElementByName("label");
    var value = element.findElementByName("value");

    if(config.label && label) {
        label.dom.innerText = config.label;
    }

    if(config.value && value) {
        value.dom.innerText = config.value;
    }

    if(config.inputType && value) {
        value.dom.type = config.inputType;
    }

    return element;
};

Sjl.component.input.init();