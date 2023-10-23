document.addEventListener('DOMContentLoaded', () => {
    const progreso = document.getElementById('progreso');
    const boton = document.getElementById('tirar');
    const llenarDuracion = 1000;
    const vaciarDuracion = 1000;
    const anchoInicial = 1;
    const anchoMaximo = 100;
    let pararBarra = false;
    let valorBarra = 1;
    let resultadoBarra = document.getElementById('resultadoBarra');


    function llenarBarra() {
        let startTime = null;
        function animarLlenado(timestamp) {
            if (!pararBarra) {
                if (!startTime) startTime = timestamp;
                const tiempoTranscurrido = timestamp - startTime;
                const porcentajeCompletado = tiempoTranscurrido / llenarDuracion;
                const valorPorcentaje = Math.ceil(anchoInicial + porcentajeCompletado * anchoMaximo);
                progreso.style.width = valorPorcentaje + '%';
                valorBarra = valorPorcentaje;

                if (porcentajeCompletado < 1) {
                    requestAnimationFrame(animarLlenado);
                } else {
                    requestAnimationFrame(vaciarBarra);
                }
            }
        }
        requestAnimationFrame(animarLlenado);
    }

    function vaciarBarra() {
        let startTime = null;
        function animarVaciado(timestamp) {
            if (!pararBarra) {
                if (!startTime) startTime = timestamp;
                const tiempoTranscurrido = timestamp - startTime;
                const porcentajeCompletado = tiempoTranscurrido / vaciarDuracion;
                const valorPorcentaje = Math.floor(anchoMaximo - porcentajeCompletado * anchoMaximo);
                progreso.style.width = valorPorcentaje + '%';
                valorBarra = valorPorcentaje;

                if (porcentajeCompletado < 1) {
                    requestAnimationFrame(animarVaciado);
                } else {
                    requestAnimationFrame(llenarBarra);
                }
            }
        }
        requestAnimationFrame(animarVaciado);
    }

    function tirar() {
        pararBarra = true;
       /** $.ajax({
            url: '/tira',
            method: 'GET',
            data: { valorBarra: valorBarra}
        });
        return false;*/
       resultadoBarra.value = valorBarra;
    }


    boton.addEventListener('click', tirar);

    llenarBarra();
});