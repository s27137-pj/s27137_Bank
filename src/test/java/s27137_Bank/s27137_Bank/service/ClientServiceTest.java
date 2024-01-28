package s27137_Bank.s27137_Bank.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import s27137_Bank.s27137_Bank.model.Client;
import s27137_Bank.s27137_Bank.repository.ClientRepository;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private static ClientService clientService;
    private static ClientRepository clientRepository;

    @BeforeAll
    static void setUp() {
        clientRepository = new ClientRepository();
        clientService = new ClientService(clientRepository);
    }

    @AfterEach
    void cleanUp() {
        clientRepository.removeAll();
    }

    @Test
    void shouldRegisterClient() {
        Client client = new Client(1234, "Anna", "Zielona", 555D, "PLN");

        Client result = assertDoesNotThrow(() -> clientService.register(client));

        assertEquals(client.getName(), result.getName());
        assertEquals(client.getSurname(), result.getSurname());
        assertEquals(client.getCurrency(), result.getCurrency());
        assertNotNull(result.getPesel());
        assertNotNull(result.getAccountValue());
    }

    @Test
    void shouldPrintClientByPesel() {
        Client client = new Client(1234, "Anna", "Zielona", 555D, "PLN");

        clientService.register(client);

        Client result = assertDoesNotThrow(() -> clientService.getByPesel(client.getPesel()));

        assertEquals(client.getPesel(), result.getPesel());
    }

//    @Test
//    public void shouldGetAccountsAboveBalance() {
//        Client client = new Client(1234, "Anna", "Zielona", 555D, "PLN");
//        Client client1 = new Client(1234, "Jan", "Zielonka", 5D, "PLN");
//        clientService.register(client);
//        clientService.register(client1);
//
//        var accounts = clientService.(10000);
//        assertEquals(1, accounts.size());
//        assertEquals(1, accounts.get(0).getPesel());
//    }







}