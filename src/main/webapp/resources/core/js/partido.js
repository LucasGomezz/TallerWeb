const botonDriblear = document.querySelector('#driblear');
const botonTirar = document.querySelector('#tirar');
const botonPasar = document.querySelector('#pasar');
const Yo = document.querySelector('#puntajeYo');
const Rival = document.querySelector('#puntajeRival');

    botonDriblear.addEventListener('click', () => {
    /**JSON.stringify({parametro: "drible"});*/

    });

    botonTirar.addEventListener('click', () => {

    });

    botonPasar.addEventListener('click', () => {

    });

function sumarPuntos() {
    $(botonTirar).on("click", function () {
        let puntajeYo = $(Yo).html();
        let puntajeRival = $(Rival).html();
        console.log(puntajeYo);
        $.ajax({
            type: "POST",
            data: {
                puntajeYo: puntajeYo,
                puntajeRival: puntajeRival
            },
            url: "/tallerwebi_base_war/sumar",
            success: function (result) {
                $(Yo).html(result.puntajeYo);
                $(Rival).html(result.puntajeRival);
                console.log(result);
            }
        })
    });
    // $("#mirar").on("click", function() {
    //     $.ajax({
    //         type: "GET",
    //         url: "/tallerwebi_base_war/haceAlgo",
    //         success: function(result){
    //             $('#puntajeYo').html(result);
    //             console.log(result);
    //         }
    //     })
    // });
}

sumarPuntos();

