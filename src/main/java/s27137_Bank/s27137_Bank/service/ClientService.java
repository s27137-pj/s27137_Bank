package s27137_Bank.s27137_Bank.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s27137_Bank.s27137_Bank.exception.ValidationException;
import s27137_Bank.s27137_Bank.model.Client;
import s27137_Bank.s27137_Bank.repository.ClientRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client register(Client client) {

        if (client.getName() == null || client.getName().isEmpty()) {
            throw new ValidationException("Imie klienta nie może być puste");
        }
        if (client.getSurname() == null || client.getSurname().isEmpty()) {
            throw new ValidationException("Nazwisko klienta nie może być puste");
        }
        if (client.getAccountValue() == null || client.getAccountValue() < 0) {
            throw new ValidationException("Stan konta nie może być ujemny");
        }
        if (client.getCurrency() == null || (!"PLN".equals(client.getCurrency()) && !"EUR".equals(client.getCurrency()) && !"USD".equals(client.getCurrency()))){
            throw new ValidationException("Obsługujemy tylko EUR, USD i PLN");
        }


        return clientRepository.register(client);
    }

    public Client getByPesel(Integer ID) {
        return clientRepository.getByPesel(ID)
                .orElseThrow(() -> new ValidationException("Klient o peselu: " + ID + " nie znajduje się w naszej bazie"));
    }


    public List<Client> findAll(){
        return clientRepository.getAll();
    }

    public List<Client> findByBalanceGreaterThan(Double balance) {
        List<Client> byBalanceGreaterThan = clientRepository.findByBalanceGreaterThan(balance);

        if (byBalanceGreaterThan.isEmpty()) {
            throw new ValidationException("Users with balance " + balance + " and greater not found");
        }

        return byBalanceGreaterThan;
    }


}

