    document.addEventListener('DOMContentLoaded', () => {
    const tirar = document.getElementById('tirar');
    const barraTiro = document.getElementById('barra-tiro');
    let llenarBarra = true;

    function llenar() {
    if (llenarBarra) {
    barraTiro.style.width = '0';
    barraTiro.style.transition = 'none';
    setTimeout(() => {
    barraTiro.style.transition = 'width 5s linear';
    barraTiro.style.width = '100%';
    llenar();
}, 100);
}

    tirar.addEventListener('click', () => {
    llenarBarra =false;
});
    llenar();
});
