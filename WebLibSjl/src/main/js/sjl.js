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