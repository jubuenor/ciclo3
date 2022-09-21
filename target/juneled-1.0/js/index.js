$(document).ready(function () {
    $("#form-login").submit(function(event){
        event.preventDefault();
        checkUser();
    });

});

function checkUser(){
    let email= $("#email").val();
    let password= $("#password").val();
    
    console.log("aaa");
    $.ajax({
        type:"GET",
        dataType:"html",
        url:"./ServletUserLogin",
        data: $.param({
            email:email,
            password:password
        }),
        success:function(result){
            let res=JSON.parse(result);
            console.log(res);
            if(res){
                console.log("success");
                document.location.href="home.html?email="+res['email'];
            }else{
                $("#login-error").removeClass("d-none");
            }
        },
        error:function(){
            console.log("error");
        }
    });

}