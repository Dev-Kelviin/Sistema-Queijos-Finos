document.querySelectorAll('.download-btn').forEach(button => {
    button.addEventListener('click', () => {
        const documentId = button.getAttribute('data-id');
        downloadDocument(documentId);
    });
});

function downloadDocument(documentId) {
    var downloadUrl = 'http://localhost:8080/Documentos/download/' + documentId;
    window.location.href = downloadUrl;
}

