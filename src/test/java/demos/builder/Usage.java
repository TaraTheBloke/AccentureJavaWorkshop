package demos.builder;

import static demos.builder.AddressBuilder.anAddress;
import static demos.builder.CustomerBuilder.aCustomerCalled;

public class Usage {

    public static void main(String[] args) {
        Customer customer = aCustomerCalled("tara")
                               .with(anAddress().withCity("Belfast"))
                               .build();
        System.out.println(customer);
    }
}
