Sjl.core.template.init = function() {
    Sjl.core.element._scopeName = "core.template";
};

Sjl.applyCustom = function(configBase, configApply) {
    if(!configApply.templateConfig) {
        console.error("cannot create custom, because no template-config is defined.");
        return;
    }

    if(configApply.extendComponent) {
        if(!configApply.templateName) {
            console.error("cannot create custom component, because no template-name is defined.");
            return;
        }

        configApply.extendComponent._templates[configApply.templateName] = configApply.templateConfig;
    }

    return Sjl.applyConfig(configBase, configApply);
};

Sjl.applyConfig = function(configBase, configApply, forceOverride) {
    if(!configBase || !configApply) {
        console.error("cannot apply config, because some config is not defined, base: "+configBase+" - apply: "+configApply);
        return;
    }

    if(configBase.constructor !== configApply.constructor) {
        console.error("cannot apply config, because both configs are not of the same type: "+configBase.constructor.name+" - "+configApply.constructor.name);
        return;
    }

    if(configApply.constructor === Array) {
        for( var i = 0; i < configApply.length; i++ ) {
            if(configBase.indexOf(configApply[i]) == -1) {
                configBase.push(Sjl.applyConfig({}, configApply[i], forceOverride));
            }
        }
    }

    if(configApply.constructor === Object) {
        for( var key in configApply ) {
            if(configApply.hasOwnProperty(key) && (forceOverride || !configBase.hasOwnProperty(key))) {
                configBase[key] = configApply[key];
            } else if(configBase.hasOwnProperty(key) && configBase[key].constructor === Object) {
                configBase[key] = Sjl.applyConfig(configBase[key], configApply[key], forceOverride);
            }
        }
    }

    return configBase;
};

Sjl.generateTemplateConfig = function(componentScope, config) {
    var scope = Sjl.core.template;
    var templateConfig = scope._getTemplateConfig(componentScope, config);
    var templateString = JSON.stringify(templateConfig);
    var parameters = scope._getParameters(templateString);

    for( var i = 0; i < parameters.length; i++ ) {
        if(config.hasOwnProperty(parameters[i])) {
            if(config[parameters[i]].constructor == Number) {
                templateString = templateString.replace("\"{{"+parameters[i]+"}}\"", config[parameters[i]]);
            } else {
                templateString = templateString.replace("{{"+parameters[i]+"}}", config[parameters[i]]);
            }
        }
    }

    templateConfig = JSON.parse(templateString);
    Sjl.applyConfig(templateConfig, config, config.componentId > 0);
    return templateConfig;
};

Sjl.core.template._getTemplateConfig = function(scope, config) {
    if(scope._templates.hasOwnProperty(config.templateName)) {
        return scope._templates[config.templateName];
    }

    return scope._templates['default'];
};

Sjl.core.template._getParameters = function(templateString) {
    var parameters = [];
    var index = templateString.indexOf("{{");
    var subTemplateString = templateString.substr(index+2, templateString.length);

    while (index != -1) {
        parameters.push(subTemplateString.substring(0, subTemplateString.indexOf("}}")));
        index = subTemplateString.indexOf("{{");
        subTemplateString = subTemplateString.substr(index+2, subTemplateString.length);
    }

    return parameters;
};

Sjl.core.template.init();