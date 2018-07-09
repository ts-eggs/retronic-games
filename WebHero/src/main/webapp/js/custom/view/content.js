var templateConfig = {
    type: "container", name: "content", parent: "{{parent}}",
    items: []
};

Sjl.applyCustom(Sjl.custom.view.header, {
    templateConfig: templateConfig,
    templateName: "content"
});