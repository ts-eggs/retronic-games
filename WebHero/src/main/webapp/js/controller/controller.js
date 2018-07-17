function login() {
    Sjl.createWindow({
        name: 'window-login', templateName: 'login',
        title: 'Login', height: 240
    });
}

function logout() {
    Sjl.fire("afterLogout");
}

function welcome(name) {
    Sjl.hideDomElement("header-login");
    Sjl.showDomElement("header-logout");
    Sjl.getDomElement("header-login-name").innerText = "Hello " + name;
}