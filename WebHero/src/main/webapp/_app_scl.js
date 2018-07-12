SclConfig.libPath = "js/lib/";
SclConfig.customPath = "js/custom/";
SclConfig.remotePath = window.location.origin + "/rest/";

Scl.custom = {
    game: {
        window: {}
    }
};

Scl.applySession = function(token) {
    var auth = window.atob(token);
    welcome(auth.substring(0, auth.indexOf(":")));
};

Scl.initialized = function(response, options) {

};