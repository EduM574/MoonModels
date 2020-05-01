const btnBot = document.getElementById('button-chatboat-icon');
const telaBot = document.getElementById('tela-chatboat');

btnBot.addEventListener("click", () => {
    var visibilidade = telaBot.style.visibility;

    if (visibilidade == "hidden")
        telaBot.style.visibility = 'visible';
    else
        telaBot.style.visibility = 'hidden';
});