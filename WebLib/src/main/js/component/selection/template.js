Sjl.component.selection._templates = {};

Sjl.component.selection._templates.default = {
    name: "selection",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    items: [{
        name: "selection-text"
    },{
        name: "selection-trigger",
        events: {
            click: "clickTrigger"
        }
    }]
};