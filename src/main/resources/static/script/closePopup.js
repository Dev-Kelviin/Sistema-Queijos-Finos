
function closePopups(){

    const sectionPopup = document.querySelector(".sectionPopupSave");
    const popupForm = document.getElementById("popupAlertForm");
    const popupDelet = document.querySelector(".popupAlertDelet");

    console.log(sectionPopup, popupForm, popupDelet)

    sectionPopup.style.display = "none";
    if(popupForm){
        popupForm.style.display = "none";

    }
    popupDelet.style.display = "none";


}

    



