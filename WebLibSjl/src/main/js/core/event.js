Sjl._eventListeners = {};

Sjl.core.event.init = function() {
    Sjl.core.event._scopeName = "core.event";
};

Sjl.listen = function(name, callback, scope) {
    scope = scope || Sjl;

    if(scope._eventListeners.hasOwnProperty(name)) {
        console.error("listeners are already defined: "+name);
        return;
    }

    scope._eventListeners[name] = callback;
};

Sjl.fire = function(name, context, scope) {
    scope = scope || Sjl;

    if(!scope._eventListeners.hasOwnProperty(name)) {
        return
    }

    scope._eventListeners[name](context, scope);
};

Sjl.core.event.init();