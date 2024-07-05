const form = document.getElementById('form');
        const campos = document.querySelectorAll('.required');
        const spans = document.querySelectorAll('.span-required');
        const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        
        function setError(index) {
        campos[index].style.borderBottom = '2px solid red'; 
        spans[index].style.display = 'block';
        
}


        function removeError(index){
            campos[index].style.border = ''; 
            spans[index].style.display = 'none';
        }
        

        function emailValidate(){

            if(!emailRegex.test(campos[0].value)){
                setError(0);

            } else{

                removeError(0);
            }
        }