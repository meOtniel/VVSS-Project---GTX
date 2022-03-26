package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class PizzaServiceTest {
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        MenuRepository menuRepository = new MenuRepository();
        PaymentRepository paymentRepository = new PaymentRepository();
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @Test
    @Tag("valid")
    void addPayment_TC1_ECP_valid() {
        assertDoesNotThrow (()->pizzaService.addPayment(3, PaymentType.Cash, 13.5));
    }

    @Test
    @Tag("invalid")
    void addPayment_TC2_ECP_invalid() {
        assertThrows (RuntimeException.class, ()->pizzaService.addPayment('1', PaymentType.Card, 27.9));
    }

    @Test
    @Tag("invalid")
    void addPayment_TC4_ECP_invalid() {
        assertDoesNotThrow (()->pizzaService.addPayment(4, PaymentType.Cash, '0'));
    }

    @Test
    @Tag("invalid")
    void addPayment_TC5_ECP_invalid() {
        assertThrows (RuntimeException.class, ()->pizzaService.addPayment(-2, PaymentType.Card, 23.5));
    }

    @Test
    @Tag("invalid")
    void addPayment_TC7_ECP_invalid() {
        assertThrows(RuntimeException.class, ()->pizzaService.addPayment(2, PaymentType.Card, -13.5));
    }

    @Test
    @Tag("invalid")
    void addPayment_TC1_BVA_invalid() {
        assertThrows (RuntimeException.class, ()->pizzaService.addPayment(0, PaymentType.Cash, 13.5));
    }

    @Test
    @Tag("valid")
    void addPayment_TC2_BVA_valid() {
        assertDoesNotThrow(()->pizzaService.addPayment(1, PaymentType.Card, 27.9));
    }

    @Test
    @Tag("valid")
    void addPayment_TC3_BVA_valid() {
        assertDoesNotThrow (()->pizzaService.addPayment(2, PaymentType.Card, 24.9));
    }

    @Test
    @Tag("invalid")
    void addPayment_TC6_BVA_invalid() {
        assertThrows(RuntimeException.class, ()->pizzaService.addPayment(9, PaymentType.Cash, 64.5));
    }

    @AfterEach
    public void tearDown() {
        pizzaService = null;
    }
}