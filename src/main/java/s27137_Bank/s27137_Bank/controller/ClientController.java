package s27137_Bank.s27137_Bank.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s27137_Bank.s27137_Bank.exception.ValidationException;
import s27137_Bank.s27137_Bank.model.Client;
import s27137_Bank.s27137_Bank.service.ClientService;

import java.util.List;


@RestController
    @RequestMapping("/clients")
    @AllArgsConstructor

    public class ClientController {
        private final ClientService clientService;

        @PostMapping("/register")
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

    @GetMapping("/getByPesel/{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id) {
        Client client = clientService.getByPesel(id);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(200))
                .body(client);
    }

    @GetMapping("/findByBalanceGreaterThan-{balance}")
    public ResponseEntity<List<Client>> findByBalanceGreaterThan(@PathVariable Double balance) {
        List<Client> client = clientService.findByBalanceGreaterThan(balance);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(200))
                .body(client);
    }
}





