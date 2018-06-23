top.Sjl.core.cookie.init = function() {
    console.info('init cookie component');
};

top.Sjl.core.cookie.setCookie = function(name, value, expireDays, path) {
    var cookieValue = name + "=" + value;

    if(expireDays) {
        var d = new Date();
        d.setTime(d.getTime() + (expireDays * 24 * 60 * 60 * 1000));
        var expires = "expires="+d.toUTCString();
        cookieValue += ";" + expires;
    }

    if(path) {
        cookieValue += ";path=" + path;
    }

    document.cookie = cookieValue;
};

top.Sjl.core.cookie.getCookie = function(name) {
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

top.Sjl.core.cookie.init();