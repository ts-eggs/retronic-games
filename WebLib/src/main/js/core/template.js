top.Sjl.core.template.init = function() {
    console.info('init template core');

    // map public functions
    top.Sjl.generateElementConfig = top.Sjl.core.template.generateElementConfig;
};

top.Sjl.core.template.generateElementConfig = function(templateConfig, config) {
    var scope = top.Sjl.core.template;
    var templateString = JSON.stringify(templateConfig);
    var parameters = scope._getParameters(templateString);

    for( var i = 0; i < parameters.length; i++ ) {
        if(config.hasOwnProperty(parameters[i])) {
            templateString = templateString.replace("{{"+parameters[i]+"}}", config[parameters[i]])
        }
    }

    templateConfig = JSON.parse(templateString);
    scope._applyConfig(templateConfig, config);
    return templateConfig;
};

top.Sjl.core.template._applyConfig = function(templateConfig, config) {
    for( var key in config ) {
        if(config.hasOwnProperty(key) && !templateConfig.hasOwnProperty(key)) {
            templateConfig[key] = config[key];
        }
    }
};

top.Sjl.core.template._getParameters = function(templateString) {
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

top.Sjl.core.template.init();