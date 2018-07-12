/**
 *
 * Simple Javascript Library Sjl
 * @Author ts
 * @Version 1.0
 *
 */
var Sjl = {
    config: {},
    style: {
        core : {},
        component : {}
    },
    core: {
        template: {},
        cookie: {},
        remote: {},
        event: {},
        element: {},
    },
    component: {
        base: {},
        button: {},
        input: {},
        list: {},
        mask: {},
        selection: {},
        window: {}
    },
    model: {
        user: {}
    },
    custom: {}
};

Sjl._scriptsToLoad = 0;
Sjl._scriptPackages = ["core", "model"];

Sjl.init = function() {
    Sjl._scriptsToLoad = 0;
    var packageName, scriptName, styleName;

    // load script packages
    for( var i = 0; i < Sjl._scriptPackages.length; i++ ) {
        packageName = Sjl._scriptPackages[i];

        if(this.hasOwnProperty(packageName)) {
            this._loadPackage(packageName);
        }
    }

    // load components
    for( scriptName in this.component ) {
        if(this.component.hasOwnProperty(scriptName)) {
            this._loadComponent(scriptName);
        }
    }

    // load styles
    for( styleName in this.style ) {
        if(this.style.hasOwnProperty(styleName)) {
            this._loadStyle(styleName);
        }
    }

    // load custom packages
    for( packageName in this.custom ) {
        if(this.custom.hasOwnProperty(packageName)) {
            this._loadPackage(packageName, this.custom, SjlConfig.customPath);
        }
    }

    Sjl._initialized();
};

Sjl._loadPackage = function(packageName, scope, path) {
    scope = scope || this;

    if(!scope.hasOwnProperty(packageName)) {
        console.warn("package not found: "+packageName);
        return;
    }

    for( var scriptName in scope[packageName] ) {
        if(scope[packageName].hasOwnProperty(scriptName)) {
            this._loadScript(packageName, scriptName, scope, path);
        }
    }
};

Sjl._loadStyle = function(styleName) {
    if(SjlConfig.loadStyles == false) {
        return;
    }

    Sjl._createLink(SjlConfig.stylePath + styleName + ".css");
};

Sjl._loadScript = function(packageName, scriptName, scope, path) {
    if(SjlConfig.loadScripts == false) {
        return;
    }

    if(!scope[packageName].hasOwnProperty(scriptName)) {
        console.warn("script not found: "+scriptName);
        return;
    }

    path = path || SjlConfig.libPath;
    Sjl._createScript(path + packageName + "/" + scriptName + ".js");
};

Sjl._loadComponent = function(scriptName) {
    if(SjlConfig.loadComponents == false) {
        return;
    }

    Sjl._createScript(SjlConfig.libPath + "component/" + scriptName + "/controller.js");

    if(scriptName != "base") {
        Sjl._createScript(SjlConfig.libPath + "component/" + scriptName + "/template.js");
        Sjl._createScript(SjlConfig.libPath + "component/" + scriptName + "/events.js");
    }
};

Sjl._createLink = function(stylePath) {
    var link = document.createElement("link");
    link.rel = "stylesheet";
    link.type = "text/css";
    link.href = stylePath;
    document.head.appendChild(link);
};

Sjl._createScript = function(scriptPath) {
    Sjl._scriptsToLoad++;
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = scriptPath;
    script.onload = Sjl._scriptLoaded;
    document.head.appendChild(script);
};

Sjl._scriptLoaded = function(event) {
    Sjl._scriptsToLoad--;
    Sjl._initialized();
};

Sjl._initialized = function() {
    if(Sjl._scriptsToLoad == 0) {
        Sjl.initialized();
    }
};

Sjl.initialized = function() {
    //TODO: not functional at all

    /*if(!Sjl.custom.view) {
        console.info("no custom view is defined, you can only use components. if you want to create an application define a view at scope Sjl.custom.view");
        return;
    }

    for(var key in Sjl.custom.view) {
        if(Sjl.custom.view.hasOwnProperty(key)) {
            Sjl.create(Sjl.custom.view[key]);
        }
    }*/
};