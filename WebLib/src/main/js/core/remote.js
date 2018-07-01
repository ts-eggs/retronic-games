Sjl.userAuthentication = null;

Sjl.core.remote.init = function() {
    // map public functions
    Sjl.request = Sjl.core.remote.request;
    Sjl.setAuthentication = Sjl.core.remote.setAuthentication;
};

Sjl.core.remote.setAuthentication = function(authentication) {
    Sjl.userAuthentication = authentication;
};

Sjl.core.remote.request = function(options) {
    if(!options || !options.resourcePath) {
        console.warn("no options defined for request.");
        return;
    }

    options.method = options.method || "GET";
    options.authentication = options.authentication || Sjl.userAuthentication;

    var request = new XMLHttpRequest();
    request.onreadystatechange = function() { Sjl.core.remote._requestReadyStateChange(this, options); };
    request.open(options.method, Sjl.config.remotePath + options.resourcePath, true);
    request.setRequestHeader("Content-type", "application/json");

    if(options.authentication) {
        request.withCredentials = true;
        request.setRequestHeader('Authorization', 'Basic ' + btoa(options.authentication.name + ':' + options.authentication.password))
    }

    //TODO: add loading mask
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

        //TODO: remove loading mask
    }
};

Sjl.core.remote.init();