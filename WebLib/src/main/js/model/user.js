Sjl.model.user._data = {
    name: "",
    password: ""
};

Sjl.model.user.init = function() {};

Sjl.model.user.getData = function(config) {
    return Sjl.model.user._data;
};

Sjl.model.user.init();