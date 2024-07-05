    const pathname = window.location.pathname;

    console.log("aaaaaaaaaa")
    const pathnameSplit = pathname.split("/");
    const id = pathnameSplit[2]

    const redirectUrlDocuments = "/documents/" + id;
    const redirectUrlTransference = "/transfer/" + id;
    const redirectUrlEdit = "/editProducer/" + id;

    const documentsButton = document.querySelector("#documentsButton");
    const transferenceButton = document.querySelector("#transferenceButton");
    const editButton = document.querySelector("#editButton");

    documentsButton.addEventListener("click", ()=> {
        console.log("aaaaa")
        window.location.replace(redirectUrlDocuments)
    })

    editButton.addEventListener("click", ()=>{
            console.log("aaaaa")
        window.location.replace(redirectUrlEdit)
    })

    transferenceButton.addEventListener("click", ()=>{
            console.log("aaaaa")
        window.location.replace(redirectUrlTransference)
    })