document.querySelectorAll('.delete-btn').forEach(button => {
    button.addEventListener('click', () => {
        const documentId = button.getAttribute('data-id');
        const alertPopupDelet = document.querySelector(".popupAlertDelet");
        const sectionPopup = document.querySelector(".sectionPopup");

        sectionPopup.style.display = "block";
        alertPopupDelet.style.display = "block";

        document.getElementById("buttonPopupAlertDelet").addEventListener("click", () => {
            alertPopupDelet.style.display = "none";
            sectionPopup.style.display = "none";
            deleteDocument(documentId);
        });
    });
});

function deleteDocument(documentId) {
    fetch('/Documentos/' + documentId, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            window.location.reload(); // Atualizar a página após a exclusão
        } else {
            alert('Falha ao excluir o documento.');
        }
    }).catch(error => {
        console.error('Erro ao excluir o documento:', error);
        alert('Erro ao excluir o documento. Por favor, tente novamente mais tarde.');
    });
}
