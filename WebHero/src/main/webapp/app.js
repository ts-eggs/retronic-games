Sjl.config.customPath = "js/custom/";
Sjl.config.remotePath = window.location.origin + "/rest/";

Sjl.afterLogin = function(response, options) {
    welcome(options.authentication.name);
};

Sjl.applySession = function(token) {
    var auth = window.atob(token);
    welcome(auth.substring(0, auth.indexOf(":")));
};

Sjl.custom = {
    game: {
        window: {}
    }
};

Sjl.init();