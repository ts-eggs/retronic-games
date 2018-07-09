customElements.define('scl-header', class extends HTMLElement {

    constructor() {
        super();
        const shadowRoot = this.attachShadow({mode: 'open'});

        shadowRoot.innerHTML = `
            <style>
                #header {
                    width: 100%;
                    height: 90px;
                    border-bottom: 1px solid #333300;
                }

                #header-title {
                    float: left;
                    margin: 20px;
                    vertical-align: middle;
                    line-height: 50px;
                    font-size: 30px;
                    font-weight: bold;
                }

                .header-button {
                    float: right;
                    width: 100px;
                    margin: 20px 20px 20px 0;
                    padding: 10px;
                    vertical-align: middle;
                    text-align: center;
                    line-height: 30px;
                    font-size: 12px;
                    color: #cccc99;
                    background-color: #333300;
                    border-radius: 5px;
                    cursor: pointer;
                }

                #header-welcome {
                    float: right;
                    margin: 20px 20px 20px 0;
                    padding: 10px;
                    vertical-align: middle;
                    text-align: center;
                    line-height: 30px;
                    font-size: 14px;
                }
            </style>

            <div id="header">
                <div id="header-title">Hero</div>
                <div id="header-logout" class="header-button" style="display: none;">Logout</div>
                <div id="header-login" class="header-button">Login</div>
                <div id="header-welcome"></div>
            </div>
        `;
    }

    connectedCallback() {
        var me = this;
        setTimeout(function() {Scl.addComponent(me);}, 500);
        const loginButton = this.shadowRoot.querySelector('#header-login');
        const logoutButton = this.shadowRoot.querySelector('#header-logout');
        loginButton.addEventListener('click', this._onLoginClick.bind(this));
        logoutButton.addEventListener('click', this._onLogoutClick.bind(this));
    }

    disconnectedCallback() {
        const loginButton = this.shadowRoot.querySelector('#header-login');
        const logoutButton = this.shadowRoot.querySelector('#header-logout');
        loginButton.removeEventListener('click', this._onLoginClick.bind(this));
        logoutButton.removeEventListener('click', this._onLogoutClick.bind(this));
    }

    _onLoginClick(e) {
        Scl.createComponent("scl-window");
    }

    _onLogoutClick(e) {
        const loginButton = this.shadowRoot.querySelector('#header-login');
        const logoutButton = this.shadowRoot.querySelector('#header-logout');
        loginButton.style.display = "block";
        logoutButton.style.display = "none";
        Scl.eraseCookie("sessionToken");
    }
});