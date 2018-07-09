//./src/main/js/sjl.js
/**
 *
 * Simple Javascript Library Sjl
 * @Author ts
 * @Version 1.0
 *
 */

var Sjl = {
    config: {},
    style: {
        core : {},
        component : {}
    },
    core: {
        template: {},
        cookie: {},
        element: {},
        remote: {},
        container: {}
    },
    component: {
        base: {},
        button: {},
        input: {},
        list: {},
        mask: {},
        selection: {},
        window: {}
    },
    model: {
        user: {}
    },
    custom: {}
};

Sjl.createApplication = function() {
    if(!Sjl.custom.view) {
        //TODO: not functional at all
        //console.info("no custom view is defined, you can only use components. if you want to create an application define a view at scope Sjl.custom.view");
    }

    for(var key in Sjl.custom.view) {
        if(Sjl.custom.view.hasOwnProperty(key)) {
            Sjl.create(Sjl.custom.view[key]);
        }
    }
};

//./src/main/js/core/container.js
Sjl.core.container._templates = {};

Sjl.core.container.init = function() {
    Sjl.core.element._scopeName = "core.container";
    Sjl.applyConfig(Sjl.core.container, Sjl.core.element);

    // map public functions
    Sjl.findElementById = Sjl.core.container.findElementById;
    Sjl.findElementByName = Sjl.core.container.findElementByName;
    Sjl.createContainer = Sjl.core.container.createElement;
    Sjl.create = Sjl.core.container.create;
};

Sjl.core.container._optimizeConfig = function (config)  {
    Sjl.core.element._optimizeConfig(config);
    config.layout = config.layout || "vertical";
};

Sjl.core.container._setChildren = function(config, parent) {
    for(var i = 0; i < config.items.length; i++) {
        var childConfig = config.items[i];
        childConfig.parent = parent.id;
        childConfig.componentId = parent.componentId;
        childConfig.componentType = parent.componentType;
        childConfig.mainComponentId = parent.mainComponentId;

        if(parent.layout === "horizontal") {
            childConfig.style = childConfig.style || {};
            childConfig.style.float = childConfig.style.float || 'left';
        }

        config.items[i] = Sjl.create(childConfig);
    }
};

Sjl.core.container.create = function(config, isComponent) {
    var componentScope = Sjl.component[config.type];

    if(componentScope) {
        return componentScope.create(config);
    }

    if(config.type === "container") {
        return Sjl.createContainer(config, isComponent);
    } else {
        return Sjl.createElement(config, isComponent);
    }
};

Sjl.core.container.createElement = function(config, isComponent) {
    var scope = Sjl.core.container;
    var element = Sjl.core.element.createElement(config, isComponent);

    if(config.items && config.items.length > 0) {
        scope._setChildren(config, element);
    }

    element.isContainer = true;
    element.findElementById = scope.findElementById;
    element.findElementByName = scope.findElementByName;
    return element;
};

Sjl.core.container.findElementById = function(id, container) {
    container = container || this;

    if(!container.hasOwnProperty('items')) {
        console.warn("cannot find element, because container has not property items: "+container.id);
        return null;
    }

    for( var i = 0; i < container.items.length; i++ ) {
        var item = container.items[i];

        if(item.id == id) {
            return item;
        }

        if(item.isContainer) {
            var foundElement = item.findElementById(id);

            if(foundElement != null) {
                return foundElement;
            }
        }
    }

    return null;
};

Sjl.core.container.findElementByName = function(name, container) {
    container = container || this;

    if(!container.hasOwnProperty('items')) {
        console.warn("cannot find element, because container has not property items: "+container.id);
        return null;
    }

    for( var i = 0; i < container.items.length; i++ ) {
        var item = container.items[i];

        if(item.name == name) {
            return item;
        }

        if(item.isContainer) {
            var foundElement = item.findElementByName(name);

            if(foundElement != null) {
                return foundElement;
            }
        }
    }

    return null;
};

Sjl.core.container.init();

