function setCookie(name, value, days) {
    const expires = new Date();
    expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);
    document.cookie = `${name}=${value};expires=${expires.toUTCString()};path=/`;
}

function getCookie(name) {
    const cookies = document.cookie.split(';');
    for (const cookie of cookies) {
        const [cookieName, cookieValue] = cookie.split('=');
        if (cookieName.trim() === name) {
            return cookieValue;
        }
    }
    return null;
}

let puntajeYo1 = parseInt(getCookie('puntajeYo1')) || 0;
let puntajeRival1 = parseInt(getCookie('puntajeRival1')) || 0;

window.onload = function () {
    let puntajeYoString = document.getElementById("puntajeYo").textContent;
    let puntajeRivalString = document.getElementById("puntajeRival").textContent;

    let puntajeRival = Number(puntajeRivalString);
    let puntajeYo = Number(puntajeYoString);

    if (puntajeYo > puntajeYo1) {
        let diferencia = puntajeYo - puntajeYo1;
        if (diferencia === 3) {
            openModal('Metiste un triple!');
        } else if (diferencia === 2) {
            openModal('Metiste  dos puntos!');
        }
    }

    if (puntajeRival > puntajeRival1) {
        let diferencia = puntajeRival - puntajeRival1;
        if (diferencia === 3) {
            openModal('Metio  un triple!');
        } else if (diferencia === 2) {
            openModal('metio dos puntos!');
        }
    }


    setCookie('puntajeYo1', puntajeYo.toString(), 365);
    setCookie('puntajeRival1', puntajeRival.toString(), 365);
};


function openModal(message) {
    let infoModal = document.querySelector('.infoModal');

    infoModal.innerHTML = `
        <span class="close" onclick="closeModal()">&times</span>
        <h2>${message}</h2>
    `;

    document.getElementById('modalOverlay').style.display = 'block';
}

setTimeout(closeModal, 2000);

function closeModal() {
    document.getElementById('modalOverlay').style.display = 'none';
}

let modalOverlay = document.getElementById('modalOverlay2');
let accion = modalOverlay.getAttribute('name');
let tengoPelotaStr = modalOverlay.getAttribute('name2');
let tengoPelota = tengoPelotaStr === "true";
function openModal2(ruta,texto) {
    /**<img src="${ruta}" alt="accion">*/
    modalOverlay.innerHTML = `
        <h2>${texto}</h2>
        <video width="500" height="500" autoplay>
            <source src="images/INTERCEPTAR.mp4" type="video/mp4" >
        </video>
    `;
    modalOverlay.style.display = 'block';
}
function closeModal2() {
    modalOverlay.style.display = 'none';
}
setTimeout(closeModal2, 1500);
let ruta = "";
let texto=" ";

switch (accion) {
    case "driblear":
        ruta = "images/DRIBLEAR.jpg"
        if(tengoPelota){
            texto = "Avanzaste con drible"
        }else{
            texto = "Avanzo con drible"
        }
        openModal2(ruta, texto);
        break;
    case "pasar":
         ruta = "images/PASAR.jpg"
        if(tengoPelota){
            texto = "Avance con pase";
        }else{
            texto = "Avanzo con pase";
        }
        openModal2(ruta, texto);
        break;
    case "interceptar":
        ruta= "images/INTERCEPTAR.jpg"
        if(tengoPelota){
            texto = "Interceptaste la pelota";
        }else{
            texto = "Te intercepto la pelota"
        }
        openModal2(ruta, texto);
        break;
    case "robar":
        ruta= "images/ROBAR.jpg"
        if(tengoPelota){
            texto = "Robaste la pelota";
        }else{
            texto = "Te robo la pelota";
        }
        openModal2(ruta, texto);
        break;
    case "tapar":
        ruta= "images/TAPAR.jpg"
        if(tengoPelota){
            texto = "Metiste un tapon";
        }else{
            texto = "Te metio un tapon";
        }
        openModal2(ruta, texto);
        break;
    case "tirar":
        ruta= "images/TIRAR.jpg"
        openModal2(ruta, texto);
        break;
    default:
        break;
    }






