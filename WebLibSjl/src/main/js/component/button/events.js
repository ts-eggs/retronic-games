Sjl.component.button._eventListeners = {};
Sjl.component.button._eventFunctions = {};

Sjl.component.button._eventFunctions.buttonClick = function(element, event) {
    Sjl.fire("buttonClick", event, element);
};
