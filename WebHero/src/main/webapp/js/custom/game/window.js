var templateConfig = {
    type: "window", parent: "{{parent}}", style: {width: "{{width}}",height: "{{height}}"},
    items: [{
        type: "container",name: "window-header",class: "window-header",layout: "horizontal",
        items: [{
            name: "window-header-title",class: "title",text: "{{title}}"
        }, {
            name: "window-header-close",class: "tool-button-close",style: {float: "right"},events: {click: "clickClose"}
        }]
    }, {
        type: "container",name: "window-content",class: "window-content",
        items: [{
            type: "selection",label: "Select Game",name: "select-game",resource: ""
        }, {
            type: "input",label: "Name",name: "input-name"
        }, {
            type: "input",label: "Difficult",name: "input-difficult"
        }]
    }, {
        type: "container",name: "window-buttons",class: "window-buttons",layout: "horizontal",
        items: [{
            type: "button",text: "Cancel", style: {float: "right"}, callbacks: {click: 'Sjl.custom.game.window.clickCancel'}
        }, {
            type: "button",text: "Save", style: {float: "right"}
        }]
    }]
};

Sjl.applyCustom(Sjl.custom.game.window, {
    extendComponent: Sjl.component.window,
    templateConfig: templateConfig,
    templateName: "game",

    clickCancel: function(element, event) {
        // do some stuff
    }
});