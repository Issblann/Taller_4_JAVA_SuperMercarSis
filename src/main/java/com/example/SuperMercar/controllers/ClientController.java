package com.example.SuperMercar.controllers;
import com.example.SuperMercar.controllers.DTO.ClientDTO;
import com.example.SuperMercar.entities.Client;
import com.example.SuperMercar.services.contracts.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    IClientService clientService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllClients(){
        List<ClientDTO> clientList = clientService.findAll()
                .stream()
                .map(client -> ClientDTO.builder()
                        .id(client.getId())
                        .name(client.getName())
                        .lastName(client.getLastName())
                        .email(client.getEmail())
                        .username(client.getUsername())
                        .password(client.getPassword())
                        .build())
                .toList();
        return  ResponseEntity.ok(clientList);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id){
        Optional<Client> clientOptional = clientService.findById(id);
        if(clientOptional.isPresent()){
            Client client = clientOptional.get();
            ClientDTO clientDTO = ClientDTO.builder()
                    .id(client.getId())
                    .name(client.getName())
                    .lastName(client.getLastName())
                    .email(client.getEmail())
                    .username(client.getUsername())
                    .password(client.getPassword())
                    .build();
            return ResponseEntity.ok(clientDTO);

        } return  ResponseEntity.notFound().build();
    }
    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody ClientDTO clientDTO)  throws URISyntaxException {
        if(clientDTO.getName().isBlank()){

            return ResponseEntity.badRequest().build();
        }
        clientService.save(Client.builder()
                .id(clientDTO.getId())
                .name(clientDTO.getName())
                .lastName(clientDTO.getLastName())
                .email(clientDTO.getEmail())
                .username(clientDTO.getUsername())
                .password(clientDTO.getPassword())
                .build());
        return ResponseEntity.created(new URI("/client/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        Optional<Client> clientOptional = clientService.findById(id);
        if (clientOptional.isPresent()){
            Client client = clientOptional.get();
            client.setName(clientDTO.getName());
            client.setLastName(clientDTO.getLastName());
            client.setEmail(clientDTO.getEmail());
            client.setUsername(clientDTO.getUsername());
            client.setPassword(clientDTO.getPassword());
            clientService.save(client);
            return  ResponseEntity.ok("Registro actualizado correctamente");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id ){

        if(id != null){
            clientService.deleteById(id);
            return ResponseEntity.ok("Registro eliminado correctamente");
        }
        return  ResponseEntity.badRequest().build();
    }
}
