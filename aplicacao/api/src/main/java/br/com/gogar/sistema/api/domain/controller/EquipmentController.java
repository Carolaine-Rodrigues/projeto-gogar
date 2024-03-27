package br.com.gogar.sistema.api.domain.controller;

import br.com.gogar.sistema.api.convert.ConvertMap;
import br.com.gogar.sistema.api.domain.dto.EquipmentDTO;
import br.com.gogar.sistema.api.domain.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @PostMapping("/save")
    public ResponseEntity postEquipment(@RequestBody EquipmentDTO data){
        var saveData = equipmentService.saveEquipment(data);
        return ResponseEntity.ok(saveData);
    }
    @GetMapping("/list")
    public ResponseEntity getAllEquipment(){
        var getAll = equipmentService.listEquipmentAll();
        return ResponseEntity.ok(getAll);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity getListId(@PathVariable Long id){
        var getId = equipmentService.listEquipmentId(id);
        return ResponseEntity.ok(getId);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteId(@PathVariable Long id){
        equipmentService.deleteId(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/all/{id}")
    public ResponseEntity updateAll(@PathVariable Long id, @RequestBody EquipmentDTO data){
        var update = equipmentService.updateAll(id,data);
        return ResponseEntity.ok(update);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity patchId(@PathVariable Long id, @RequestBody EquipmentDTO data) throws IllegalAccessException {
        Map<String, Object> equipmentMap = ConvertMap.convertDTOToMap(data);
        var update = equipmentService.updatePatchId(id,equipmentMap);
        return ResponseEntity.ok(update);
    }
}
