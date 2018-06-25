Sjl.component.window._templates = {};

Sjl.component.window._templates.default = {
    name: "window",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    items: [{
        name: "window-header",
        items: [{
            name: "window-header-title",
            text: "{{title}}"
        }, {
            name: "window-header-close",
            events: {
                click: "clickClose"
            }
        }]
    }, {
        name: "window-content"
    }]
};