package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class PizzaServiceTestF02 {
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        MenuRepository menuRepository = new MenuRepository();
        PaymentRepository paymentRepository = new PaymentRepository();
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @Test
    @Tag("valid")
    void getTotalAmount_TC1_WBT_valid() {
        List<Payment> paymentList;
        Payment payment;
        double TotalSum = 0.0;
        PaymentType paymentType = PaymentType.Cash;

        paymentList = pizzaService.getPayments();
        int countPaymentsBefore = paymentList.size();

        // testare functionalitate addPayment
        assertDoesNotThrow (()->pizzaService.addPayment(3, PaymentType.Cash, 13.5));
        paymentList = pizzaService.getPayments();
        int countPaymentsAfter = paymentList.size();

        // testare ca s-a adaugat un nou entry in fisier
        assertEquals(countPaymentsAfter, countPaymentsBefore + 1);

        // testare ca s-a adaugat in fisier entitatea corecta
        payment = paymentList.get(paymentList.size() - 1);
        assertEquals(3, payment.getTableNumber());
        assertEquals(PaymentType.Cash, payment.getType());
        assertEquals(13.5, payment.getAmount());

        // testare functionalitate getTotalAmount
        for(Payment p: paymentList) {
            if (p.getType().equals(paymentType)) {
                TotalSum += p.getAmount();
            }
        }
        assertEquals(TotalSum, pizzaService.getTotalAmount(paymentType));
    }

    @Test
    @Tag("invalid")
    void getTotalAmount_TC2_WBT_invalid() {
        List<Payment> paymentList;
        Payment payment;
        double TotalSum = 0.0;
        PaymentType paymentType = PaymentType.Cash;

        paymentList = pizzaService.getPayments();
        int countPaymentsBefore = paymentList.size();

        // testare functionalitate addPayment
        assertDoesNotThrow (()->pizzaService.addPayment(3, PaymentType.Cash, 13.5));
        paymentList = pizzaService.getPayments();
        int countPaymentsAfter = paymentList.size();

        // testare ca s-a adaugat un nou entry in fisier
        assertEquals(countPaymentsAfter, countPaymentsBefore + 1);

        // testare ca s-a adaugat in fisier entitatea corecta
        payment = paymentList.get(paymentList.size() - 1);
        assertEquals(3, payment.getTableNumber());
        assertEquals(PaymentType.Cash, payment.getType());
        assertEquals(13.5, payment.getAmount());

        // testare functionalitate getTotalAmount
        for(Payment p: paymentList) {
            if (p.getType().equals(paymentType)) {
                TotalSum += p.getAmount();
            }
        }

        // eroare, deoarece noi calculam suma pentru paymentType.Cash
        // si o validam pentru cea cu paymentType.Mixt
        assertEquals(TotalSum, pizzaService.getTotalAmount(PaymentType.Mixt));
    }

    @AfterEach
    public void tearDown() {
        // clean up - stergem tot din fisier dupa fiecare rulare
        pizzaService.deleteAllPayments();
        pizzaService = null;
    }
}