//./src/main/js/core/cookie.js
Sjl.core.cookie.init = function() {
    Sjl.core.element._scopeName = "core.cookie";

    // map public functions
    Sjl.setCookie = Sjl.core.cookie.setCookie;
    Sjl.getCookie = Sjl.core.cookie.getCookie;
    Sjl.eraseCookie = Sjl.core.cookie.eraseCookie;
    var sessionToken = Sjl.getCookie("sessionToken");

    if(Sjl.applySession.constructor == Function && sessionToken && sessionToken.length > 0) {
        Sjl.applySession(sessionToken);
    }
};

Sjl.core.cookie.setCookie = function(name, value, expireHours, path) {
    var cookieValue = name + "=" + value;

    if(expireHours) {
        var d = new Date();
        d.setTime(d.getTime() + (expireHours * 60 * 60 * 1000));
        var expires = "expires="+d.toUTCString();
        cookieValue += ";" + expires;
    }

    if(path) {
        cookieValue += ";path=" + path;
    }

    document.cookie = cookieValue;
};

Sjl.core.cookie.getCookie = function(name) {
    var ca = document.cookie.split(';');
    name = name + "=";

    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];

        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }

        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }

    return "";
};

Sjl.core.cookie.eraseCookie = function(name) {
    document.cookie = name+'=; Max-Age=-99999999;';
};

Sjl.core.cookie.init();

//./src/main/js/core/element.js
Sjl.core.element._elements = {};

Sjl.core.element.init = function() {
    Sjl.core.element._scopeName = "core.element";

    // map public functions
    Sjl.createElement = Sjl.core.element.createElement;
    Sjl.removeElement = Sjl.core.element.removeElement;
    Sjl.getElement = Sjl.core.element.getElement;
    Sjl.showElement = Sjl.core.element.showElement;
    Sjl.hideElement = Sjl.core.element.hideElement;
};

Sjl.core.element._getNextElementId = function() {
    var scope = Sjl.core.element;
    var id = 1;

    for(var key in scope._elements) {
        if(scope._elements.hasOwnProperty(key)) {
            id = id < parseInt(key) ? parseInt(key) : id;
        }
    }

    return id+1;
};

Sjl.core.element._hasElementId = function(id) {
    var scope = Sjl.core.element;

    for(var key in scope._elements) {
        if(id == key) {
            return true;
        }
    }

    return false;
};

Sjl.core.element._addElement = function(element) {
    var scope = Sjl.core.element;

    if(scope._elements.hasOwnProperty(element.id)) {
        console.warn('element already exists in map: ' + element.id);
        return;
    }

    scope._elements[element.id] = element;
};

Sjl.core.element._removeElement = function(element) {
    var scope = Sjl.core.element;

    if(!scope._elements.hasOwnProperty(element.id)) {
        console.warn('element not found in map for remove: '+element.id);
        return;
    }

    scope._removeElementsWithParentId(element.id);
    delete scope._elements[element.id];
};

Sjl.core.element._removeElementsWithParentId = function (parentId)  {
    var scope = Sjl.core.element;

    for( var key in scope._elements ) {
        if(scope._elements.hasOwnProperty(key)) {
            if(scope._elements[key].id != parentId && scope._elements[key].parent === parentId) {
                scope._removeElement(scope._elements[key]);
            }
        }
    }
};

Sjl.core.element._optimizeConfig = function (config)  {
    var scope = Sjl.core.element;
    config = config || {};
    config.id = config.id || scope._getNextElementId();

    if(scope._hasElementId(config.id)) {
        console.error('element id is already used: '+config.id);
        return;
    }

    config.type = config.type || "element";
    config.name = config.name || config.type;
    var autoClass = config.type != "element" ? config.type : config.name;
    config.class = config.class || autoClass;

    if(config.class.indexOf("tool-button") != -1) {
        config.class = "tool-button " + config.class;
    }

    config.domType = config.domType || "div";
};

Sjl.core.element._setStyling = function(config, element) {
    var scope = Sjl.core.element;

    for( var key in config.style ) {
        if(config.style.hasOwnProperty(key) && element.dom.style.hasOwnProperty(key)) {
            element.dom.style[key] = config.style[key].constructor === Number && scope._isMeasureStyle(key) ?  config.style[key] + "px" : config.style[key];
        }
    }
};

Sjl.core.element._isMeasureStyle = function(style) {
    return style == "width" || style == "height" || style == "top" || style == "left" || style.indexOf("margin") != -1 || style.indexOf("padding") != -1;
};

