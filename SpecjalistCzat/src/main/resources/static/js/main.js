'use strict';

var loginUserPage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var noConnectedPage = document.querySelector('#notConnectedUser');
var usernameForm = document.querySelector('#usernameForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var userName = null;
var sessionId = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
	userName = document.querySelector('#name').value.trim();

	if (userName) {
		loginUserPage.classList.add('hidden');

		var socket = new SockJS('/ws'); // zmiana na localhost:8080/ws po zmianie na angulara
		stompClient = Stomp.over(socket);

		stompClient.connect({}, onConnected, onError);
	}
	event.preventDefault();
}

function onConnected() {
	
	stompClient.subscribe('/user/queue/response', onMessageReceived);
	
	stompClient.send("/app/chat.addUser", {}, JSON.stringify({
		username : userName		
	}))

	connectingElement.classList.add('hidden');
}

function onError(error) {
	connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
	connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            username: userName,
            content: messageInput.value,
            sessionId: sessionId,
            messageType: 'CHAT'
        };

        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
	var message = JSON.parse(payload.body);
	sessionId = message.sessionId;
	
	if (message.messageType === 'ACCESS') {		
		chatPage.classList.remove('hidden');						
		stompClient.subscribe('/channel/public', onMessageReceivedPublic);		
	} else if (message.messageType === 'BLOCK') {		
		noConnectedPage.classList.remove('hidden');		
	}		
}

function onMessageReceivedPublic(payload) {	
	var message = JSON.parse(payload.body);	
	var messageElement = document.createElement('li');
		
	if (message.messageType === 'CHAT') {
		messageElement.classList.add('chat-message');
		var avatarElement = document.createElement('i');
		var avatarText = document.createTextNode(message.username[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.username);        
        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.username);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);           
        
		var textElement = document.createElement('p');
	    var messageText = document.createTextNode(message.content);
	    textElement.appendChild(messageText);

	    messageElement.appendChild(textElement);
	    messageArea.appendChild(messageElement);
	    messageArea.scrollTop = messageArea.scrollHeight;
	}
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)