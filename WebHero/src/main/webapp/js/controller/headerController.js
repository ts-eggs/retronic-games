function createGame() {
    Sjl.createWindow({
        title: 'window title',
        items: [{
            type: "container",
            layout: "horizontal",
            items: [{
                name: "label-select-game",
                class: "label",
                text: "Select Game"
            },{
                type: "selection",
                name: "select-game"
            }]
        }]
    });
}

function login() {

}