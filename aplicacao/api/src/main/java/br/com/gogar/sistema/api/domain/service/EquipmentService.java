package br.com.gogar.sistema.api.domain.service;

import br.com.gogar.sistema.api.domain.dto.EquipmentDTO;
import br.com.gogar.sistema.api.domain.entity.Equipment;
import br.com.gogar.sistema.api.domain.repository.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    //metado para salvar um produto
    public Equipment saveEquipment(EquipmentDTO data){
        var equipment = new Equipment(data);
        equipmentRepository.save(equipment);
        return equipment;
    }

    //metado para listar todos
    public List<Equipment> listEquipmentAll(){
        var listAll = equipmentRepository.findAll();
        return listAll;
    }
    //metado para listar por id
    public Equipment listEquipmentId(Long id){
        var list = equipmentRepository.findById(id).get();
        return list;
    }

    // metado para atualizar parcialmente
    public Equipment updatePatchId(Long id, Map<String, Object> data) {
        Optional<Equipment> optionalPatchEquipment = equipmentRepository.findById(id);
        if (optionalPatchEquipment.isPresent()) {
            Equipment updateEquipment = optionalPatchEquipment.get();

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if ("name".equals(key) && value instanceof String) {
                    updateEquipment.setName((String) value);
                } else if ("description".equals(key) && value instanceof String) {
                    updateEquipment.setDescription((String) value);
                } else if ("price".equals(key) && value instanceof BigDecimal) {
                    updateEquipment.setPrice((BigDecimal) value);
                } else if ("totalPrice".equals(key) && value instanceof BigDecimal) {
                    updateEquipment.setTotalPrice((BigDecimal) value);
                } else if ("amount".equals(key) && value instanceof Integer) {
                    updateEquipment.setAmount((Integer) value);
                } else if ("totalAmount".equals(key) && value instanceof Integer) {
                    updateEquipment.setTotalAmount((Integer) value);
                }
            }
            return equipmentRepository.save(updateEquipment); // Salvar no repositório após as atualizações
        } else {
            throw new EntityNotFoundException();
        }
    }

    // metado para atualizar todos
    public Equipment updateAll(Long id, EquipmentDTO data){
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        if(optionalEquipment.isPresent()){
            Equipment updateProduct = optionalEquipment.get();

            if(data.getName()!=null){
                updateProduct.setName(data.getName());
            }
            if(data.getAmount()!=null){
                updateProduct.setAmount(data.getAmount());
            }
            if(data.getPrice()!=null){
                updateProduct.setPrice(data.getPrice());
            }
            if(data.getDescription()!=null){
                updateProduct.setDescription(data.getDescription());
            }
            if(data.getTotalAmount()!=null){
                updateProduct.setTotalAmount(data.getTotalAmount());
            }
            if(data.getTotalPrice()!=null){
                updateProduct.setTotalPrice(data.getTotalPrice());
            }
            return equipmentRepository.save(updateProduct);
        } else {
            throw new EntityNotFoundException();
        }

    }

    //metado para deletar por id
    public void deleteId(Long id){
        Optional<Equipment> deleteEquipment = equipmentRepository.findById(id);
        if(deleteEquipment.isPresent()){
            Equipment delete = deleteEquipment.get();
            equipmentRepository.delete(delete);
        }
    }
}
