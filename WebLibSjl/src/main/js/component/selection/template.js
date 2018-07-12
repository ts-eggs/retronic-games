Sjl.component.selection._templates = {};

Sjl.component.selection._templates.default = {
    type: "selection",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    layout: "horizontal",
    items: [{
        name: "label",
        style: {
            width: "40%"
        }
    },{
        name: "value",
        domType: "input",
        inputType: "text",
        style: {
            width: "calc(60% - 30px)",
            "border-bottom": "1px solid"
        }
    },{
        name: "tool-button-expand",
        style: {
            "border-bottom": "1px solid"
        },
        events: {
            click: "clickExpand"
        }
    }]
};