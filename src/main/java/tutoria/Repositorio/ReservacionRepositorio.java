/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.Repositorio;

import java.util.ArrayList;
import java.util.Date;
import tutoria.Interface.InterfaceReservacion;
import tutoria.Modelo.Reservacion;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tutoria.Modelo.Cliente;
import tutoria.reportes.ContadorClientes;


/**
 *
 * @author Luis Alberto Quintero
 */
@Repository
public class ReservacionRepositorio {
     @Autowired
    private InterfaceReservacion crud4;
    
    public List<Reservacion> getAll(){
        return (List<Reservacion>) crud4.findAll();
    }
    public Optional<Reservacion> getReservation(int id){
        return crud4.findById(id);
    }
    public Reservacion save(Reservacion reservation){
        return crud4.save(reservation);
    }
     public void delete(Reservacion reservacion){
        crud4.delete(reservacion);
    }
     
     //  ***** R E T O  5  ******     
     
     public List<Reservacion> ReservacionStatusRepositorio (String status){
         return crud4.findAllByStatus(status);
     }
     
     public List<Reservacion> ReservacionTiempoRepositorio (Date a, Date b){
         return crud4.findAllByStartDateAfterAndStartDateBefore(a, b);
     }
     // Metodo que calcula la cantidad de clientes
     public List<ContadorClientes> getClientesRepositorio(){
         List<ContadorClientes> res = new ArrayList<>();
         List<Object[]> report = crud4.countTotalReservationsByClient();
         for(int indice=0; indice<report.size(); indice++){
             res.add(new ContadorClientes((Long)report.get(indice)[1],(Cliente) report.get(indice)[0]));
         }
         return res;
     }
   
}
