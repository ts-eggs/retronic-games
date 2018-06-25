Sjl.component.window._eventFunctions = {};

Sjl.component.window._eventFunctions.clickClose = function(element, event) {
    Sjl.removeWindow(element.componentId);
};