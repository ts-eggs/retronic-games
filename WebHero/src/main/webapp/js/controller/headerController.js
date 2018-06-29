function createGame() {
    Sjl.createWindow({
        title: 'window title',
        name: 'window-game',
        items: [{
            type: "selection",
            label: "Select Game",
            name: "select-game"
        }, {
            type: "input",
            label: "Name",
            name: "input-name"
        }, {
            type: "input",
            label: "Difficult",
            name: "input-difficult"
        }],
        buttons: [{
            type: "button",
            text: "Cancel"
        }, {
            type: "button",
            text: "Save"
        }]
    });
}

function login() {

}