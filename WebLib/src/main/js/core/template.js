Sjl.core.template.init = function() {
    console.info('init template core');

    // map public functions
    Sjl.applyConfig = Sjl.core.template.applyConfig;
    Sjl.generateTemplateConfig = Sjl.core.template.generateTemplateConfig;
};

Sjl.core.template.applyConfig = function(configBase, configApply) {
    var scope = Sjl.core.template;

    if(configApply.constructor !== configBase.constructor) {
        console.error("cannot apply config, because both configs are not of the same type: "+configBase.constructor.name+" - "+configApply.constructor.name);
        return;
    }

    if(configApply.constructor === Array) {
        for( var i = 0; i < configApply.length; i++ ) {
            if(configBase.indexOf(configApply[i]) == -1) {
                configBase.push(scope.applyConfig({}, configApply[i]));
            }
        }
    }

    if(configApply.constructor === Object) {
        for( var key in configApply ) {
            if(configApply.hasOwnProperty(key) && !configBase.hasOwnProperty(key)) {
                configBase[key] = configApply[key];
            } else if(configBase.hasOwnProperty(key) && configBase[key].constructor === Object) {
                configBase[key] = scope.applyConfig(configBase[key], configApply[key]);
            }
        }
    }

    return configBase;
};

Sjl.core.template.generateTemplateConfig = function(componentScope, config) {
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
    scope.applyConfig(templateConfig, config);
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