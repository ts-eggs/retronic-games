Scl.core.cookie.init = function() {
    Scl.core.cookie._scopeName = "core.cookie";
    var sessionToken = Scl.getCookie("sessionToken");

    if(sessionToken && sessionToken.length > 0) {
        Scl.applySession(sessionToken);
    }
};

Scl.setCookie = function(name, value, expireHours, path) {
    var cookieValue = name + "=" + value;

    if(expireHours) {
        var d = new Date();
        d.setTime(d.getTime() + (expireHours * 60 * 60 * 1000));
        var expires = "expires="+d.toUTCString();
        cookieValue += ";" + expires;
    }

    if(path) {
        cookieValue += ";path=" + path;
    }

    document.cookie = cookieValue;
};

Scl.getCookie = function(name) {
    var ca = document.cookie.split(';');
    name = name + "=";

    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];

        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }

        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }

    return "";
};

Scl.eraseCookie = function(name) {
    document.cookie = name+'=; Max-Age=-99999999;';
};

Scl.applySession = function(sessionToken) {
    // use to apply session auth
};

Scl.core.cookie.init();