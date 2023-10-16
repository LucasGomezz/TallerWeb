let imgLoc1 = document.querySelector(".jug-loc-1");
let stats = document.createElement("div");
let statsVisible = false;

    imgLoc1.addEventListener("click", function () {
        if (statsVisible) {
            stats.style.display = "none";
            statsVisible = false;
        } else {
            stats.innerText = "Tiro: 12, Pase: 45";
            stats.style.display = "block";
            imgLoc1.appendChild(stats);
            statsVisible = true;
        }
    });
