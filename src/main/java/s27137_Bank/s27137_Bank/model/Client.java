package s27137_Bank.s27137_Bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private Integer pesel;
    private String name;
    private String surname;
    private Double accountValue;
    private String currency;
}