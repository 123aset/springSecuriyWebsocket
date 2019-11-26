var stompClient = null;
var header = {
    'Authorization': 'Bearer_eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU3NDc2NzkwMiwiaWF0IjoxNTc0NzQ2MzAyfQ.0Mz7ixaM0GmqUeurhGEhbrJ0jBX1MQZRqEXzQGXunqUox2_kt17VjWdhTJWphP2GXAluBGb_zhYHFzMCdo1nsA'
};
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    var login = $("#login").val();

    stompClient.connect(header, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/greetings/' + login, function (greeting) {
            showGreeting(JSON.parse(greeting.body).name);
        });

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName(login) {
    var name = $("#name").val();
    console.log(name);
    stompClient.send("/app/hello/" + login, header, JSON.stringify({'name':name }));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    // setInterval(sendName, 1000);

    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        var login = $("#login").val();
        sendName(login);
    });
});

