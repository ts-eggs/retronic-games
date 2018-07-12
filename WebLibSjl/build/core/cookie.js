Sjl.core.cookie.init = function() {
    Sjl.core.cookie._scopeName = "core.cookie";
};

Sjl.setCookie = function(name, value, expireHours, path) {
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

Sjl.getCookie = function(name) {
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

Sjl.eraseCookie = function(name) {
    document.cookie = name+'=; Max-Age=-99999999;';
};

Sjl.core.cookie.init();