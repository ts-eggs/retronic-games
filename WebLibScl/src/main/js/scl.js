/**
 *
 * Simple Component Library Scl
 * @Author ts
 * @Version 1.0
 *
 */

var Scl = {
    core: {
        cookie: {},
        remote: {}
    },
    component: {
        header: {}
    }
};

Scl._scriptsToLoad = 0;
Scl._knownPackages = ["core", "component"];

Scl.init = function() {
    Scl._scriptsToLoad = 0;

    // Feature detect
    if (!(window.customElements && document.body.attachShadow)) {
        console.error("Your browser doesn't support Shadow DOM and Custom Elements v1.");
        return;
    }

    var packageName;

    // load library packages
    for( packageName in this ) {
        if(this.hasOwnProperty(packageName)) {
            this._loadPackage(packageName);
        }
    }

    Scl._initialized();
};

Scl._loadPackage = function(packageName) {
    if(SclConfig.loadScripts == false) {
        return;
    }

    if(!this.hasOwnProperty(packageName)) {
        console.warn("package not found: "+packageName);
        return;
    }

    for( var scriptName in this[packageName] ) {
        if(this[packageName].hasOwnProperty(scriptName)) {
            if(Scl._knownPackages.indexOf(packageName) == -1) {
                continue;
            }

            if(!this[packageName].hasOwnProperty(scriptName)) {
                console.warn("script not found: "+scriptName);
                return;
            }

            Scl._createScript(SclConfig.libPath + packageName + "/" + scriptName + ".js");
        }
    }
};

Scl._createScript = function(scriptPath) {
    Scl._scriptsToLoad++;
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = scriptPath;
    script.onload = Scl._scriptLoaded;
    document.head.appendChild(script);
};

Scl._scriptLoaded = function(event) {
    Scl._scriptsToLoad--;
    Scl._initialized();
};

Scl._initialized = function() {
    if(Scl._scriptsToLoad == 0) {
        Scl.initialized();
    }
};

Scl.initialized = function() {};