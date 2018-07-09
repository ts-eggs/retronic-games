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
                <div id="header-logout" class="header-button" style="display: none;" onclick="logout()">Logout</div>
                <div id="header-login" class="header-button" onclick="login()">Login</div>
                <div id="header-welcome"></div>
            </div>
        `;
    }

    connectedCallback() {
        const loginButton = this.shadowRoot.querySelector('#header-login');
        this._boundOnLoginClick = this._onLoginClick.bind(this);
        loginButton.addEventListener('click', this._boundOnLoginClick);
    }

    disconnectedCallback() {
        const loginButton = this.shadowRoot.querySelector('#header-login');
        loginButton.removeEventListener('click', this._boundOnLoginClick);
    }

    _onLoginClick(e) {
        // open login window
    }
});