function createGame() {
    Sjl.createWindow({
        title: 'Create Game',
        name: 'window-game',
        templateName: 'game'
    });
}

function login() {
    Sjl.createWindow({
        title: 'Login',
        name: 'window-login',
        templateName: 'login',
        height: 240
    });
}

function logout() {
    Sjl.setAuthentication(null);
    document.getElementById('header-logout').style.display = "none";
    document.getElementById('header-login').style.display = "block";
    document.getElementById('header-login-name').innerText = "";
}