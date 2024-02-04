package s27137_Bank.s27137_Bank.repository;

import org.springframework.stereotype.Repository;
import s27137_Bank.s27137_Bank.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    private List<Client> clientList = new ArrayList<>();

    public Client register(Client client) {
//        client.setPesel(clientList.size());
        clientList.add(client);

        return client;
    }

    public Optional<Client> getByPesel(Integer ID) {
        return clientList.stream()
                .filter(client -> client.getPesel().equals(ID))
                .findFirst();
    }



    public List<Client> findByBalanceGreaterThan(Double balance) {
        return clientList.stream()
                .filter(userAccount -> userAccount.getAccountValue() > balance)
                .toList();
    }



    public List<Client> getAll() {
        return clientList;
    }

    public void removeAll() {
        clientList = new ArrayList<>();
    }


}

