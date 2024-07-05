const editButtons = document.querySelectorAll(".editButton");

editButtons.forEach((button) => {
    button.addEventListener("click", () => {
        const tableRowWithButton = button.closest("tr");
        const userData = {
            nameUser: tableRowWithButton.querySelector("td:nth-child(1)").textContent,
            email: tableRowWithButton.querySelector("td:nth-child(2)").textContent,
            tipoUserPermission: tableRowWithButton.querySelector("td:nth-child(3)").textContent,
            id: tableRowWithButton.querySelector(".delete-btn").getAttribute("data-id")
        };

        fillFormWithUserData(userData);
        opemFormEdit(userData);
    });
});

function fillFormWithUserData(userData) {
    const sectionForm = document.getElementById("sectionForm");
    const formInputs = sectionForm.querySelectorAll("input");
    const selectInput = sectionForm.querySelector("#tipoUserPermission");

    formInputs.forEach(field => {
        const fieldName = field.name;
        if (userData.hasOwnProperty(fieldName)) {
            field.value = userData[fieldName];
        }
    });

    selectInput.value = userData.tipoUserPermission;
}

function opemFormEdit(userData) {
    const sectionForm = document.getElementById("sectionForm");
    const buttonAdd = document.getElementById("buttonAdd");
    const titleForm = document.querySelector(".titleForm");
    const formAction = sectionForm.querySelector("form");

    formAction.setAttribute("method","post");
    formAction.setAttribute("action", "/users/updateUser");
    titleForm.textContent = "atualizar usuario " + userData.nameUser;

    sectionForm.style.display = "block";
    buttonAdd.style.display = "none";
}
