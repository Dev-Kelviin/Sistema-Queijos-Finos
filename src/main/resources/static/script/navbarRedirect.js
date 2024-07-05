
const dashboardLink = document.getElementById("dashboardLink");
const producersLink = document.getElementById("producersLink");
const tecnologiesLink = document.getElementById("tecnologiesLink");
const configurationLink = document.getElementById("configurationLink")

dashboardLink.addEventListener("click", ()=> {
    window.location.replace("/dashboard");
})

producersLink.addEventListener("click", ()=> {
    window.location.replace("/producers");
})

tecnologiesLink.addEventListener("click", ()=>{
    window.location.replace("/tecnologias");
})

configurationLink.addEventListener("click", ()=>{
    window.location.replace("/configuration");
})