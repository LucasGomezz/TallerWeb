document.addEventListener("DOMContentLoaded", function () {
    var botonEquipo1 = document.getElementById("idEquipo");
    var botonEquipo2 = document.getElementById("idEquipo2");


    botonEquipo1.addEventListener("click", function (event) {
        if (botonEquipo1.value === botonEquipo2.value) {
            event.preventDefault();
        }
    });

    botonEquipo2.addEventListener("click", function (event) {
        if (botonEquipo1.value === botonEquipo2.value) {
            event.preventDefault();
        }
    });
});



