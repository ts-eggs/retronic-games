/**
 *
 * Simple Javascript Library Sjl
 * @Author ts
 * @Version 1.0
 *
 */

var Sjl = {
    style: {
        core : {},
        component : {}
    },
    core: {
        template: {},
        cookie: {},
        element: {},
        container: {}
    },
    component: {
        base: {},
        window: {},
        input: {},
        selection: {},
        list: {}
    },
    custom: {}
};

Sjl.init = function() {
    var packageName;

    // load library packages
    for( packageName in this ) {
        if(this.hasOwnProperty(packageName)) {
            this._loadPackage(packageName);
        }
    }

    // load custom packages
    for( packageName in this.custom ) {
        if(this.custom.hasOwnProperty(packageName)) {
            this._loadPackage(packageName, this.custom);
        }
    }
};

Sjl._loadPackage = function(packageName, scope) {
    scope = scope || this;

    if(!scope.hasOwnProperty(packageName)) {
        console.warn("package not found: "+packageName);
        return;
    }

    for( var scriptName in scope[packageName] ) {
        if(scope[packageName].hasOwnProperty(scriptName)) {
            if(packageName === "style") {
                scope._loadStyle(packageName, scriptName, scope);
            } else if (packageName === 'component') {
                scope._loadComponent(packageName, scriptName, scope);
            } else {
                scope._loadScript(packageName, scriptName, scope);
            }
        }
    }
};

Sjl._loadStyle = function(packageName, styleName, scope) {
    if(!scope[packageName].hasOwnProperty(styleName)) {
        console.warn("style not found: "+styleName);
        return;
    }

    Sjl._createLink("css/sjl/" + styleName + ".css");
};

Sjl._loadScript = function(packageName, scriptName, scope) {
    if(!scope[packageName].hasOwnProperty(scriptName)) {
        console.warn("script not found: "+scriptName);
        return;
    }

    Sjl._createScript("js/lib/" + packageName + "/" + scriptName + ".js");
};

Sjl._loadComponent = function(packageName, scriptName, scope) {
    if(!scope[packageName].hasOwnProperty(scriptName)) {
        console.warn("component not found: "+scriptName);
        return;
    }

    if(scriptName != "base") {
        Sjl._createScript("js/lib/" + packageName + "/" + scriptName + "/template.js");
        Sjl._createScript("js/lib/" + packageName + "/" + scriptName + "/events.js");
    }

    Sjl._createScript("js/lib/" + packageName + "/" + scriptName + "/controller.js");
};

Sjl._createScript = function(scriptPath) {
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = scriptPath;
    document.head.appendChild(script);
};

Sjl._createLink = function(stylePath) {
    var link = document.createElement("link");
    link.rel = "stylesheet";
    link.type = "text/css";
    link.href = stylePath;
    document.head.appendChild(link);
};

Sjl.init();