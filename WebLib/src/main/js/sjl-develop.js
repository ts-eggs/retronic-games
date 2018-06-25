/**
 *
 * Simple Javascript Library Sjl
 * @Author ts
 * @Version 1.0
 *
 */

top.Sjl = {
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

top.Sjl.init = function() {
    for( var packageName in this ) {
        if(this.hasOwnProperty(packageName)) {
            this._loadPackage(packageName);
        }
    }
};

top.Sjl._loadPackage = function(packageName) {
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

top.Sjl._loadStyle = function(packageName, styleName) {
    if(!this[packageName].hasOwnProperty(styleName)) {
        console.warn("style not found: "+styleName);
        return;
    }

    top.Sjl._createLink("css/sjl/" + styleName + ".css");
};

top.Sjl._loadScript = function(packageName, scriptName) {
    if(!this[packageName].hasOwnProperty(scriptName)) {
        console.warn("script not found: "+scriptName);
        return;
    }

    top.Sjl._createScript("js/lib/" + packageName + "/" + scriptName + ".js");
};

top.Sjl._loadComponent = function(packageName, scriptName) {
    if(!this[packageName].hasOwnProperty(scriptName)) {
        console.warn("component not found: "+scriptName);
        return;
    }

    top.Sjl._createScript("js/lib/" + packageName + "/" + scriptName + "/template.js");
    top.Sjl._createScript("js/lib/" + packageName + "/" + scriptName + "/controller.js");
    top.Sjl._createScript("js/lib/" + packageName + "/" + scriptName + "/events.js");
};

top.Sjl._createScript = function(scriptPath) {
    console.info("load script: "+scriptPath);
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = scriptPath;
    document.head.appendChild(script);
};

top.Sjl._createLink = function(stylePath) {
    console.info("load style: "+stylePath);
    var link = document.createElement("link");
    link.rel = "stylesheet";
    link.type = "text/css";
    link.href = stylePath;
    document.head.appendChild(link);
};

top.Sjl.init();