Sjl.core.element._addDomEvents = function(events, callbacks, element, scope, componentScope) {
    if(!scope) {
        console.warn('could not add dom events, scope undefined for dom: '+element.dom.id);
        return;
    }

    if(!componentScope) {
        console.warn('could not add dom events, componentScope undefined for component: '+element.type);
        return;
    }

    var key;

    if(events) {
        for( key in events ) {
            if(events.hasOwnProperty(key)) {
                var fnName = events[key];

                if(!componentScope._eventFunctions.hasOwnProperty(fnName)) {
                    console.warn('no function defined for component scope: '+componentScope.toString()+' fn: '+fnName);
                    continue;
                }

                scope._addDomEvent(element, key, componentScope._eventFunctions[fnName]);
            }
        }
    }

    if(callbacks) {
        for( key in callbacks ) {
            if(callbacks.hasOwnProperty(key)) {
                var currentScope = Sjl;
                var packages = callbacks[key].split(".");

                for( var i = 0; i < packages.length; i++ ) {
                    if(packages[i] === "Sjl") {
                        continue;
                    }

                    currentScope = currentScope[packages[i]];
                }

                if(currentScope.constructor === Function) {
                    scope._addDomEvent(element, key, currentScope);
                }
            }
        }
    }
};

Sjl.core.element._addDomEvent = function(element, event, fn) {
    if(event === 'click') {
        element.dom.onclick = function(e) {
            fn(element, e);
        }
    } else if(event === 'change') {
        element.dom.onkeyup = function(e) {
            fn(element, e);
        }
    }
};

Sjl.core.element.createElement = function(config, isComponent) {
    var componentScope = Sjl.component.hasOwnProperty(config.componentType) ? Sjl.component[config.componentType] : Sjl.component[config.type];
    var scope = Sjl.core.element;
    scope._optimizeConfig(config);

    var element = config;
    element.dom = document.createElement(config.domType);
    element.dom.id = config.name + "-" + config.id;
    element.dom.className = config.class;

    if(isComponent === true) {
        element.componentId = element.id;
        element.componentType = element.type;
        element.mainComponentId = element.mainComponentId || element.id;
    }

    if(config.style) {
       scope._setStyling(config, element);
    }
    
    if(config.text) {
        element.dom.innerText = config.text;
    }

    scope._addElement(element);

    if(config.parent) {
        var parentElement = scope._hasElementId(config.parent) ? scope.getElement(config.parent) : null;
        var parentElementDom = parentElement ? parentElement.dom : null;
        var parentDom = config.parent === 'body' ? top.document.body : parentElementDom;

        if(parentDom) {
            parentDom.appendChild(element.dom);
        }
    }

    if(componentScope) {
        scope._addDomEvents(config.events, config.callbacks, element, scope, componentScope);
    }

    // console.info("created element: "+element.type+" id: "+element.id);
    return element;
};

Sjl.core.element.getElement = function(id) {
    var scope = Sjl.core.element;

    if(!scope._elements.hasOwnProperty(id)) {
        console.warn('element not found in map for get: '+id);
        return null;
    }

    return scope._elements[id];
};

Sjl.core.element.removeElement = function(id) {
    var scope = Sjl.core.element;
    var element = scope.getElement(id);

    if(element == null) {
        console.warn('element not found for remove: '+id);
        return;
    }

    if(element.dom.parentNode) {
        element.dom.parentNode.removeChild(element.dom);
    }

    // console.info("removed element: "+element.type+" id: "+element.id);
    scope._removeElement(element);
};

Sjl.core.element.showElement = function(id) {
    var scope = Sjl.core.element;
    var element = scope.getElement(id);

    if(element == null) {
        console.warn('element not found to show: '+id);
        return;
    }

    element.dom.style.display = "block";
};

Sjl.core.element.hideElement = function(id) {
    var scope = Sjl.core.element;
    var element = scope.getElement(id);

    if(element == null) {
        console.warn('element not found to hide: '+id);
        return;
    }

    element.dom.style.display = "none";
};

Sjl.core.element.init();

