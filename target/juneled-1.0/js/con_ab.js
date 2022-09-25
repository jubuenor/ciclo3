let email = new URL(location.href).searchParams.get('email');

$(document).ready(() => {
    $("#user-email").html("  " + email.split('@')[0].toUpperCase());
    getCategorias();
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

function showCategory(id, nombre) {
    document.location.href = `home.html?email=${email}&showCategory=true&id=${id}&nombre=${nombre}`;

}

function searchProductos(input) {
    document.location.href = `home.html?email=${email}&showProducts=true&input=${input}`;
}