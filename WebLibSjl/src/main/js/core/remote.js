Sjl.core.remote.init = function() {
    Sjl.core.element._scopeName = "core.remote";

    // map public functions
    Sjl.request = Sjl.core.remote.request;
};

Sjl.core.remote.request = function(options) {
    if(!options || !options.resourcePath) {
        console.warn("no options defined for request.");
        return;
    }

    options.method = options.method || "GET";
    var token = Sjl.getCookie("sessionToken");
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() { Sjl.core.remote._requestReadyStateChange(this, options); };
    request.open(options.method, Sjl.config.remotePath + options.resourcePath, true);
    request.setRequestHeader("Content-type", "application/json");

    if(!token && options.authentication) {
        token = btoa(options.authentication.name + ':' + options.authentication.password);
    }

    if(token) {
        options.sessionToken = token;
        request.withCredentials = true;
        request.setRequestHeader('Authorization', 'Basic ' + token);
    }

    options.loadingElement = Sjl.createMask(options.loadingText ? {title: options.loadingText} : {});
    request.send();
};

Sjl.core.remote._requestReadyStateChange = function(request, options) {
    if (request.readyState == 4) {
        var value = null;

        if(request.status.toString().indexOf("2") == 0) {
            try {
                value = JSON.parse(request.responseText);
            } catch(e) {
                value = null;
                console.warn("Could not parse response: "+e);
            }
        }

        var response = {
            request: request,
            status: request.status,
            value: value
        };

        if(options.callback) {
            options.callback(response, options);
        }

        Sjl.removeMask(options.loadingElement.id);
    }
};

Sjl.core.remote.init();