//./src/main/js/core/remote.js
Sjl.core.remote.init = function() {
    Sjl.core.element._scopeName = "core.remote";

    // map public functions
    Sjl.request = Sjl.core.remote.request;
};

Sjl.core.remote.request = function(options) {
    if(!options || !options.resourcePath) {
        console.warn("no options defined for request.");
        return;
    }

    options.method = options.method || "GET";
    var token = Sjl.getCookie("sessionToken");
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() { Sjl.core.remote._requestReadyStateChange(this, options); };
    request.open(options.method, Sjl.config.remotePath + options.resourcePath, true);
    request.setRequestHeader("Content-type", "application/json");

    if(!token && options.authentication) {
        token = btoa(options.authentication.name + ':' + options.authentication.password);
    }

    if(token) {
        options.sessionToken = token;
        request.withCredentials = true;
        request.setRequestHeader('Authorization', 'Basic ' + token);
    }

    options.loadingElement = Sjl.createMask(options.loadingText ? {title: options.loadingText} : {});
    request.send();
};

Sjl.core.remote._requestReadyStateChange = function(request, options) {
    if (request.readyState == 4) {
        var value = null;

        if(request.status.toString().indexOf("2") == 0) {
            try {
                value = JSON.parse(request.responseText);
            } catch(e) {
                value = null;
                console.warn("Could not parse response: "+e);
            }
        }

        var response = {
            request: request,
            status: request.status,
            value: value
        };

        if(options.callback) {
            options.callback(response, options);
        }

        Sjl.removeMask(options.loadingElement.id);
    }
};

Sjl.core.remote.init();

//./src/main/js/core/template.js
Sjl.core.template.init = function() {
    Sjl.core.element._scopeName = "core.template";

    // map public functions
    Sjl.applyCustom = Sjl.core.template.applyCustom;
    Sjl.applyConfig = Sjl.core.template.applyConfig;
    Sjl.generateTemplateConfig = Sjl.core.template.generateTemplateConfig;
};

Sjl.core.template.applyCustom = function(configBase, configApply) {
    if(!configApply.templateConfig) {
        console.error("cannot create custom, because no template-config is defined.");
        return;
    }

    if(configApply.extendComponent) {
        if(!configApply.templateName) {
            console.error("cannot create custom component, because no template-name is defined.");
            return;
        }

        configApply.extendComponent._templates[configApply.templateName] = configApply.templateConfig;
    }

    return Sjl.core.template.applyConfig(configBase, configApply);
};

Sjl.core.template.applyConfig = function(configBase, configApply) {
    var scope = Sjl.core.template;

    if(!configApply || !configBase) {
        return;
    }

    if(configApply.constructor !== configBase.constructor) {
        console.error("cannot apply config, because both configs are not of the same type: "+configBase.constructor.name+" - "+configApply.constructor.name);
        return;
    }

    if(configApply.constructor === Array) {
        for( var i = 0; i < configApply.length; i++ ) {
            if(configBase.indexOf(configApply[i]) == -1) {
                configBase.push(scope.applyConfig({}, configApply[i]));
            }
        }
    }

    if(configApply.constructor === Object) {
        for( var key in configApply ) {
            if(configApply.hasOwnProperty(key) && !configBase.hasOwnProperty(key)) {
                configBase[key] = configApply[key];
            } else if(configBase.hasOwnProperty(key) && configBase[key].constructor === Object) {
                configBase[key] = scope.applyConfig(configBase[key], configApply[key]);
            }
        }
    }

    return configBase;
};

Sjl.core.template.generateTemplateConfig = function(componentScope, config) {
    var scope = Sjl.core.template;
    var templateConfig = scope._getTemplateConfig(componentScope, config);
    var templateString = JSON.stringify(templateConfig);
    var parameters = scope._getParameters(templateString);

    for( var i = 0; i < parameters.length; i++ ) {
        if(config.hasOwnProperty(parameters[i])) {
            if(config[parameters[i]].constructor == Number) {
                templateString = templateString.replace("\"{{"+parameters[i]+"}}\"", config[parameters[i]]);
            } else {
                templateString = templateString.replace("{{"+parameters[i]+"}}", config[parameters[i]]);
            }
        }
    }

    templateConfig = JSON.parse(templateString);
    scope.applyConfig(templateConfig, config);
    return templateConfig;
};

