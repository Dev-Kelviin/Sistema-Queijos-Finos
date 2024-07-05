document.querySelectorAll('.delete-btn').forEach(button => {
    button.addEventListener('click', () => {
        const userId = button.getAttribute('data-id');
        const alertPopupDelet = document.querySelector(".popupAlertDelet");
        const sectionPopup = document.querySelector(".sectionPopup");

        sectionPopup.style.display = "block";
        alertPopupDelet.style.display = "block";

        document.getElementById("buttonPopupAlertDelet").addEventListener("click", () => {
            alertPopupDelet.style.display = "none";
            sectionPopup.style.display = "none";
            deleteUser(userId);
        });
        
    });
});


function deleteUser(userId) {
    
        fetch('/users/' + userId, {
            method: 'DELETE'
        }).then(response => {
            if (response.ok) {
                window.location.reload(); // Atualizar a página após a exclusão
            } else {
                alert('Falha ao excluir o usuário.');
            }
        }).catch(error => {
            console.error('Erro ao excluir o usuário:', error);
            alert('Erro ao excluir o usuário. Por favor, tente novamente mais tarde.');
        });
}