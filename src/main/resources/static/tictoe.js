var playerId = null;
var stompClient = null;
var game = null

function updateConnection(message) {
    $('#connection').html(`${message}`)
}

function connectWs() {
    this.updateConnection('conectando...')
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        updateConnection(frame)
        stompClient.subscribe('/user/tictoe/player_info', function (message) {
            console.log(`my player id: ${message.body}`);
            playerId = JSON.parse(message.body);
        });
        stompClient.subscribe('/user/tictoe/game', function (message) {
            render(JSON.parse(message.body))
        });
        stompClient.send('/game/player_login', {}, {});
    });
}

function render(game) {
    this.game = game
    for (const tile in game.tiles) {
        $(`#${tile}`).html(`${getCharacter(game.tiles[tile])}`)
    }
}

function getCharacter(player) {
    if (player === null)
        return ''

    return player['player_id'] === playerId ? 'X' : 'O'
}

function createGame() {
    console.log('create-lobby')
    stompClient.send('/game/create', {}, $('#player2').val())
}

function doAction(position) {
    console.log(position)
    if (playerId !== game.turn) {
        console.warn('não é seu turno malandro!')
        return;
    }

    stompClient.send('/game/player_action', {}, position);
}

$(function () {
    console.log("TicToe - Iniciando")
    connectWs()
    $("#create-lobby").click(function () {
        createGame()
    })
    $(".box").click(function (event) {
        doAction(event.currentTarget.id)
    })
})