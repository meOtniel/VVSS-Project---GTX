package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class PizzaServiceTest {
    private MenuRepository menuRepository;
    private PaymentRepository paymentRepository;
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository();
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @Test
    @Tag("valid")
    void addPayment_TC1_ECP_valid() {
        assertDoesNotThrow (()->pizzaService.addPayment(1, PaymentType.Card, 23.5));
    }

    @Test
    @Tag("invalid")
    void addPayment_TC2_ECP_invalid() {
        assertThrows (RuntimeException.class, ()->pizzaService.addPayment(-1, PaymentType.Card, 23.5));
    }

    //urmatoarele teste se constuiresc mai jos pe modelul celor 2 de mai sus
}