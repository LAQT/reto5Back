/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.Servicios;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tutoria.Modelo.Reservacion;
import tutoria.Repositorio.ReservacionRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutoria.reportes.ContadorClientes;
import tutoria.reportes.StatusReservas;

/**
     *
     * @author Luis Alberto Quintero
     */
    // Clase Publica  serviciosReservacion
    @Service
    public class ServiciosReservacion {
      /** Repositorio
        * 
        */  
        @Autowired
        private ReservacionRepositorio metodosCrud;

       /** Lectura  y cargue de la tabla de revervaciones
        * 
        * @return 
        */    
        public List<Reservacion> getAll(){
            return metodosCrud.getAll();
        }

        /** Lectura de Reservaciones por el ID
         * 
         * @param reservationId
         * @return 
         */
        public Optional<Reservacion> getReservation(int reservationId) {
            return metodosCrud.getReservation(reservationId);
        }

        /** Metodo para guarda la informacion de reservaciones
         * 
         * @param reservation
         * @return 
         */
        public Reservacion save(Reservacion reservation){
            if(reservation.getIdReservation()==null){
                return metodosCrud.save(reservation);
            }else{
                Optional<Reservacion> evt= metodosCrud.getReservation(reservation.getIdReservation());
                if(evt.isEmpty()){
                    return metodosCrud.save(reservation);
                }else{
                    return reservation;
                }
            }
        }

        /**  Actualizacion datos de reservaciones
         * 
         * @param reservacion
         * @return 
         */
        public Reservacion update(Reservacion reservacion){
            if(reservacion.getIdReservation()!=null){
                Optional<Reservacion> eresul= metodosCrud.getReservation(reservacion.getIdReservation());
                if(!eresul.isEmpty()){
                    if(reservacion.getStartDate()!=null){
                        eresul.get().setStartDate(reservacion.getStartDate());
                    }
                    if(reservacion.getDevolutionDate()!=null){
                        eresul.get().setDevolutionDate(reservacion.getDevolutionDate());
                    }
                    if(reservacion.getStatus()!=null){
                        eresul.get().setStatus(reservacion.getStatus());
                    }
                    metodosCrud.save(eresul.get());
                    return eresul.get();
                }else{
                    return reservacion;
                }
            }else{
                return reservacion;
            }
        }
    /** Eliminación de datos de reservaciones
     * 
     * @param reservationId
     * @return 
     */
        public boolean deleteReservation(int reservationId) {
            Boolean aBoolean = getReservation(reservationId).map(reservation -> {
                metodosCrud.delete(reservation);
                return true;
            }).orElse(false);  
            return aBoolean;
        }

     //  ***** R E T O  5  ******

        /** Metodo para la generacion del reporte de estado de reservaciones
         * 
         * @return 
         */
        public StatusReservas reporteStatusServicio (){
            List<Reservacion>completed= metodosCrud.ReservacionStatusRepositorio("completed");
            List<Reservacion>cancelled= metodosCrud.ReservacionStatusRepositorio("cancelled");

            return new StatusReservas(completed.size(), cancelled.size() );
        }

        /** Metodo para reporte de reservaciones entre  rando de fechas
         * 
         * @param datoA
         * @param datoB
         * @return 
         */
        public List<Reservacion> reporteTiempoServicio (String datoA, String datoB){
            SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
            Date datoUno = new Date();
            Date datoDos = new Date();
            try{
                datoUno = parser.parse(datoA);
                datoDos = parser.parse(datoB);
            }catch(ParseException evt){
                evt.printStackTrace();
            }if(datoUno.before(datoDos)){
                return metodosCrud.ReservacionTiempoRepositorio(datoUno, datoDos);
            }else{
                return new ArrayList<>(); 
            } 
        } 
        /** Matodo para  determinar el top de clientes
         * 
         * @return 
         */ 
         public List<ContadorClientes> reporteClientesServicio(){
                return metodosCrud.getClientesRepositorio();
            } 
    }
