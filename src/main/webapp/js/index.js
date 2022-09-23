$(document).ready(()=>{
    
    $("#form-login").submit((event)=>{
        event.preventDefault();
        checkUser();
    });

    $("#form-register").submit((event)=>{
        
        let nombre= $("#reg-nombre").val();
        let apellido=$("#reg-apellido").val();
        let email= $("#reg-email").val();
        let contrasena= $("#reg-contrasena").val();
        event.preventDefault();
        if(nombre.length<=2||apellido.length<=2||email.length<=2||contrasena.length<=2){
        $("#reg-err").removeClass("d-none")
        }else {
            registerUser(nombre,apellido,email,contrasena);
        }
        
    });
});


function checkUser(){
    let email= $("#email").val();
    let password= $("#password").val();
    
    $.ajax({
        type:"GET",
        dataType:"html",
        url:"./ServletUserLogin",
        data: $.param({
            email:email,
            password:password
        }),
        success:(result)=>{
            let res=JSON.parse(result);
            res?
            document.location.href="home.html?email="+res.email
            :$("#login-error").removeClass("d-none");
        }

    });
}

function registerUser(nombre,apellido,email,contrasena){

    $.ajax({
        type:"POST",
        dataType:"html",
        url:"./ServletUserRegister",
        data: $.param({
            nombre:nombre,
            apellido:apellido,
            email:email,
            contrasena:contrasena,
            saldo:"50000.0"
        }),
        error:()=>{
            console.log("error");
        },
        complete:()=>{
            $("#reg-nombre").val("");
            $("#reg-apellido").val("");
            $("#reg-email").val("");
            $("#reg-contrasena").val("");
            var myModal = document.getElementById('register-modal');
            var modal1 = bootstrap.Modal.getOrCreateInstance(myModal);
            modal1.toggle();
            var modal2 = document.getElementById('succ-regi-modal');
            var moda = bootstrap.Modal.getOrCreateInstance(modal2);
            $('.modal-backdrop').remove();
            moda.toggle();
        }
        
    });

}