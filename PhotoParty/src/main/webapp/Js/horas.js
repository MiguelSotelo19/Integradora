
const horas_total = [
    "","00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
    "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30",
    "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
    "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
    "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
    "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"
];

function addElement(){
    let options = document.querySelectorAll('#hora_inicio option');
    options.forEach(o => o.remove());

     let disponibles = [
         "","00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
         "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30",
         "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
         "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
         "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
         "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"
     ];
    let horas=[];
    let horas_fin=[];
    let fecha_ev= document.getElementById("fecha_ev").value;;

    const url = "/horas";
    fetch(url)
        .then(response => response.json())
        .then(data => {
            for (let x=0; x<data.length; x++){
                if(fecha_ev==data[x].fecha){
                    horas.push(data[x].hrs_inicio);
                    horas_fin.push(data[x].hrs_final);
                }
            }

            if(horas != '' && horas_fin != ''){
                for(let i=0; i<horas_total.length; i++){
                    if(i<horas.length){
                        let inicio = parseFloat(horas[i].charAt(0)+horas[i].charAt(1)+"."+horas[i].charAt(3)+horas[i].charAt(4));
                        let fin = parseFloat(horas_fin[i].charAt(0)+horas_fin[i].charAt(1)+"."+horas_fin[i].charAt(3)+horas_fin[i].charAt(4));

                        for(let j=0; j<disponibles.length; j++){
                            let disponible = parseFloat(disponibles[j].charAt(0)+disponibles[j].charAt(1)+"."+disponibles[j].charAt(3)+disponibles[j].charAt(4));
                            let aux= Math.round((fin-inicio)*2+1);

                            if(disponible==inicio){
                                disponibles.splice(j, aux);
                            }
                        }
                    }
                }
            }

            for(let i=0; i<disponibles.length; i++){
                let aux = document.getElementById("hora_inicio");

                let newContent = document.createElement("option");
                newContent.setAttribute("value", disponibles[i]);
                newContent.innerHTML=disponibles[i];

                aux.insertBefore(newContent, aux.firstElementChild);
            }

        })
        .catch(err=> console.log(err))
}




function addHora() {
    fetch("/horas")
        .then(response => response.json())
        .then(data => {
            document.getElementById("hora_final").innerHTML="";
            let fecha_ev= document.getElementById("fecha_ev").value;

            //Creacion de un option vacio
            let Div = document.getElementById("hora_final");
            let probContent = document.createElement("option");
            probContent.setAttribute("value", "");
            Div.insertBefore(probContent, Div.firstElementChild);

            let start = [document.getElementById("hora_inicio").value];
            let available = [
                "","00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
                "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30",
                "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
                "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"
            ];

            for(let x=0; x<available.length; x++){
                for (let z = 0; z < data.length; z++) {
                    if(fecha_ev==data[z].fecha) {
                        if (available[x] == data[z].hrs_inicio) {
                            available.splice(x, 1);
                            while(available[x] != data[z].hrs_final){
                                available.splice(x, 1);
                            }
                        }
                        if (available[x] == data[z].hrs_final) {
                            available.splice(x, 1);
                        }
                    }
                }
            }

            available.push("");
            let available2=[], a=0, b=0;

            let start2 = parseFloat(start[0].charAt(0)+start[0].charAt(1));
            let start3 = 0;

            for(let l=0; l<available.length; l++){
                let available3 = parseFloat(available[l].charAt(0)+available[l].charAt(1));

                while(start3<start2){
                    start3+=.5;
                }

                if(start[0].charAt(3)=="3" && a==0 && start2!=0){
                    start3-=.5;
                    l+=1;
                    a++;
                } else if(a==0 && start2!=0){
                    start3-=.5;
                    l+=1;
                    a++;
                } else if(a==0 && start2==0 && start[0].charAt(3)=="0"){
                    start3+=.5;
                    a++;
                }

                if(start3==available3 && start2!=0 && b==0 && start[0].charAt(3)=="0"){
                    start3+=.5;
                    l+=1;
                    b++;
                } else if(start3==available3 && start2!=0 && b==0 && start[0].charAt(3)=="3") {
                    start3+=.5;
                    l+=1;
                    b++;
                    if(available2=='' && ((parseFloat(available[l-1].charAt(0)+available[l-1].charAt(1))) < parseFloat(available[l].charAt(0)+available[l].charAt(1))  )){
                        l-=1;
                    }
                }

                let prueba = parseFloat(available[l].charAt(0)+available[l].charAt(1));
                if(prueba > available3){
                    start3-=1;
                }

                if(b==1 && start2!=0 && start[0].charAt(3)=="3"){
                    start3+=.5;
                    l+=1;
                    b++;
                }

                if(start2==0 && start[0].charAt(3)=="3" && b==0){
                    start3+=1;
                    l+=2;
                    b++;
                }

                if(start2!=0 && start[0].charAt(3)=="3" && (available3+1<parseFloat(available[l].charAt(0)+available[l].charAt(1)) && available3+2<parseFloat(available[l+2].charAt(0)+available[l+2].charAt(1)))){
                    break;
                }

                if(start3>=start2){
                    if(start2<=available3){
                        if(start2==available3 || (Math.floor(start3))==(available3)){
                            if(a==1 && start2==0){
                                start3-=.5;
                                a++;
                            }else {
                                available2.push(available[l]);
                            }
                        }
                        start3+=.5;
                    }
                }

            }

            for(let i=0; i<available2.length; i++){
                let aux = document.getElementById("hora_final");

                let newContent = document.createElement("option");
                newContent.setAttribute("value", available2[i]);
                newContent.innerHTML=available2[i];

                aux.insertBefore(newContent, aux.firstElementChild);
            }
        })
        .catch(err=> console.log(err));
}