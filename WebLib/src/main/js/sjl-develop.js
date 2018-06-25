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
    }
};

Sjl.init = function() {
    for( var packageName in this ) {
        if(this.hasOwnProperty(packageName)) {
            this._loadPackage(packageName);
        }
    }
};

Sjl._loadPackage = function(packageName) {
    if(!this.hasOwnProperty(packageName)) {
        console.warn("package not found: "+packageName);
        return;
    }

    for( var scriptName in this[packageName] ) {
        if(this[packageName].hasOwnProperty(scriptName)) {
            if(packageName === "style") {
                this._loadStyle(packageName, scriptName);
            } else if (packageName === 'component') {
                this._loadComponent(packageName, scriptName);
            } else {
                this._loadScript(packageName, scriptName);
            }
        }
    }
};

Sjl._loadStyle = function(packageName, styleName) {
    if(!this[packageName].hasOwnProperty(styleName)) {
        console.warn("style not found: "+styleName);
        return;
    }

    Sjl._createLink("css/sjl/" + styleName + ".css");
};

Sjl._loadScript = function(packageName, scriptName) {
    if(!this[packageName].hasOwnProperty(scriptName)) {
        console.warn("script not found: "+scriptName);
        return;
    }

    Sjl._createScript("js/lib/" + packageName + "/" + scriptName + ".js");
};

Sjl._loadComponent = function(packageName, scriptName) {
    if(!this[packageName].hasOwnProperty(scriptName)) {
        console.warn("component not found: "+scriptName);
        return;
    }

    Sjl._createScript("js/lib/" + packageName + "/" + scriptName + "/template.js");
    Sjl._createScript("js/lib/" + packageName + "/" + scriptName + "/controller.js");
    Sjl._createScript("js/lib/" + packageName + "/" + scriptName + "/events.js");
};

Sjl._createScript = function(scriptPath) {
    console.info("load script: "+scriptPath);
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = scriptPath;
    document.head.appendChild(script);
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