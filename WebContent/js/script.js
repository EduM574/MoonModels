function openBot() {
    var btnBot = document.getElementById('button-chatboat-icon');
    var telaBot = document.getElementById('tela-chatboat');

    var visibilidade = telaBot.style.visibility;

    visibilidade == "hidden" ?
        telaBot.style.visibility = 'visible' :
        telaBot.style.visibility = 'hidden';
}

function editarSenhaAluno() {
    var passEditarAluno = document.getElementById("passEditarAluno").value;
    var confEditarAluno = document.getElementById("confEditarAluno").value;

    if (passEditarAluno != confEditarAluno) {
        document.getElementById('errorPassword').innerHTML = "As senhas não correspondem";
        return false;
    } else {
        document.getElementById("editarSenhaAluno").submit();
    }
}

function editarSenhaAdm() {
    var passEditarAdm = document.getElementById("passEditarAdm").value;
    var confEditarAdm = document.getElementById("confEditarAdm").value;

    if (passEditarAdm != confEditarAdm) {
        document.getElementById('errorPassword').innerHTML = "As senhas não correspondem";
        return false;
    } else {
        document.getElementById("editarSenhaAdm").submit();
    }
}