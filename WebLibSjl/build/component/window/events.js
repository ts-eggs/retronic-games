Sjl.component.window._eventFunctions.clickClose = function(element, event) {
    Sjl.removeWindow(element.componentId);
};

Sjl.component.window._eventFunctions.clickLogin = function(element, event) {
    var window = Sjl.getElement(element.mainComponentId);
    var name = Sjl.findElementByName('input-name', window);
    var password = Sjl.findElementByName('input-password', window);

    if(!name || !name.value || !password || !password.value) {
        console.error("no login values found.");
        return;
    }

    var options = {
        method: "GET", resource: "users/login",
        callback: Sjl.component.window._eventFunctions.loginCallback,
        authentication: {name: name.value, password: password.value},
        mainComponentId: element.mainComponentId
    };

    Sjl.request(options);
};

Sjl.component.window._eventFunctions.clickRegister = function(element, event) {

};

Sjl.component.window._eventFunctions.loginCallback = function(response, options) {
    if(response.status != 200) {
        console.error("login failed.");
        return;
    }

    Sjl.setCookie("sessionToken", options.sessionToken, 4);
    Sjl.removeWindow(options.mainComponentId);
    Sjl.fire("afterLogin", response, options);

    /*if(Sjl.afterLogin.constructor == Function) {
        Sjl.afterLogin(response, options);
    }*/
};