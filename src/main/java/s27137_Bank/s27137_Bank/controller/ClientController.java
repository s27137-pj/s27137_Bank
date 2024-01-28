package s27137_Bank.s27137_Bank.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s27137_Bank.s27137_Bank.excception.ValidationException;
import s27137_Bank.s27137_Bank.model.Client;
import s27137_Bank.s27137_Bank.service.ClientService;

import java.util.List;


@RestController
    @RequestMapping("/register")
    @AllArgsConstructor

    public class ClientController {
        private final ClientService clientService;

        @PostMapping
        public ResponseEntity<Client> create(@RequestBody Client client) {
            try {
                Client createdClient = clientService.register(client);
                return ResponseEntity.status(201).body(createdClient);
            } catch (ValidationException exception){
                return ResponseEntity.badRequest().build();
            }

        }

        @GetMapping
        public ResponseEntity<List<Client>> getAll(){
            List<Client> list = clientService.findAll();


            return ResponseEntity.ok(list);

        }
}





