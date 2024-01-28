package s27137_Bank.s27137_Bank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import s27137_Bank.s27137_Bank.model.Client;
import s27137_Bank.s27137_Bank.repository.ClientRepository;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ClientControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSaveNewClient() throws JsonProcessingException {
        Client client = new Client(12355, "Jan", "Mak", 45D, "PLN");

        String json = objectMapper.writeValueAsString(client);

        Client result = webTestClient.post().uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .exchange()
                .expectStatus().isEqualTo(201)
                .expectBody(Client.class)
                .returnResult().getResponseBody();

        assertEquals(result.getName(), client.getName());
        assertEquals(result.getSurname(), client.getSurname());


    }

    @Test
    void shouldReturnAllClients(){
        Client client = new Client(12355, "Jan", "Mak", 45D, "PLN");

        clientRepository.register(client);

        List<Client> result = webTestClient.get().uri("/register")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Client.class)
                .returnResult().getResponseBody();

        assertEquals(result.get(0).getName(), client.getName());
        assertEquals(1, result.size());


    }

}