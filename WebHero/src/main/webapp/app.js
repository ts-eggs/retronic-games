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
    var createGameButton = Sjl.createButton({
        name: 'button-create-game', class: 'content-button', parent: 'tree',
        text: 'Create Game', width: 180, height: 50
    });

    var findGameButton = Sjl.createButton({
        name: 'button-find-game', class: 'content-button', parent: 'tree',
        text: 'Find Game', width: 180, height: 50
    });

    Sjl.listen("buttonClick", Sjl.findGameClicked, findGameButton);
    Sjl.listen("buttonClick", Sjl.createGameClicked, createGameButton);

    var sessionToken = Sjl.getCookie("sessionToken");

    if(sessionToken && sessionToken.length > 0) {
        var auth = window.atob(sessionToken);
        welcome(auth.substring(0, auth.indexOf(":")));
        createGameButton.hide();
        findGameButton.hide();
    }

    Sjl.listen("afterLogin", Sjl.afterLogin, Sjl, {createGameButton: createGameButton, findGameButton: findGameButton});
    Sjl.listen("afterLogout", Sjl.afterLogout, Sjl, {createGameButton: createGameButton, findGameButton: findGameButton});
};

Sjl.afterLogin = function(context, scope, params) {
    var user = context.response.value;
    var name = user.lastname && user.firstname ? user.firstname + ", " + user.lastname : user.login;
    welcome(name);

    if(params) {
        params.createGameButton.hide();
        params.findGameButton.hide();
    }

    // add tree comp to container...
};

Sjl.afterLogout = function(context, scope, params) {
    Sjl.eraseCookie("sessionToken");
    Sjl.hideDomElement("header-logout");
    Sjl.showDomElement("header-login");
    Sjl.getDomElement("header-login-name").innerText = "";

    if(params) {
        params.createGameButton.show();
        params.findGameButton.show();
    }
};

Sjl.createGameClicked = function(context, scope) {
};

Sjl.findGameClicked = function(context, scope) {
};

Sjl.init();