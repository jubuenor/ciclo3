let email = new URL(location.href).searchParams.get('email');
let showC = new URL(location.href).searchParams.get('showCategory');
let showP = new URL(location.href).searchParams.get('showProducts');
let usuario;
let productos;
let categorias;
const currency = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0, minimumFractionDigits: 0 });

getProductos();
getCategorias();
getUsuario(email);

$(document).ready(() => {
    $("#user-email").html("  " + email.split('@')[0].toUpperCase());
    if (showC) {
        let id = new URL(location.href).searchParams.get('id');
        let nombre = new URL(location.href).searchParams.get('nombre');
        showCategory(id, nombre);
    }
    if (showP) {
        let input = new URL(location.href).searchParams.get('input');
        searchProductos(input);
    }
    $("#btn-search").click(() => {
        let input = $("#input-search").val();
        if (input != "") { searchProductos(input); }
    });
    $("#nav-img-home").click(() => { document.location.href = "home.html?email=" + email });
    $("#nav-about").click(() => { document.location.href = "sobreNosotros.html?email=" + email });
    $("#nav-contact").click(() => { document.location.href = "contacto.html?email=" + email });
    $("#nav-home").click(() => { document.location.href = "home.html?email=" + email });
    $("#nav-profile").click(() => { document.location.href = "perfil.html?email=" + email });
    $("#foot-about").click(() => { document.location.href = "sobreNosotros.html?email=" + email });
    $("#foot-contact").click(() => { document.location.href = "contacto.html?email=" + email });
    $("#nav-carrito").click(() => { document.location.href = "carrito.html?email=" + email });

});

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
        }
    });
}

function searchProductos(input) {
    $("#input-search").val("");
    let pro = productos.filter((product) => product.nombre_producto.toUpperCase().includes(input.toUpperCase()));
    listProductos(pro);
}

function getProductos() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletListProducts",
        success: (result) => {
            productos = JSON.parse(result);
            productos ? listProductos(productos) : console.log("Error al recuperar productos");
        }
    });
}

function getCategorias() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletCategorias",
        success: (result) => {
            categorias = JSON.parse(result);
            categorias ? listCategorias(categorias) : console.log("Error al recuperar categorias");
        }
    });
}

function showCategory(id, nombre) {
    $('html, body').animate({
        scrollTop: 0
    }, 100);
    let cat_productos = productos.filter((producto) => producto.id_categoria == id);
    listProductos(cat_productos);
    $("#cat-breadcrumb").html(nombre);

}

function listCategorias(categorias) {
    let contenido = "";
    let contenido2 = `<p class="h5 mb-3 text-light"> Categorías </p>`;
    $.each(categorias, (index, category) => {
        contenido += `
        <li><a id="cat-${category.nombre_categoria}" class="btn dropdown-item" onClick="showCategory(${category.id_categoria},'${category.nombre_categoria}')">${category.nombre_categoria}</a></li>
        `;
        contenido2 += `
        <div class="mb-2 ms-2">
            <a id="cat-${category.nombre_categoria}" class="btn text-secondary text-decoration-none" onClick="showCategory(${category.id_categoria},'${category.nombre_categoria}')">${category.nombre_categoria}</a>
        </div>
        `;
    });
    $("#dropdown-category").html(contenido);
    $("#footer-category").html(contenido2);
}

function listProductos(productos) {
    let contenido = "";
    $.each(productos, (index, product) => {
        contenido += `
        <div class="card shadow p-3 mb-5 bg-body rounded" style="width: 18rem;">
            <button class="position-absolute btn" onClick="addVenta(${product.id_producto})">
                <span class="bi bi-bag-plus"></span>
            </button>
            <img src="./Public/Images/Productos/${product.id_producto}.jpg" class="card-img-top" alt="Producto" width="254" height="254">
            <div class="card-body">
              <h5 class="card-title">${product.nombre_producto}</h5>
              <p class="card-text">${product.descripcion}</p>
              <h5 class="card-subtitle mb-2">${currency.format(product.valor)}</h5>
              <a href="#" class="btn btn-success">Comprar</a>
            </div>
        </div>`;
    });
    $("#product-container").html(contenido);
}

function addVenta(id_producto){
    console.log(id_producto);
    let fecha = new Date();

    $.ajax({
        type:"POST",
        dataType:"html",
        url:"./ServletaddVenta",
        data: $.param({
            id_usuario:usuario.id_usuario,
            id_producto:id_producto,
            cantidad:"1",
            valor_total:`${productos.filter((product)=>product.id_producto==id_producto)[0].valor}`,
            fecha: `${fecha.toUTCString()}`,
        }),
        error:()=>{
            console.log("error");
        },
        success:()=>{
            var myModal = document.getElementById('add-venta-msg');
            var modal1 = bootstrap.Modal.getOrCreateInstance(myModal);
            modal1.show();
        }
    });
}
