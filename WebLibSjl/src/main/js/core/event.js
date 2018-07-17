Sjl._eventListeners = {};

Sjl.core.event.init = function() {
    Sjl.core.event._scopeName = "core.event";
};

Sjl.listen = function(name, callback, scope, params) {
    scope = scope || Sjl;

    if(!scope.hasOwnProperty("_eventListeners")) {
        scope._eventListeners = {};
    }

    if(scope._eventListeners.hasOwnProperty(name)) {
        console.error("listener is already defined: "+name+" for scope: "+scope._scopeName);
        return;
    }

    scope._eventListeners[name] = {callback: callback, params: params || null};
};

Sjl.fire = function(name, context, scope) {
    scope = scope || Sjl;

    if(!scope.hasOwnProperty("_eventListeners") || !scope._eventListeners.hasOwnProperty(name) || !scope._eventListeners[name].hasOwnProperty("callback")) {
        return
    }

    scope._eventListeners[name].callback(context, scope, scope._eventListeners[name].params);
};

Sjl.core.event.init();