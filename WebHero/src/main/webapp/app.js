SjlConfig.libPath = "js/lib/";
SjlConfig.customPath = "js/custom/";
SjlConfig.remotePath = window.location.origin + "/rest/";

Sjl.custom = {
    game: {
        window: {}
    }
};

// override
Sjl.initialized = function() {
    var sessionToken = Sjl.getCookie("sessionToken");

    if(sessionToken && sessionToken.length > 0) {
        var auth = window.atob(sessionToken);
        welcome(auth.substring(0, auth.indexOf(":")));
    }

    Sjl.listen("afterLogin", Sjl.afterLogin);

    // add tree comp to container...
};

Sjl.afterLogin = function(context, scope) {
    var user = context.response.value;
    var name = user.lastname && user.firstname ? user.firstname + ", " + user.lastname : user.login;
    welcome(name);
};

Sjl.init();