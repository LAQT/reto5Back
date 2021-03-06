/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.Repositorio;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tutoria.Modelo.Habitacion;
import tutoria.Interface.InterfaceHabitacion;

/**
 *
 * @author Luis Alberto Quintero
 */
@Repository
public class HabitacionRepositorio {
      @Autowired
    private InterfaceHabitacion crud;
    

    public List<Habitacion> getAll(){
        return (List<Habitacion>) crud.findAll();       
    }
    
    public Optional <Habitacion> getRoom(int id){
        return crud.findById(id);
    }
    
    public Habitacion save(Habitacion room){
        return crud.save(room);
    }
     public void delete(Habitacion room){
        crud.delete(room);
    }
    
}
