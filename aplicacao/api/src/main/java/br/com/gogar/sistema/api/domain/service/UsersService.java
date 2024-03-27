package br.com.gogar.sistema.api.domain.service;

import br.com.gogar.sistema.api.domain.address.AddressFeignClient;
import br.com.gogar.sistema.api.domain.address.AddressRequest;
import br.com.gogar.sistema.api.domain.address.AddressResponse;
import br.com.gogar.sistema.api.domain.dto.UserPostDTO;
import br.com.gogar.sistema.api.domain.entity.User;
import br.com.gogar.sistema.api.domain.identification.IdenticationRequest;
import br.com.gogar.sistema.api.domain.identification.IdentificationFeingClient;
import br.com.gogar.sistema.api.domain.identification.IdentificationResponse;
import br.com.gogar.sistema.api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressFeignClient addressFeignClient;
    @Autowired
    IdentificationFeingClient identificationFeingClient;

    // metado que pega os dados da api via cep
//    public AddressResponse apiAddress(AddressRequest request){
//       return addressFeignClient.searchAddress(request.getCep());
//    }
   // metado que paga os dados da api de cnpj
//    public IdentificationResponse apiIdetification(IdenticationRequest request){
//        return identificationFeingClient.searchIdentication(request.getCnpj());
//    }

    // metado para salvar
    public User saveUserPost(User user){

        this.addressFeignClient.searchAddress(user.getAddress().getCep());
        this.identificationFeingClient.searchIdentication(user.getCnpj().getCnpj());



        return userRepository.save(user);

    }

    // metado para listar todos
    public List<User> allListUsers(){
        List<User> list = userRepository.findAll();
        return list;
    }
    // metado para listar por id
    public User listUserId(UUID id){
        var userId = userRepository.findById(id).get();
        return userId;
    }

    //metado para atualizar parcialmente
    public User updatePatch(UUID id, Map<String, Object> data){
        Optional<User> userPatch = userRepository.findById(id);
       if(userPatch.isPresent()){
           User user = userPatch.get();
           if(data.containsKey("email")){
               user.setEmail((String) data.get("email"));
           }
           if(data.containsKey("password")){
               user.setPassword((String) data.get("password"));
           }
           if(data.containsKey("phone")){
               user.setPhone((String) data.get("phone"));
           }
           userRepository.save(user);
           return user;
       }else{
           throw new EntityNotFoundException();
       }
    }

    // metado para deletar
    public void deleteUserId(UUID id){
        Optional<User> deleteUser= userRepository.findById(id);
        if(deleteUser.isPresent()){
            User user = deleteUser.get();
            userRepository.delete(user);
        }else {
            throw new EntityNotFoundException();
        }
    }
}
