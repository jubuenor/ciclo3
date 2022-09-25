let usuario;

const currency = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0, minimumFractionDigits: 0 });

$(document).ready(() => {
    getUsuario(email);
    $("#verify-modal").on("hidden.bs.modal",()=>{
        $("#ver-contrasena").val("");
        $("#ver-error").addClass("d-none");
    });

    $("#form-verify").submit((event)=>{
        event.preventDefault();
        usuario.contrasena==$("#ver-contrasena").val()?
        showUpdateModal():
        $("#ver-error").removeClass("d-none");
    });

    $("#form-update").submit((event)=>{
        event.preventDefault();
        let nombre= $("#upd-nombre").val();
        let apellido=$("#upd-apellido").val();
        let email= $("#upd-email").val();
        let contrasena= $("#upd-contrasena").val();
        let saldo= $("#upd-saldo").val();
        if(nombre.length<=2||apellido.length<=2||email.length<=2||contrasena.length<=2){
        $("#upd-err").removeClass("d-none")
        }else {
            updateDatos(nombre,apellido,email,contrasena,saldo);
        }
    });

});

function updateDatos(nombre,apellido,email,contrasena,saldo){
    $.ajax({
        type:"POST",
        dataType:"html",
        url:"./ServletUserUpdate",
        data: $.param({
            id:usuario.id_usuario,
            nombre:nombre,
            apellido:apellido,
            email:email,
            contrasena:contrasena,
            saldo:saldo
        }),
        error:()=>{
            console.log("error");
        },
        success:()=>{
            $("#upd-nombre").val("");
            $("#upd-apellido").val("");
            $("#upd-email").val("");
            $("#upd-contrasena").val("");
            $("#upd-saldo").val("");
            var myModal = document.getElementById('update-modal');
            var modal1 = bootstrap.Modal.getOrCreateInstance(myModal);
            modal1.toggle();
            var modal2 = document.getElementById('succ-upd-modal');
            var moda = bootstrap.Modal.getOrCreateInstance(modal2);
            $('.modal-backdrop').remove();
            moda.toggle();
        }
        
    });
}

function showUpdateModal(){
    var myModal2 = document.getElementById('verify-modal');
    var modal2 = bootstrap.Modal.getOrCreateInstance(myModal2);
    modal2.hide();
    $("#upd-nombre").val(usuario.nombre);
    $("#upd-apellido").val(usuario.apellido);
    $("#upd-email").val(usuario.email);
    $("#upd-contrasena").val(usuario.contrasena);
    $("#upd-saldo").val(usuario.saldo);
    var myModal = document.getElementById('update-modal');
    var modal1 = bootstrap.Modal.getOrCreateInstance(myModal);
    modal1.show();
}

function getUsuario(email){
    $.ajax({
        type:"GET",
        dataType:"html",
        url:"./ServletgetUser",
        data: $.param({
            email:email,
        }),
        success:(result)=>{
            let res=JSON.parse(result);
            usuario=res;
            usuario?
            listData():
            console.log("Error");
        }
    });
}
function formato(palabra){
    return palabra.charAt(0).toUpperCase()+palabra.slice(1).toLowerCase();
}
function listData(){
    let contenido=`
    <div class="mb-3">
        <label>Nombre </label>
        <li class="list-group-item">${formato(usuario.nombre)}</li>
    </div>
    <div class="mb-3">
        <label>Apellido </label>
        <li class="list-group-item">${formato(usuario.apellido)}</li>
    </div>
    <div class="mb-3">
        <label>Email </label>
        <li class="list-group-item">${usuario.email}</li>
    </div>
    <div class="mb-3">
        <label>Saldo </label>
        <li class="list-group-item text-success">${currency.format(usuario.saldo)}</li>
    </div>
    `;
    $("#data-list").html(contenido);

}