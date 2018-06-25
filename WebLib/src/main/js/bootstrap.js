/**
 * Load the library located at the same path with this file
 *
 * Automatically load sjl lib file uglified
 * if url parameter debug is set, load sjl lib file
 * if url parameter develop is set, load sjl lib packages & files
 */
(function() {
    var scriptFile = "sjl.js",
        queryString = window.location.search,
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
        scriptFile = "sjl-debug.js";
    } else if (queryString.match('(\\?|&)develop') !== null) {
        scriptFile = "sjl-develop.js";
    }

    document.write('<script type="text/javascript" charset="UTF-8" src="' + path + scriptFile + '"></script>');
})();
