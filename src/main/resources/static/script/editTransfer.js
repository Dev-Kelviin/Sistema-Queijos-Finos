

document.addEventListener('DOMContentLoaded', function() {
    const editButtons = document.querySelectorAll(".editButton");
    editButtons.forEach((button) => {
        button.addEventListener("click", function() {
            const transferId = this.closest('tr').querySelector('td:first-child').textContent;
            fetch(`/transfer/edit/${transferId}`)
                .then(response => response.text())
                .then(html => {
                    const parser = new DOMParser();
                    const doc = parser.parseFromString(html, 'text/html');
                    const formSection = doc.querySelector('#sectionForm');
                    document.querySelector('#sectionForm').innerHTML = formSection.innerHTML;
                    openFormEdit();
                });
        });
    });
});

function openFormEdit() {
    const sectionForm = document.getElementById("sectionForm");
    const buttonAdd = document.getElementById("buttonAdd");
    const titleForm = document.querySelector(".titleForm");
    const formAction = sectionForm.querySelector("form");

    const pathname = window.location.pathname;
    const pathnameSplit = pathname.split("/");
    const id = pathnameSplit[2]

    formAction.setAttribute("method", "post");
    formAction.setAttribute("action", "/transfer/updateTransfer/"+id);
    titleForm.textContent = "Atualizar Transferência";

    sectionForm.style.display = "block";
    buttonAdd.style.display = "none";
}
