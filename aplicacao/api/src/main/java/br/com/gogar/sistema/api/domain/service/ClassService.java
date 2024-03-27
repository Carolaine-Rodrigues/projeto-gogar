package br.com.gogar.sistema.api.domain.service;

import br.com.gogar.sistema.api.domain.dto.ServicesDTO;
import br.com.gogar.sistema.api.domain.entity.Equipment;
import br.com.gogar.sistema.api.domain.entity.ServicesEntity;
import br.com.gogar.sistema.api.domain.repository.ServicesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClassService {
    @Autowired
    ServicesRepository servicesRepository;

    //metado para salvar um serviço
    public ServicesEntity saveServices(ServicesDTO data){
        var services = new ServicesEntity(data);
        servicesRepository.save(services);
        return services;
    }

    // metado para listar todos
    public List<ServicesEntity> ListAllServices(){
        var listAll = servicesRepository.findAll();
        return listAll;
    }

    // metado para listar por id
    public ServicesEntity listId(Long id){
        var list = servicesRepository.findById(id).get();
        return list;
    }

    //metado para atualizar todos
    public ServicesEntity updateAll(Long id, ServicesDTO data) {
        Optional<ServicesEntity> optionalServices = servicesRepository.findById(id);
        if (optionalServices.isPresent()) {
            ServicesEntity updateService = optionalServices.get();
            if (data.getName() != null) {
                updateService.setName(data.getName());
            }
            if (data.getDescription() != null) {
                updateService.setDescription(data.getDescription());
            }
            if (data.getTypeTraining() != null) {
                updateService.setTypeTraining(data.getTypeTraining());
            }
            if (data.getDistributorMonthlyPrice() != null) {
                updateService.setDistributorMonthlyPrice(data.getDistributorMonthlyPrice());
            }
            if (data.getMonthlyResalePrice() != null) {
                updateService.setMonthlyResalePrice(data.getMonthlyResalePrice());
            }if (data.getProductHours() != null) {
                updateService.setProductHours(data.getProductHours());
            }if (data.getUserPrice() != null) {
                updateService.setUserPrice(data.getUserPrice());
            }
            return servicesRepository.save(updateService);
        }else {
            throw new EntityNotFoundException();
        }
    }
    // metado para atualizar parcialmente
    public ServicesEntity patchUpdate(Long id, Map<String, Object> data){
            Optional<ServicesEntity> optionalPatchService = servicesRepository.findById(id);
            if (optionalPatchService.isPresent()) {
                ServicesEntity updateService = optionalPatchService.get();

                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();

                    if ("name".equals(key) && value instanceof String) {
                        updateService.setName((String) value);
                    } else if ("description".equals(key) && value instanceof String) {
                        updateService.setDescription((String) value);
                    } else if ("typeTraining".equals(key) && value instanceof String) {
                        updateService.setTypeTraining((String) value);
                    }else if ("monthlyResalePrice".equals(key) && value instanceof BigDecimal) {
                        updateService.setMonthlyResalePrice((BigDecimal) value);
                    }else if ("distributorMonthlyPrice".equals(key) && value instanceof BigDecimal) {
                        updateService.setDistributorMonthlyPrice((BigDecimal) value);
                    }else if ("productHours".equals(key) && value instanceof LocalDate) {
                        updateService.setProductHours((LocalDate) value);
                    }else if ("userPrice".equals(key) && value instanceof BigDecimal) {
                        updateService.setUserPrice((BigDecimal) value);
                    }
                }
                return servicesRepository.save(updateService);
            } else {
                throw new EntityNotFoundException();
            }
    }
    public void deleteId(Long id){
        Optional<ServicesEntity> deleteService = servicesRepository.findById(id);
        if(deleteService.isPresent()){
            ServicesEntity delete = deleteService.get();
            servicesRepository.delete(delete);
        }

    }

}


