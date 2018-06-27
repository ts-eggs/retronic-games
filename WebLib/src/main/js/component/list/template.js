Sjl.component.list._templates = {};

Sjl.component.list._templates.default = {
    type: "list",
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