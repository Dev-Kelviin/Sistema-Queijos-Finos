const openFormButton = document.getElementById("buttonAdd");
const closeFormButton = document.getElementById("buttonCancel");
const sectionForm = document.getElementById("sectionForm");

console.log("aaa");
console.log(closeFormButton)

const pathname1 = window.location.pathname;
const pathnameSplit1 = pathname1.split("/");
const id1 = pathnameSplit1[2]

const formValues = {
    technology: {
        action: "/tecnologias/cadastrar",
        method: "post",
        title: "Nova tecnologia"
    },
    transfer: {
        action: "/transfer/register/"+id1,
        method: "post",
        title: "Transferencia"
    },
    user: {
        action: "/users/register",
        method: "post",
        title: "Novo usuário"
    },
    Document: {
        action: "/documents/register/"+id1,
        method: "post",
        title: "Novo Documento"
    }
};

closeFormButton.addEventListener("click", () => {
    console.log("aaaaaa")
    const sectionForm = document.getElementById("sectionForm");
    const alertPopupForm = document.getElementById("popupAlertForm");
    const sectionPopup = document.querySelector(".sectionPopup");

    sectionPopup.style.display = "block";

    if(alertPopupForm){
        alertPopupForm.style.display = "block";
    }

    const exitButton = document.getElementById("buttonPopupAlertExit").addEventListener("click", () => {
        sectionForm.style.display = "none";
        openFormButton.style.display = "block";
        alertPopupForm.style.display = "none";
        clearFormFields();
    });
});

openFormButton.addEventListener("click", () => {
    const page = openFormButton.getAttribute("data-page");
    const formValue = formValues[page] || formValues.user;
    opemForm(formValue);
});

function opemForm(formValues){
    const formAction = sectionForm.querySelector("form");

    formAction.setAttribute("method", formValues.method);
    formAction.setAttribute("action", formValues.action);
    sectionForm.querySelector(".titleForm").textContent = formValues.title;

    sectionForm.style.display = "block";
    openFormButton.style.display = "none";
}

function clearFormFields() {
    const formInputs = document.querySelectorAll("#sectionForm input");

    formInputs.forEach(input => {
        input.value = "";
    });
}