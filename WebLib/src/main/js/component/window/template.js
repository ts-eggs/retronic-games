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
        items: [{
            name: "window-header-title",
            class: "title",
            text: "{{title}}"
        }, {
            name: "window-header-close",
            events: {
                click: "clickClose"
            }
        }]
    }, {
        type: "container",
        name: "window-content"
    }]
};