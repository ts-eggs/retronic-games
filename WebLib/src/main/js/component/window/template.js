Sjl.component.window._templates = {};

Sjl.component.window._templates.default = {
    type: "window",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    items: [{
        type: "container",
        name: "window-header",
        class: "window-header",
        layout: "horizontal",
        items: [{
            name: "window-header-title",
            class: "title",
            text: "{{title}}"
        }, {
            name: "window-header-close",
            class: "tool-button-close",
            style: {
                float: "right"
            },
            events: {
                click: "clickClose"
            }
        }]
    }, {
        type: "container",
        name: "window-content",
        class: "window-content"
    }, {
        type: "container",
        name: "window-buttons",
        class: "window-buttons",
        layout: "horizontal"
    }]
};

Sjl.component.window._templates.login = {
    type: "window",
    name: "login",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    items: [{
        type: "container",
        name: "window-header",
        class: "window-header",
        layout: "horizontal",
        items: [{
            name: "window-header-title",
            class: "title",
            text: "{{title}}"
        }, {
            name: "window-header-close",
            class: "tool-button-close",
            style: {
                float: "right"
            },
            events: {
                click: "clickClose"
            }
        }]
    }, {
        type: "container",
        name: "window-content",
        class: "window-content",
        items: [{
            type: "input",
            label: "Name",
            name: "input-name"
        }, {
            type: "input",
            label: "Password",
            name: "input-password",
            inputType: "password"
        }]
    }, {
        type: "container",
        name: "window-buttons",
        class: "window-buttons",
        layout: "horizontal",
        items: [{
            type: "button",
            text: "Register",
            style: {
                float: "right"
            },
            events: {
                click: 'clickRegister'
            }
        }, {
            type: "button",
            text: "Login",
            style: {
                float: "right"
            },
            events: {
                click: 'clickLogin'
            }
        }]
    }]
};