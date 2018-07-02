Sjl.component.mask._masks = {};

Sjl.component.mask.init = function() {
    Sjl.applyConfig(Sjl.component.window, Sjl.component.base);

    // map public functions
    Sjl.createMask = Sjl.component.mask.create;
    Sjl.removeMask = Sjl.component.mask.remove;
};

Sjl.component.mask._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "mask";
    config.width = config.width || "100%";
    config.height = config.height || "100%";
    config.title = config.title || "Loading";
};

Sjl.component.mask.create = function(config, scope) {
    scope = scope || Sjl.component.mask;
    var element = Sjl.component.base.create(config, scope);
    scope._masks[element.id] = element;
    return element;
};

Sjl.component.mask.remove = function(id) {
    var scope = Sjl.component.mask;

    if(!scope._masks.hasOwnProperty(id)) {
        console.error("no mask found to remove: "+id);
    }

    Sjl.removeElement(id);
    delete scope._masks[id];
};

Sjl.component.mask.init();