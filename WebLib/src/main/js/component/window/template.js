top.Sjl.component.window._templates = {};

top.Sjl.component.window._templates.default = {
    name: 'window',
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    items: [{
        name: 'window-header',
        items: [{
            name: 'window-header-title',
            text: "{{title}}"
        }, {
            name: 'window-header-close'
        }]
    }, {
        name: 'window-content'
    }]
};