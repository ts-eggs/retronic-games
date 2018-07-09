Scl.core.remote.init = function() {
    Scl.core.remote._scopeName = "core.remote";
};

Scl.core.remote._options = { method: "GET", resource: "users", callback: null, authentication: {name: "", password: ""} };

/**
* @options:
 *  method: Request method
 *  resource: Url path of the resource
 *  callback: callback function which is called after response
 *  authentication: object for basic authentication {name: "", password: ""}
**/
Scl.request = function(options) {
    if(!options || !options.method || !options.resource) {
        console.warn("no options defined for request.");
        return;
    }

    var token = Scl.getCookie("sessionToken");
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() { Scl.core.remote._requestReadyStateChange(this, options); };
    request.open(options.method, SclConfig.remotePath + options.resource, true);
    request.setRequestHeader("Content-type", "application/json");

    if(!token && options.authentication) {
        token = btoa(options.authentication.name + ':' + options.authentication.password);
    }

    if(token) {
        options.sessionToken = token;
        request.withCredentials = true;
        request.setRequestHeader('Authorization', 'Basic ' + token);
    }

    //options.loadingElement = Scl.createMask(options.loadingText ? {title: options.loadingText} : {});
    request.send();
};

Scl.core.remote._requestReadyStateChange = function(request, options) {
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

        //Scl.removeMask(options.loadingElement.id);
    }
};

Scl.core.remote.init();