Sjl.core.template._getTemplateConfig = function(scope, config) {
    if(scope._templates.hasOwnProperty(config.templateName)) {
        return scope._templates[config.templateName];
    }

    return scope._templates['default'];
};

Sjl.core.template._getParameters = function(templateString) {
    var parameters = [];
    var index = templateString.indexOf("{{");
    var subTemplateString = templateString.substr(index+2, templateString.length);

    while (index != -1) {
        parameters.push(subTemplateString.substring(0, subTemplateString.indexOf("}}")));
        index = subTemplateString.indexOf("{{");
        subTemplateString = subTemplateString.substr(index+2, subTemplateString.length);
    }

    return parameters;
};

Sjl.core.template.init();

//./src/main/js/component/base/controller.js
Sjl.component.base._templates = {};

Sjl.component.base.init = function() {
    Sjl.core.element._scopeName = "component.base";
};

Sjl.component.base._optimizeConfig = function (config)  {
    config = config || {};
    config.parent = config.parent || 'body';
    config.hidden = config.hidden || false;
    config.templateName = config.templateName || 'default';
};

Sjl.component.base.create = function(config, scope) {
    scope = scope || Sjl.component.base;
    scope._optimizeConfig(config);
    var element = Sjl.createContainer(Sjl.generateTemplateConfig(scope, config), true);

    if(config.hidden != true) {
        Sjl.showElement(element.id);
    }

    return element;
};

Sjl.component.base.init();

//./src/main/js/component/button/controller.js
Sjl.component.button._eventFunctions = {};
Sjl.component.button._templates = {};

Sjl.component.button.init = function() {
    Sjl.core.element._scopeName = "component.button";
    Sjl.applyConfig(Sjl.component.button, Sjl.component.base);
};

Sjl.component.button._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "input";
    config.height = config.height || 30;
    config.callbacks = config.callbacks || {};
};

Sjl.component.button.create = function(config, scope) {
    scope = scope || Sjl.component.button;
    var element = Sjl.component.base.create(config, scope);
    return element;
};

Sjl.component.button.init();

//./src/main/js/component/button/events.js


//./src/main/js/component/button/template.js
Sjl.component.button._templates.default = {
    type: "button",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    }
};

//./src/main/js/component/input/controller.js
Sjl.component.input._eventFunctions = {};
Sjl.component.input._templates = {};

Sjl.component.input.init = function() {
    Sjl.core.element._scopeName = "component.input";
    Sjl.applyConfig(Sjl.component.input, Sjl.component.base);
};

Sjl.component.input._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "input";
    config.width = config.width || "100%";
    config.height = config.height || 30;
};

Sjl.component.input.create = function(config, scope) {
    scope = scope || Sjl.component.input;
    var element = Sjl.component.base.create(config, scope);
    var label = element.findElementByName("label");
    var value = element.findElementByName("value");

    if(config.label && label) {
        label.dom.innerText = config.label;
    }

    if(config.value && value) {
        value.dom.innerText = config.value;
    }

    if(config.inputType && value) {
        value.dom.type = config.inputType;
    }

    return element;
};

Sjl.component.input.init();

//./src/main/js/component/input/events.js
Sjl.component.input._eventFunctions.changeValue = function(element, event) {
    element.value = element.dom.value;
    var inputComponent = Sjl.getElement(element.componentId);

    if(inputComponent) {
        inputComponent.value = element.value;
    }
};

//./src/main/js/component/input/template.js
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

//./src/main/js/component/list/controller.js
Sjl.component.list._eventFunctions = {};
Sjl.component.list._templates = {};

Sjl.component.list.init = function() {
    Sjl.core.element._scopeName = "component.list";
    Sjl.applyConfig(Sjl.component.list, Sjl.component.base);
};

Sjl.component.list._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "list";
    config.width = config.width || 200;
    config.height = config.height || 100;
};

Sjl.component.list.create = function(config, scope) {
    scope = scope || Sjl.component.list;
    var element = Sjl.component.base.create(config, scope);
    return element;
};

Sjl.component.list.init();

//./src/main/js/component/list/events.js
Sjl.component.list._eventFunctions.clickList = function(element, event) {
    
};

