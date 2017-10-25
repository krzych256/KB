'use strict';

var loginUserPage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var reservePage = document.querySelector('#reserve-queue');
var usernameForm = document.querySelector('#usernameForm');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

function connect(event) {
	username = document.querySelector('#name').value.trim();
	
	if(username) {
		loginUserPage.classList.add('hidden');		
		
		var socket = new SockJS('/ws');
		stompClient = Stomp.over(socket);
		 
		stompClient.connect({}, onConnected, onError);
	}
	event.preventDefault();
}

function onConnected() {
	
	// Subscribe to the Public Channel
    stompClient.subscribe('/channel/public', onMessageReceived);
	
	//wyslij dane o swoim imieniu
	stompClient.send("/app/chat.addUser", {}, JSON.stringify({sender: username, type: 'JOIN'}))

	connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function onMessageReceived(payload) {
	var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');
    
	if(message.type === 'JOIN') {
		chatPage.classList.remove('hidden');
		messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'BLOCK') {
    	reservePage.classList.remove('hidden');    	
    }
	
	
}




usernameForm.addEventListener('submit', connect, true)