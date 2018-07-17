Sjl.component.button._templates = {};

Sjl.component.button._templates.default = {
    type: "button",
    parent: "{{parent}}",
    text: "{{text}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    events: {
        click: "buttonClick"
    }
};