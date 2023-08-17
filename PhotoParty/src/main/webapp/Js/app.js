let calendarEl = document.getElementById('calendar');
let frm = document.getElementById('formulario');
let eliminar = document.getElementById('btnEliminar');
let myModal = new bootstrap.Modal(document.getElementById('formModal'));

const horas_total = [
    "","00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
    "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30",
    "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
    "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
    "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
    "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"
];

document.addEventListener('DOMContentLoaded', function () {
    calendar = new FullCalendar.Calendar(calendarEl, {
        timeZone: 'local',
        initialView: 'dayGridMonth',
        locale: 'es',
        headerToolbar: {
            start: 'prev,next today', // Botones antes del título
            center: 'title',
            end: 'dayGridMonth,timeGridWeek,listWeek'
        },
        events: '/evento/listar',
        editable: true,


        dateClick: function (info) {
            frm.reset();
            eliminar.classList.add('d-none');
            document.getElementById('start').value = info.dateStr;
            document.getElementById('id').value = '';
            document.getElementById('btnAccion').textContent = 'Registrar';
            document.getElementById('titulo').textContent = 'Registrar Evento';
            myModal.show();
            addElementAgenda();
            document.getElementById("hora_final").innerHTML="";
        },

        eventClick: function (info) {
            addHoraAgenda();
            document.getElementById('id').value= info.event.id;
            document.getElementById('start').value = info.event.startStr ;
            document.getElementById('color').value = info.event.backgroundColor;

            addElementAgenda();
            let aux = document.getElementById("hora_inicio");
            let newContent = document.createElement("option");
            newContent.setAttribute("value", info.event.extendedProps.hora_inicio);
            newContent.innerHTML=info.event.extendedProps.hora_inicio;
            aux.insertBefore(newContent, aux.firstElementChild);

            document.getElementById("hora_final").innerHTML="";
            let aux2 = document.getElementById("hora_final");
            let newContent2 = document.createElement("option");
            newContent2.setAttribute("value", info.event.extendedProps.hora_final);
            newContent2.innerHTML=info.event.extendedProps.hora_final;
            aux2.insertBefore(newContent2, aux2.firstElementChild);

            document.getElementById('telefono').value= info.event.extendedProps.telefono;
            document.getElementById('direccion').value= info.event.extendedProps.direccion;
            document.getElementById('total').value= info.event.extendedProps.total;
            document.getElementById('hora_extra').value= info.event.extendedProps.hora_extra;
            document.getElementById('nombre').value= info.event.title;
            document.getElementById('segnombre').value= info.event.extendedProps.segnombre;
            document.getElementById('correo').value= info.event.extendedProps.correo;
            document.getElementById('cabina').value= info.event.extendedProps.cabina;
            document.getElementById('ape_p').value= info.event.extendedProps.ape_p;
            document.getElementById('ape_m').value= info.event.extendedProps.ape_m;
            document.getElementById('btnAccion').textContent = 'Modificar';
            document.getElementById('titulo').textContent = 'Actualizar Evento';
            eliminar.classList.remove('d-none');
            myModal.show();
        },


        eventDrop: function (info) {
            const start = info.event.startStr;
            const id = info.event.id;
            const hora_inicio = info.event.extendedProps.hora_inicio;
            const hora_final = info.event.extendedProps.hora_final;

            // Mostrar mensaje de advertencia utilizando SweetAlert
            Swal.fire({
                title: 'Advertencia',
                text: "¿Estás seguro de modificar?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí, Quiero modificarlo!',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed == true) {
                    const data = { id, start, hora_inicio, hora_final };

                    fetch('/evento/drag', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(data)
                    })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Error en la respuesta de la red.');
                            }
                            return response.json();
                        })
                        .then(res => {
                            console.log(res);
                            Swal.fire(
                                'Éxito',
                                'Evento modificado',
                                'success'
                            );
                            if (res.estado) {
                                // Si el servidor devuelve éxito, ocultar el modal si existe y actualizar el calendario
                                if (myModal) {
                                    myModal.hide();
                                }
                                calendar.refetchEvents();
                            }
                        })
                        .catch(error => {
                            console.log('Se produjo un error:', error);
                        });
                } else {
                    // Si el usuario presiona el botón "Cancelar", revertir el evento a su posición original
                    info.revert();
                }
            });
        }

    });

    calendar.render();

    frm.addEventListener('submit', function (e) {
        e.preventDefault();
        let id = document.getElementById('id').value;
        let start = document.getElementById('start').value;
        let color = document.getElementById('color').value;
        let hora_inicio = document.getElementById('hora_inicio').value;
        let hora_final = document.getElementById('hora_final').value;
        let telefono = document.getElementById('telefono').value;
        let direccion = document.getElementById('direccion').value;
        let total = document.getElementById('total').value;
        let hora_extra = document.getElementById('hora_extra').value;
        let title = document.getElementById('nombre').value;
        let segnombre = document.getElementById('segnombre').value;
        let correo = document.getElementById('correo').value;
        let cabina = document.getElementById('cabina').value;
        let ape_p = document.getElementById('ape_p').value;
        let ape_m = document.getElementById('ape_m').value;


        if (title == '' || start == '' || color == '' || hora_inicio == '' || hora_final == ''
            || telefono == ''|| direccion == ''|| total == ''|| hora_extra == ''|| cabina == ''||
            ape_p == ''|| ape_m == ''|| correo == '') {
            Swal.fire(
                'Avisos?',
                'Todo los campos son obligatorios',
                'warning'
            );
        } else {
            // Crear un objeto con los datos que deseas enviar
            const data = {
                id: id,
                title: title,
                start: start,
                color: color,
                hora_inicio: hora_inicio,
                hora_final: hora_final,
                telefono: telefono,
                direccion: direccion,
                total: total,
                hora_extra: hora_extra,
                segnombre: segnombre,
                correo: correo,
                ape_m: ape_m,
                ape_p: ape_p,
                cabina: cabina,
            };
            fetch('/evento/registrar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error en la respuesta de la red.');
                    }
                    return response.json();
                })
                .then(res => {
                    console.log(res);
                    Swal.fire(
                        'Aviso',
                        res.mensaje,
                        res.estado = 'success' || 'warning',
                    );
                    if (res.estado) {
                        myModal.hide();
                        calendar.refetchEvents();
                    }
                })
                .catch(error => {
                    console.log('Se produjo un error:', error);
                });

        }
    });


    eliminar.addEventListener('click', function (info) {
        info.preventDefault();

        let id = document.getElementById('id').value;

        myModal.hide();

        Swal.fire({
            title: 'Advertencia',
            text: "Esta seguro de eliminar!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si, Quiero eliminarlo!'
        }).then((result) => {
            if (result.isConfirmed) {
                const data = {
                    id: id
                };

                fetch('/evento/eliminar', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Error en la respuesta de la red.');
                        }
                        return response.json();
                    })
                    .then(res => {
                        console.log(res);
                        Swal.fire(
                            'Aviso',
                            res.mensaje,
                            res.estado = 'success' || 'warning',
                        );
                        if (res.estado) {
                            calendar.refetchEvents();
                        }
                    })
            }
        })
    });
})



function addElementAgenda(){
    let options = document.querySelectorAll('#hora_inicio option');
    options.forEach(o => o.remove());

    let disponibles = [
        "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
        "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30",
        "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
        "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
        "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
        "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"
    ];
    let horas=[];
    let horas_fin=[];
    let fecha_ev= document.getElementById("start").value;
    console.log(fecha_ev)

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




function addHoraAgenda() {
    fetch("/horas")
        .then(response => response.json())
        .then(data => {

            let fecha_ev= document.getElementById("start").value;

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