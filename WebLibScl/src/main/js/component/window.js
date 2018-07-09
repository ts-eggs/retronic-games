customElements.define('scl-window', class extends HTMLElement {

    constructor() {
        super();
        const shadowRoot = this.attachShadow({mode: 'open'});
        this._componentId = Scl.addComponent(this);

        shadowRoot.innerHTML = `
            <style>
                .window {
                    position: fixed;
                    width: 400px;
                    height: 300px;
                    z-index: 100;
                    box-shadow: 0 0 20px 4px rgba(0, 0, 0, 0.3);
                    background-color: #cccc99;
                }

                .window-header {
                    width: calc(100% - 20px);
                    height: 30px;
                    padding: 10px;
                    cursor: move;
                    background-color: #bbbb88;
                }

                .window-content {
                    width: calc(100% - 20px);
                    height: calc(100% - 140px);
                    padding: 20px 10px;
                    overflow: auto;
                }

                .window-buttons {
                    width: calc(100% - 20px);
                    height: 30px;
                    padding: 10px;
                }

                .tool-button {
                    float: right;
                    width: 30px;
                    height: 30px;
                    cursor: pointer;
                }

                .tool-button-close {
                    background: url("images/tools/close.png") no-repeat center;
                }

            </style>

            <div id="window-` + this._componentId + `" class="window">
                <div id="window-header-` + this._componentId + `" class="window-header">
                    <div id="window-header-title-` + this._componentId + `" class="title"></div>
                    <div id="window-header-close-` + this._componentId + `" class="tool-button tool-button-close" style="float: right;"></div>
                </div>
                <div id="window-content-` + this._componentId + `" class="window-content"></div>
                <div id="window-buttons-` + this._componentId + `" class="window-buttons"></div>
            </div>
        `;
    }

    connectedCallback() {
        const closeButton = this.shadowRoot.querySelector('#window-header-close-'+this._componentId);
        closeButton.addEventListener('click', this._onCloseClick.bind(this));

        const window = this.shadowRoot.querySelector('#window-'+this._componentId);
        window.style.left = ((top.document.body.offsetWidth - window.offsetWidth) / 2) + "px";
        window.style.top = ((top.document.body.offsetHeight - window.offsetHeight) / 2) + "px";
    }

    disconnectedCallback() {
        const closeButton = this.shadowRoot.querySelector('#window-header-close-'+this._componentId);
        closeButton.removeEventListener('click', this._onCloseClick.bind(this));
    }

    _onCloseClick(e) {
        Scl.removeComponent(this, this._componentId);
    }
});