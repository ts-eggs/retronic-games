Sjl.component.list._templates = {};

Sjl.component.list._templates.default = {
    name: "list",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    events: {
        click: "clickList"
    },
    items: []
};