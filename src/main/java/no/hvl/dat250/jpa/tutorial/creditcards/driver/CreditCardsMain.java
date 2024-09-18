package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat250.jpa.tutorial.creditcards.*;

import java.util.Set;

public class CreditCardsMain {

  static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";

  public static void main(String[] args) {
    try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(
        PERSISTENCE_UNIT_NAME); EntityManager em = factory.createEntityManager()) {
      em.getTransaction().begin();
      createObjects(em);
      em.getTransaction().commit();
    }

  }

  private static void createObjects(EntityManager em) {
    // TODO: Create object world as shown in the README.md.
    // Create a bank
    Bank bank = new Bank();
    bank.setName("Pengebank");

    // Create a customer
    Customer customer = new Customer();
    customer.setName("Max Mustermann");

    // Create a pincode
    Pincode pincode = new Pincode();
    pincode.setPincode("123");
    pincode.setCount(1);

    // Create a credit card
    CreditCard creditCard = new CreditCard();
    creditCard.setNumber(123);
    creditCard.setBalance(1);
    creditCard.setCreditLimit(2000);
    creditCard.setPincode(pincode);
    creditCard.setOwningBank(bank);

    // Create a credit card
    CreditCard creditCard2 = new CreditCard();
    creditCard2.setNumber(12345);
    creditCard2.setBalance(-5000);
    creditCard2.setCreditLimit(-10000);
    creditCard2.setPincode(pincode);
    creditCard2.setOwningBank(bank);

    // Create an address
    Address address = new Address();
    address.setStreet("Inndalsveien");
    address.setNumber(28);
    address.setResidents(Set.of(customer));

    // Set relationships
    customer.getCreditCards().add(creditCard);
    customer.getCreditCards().add(creditCard2);
    customer.getAddresses().add(address);

    // Persist entities
    em.persist(bank);
    em.persist(customer);
    em.persist(pincode);
    em.persist(creditCard);
    em.persist(creditCard2);
    em.persist(address);
  }
}
