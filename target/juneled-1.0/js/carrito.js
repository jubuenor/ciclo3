let usuario;
let productos;
let ventas;
let valor_total=0;
const currency = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0, minimumFractionDigits: 0 });

$(document).ready(() => {
    getProductos();
    getUsuario(email);

    $("#btn_comprar").click(()=>{
        console.log(valor_total)
        valor_total>usuario.saldo?
        $("#saldo-err").removeClass("d-none"):
        comprar();

    });
    
});

function comprar(){
    $("#saldo-err").addClass("d-none");
    usuario.saldo=usuario.saldo-valor_total;
    $("#saldo_act").html(currency.format(usuario.saldo));

    $.ajax({
        type:"POST",
        dataType:"html",
        url:"./ServletUserUpdate",
        data: $.param({
            id:usuario.id_usuario,
            nombre:usuario.nombre,
            apellido:usuario.apellido,
            email:usuario.email,
            contrasena:usuario.contrasena,
            saldo:usuario.saldo
        }),
        error:()=>{
            console.log("error");
        },
        success:()=>{
            var myModal = document.getElementById('msg-venta');
            var modal1 = bootstrap.Modal.getOrCreateInstance(myModal);
            modal1.toggle();
        }
        
    });
}

function getVentas(id_usuario){
    $.ajax({
        type:"GET",
        dataType:"html",
        url:"./ServletgetVentas",
        data: $.param({
            id_usuario:id_usuario,
        }),
        success:(result)=>{
            let res=JSON.parse(result);
            ventas=res;
            ventas?
            listVentas():
            console.log("Error");
        }
    });
}

function listVentas(){
    
    $("#saldo_act").html(currency.format(usuario.saldo));
    let contenido="";

    ventas.forEach((venta)=>{
        let producto=productos.filter((product)=>product.id_producto==venta.id_producto)[0];
        updateValor(producto.valor);
        contenido+=`
        <div class="row mb-3">
            <li id="${venta.id_venta}" class="list-group-item">
                <div class="row">
                    <div class="col-xs-1 col-md-6 col-lg-2">
                        <img src="./Public/Images/Productos/${producto.id_producto}.jpg" alt="Producto" width="100" height="100">
                    </div>
                    <div class="col-xs-12 col-md-6 col-lg-7 mt-3">
                        <h5 class="card-title">${producto.nombre_producto}</h5>
                        <p class="card-text">${producto.descripcion}</p>
                    </div>
                    <div class="col-xs-12 col-md-4 col-lg-1 mt-3">
                        <label for="cantidad"> Cantidad </label>
                        <input id="cant-${producto.id_producto}" onChange="changeValor(${producto.id_producto},${producto.valor})" class="ms-2" value="1" type="number" min="1"style="width: 50px;">
                    </div>
                    <div class="col-xs-6 col-md-4 col-lg-1 mt-4">
                        <h5 id="val-${producto.id_producto}" class="card-subtitle mb-2">${currency.format(producto.valor)}</h5>
                    </div> 
                    <div class="col-xs-6 col-md-4 col-lg-1 mt-4">
                        <button onClick="remove(${venta.id_venta},${producto.valor})" type="button" class="btn rounded-circle">
                            <span class="bi bi-x-circle"></span>
                        </button>
                    </div> 
                </div>
            </li>
        </div>
        `;
    });
    showValor_total();
    $("#ventas-list").html(contenido);
}

function showValor_total(){
    $("#valor_total").html(currency.format(valor_total));
}

function updateValor(valor){
    valor_total+=valor;
}

function changeValor(id,valor){
    let cant=$(`#cant-${id}`).val();
    var number = Number($(`#val-${id}`).html().replace(/[^0-9.-]+/g,""));
    valor_total+=cant*valor-number;
    $(`#val-${id}`).html(currency.format(cant*valor));
    showValor_total();
}

function remove(id_venta,valor){
    valor_total-=valor;
    $(`#${id_venta}`).remove();
    showValor_total();
    $.ajax({
        type:"POST",
        dataType:"html",
        url:"./ServletremoveVenta",
        data: $.param({
            id_venta:id_venta,
        }),
        success:(result)=>{
            console.log(result);
        }
    });
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
            getVentas(usuario.id_usuario):
            console.log("Error");
        }
    });
}

function getProductos() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletListProducts",
        success: (result) => {
            let res=JSON.parse(result);
            productos=res;
        }
    });
}
