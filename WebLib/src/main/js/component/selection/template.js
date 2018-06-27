Sjl.component.selection._templates = {};

Sjl.component.selection._templates.default = {
    type: "selection",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    items: [{
        name: "selection-text",
        class: "label"
    },{
        name: "selection-trigger",
        events: {
            click: "clickTrigger"
        }
    }]
};