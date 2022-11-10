function perfilGamer() {

    if (localStorage.getItem("avaliacao_Minecraft") != null) {
        var Minecraft = localStorage.getItem("avaliacao_Minecraft")
        console.log("avaliacao_Minecraft")
        console.log(Minecraft)
    }
    if (localStorage.getItem("avaliacao_CSGO") != null) {
        var CSGO = localStorage.getItem("avaliacao_CSGO")
        console.log("avaliacao_CSGO")
        console.log(CSGO)

    }

    if (localStorage.getItem("avaliacao_Assasins") != null) {
        var Assasins = localStorage.getItem("avaliacao_Assasins")
        console.log("avaliacao_Assasins")
        console.log(Assasins)

    }
    if (localStorage.getItem("avaliacao_Valorant") != null) {
        var Valorant = localStorage.getItem("avaliacao_Valorant")
        console.log("avaliacao_Valorant")
        console.log(Valorant)

    }

    //Perfil Game Indisponível
    if (localStorage.getItem("avaliacao_Valorant") == null || localStorage.getItem("avaliacao_Assasins") == null || localStorage.getItem("avaliacao_CSGO") == null || localStorage.getItem("avaliacao_Minecraft") == null) {
        document.getElementsByClassName("row perfil")[0].innerHTML = '<div class="col d-flex justify-content-center"> <div class=" card-perfil" style="padding: 30px; margin-top: 60px; padding-left: 110px; padding-right: 110px;"onclick="fechaPerfil()"><p style="text-align: center; font-size: 30px;">Pefil Gamer</p> <p style="text-align: center; padding-top: 15px;"> Indisponível &#10060;</p> <p style="text-align: center; padding-top: 10px;"> Tente acabar ou refazer a avaliação dos jogos...</p></div> </div>'
    }

    //Perfil Gamer Incompatível
    if (CSGO == 0 && Valorant == 0 && Assasins == 0 && Minecraft == 0) {
        document.getElementsByClassName("row perfil")[0].innerHTML = '<div class="col d-flex justify-content-center"><div class=" card-perfil" style="padding: 30px; margin-top: 60px; padding-left: 110px;padding-right: 110px;"onclick="fechaPerfil()"><p style="text-align: center; font-size: 30px;">Pefil Gamer</p> <p style="text-align: center; padding-top: 15px;"> Incompatível &#128546;</p> </div> </div>'
    }

    //Perfil Gamer Soldado
    if (CSGO + Valorant > Assasins + Minecraft) {
        document.getElementsByClassName("row perfil")[0].innerHTML = '<div class="col d-flex justify-content-center"><div class=" card-perfil" style="padding: 30px; margin-top: 60px; padding-left: 110px; padding-right: 110px;"onclick="fechaPerfil()"><p style="text-align: center; font-size: 30px;">Pefil Gamer</p> <p style="text-align: center; padding-top: 15px;"> Soldado &#128299;</p> <p> Gosta de FPS, ação e adrenalina!<p></div> </div>'
    }

    //Perfil Gamer de Aventureiro 
    if (Minecraft + Assasins > Valorant + CSGO) {
        document.getElementsByClassName("row perfil")[0].innerHTML = '<div class="col d-flex justify-content-center"><div class=" card-perfil" style="padding: 30px; margin-top: 60px; padding-left: 110px;padding-right: 110px;"onclick="fechaPerfil()"><p style="text-align: center; font-size: 30px;">Pefil Gamer</p> <p style="text-align: center; padding-top: 15px;"> Aventureiro 🤠</p> <p> Prefere explorar, jogos longos e com boas histórias!<p></div> </div>'
    }

    //Perfil Gamer de Diversificado
    if (Minecraft == 5 && CSGO == 5 && Valorant == 5 && Assasins == 5 || Minecraft > 3 && Minecraft == CSGO && CSGO == Valorant && Valorant == Assasins) {
        document.getElementsByClassName("row perfil")[0].innerHTML = '<div class="col d-flex justify-content-center"><div class=" card-perfil" style="padding: 30px; margin-top: 60px; padding-left: 110px;padding-right: 110px;"onclick="fechaPerfil()"><p style="text-align: center; font-size: 30px;">Pefil Gamer</p> <p style="text-align: center; padding-top: 15px;"> Diversificado &#128541</p> </div> </div>'
    }

}
function fechaPerfil() {

    document.getElementsByClassName("row perfil")[0].innerHTML = '<div class="col d-flex justify-content-center"><div class=" card-perfil" style = "padding: 30px; margin-top: 60px; padding-left: 110px; padding-right: 110px;"onclick = "perfilGamer()"><p style="text-align: center; font-size: 30px;">Pefil Gamer</p></div></div >'

}