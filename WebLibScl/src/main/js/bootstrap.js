/**
 * Load the library located at the same path with this file
 * Automatically load scl lib file uglified
 * if url parameter debug is set, load scl lib file
 * if url parameter develop is set, load scl lib packages & files
 */
(function() {
    var libFile = "scl.js",
        configFile = "config/config.js",
        queryString = window.location.search,
        hostname = window.location.hostname,
        scripts = document.getElementsByTagName('script'),
        path, i, ln, scriptSrc, match;

    for (i = 0, ln = scripts.length; i < ln; i++) {
        scriptSrc = scripts[i].src;
        match = scriptSrc.match(/bootstrap\.js$/);

        if (match) {
            path = scriptSrc.substring(0, scriptSrc.length - match[0].length);
            break;
        }
    }

    if (queryString.match('(\\?|&)debug') !== null) {
        libFile = "scl-debug.js";
    } else if (queryString.match('(\\?|&)develop') !== null || hostname == 'localhost') {
        configFile = "config/config-develop.js";
    }

    document.write('<script type="text/javascript" charset="UTF-8" src="' + path + configFile + '"></script>');
    document.write('<script type="text/javascript" charset="UTF-8" src="' + path + libFile + '"></script>');
})();
