Sjl.component.input._eventListeners = {};
Sjl.component.input._eventFunctions = {};

Sjl.component.input._eventFunctions.changeValue = function(element, event) {
    element.value = element.dom.value;
    var inputComponent = Sjl.getElement(element.componentId);

    if(inputComponent) {
        inputComponent.value = element.value;
    }
};