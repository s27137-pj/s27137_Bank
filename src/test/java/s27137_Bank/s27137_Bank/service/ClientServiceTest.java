package s27137_Bank.s27137_Bank.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import s27137_Bank.s27137_Bank.exception.ValidationException;
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
    void shouldThrowNameValidationException(){
        Client client = new Client(1234, "", "Zielona", 555D, "PLN");

        ValidationException validationException = assertThrows(ValidationException.class, () -> clientService.register(client));

        assertEquals(validationException.getMessage(), "Imie klienta nie może być puste");
    }

    @Test
    void shouldThrowSurnameValidationException(){
        Client client = new Client(1234, "Anna", "", 555D, "PLN");

        ValidationException validationException = assertThrows(ValidationException.class, () -> clientService.register(client));

        assertEquals(validationException.getMessage(), "Nazwisko klienta nie może być puste");
    }

    @Test
    void shouldThrowAccountValueValidationException(){
        Client client = new Client(1234, "Anna", "Zielona", -555D, "PLN");

        ValidationException validationException = assertThrows(ValidationException.class, () -> clientService.register(client));

        assertEquals(validationException.getMessage(), "Stan konta nie może być ujemny");
    }

    @Test
    void shouldThrowCurrencyValidationException(){
        Client client = new Client(1234, "Anna", "Zielona", 555D, "CZK");

        ValidationException validationException = assertThrows(ValidationException.class, () -> clientService.register(client));

        assertEquals(validationException.getMessage(), "Obsługujemy tylko EUR, USD i PLN");
    }

    @Test
    void shouldThrowGetByPeselValidationException(){
        Client client = new Client(1234, "Anna", "Zielona", 555D, "CZK");

        ValidationException validationException = assertThrows(ValidationException.class, () -> clientService.getByPesel(1234));

        assertEquals(validationException.getMessage(), "Klient o peselu: " + client.getPesel() + " nie znajduje się w naszej bazie");
    }

    @Test
    void shouldPrintClientByPesel() {
        Client client = new Client(1234, "Anna", "Zielona", 555D, "PLN");

        clientService.register(client);

        Client result = assertDoesNotThrow(() -> clientService.getByPesel(client.getPesel()));

        assertEquals(client.getPesel(), result.getPesel());
    }





}