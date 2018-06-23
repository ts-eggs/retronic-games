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
        cookie: {}
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
            } else {
                this._loadScript(packageName, scriptName);
            }
        }
    }
};

top.Sjl._loadScript = function(packageName, scriptName) {
    if(!this[packageName].hasOwnProperty(scriptName)) {
        console.warn("script not found: "+scriptName);
        return;
    }

    var scriptPath = "js/lib/" + packageName + "/" + scriptName + ".js";
    console.info("load script: "+scriptPath);
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = scriptPath;
    document.head.appendChild(script);
};

top.Sjl._loadStyle = function(packageName, styleName) {
    if(!this[packageName].hasOwnProperty(styleName)) {
        console.warn("style not found: "+styleName);
        return;
    }

    var stylePath = "css/sjl/" + styleName + ".css";
    console.info("load style: "+stylePath);
    var link = document.createElement("link");
    link.rel = "stylesheet";
    link.type = "text/css";
    link.href = stylePath;
    document.head.appendChild(link);
};

top.Sjl.init();