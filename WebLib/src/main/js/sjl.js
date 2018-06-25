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
        element: {},
        cookie: {},
        template: {}
    },
    component: {
        window: {}
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

Sjl._createLink = function(stylePath) {
    console.info("load style: "+stylePath);
    var link = document.createElement("link");
    link.rel = "stylesheet";
    link.type = "text/css";
    link.href = stylePath;
    document.head.appendChild(link);
};

Sjl.init();