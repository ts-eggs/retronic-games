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
            this._loadPackage(packageName, this.custom, Sjl.config.customPath);
        }
    }
};

Sjl._loadPackage = function(packageName, scope, path) {
    scope = scope || this;

    if(!scope.hasOwnProperty(packageName)) {
        console.warn("package not found: "+packageName);
        return;
    }

    for( var scriptName in scope[packageName] ) {
        if(scope[packageName].hasOwnProperty(scriptName)) {
            if(packageName === "config" || packageName === "custom") {
                continue;
            }

            if(packageName === "style") {
                this._loadStyle(packageName, scriptName, scope);
            } else if (packageName === 'component') {
                this._loadComponent(packageName, scriptName, scope);
            } else {
                this._loadScript(packageName, scriptName, scope, path);
            }
        }
    }
};

Sjl._loadStyle = function(packageName, styleName, scope) {
    if(Sjl.config.loadStyles == false) {
        return;
    }

    if(!scope[packageName].hasOwnProperty(styleName)) {
        console.warn("style not found: "+styleName);
        return;
    }

    Sjl._createLink("css/sjl/" + styleName + ".css");
};

Sjl._loadScript = function(packageName, scriptName, scope, path) {
    if(Sjl.config.loadScripts == false) {
        return;
    }

    if(!scope[packageName].hasOwnProperty(scriptName)) {
        console.warn("script not found: "+scriptName);
        return;
    }

    path = path || "js/lib/";
    Sjl._createScript(path + packageName + "/" + scriptName + ".js");
};

Sjl._loadComponent = function(packageName, scriptName, scope) {
    if(Sjl.config.loadComponents == false) {
        return;
    }

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