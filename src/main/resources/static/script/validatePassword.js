document.getElementById("password").addEventListener("input", () => {
    const password = document.getElementById("password").value;
    const feedbackPassword = document.getElementById("feedbackPassword");

    // Verifica se a senha atende aos critérios e exibe ou oculta o feedback
    const isValid = password.length >= 8 && /[A-Z]/.test(password) && /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(password);
    feedbackPassword.textContent = isValid ? "A senha atende aos requisitos." : "Senha invalida.";
    feedbackPassword.style.color = isValid ? "green" : "red";
});

document.getElementById("confirmPassword").addEventListener("input", () => {
    const passwordOne = document.getElementById("password").value;
    const passwordTwo = document.getElementById("confirmPassword").value;
    const feedbackConfirmPassword = document.getElementById("feedbackConfirmPassword");
    const buttonSave = document.getElementById("buttonSave");

    const isValid = passwordOne !== passwordTwo;

    // Verifica se as senhas coincidem e exibe ou oculta o feedback
    buttonSave.disabled = isValid ? true : false;
    feedbackConfirmPassword.textContent = isValid ? "A confirmação da senha deve ser igual à senha." : "As senhas conferem.";
    feedbackConfirmPassword.style.color = isValid ? "red" : "green";
});
