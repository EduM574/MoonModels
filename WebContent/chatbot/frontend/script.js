const textInput = document.getElementById('textInput');
const chat = document.getElementById('chat-content');

let context = {};

const templateChatMessage = (message, from) => `
  <div class="from-${from}">
    <div class="message-inner">
      <p>${message}</p>
    </div>
  </div>
  `;

// Crate a Element and append to chat
const InsertTemplateInTheChat = (template) => {
    const div = document.createElement('div');
    div.innerHTML = template;

    chat.appendChild(div);
};

// Calling server and get the watson output
const getWatsonMessageAndInsertTemplate = async(text = '') => {
    const uri = 'http://localhost:3000/conversation/';

    const response = await (await fetch(uri, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            text,
            context,
        }),
    })).json();

    context = response.context;

    const template = templateChatMessage(response.output.text, 'watson');

    InsertTemplateInTheChat(template);
    var objDiv = document.getElementById("chat-content");
    objDiv.scrollTop = objDiv.scrollHeight;
};

textInput.addEventListener('keydown', (event) => {
    if (event.keyCode === 13 && textInput.value) {
        // Send the user message
        getWatsonMessageAndInsertTemplate(textInput.value);

        const template = templateChatMessage(textInput.value, 'user');
        InsertTemplateInTheChat(template);

        // Clear input box for further messages
        textInput.value = '';

        var objDiv = document.getElementById("chat-content");
        objDiv.scrollTop = objDiv.scrollHeight;
    }
});


getWatsonMessageAndInsertTemplate();