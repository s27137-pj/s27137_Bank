package s27137_Bank.s27137_Bank.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import s27137_Bank.s27137_Bank.model.Client;
import s27137_Bank.s27137_Bank.service.ClientService;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {
    private static ClientRepository clientRepository;

    @BeforeAll
    static void setUp() {
        clientRepository = new ClientRepository();

    }

    @AfterEach
    void cleanUp() {
        clientRepository.removeAll();
    }

    @Test
            void shouldFindAboveAmount(){

        Client client = new Client(1234, "Anna", "Zielona", 555D, "PLN");
        Client client1 = new Client(1234, "Jan", "Zielonka", 5D, "PLN");
        Client client2 = new Client(1234, "Anna", "Zielona", 10D, "PLN");
        Client client3 = new Client(1234, "Jan", "Zielonka", 10000D, "PLN");

        clientRepository.register(client);
        clientRepository.register(client1);
        clientRepository.register(client2);
        clientRepository.register(client3);

        assertEquals(3, clientRepository.findByBalanceGreaterThan(6.0).size());

    }


}