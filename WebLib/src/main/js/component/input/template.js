Sjl.component.input._templates = {};

Sjl.component.input._templates.default = {
    type: "input",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}",
        "margin-bottom": "10px"
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
            width: "60%",
            "border-bottom": "1px solid"
        },
        events: {
            change: "changeValue"
        }
    }]
};