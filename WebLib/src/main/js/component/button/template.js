Sjl.component.button._templates = {};

Sjl.component.button._templates.default = {
    type: "button",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    events: {
        click: "clickButton"
    }
};