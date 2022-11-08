function onLoad() {
    let space = document.createElement("div");
    space.setAttribute("class","left");
    space.innerHTML="''''''''''''''" ;
    space.setAttribute("class","left");
    let res_elm = document.createElement("div");
    res_elm.setAttribute("class","left");
    res_elm.innerHTML="Olá, sou o Mário, como posso te ajudar?" ;
    res_elm.setAttribute("class","left");
    let nums = document.createElement("div");
    nums.setAttribute("class","left");
    nums.innerHTML="Digite 1 receber a explicação de como faz o cadastro. <br><br>Digite 2 para receber a explicação de criar playlist" ;
    nums.setAttribute("class","left");
    
 
    document.getElementById('msg_bot').appendChild(space);
    document.getElementById('msg_bot').appendChild(res_elm);
    document.getElementById('msg_bot').appendChild(nums);
}
 
 
document.getElementById('reply').addEventListener("click",(e) => {
    e.preventDefault();
 
    var req = document.getElementById('msg_send').value ;
    var res = "";
    if (req == undefined || req== "") {
 
    }
    else{
           
        let data_req = document.createElement('div');
        let data_res = document.createElement('div');
 
        let container1 = document.createElement('div');
        let container2 = document.createElement('div');
 
        container1.setAttribute("class","msgCon1");
        container2.setAttribute("class","msgCon2");

        if(req === '1'){
            res = "Para se cadastrar no nosso site, é necessário pressionar o botão 'cadastro' no cabeçalho e colocar seu usuário e email";
            data_req.innerHTML = req ;
            data_res.innerHTML = res ;
     
     
            data_req.setAttribute("class","right");
            data_res.setAttribute("class","left");
     
            let message = document.getElementById('msg_bot');
     
             
            message.appendChild(container1);
            message.appendChild(container2);
     
            container1.appendChild(data_req);
            container2.appendChild(data_res);
     
            document.getElementById('msg_send').value = "";
        } else if(req === '2'){
            res = "Para fazer uma playlist, basta pressionar o botão 'gamerlist' no cabeçalho e inserir seus jogos preferidos!";
            data_req.innerHTML = req ;
            data_res.innerHTML = res ;
     
     
            data_req.setAttribute("class","right");
            data_res.setAttribute("class","left");
     
            let message = document.getElementById('msg_bot');
     
             
            message.appendChild(container1);
            message.appendChild(container2);
     
            container1.appendChild(data_req);
            container2.appendChild(data_res);
     
            document.getElementById('msg_send').value = "";
        } else if(req === '3'){
            res = "mensagem 3";
            data_req.innerHTML = req ;
            data_res.innerHTML = res ;
     
     
            data_req.setAttribute("class","right");
            data_res.setAttribute("class","left");
     
            let message = document.getElementById('msg_bot');
     
             
            message.appendChild(container1);
            message.appendChild(container2);
     
            container1.appendChild(data_req);
            container2.appendChild(data_res);
     
            document.getElementById('msg_send').value = "";
        } else{
            res = "Digite 1, 2 ou 3";
            data_req.innerHTML = req ;
            data_res.innerHTML = res ;
     
     
            data_req.setAttribute("class","right");
            data_res.setAttribute("class","left");
     
            let message = document.getElementById('msg_bot');
     
             
            message.appendChild(container1);
            message.appendChild(container2);
     
            container1.appendChild(data_req);
            container2.appendChild(data_res);
     
            document.getElementById('msg_send').value = "";
        }
 
 
    function scroll() {
        var scrollMsg = document.getElementById('msg_bot')
        scrollMsg.scrollTop = scrollMsg.scrollHeight ;
    }
    scroll();
 
    }
 
 
    });