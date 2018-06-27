Sjl.component.list.init = function() {
    console.info('init list component');
};

Sjl.component.list._optimizeConfig = function (config)  {
    config = config || {};
    config.type = "list";
    config.parent = config.parent || 'body';
    config.width = config.width || 200;
    config.height = config.height || 100;
    config.hidden = config.hidden || true;
    config.templateName = config.templateName || 'default';
};

Sjl.component.list.create = function(config) {

};

Sjl.component.list.init();