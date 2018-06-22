top.Sjl = {
    element: {},
    cookie: {},
    window: {}
};

top.Sjl.init = function() {
    this._loadScript("js/lib/core/element.js");
    this._loadScript("js/lib/core/cookie.js");
    this._loadScript("js/lib/components/window.js");
};

top.Sjl._loadScript = function(url) {
    var script = document.createElement("script");
    script.src = url;
    document.head.appendChild(script);
};

top.Sjl.init();