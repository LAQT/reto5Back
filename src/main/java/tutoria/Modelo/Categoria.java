/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 *
 * @author Luis Alberto Quintero
 */
@Entity
@Table(name = "category")
public class Categoria implements Serializable {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
    private String description;
    
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="category")
    @JsonIgnoreProperties({"category","message"})
    private List<Habitacion> rooms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Habitacion> getRooms() {
        return rooms;
    }

    public void setRooms(List<Habitacion> rooms) {
        this.rooms = rooms;
    }

   
    
    
    
    
    
    
    
    
    
}
