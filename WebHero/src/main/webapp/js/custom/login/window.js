var templateConfig = {
    type: "window",name: "login", parent: "{{parent}}", style: {width: "{{width}}",height: "{{height}}"},
    items: [{
        type: "container",name: "window-header",class: "window-header",layout: "horizontal",
        items: [{
            name: "window-header-title",class: "title",text: "{{title}}"
        }, {
            name: "window-header-close",class: "tool-button-close",style: {float: "right"},events: {click: "clickClose"}
        }]
    }, {
        type: "container",name: "window-content",class: "window-content",
        items: [{
            type: "input",label: "Name",name: "input-name"
        }, {
            type: "input",label: "Password",name: "input-password",inputType: "password"
        }]
    }, {
        type: "container",name: "window-buttons",class: "window-buttons",layout: "horizontal",
        items: [{
            type: "button",text: "Register", style: {float: "right"}, callbacks: {click: 'Sjl.custom.login.window.clickRegister'}
        }, {
            type: "button",text: "Login", style: {float: "right"}, callbacks: {click: 'Sjl.custom.login.window.clickLogin'}
        }]
    }]
};

Sjl.applyCustom(Sjl.custom.login.window, {
    extendScope: Sjl.component.window,
    templateConfig: templateConfig,
    templateName: "login",

    clickLogin: function(element, event) {
        var window = Sjl.getElement(element.mainComponentId);
        var name = Sjl.findElementByName('input-name', window);
        var password = Sjl.findElementByName('input-password', window);

        if(!name || !name.value || !password || !password.value) {
            console.error("no login values found.");
            return;
        }

        var options = {
            method: "GET",
            resourcePath: "users/login",
            callback: Sjl.custom.login.window.loginCallback,
            authentication: {name: name.value, password: password.value},
            mainComponentId: element.mainComponentId
        };

        Sjl.request(options);
    },

    clickRegister: function(element, event) {

    },

    loginCallback: function(response, options) {
        if(response.status != 200) {
            console.error("login failed.");
            return;
        }

        Sjl.setAuthentication(options.authentication);
        Sjl.removeWindow(options.mainComponentId);
        document.getElementById('header-login').style.display = "none";
        document.getElementById('header-logout').style.display = "block";
        document.getElementById('header-login-name').innerText = "Hello " + options.authentication.name;
    }
});