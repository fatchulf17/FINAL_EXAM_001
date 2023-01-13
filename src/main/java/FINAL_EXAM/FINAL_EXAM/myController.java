/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FINAL_EXAM.FINAL_EXAM;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 * Fatchul Fajri_20200140001
 */

@RestController
@CrossOrigin
@RequestMapping("/surat")
public class myController {
    Surat data = new Surat();
    SuratJpaController ctrl = new SuratJpaController();
    
    @GetMapping
    public List<Surat> getAll() {
        List<Surat> dt = new ArrayList<>();
        
        try{
            dt = ctrl.findSuratEntities();        
        }catch(Exception e)
        {
            dt = List.of();        
        }
        return dt;
    }
    
    @GetMapping("/{id}")
    public List<Surat> getNameById(@PathVariable("id") int id) {
        List<Surat> dt = new ArrayList<>(); // Declare new LIST
        try {
            data = ctrl.findSurat(id); // get data from db
            dt.add(data); // fill data from db to list
        } catch (Exception e) {
            dt = List.of(); // data not found
        }
        return dt;
    }
    
    @PostMapping()
    public String createData(HttpEntity<String> paket) {
        String message = "";

        try {
            String json_receive = paket.getBody();

            ObjectMapper map = new ObjectMapper();
        
            data = map.readValue(json_receive, Surat.class);

            ctrl.create(data);
            message = "File " +data.getJudul()+ " Tersimpan";

        } catch (Exception e) {
            message = "Failed";
        }

        return message;
    }
    
    @PutMapping()
    public String editData(HttpEntity<String> paket) {
        String message = "";

        try {
            String json_receive = paket.getBody();

            ObjectMapper map = new ObjectMapper();
        
            data = map.readValue(json_receive, Surat.class);

            ctrl.edit(data);
            message = "File " +data.getJudul()+ " Teredit";

        } catch (Exception e) {
            message = "Failed";
        }

        return message;
    }
    
    @DeleteMapping()
    public String deleteData(HttpEntity<String> paket) {
        String message = "";

        try {
            String json_receive = paket.getBody();

            ObjectMapper map = new ObjectMapper();
        
            data = map.readValue(json_receive, Surat.class);

            ctrl.destroy(data.getId());
            message = "Id file " +data.getId().toString() + " terhapus";

        } catch (Exception e) {
            message = "Failed";
        }
        return message;
    }
}