//./src/main/js/component/list/template.js
Sjl.component.list._templates.default = {
    type: "list",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    events: {
        click: "clickList"
    },
    items: []
};

//./src/main/js/component/mask/controller.js
Sjl.component.mask._eventFunctions = {};
Sjl.component.mask._masks = {};
Sjl.component.mask._templates = {};

Sjl.component.mask.init = function() {
    Sjl.core.element._scopeName = "component.mask";
    Sjl.applyConfig(Sjl.component.window, Sjl.component.base);

    // map public functions
    Sjl.createMask = Sjl.component.mask.create;
    Sjl.removeMask = Sjl.component.mask.remove;
};

Sjl.component.mask._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "mask";
    config.width = config.width || "100%";
    config.height = config.height || "100%";
    config.title = config.title || "Loading";
};

Sjl.component.mask.create = function(config, scope) {
    scope = scope || Sjl.component.mask;
    var element = Sjl.component.base.create(config, scope);
    scope._masks[element.id] = element;
    return element;
};

Sjl.component.mask.remove = function(id) {
    var scope = Sjl.component.mask;

    if(!scope._masks.hasOwnProperty(id)) {
        console.error("no mask found to remove: "+id);
    }

    Sjl.removeElement(id);
    delete scope._masks[id];
};

Sjl.component.mask.init();

//./src/main/js/component/mask/events.js


//./src/main/js/component/mask/template.js
Sjl.component.mask._templates.default = {
    type: "mask",
    name: "mask",
    items: [{
        name: "mask-icon"
    }, {
        name: "mask-text",
        text: "{{title}}"
    }]
};

//./src/main/js/component/selection/controller.js
Sjl.component.selection._eventFunctions = {};
Sjl.component.selection._templates = {};

Sjl.component.selection.init = function() {
    Sjl.core.element._scopeName = "component.selection";
    Sjl.applyConfig(Sjl.component.selection, Sjl.component.input);
};

Sjl.component.selection._optimizeConfig = function (config)  {
    Sjl.component.input._optimizeConfig(config);
    config.type = "selection";
    config.readOnly = config.readOnly || false;
};

Sjl.component.selection.create = function(config, scope) {
    scope = scope || Sjl.component.selection;
    var element = Sjl.component.input.create(config, scope);
    var value = element.findElementByName("value");

    if(config.readOnly === true && value) {
        value.dom.readOnly = config.readOnly;
    }

    return element;
};

Sjl.component.selection.init();

//./src/main/js/component/selection/events.js
Sjl.component.selection._eventFunctions.clickExpand = function(element, event) {
    
};

//./src/main/js/component/selection/template.js
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

//./src/main/js/component/window/controller.js
Sjl.component.window._eventFunctions = {};
Sjl.component.window._templates = {};
Sjl.component.window._windows = {};

Sjl.component.window.init = function() {
    Sjl.core.element._scopeName = "component.window";
    Sjl.applyConfig(Sjl.component.window, Sjl.component.base);

    // map public functions
    Sjl.createWindow = Sjl.component.window.create;
    Sjl.getWindow = Sjl.component.window.getWindow;
    Sjl.removeWindow = Sjl.component.window.removeWindow;
    Sjl.centerWindow = Sjl.component.window.centerWindow;
};

Sjl.component.window._optimizeConfig = function (config)  {
    Sjl.component.base._optimizeConfig(config);
    config.type = "window";
    config.width = config.width || 400;
    config.height = config.height || 400;
};

Sjl.component.window.create = function(config, scope) {
    scope = scope || Sjl.component.window;
    var element = Sjl.component.base.create(config, scope);
    var items = Sjl.applyConfig([], config.items) || [];
    var buttons = Sjl.applyConfig([], config.buttons) || [];
    var i;

    for( i = 0; i < items.length; i++ ) {
        var child = items[i];
        var itemContainer = element.findElementByName("window-content");
        child.parent = itemContainer == null ? null : itemContainer.id;
        Sjl.create(child);
    }

    for( i = 0; i < buttons.length; i++ ) {
        var button = buttons[i];
        button.style = button.style || {};
        button.style.float = 'right';
        var buttonContainer = element.findElementByName("window-buttons");
        button.parent = buttonContainer == null ? null : buttonContainer.id;
        Sjl.create(button);
    }

    scope._windows[element.id] = element;
    scope.centerWindow(element);
    return element;
};

