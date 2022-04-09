package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

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
    void getTotalAmount_TC3_WBT_valid() {
        List<Payment> paymentList;
        double TotalSum = 0.0;
        PaymentType paymentType = PaymentType.Cash;

        paymentList = pizzaService.getPayments();

        // testare functionalitate getTotalAmount
        // cazul paymentList = 0
        for(Payment p: paymentList) {
            if (p.getType().equals(paymentType)) {
                TotalSum += p.getAmount();
            }
        }
        assertEquals(TotalSum, pizzaService.getTotalAmount(paymentType));
    }

    @Test
    @Tag("valid")
    void getTotalAmount_TC6_WBT_valid() {
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
        // cazul paymentList = 1
        for(Payment p: paymentList) {
            if (p.getType().equals(paymentType)) {
                TotalSum += p.getAmount();
            }
        }
        assertEquals(TotalSum, pizzaService.getTotalAmount(paymentType));
    }

    @Test
    @Tag("valid")
    void getTotalAmount_TC7_WBT_valid() {
        List<Payment> paymentList;
        double TotalSum = 0.0;
        PaymentType paymentType = PaymentType.Mixt;

        paymentList = pizzaService.getPayments();
        int countPaymentsBefore = paymentList.size();

        // testare functionalitate getTotalAmount
        // cazul paymentList = 1 si paymentType diferit
        for(Payment p: paymentList) {
            if (p.getType().equals(paymentType)) {
                TotalSum += p.getAmount();
            }
        }
        assertEquals(TotalSum, pizzaService.getTotalAmount(paymentType));
    }

    @Test
    @Tag("valid")
    void getTotalAmount_TC8_WBT_valid() {
        List<Payment> paymentList;
        double TotalSum = 0.0;
        PaymentType paymentType = PaymentType.Mixt;

        paymentList = pizzaService.getPayments();
        int countPaymentsBefore = paymentList.size();

        // testare functionalitate getTotalAmount
        // cazul paymentList = 0 si false pe toate ramurile
        for(Payment p: paymentList) {
            if (p.getType().equals(paymentType)) {
                TotalSum += p.getAmount();
            }
        }
        assertEquals(TotalSum, pizzaService.getTotalAmount(paymentType));
    }

    @Test
    @Tag("valid")
    void getTotalAmount_TC10_WBT_valid() {
        List<Payment> paymentList;
        Payment payment;
        double TotalSum = 0.0;
        PaymentType paymentType = PaymentType.Cash;

        paymentList = pizzaService.getPayments();
        int countPaymentsBefore = paymentList.size();

        // testare functionalitate addPayment
        assertDoesNotThrow (()->pizzaService.addPayment(1, PaymentType.Cash, 27.9));
        paymentList = pizzaService.getPayments();
        int countPaymentsAfter = paymentList.size();

        // testare ca s-a adaugat un nou entry in fisier
        assertEquals(countPaymentsAfter, countPaymentsBefore + 1);

        // testare ca s-a adaugat in fisier entitatea corecta
        payment = paymentList.get(paymentList.size() - 1);
        assertEquals(1, payment.getTableNumber());
        assertEquals(PaymentType.Cash, payment.getType());
        assertEquals(27.9, payment.getAmount());

        // testare functionalitate getTotalAmount
        // cazul paymentList > 1
        for(Payment p: paymentList) {
            if (p.getType().equals(paymentType)) {
                TotalSum += p.getAmount();
            }
        }
        assertEquals(TotalSum, pizzaService.getTotalAmount(paymentType));
    }

    @AfterEach
    public void tearDown() {
        // clean up - stergem tot din fisier dupa fiecare rulare
        pizzaService.deleteAllPayments();
        pizzaService = null;
    }
}