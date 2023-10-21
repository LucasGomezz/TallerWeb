const Yo = document.querySelector('#puntajeYo');
const Rival = document.querySelector('#puntajeRival');



function ponerPosicion(){
    var posicion = parseInt(document.getElementById("posicion").textContent);
    var divJugadores = document.querySelector(".div-jugadores");
    switch(posicion){
        case 1:
            divJugadores.style.position = "relative";
            divJugadores.style.right = "26em";
            break;
        case 2:
            divJugadores.style.position = "relative";
            divJugadores.style.right = "1em";
            break;
        case 3:
            break;
        case 4:
            break;

    }
}


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
ponerPosicion();