Sjl.component.window.getWindow = function(id) {
    var scope = Sjl.component.window;

    if(!scope._windows.hasOwnProperty(id)) {
        console.error("no window found to get: "+id);
        return null;
    }

    return this._windows[id];
};

Sjl.component.window.removeWindow = function(id) {
    var scope = Sjl.component.window;

    if(!scope._windows.hasOwnProperty(id)) {
        console.error("no window found to remove: "+id);
    }

    Sjl.removeElement(id);
    delete scope._windows[id];
};

Sjl.component.window.centerWindow = function(view) {
    var scope = Sjl.component.window;
    view = typeof view === 'object' ? view : scope.getWindow(view);
    view.dom.style.left = ((top.document.body.offsetWidth - view.style.width) / 2) + "px";
    view.dom.style.top = ((top.document.body.offsetHeight - view.style.height) / 2) + "px";
};

Sjl.component.window.init();

//./src/main/js/component/window/events.js
Sjl.component.window._eventFunctions.clickClose = function(element, event) {
    Sjl.removeWindow(element.componentId);
};

Sjl.component.window._eventFunctions.clickLogin = function(element, event) {
    var window = Sjl.getElement(element.mainComponentId);
    var name = Sjl.findElementByName('input-name', window);
    var password = Sjl.findElementByName('input-password', window);

    if(!name || !name.value || !password || !password.value) {
        console.error("no login values found.");
        return;
    }

    var options = {
        method: "GET", resourcePath: "users/login",
        callback: Sjl.component.window._eventFunctions.loginCallback,
        authentication: {name: name.value, password: password.value},
        mainComponentId: element.mainComponentId
    };

    Sjl.request(options);
};

Sjl.component.window._eventFunctions.clickRegister = function(element, event) {

};

Sjl.component.window._eventFunctions.loginCallback = function(response, options) {
    if(response.status != 200) {
        console.error("login failed.");
        return;
    }

    Sjl.setCookie("sessionToken", options.sessionToken, 4);
    Sjl.removeWindow(options.mainComponentId);

    if(Sjl.afterLogin.constructor == Function) {
        Sjl.afterLogin(response, options);
    }
};

//./src/main/js/component/window/template.js
Sjl.component.window._templates.default = {
    type: "window",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    items: [{
        type: "container",
        name: "window-header",
        class: "window-header",
        layout: "horizontal",
        items: [{
            name: "window-header-title",
            class: "title",
            text: "{{title}}"
        }, {
            name: "window-header-close",
            class: "tool-button-close",
            style: {
                float: "right"
            },
            events: {
                click: "clickClose"
            }
        }]
    }, {
        type: "container",
        name: "window-content",
        class: "window-content"
    }, {
        type: "container",
        name: "window-buttons",
        class: "window-buttons",
        layout: "horizontal"
    }]
};

Sjl.component.window._templates.login = {
    type: "window",
    name: "login",
    parent: "{{parent}}",
    style: {
        width: "{{width}}",
        height: "{{height}}"
    },
    items: [{
        type: "container",
        name: "window-header",
        class: "window-header",
        layout: "horizontal",
        items: [{
            name: "window-header-title",
            class: "title",
            text: "{{title}}"
        }, {
            name: "window-header-close",
            class: "tool-button-close",
            style: {
                float: "right"
            },
            events: {
                click: "clickClose"
            }
        }]
    }, {
        type: "container",
        name: "window-content",
        class: "window-content",
        items: [{
            type: "input",
            label: "Name",
            name: "input-name"
        }, {
            type: "input",
            label: "Password",
            name: "input-password",
            inputType: "password"
        }]
    }, {
        type: "container",
        name: "window-buttons",
        class: "window-buttons",
        layout: "horizontal",
        items: [{
            type: "button",
            text: "Register",
            style: {
                float: "right"
            },
            events: {
                click: 'clickRegister'
            }
        }, {
            type: "button",
            text: "Login",
            style: {
                float: "right"
            },
            events: {
                click: 'clickLogin'
            }
        }]
    }]
};
