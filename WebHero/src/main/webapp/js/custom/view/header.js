var templateConfig = {
    type: "container", name: "header", parent: "{{parent}}", layout: "horizontal",
    items: [{
        name: "header-title"
    }, {
        type: "button", name: "header-logout", class: "header-button", visible: false,
        events: { click: 'onLoginClick' }
    }, {
        type: "button", name: "header-login", class: "header-button",
        events: { click: 'onLogoutClick' }
    }, {
        name: "header-login-name", class: "header-text"
    }]
};

Sjl.applyCustom(Sjl.custom.view.header, {
    templateConfig: templateConfig,
    templateName: "header",

    onLoginClick: function(element, event) {
        // do some stuff
    },

    onLogoutClick: function(element, event) {
        // do some stuff
    }
});