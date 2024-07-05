const editButtons = document.querySelectorAll(".editButton");

editButtons.forEach((button) => {
    button.addEventListener("click", () => {
        const tableRowWithButton = button.closest("tr");
        const technologyData = {
            id: tableRowWithButton.querySelector(".delete-btn").getAttribute("data-id"),
            name: tableRowWithButton.querySelector("td:nth-child(1)").textContent
           
        };

        fillFormWithTechnologyData(technologyData);
        openFormEdit();
    });
});

function fillFormWithTechnologyData(technologyData) {
    const sectionForm = document.getElementById("sectionForm");
    const formInputs = sectionForm.querySelectorAll("input");

    formInputs.forEach(field => {
        const fieldName = field.name;
        if (technologyData.hasOwnProperty(fieldName)) {
            field.value = technologyData[fieldName];
        }
    });
}

function openFormEdit() {
    const sectionForm = document.getElementById("sectionForm");
    const buttonAdd = document.getElementById("buttonAdd");
    const titleForm = document.querySelector(".titleForm");
    const formAction = sectionForm.querySelector("form");

    formAction.setAttribute("method", "post");
    formAction.setAttribute("action", "/technology/updateTechnology");
    titleForm.textContent = "Atualizar Tecnologia";

    sectionForm.style.display = "block";
    buttonAdd.style.display = "none";
}
