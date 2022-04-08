package pizzashop.service;

import pizzashop.model.Menu;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;
import java.util.Objects;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<Menu> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    // validate exceptions
    public void addPayment(int table, PaymentType type, double amount){
        if(table < 1 || table > 8)
            throw new RuntimeException("Table must be a value in [1,8]!");
        if(amount <= 0.0)
            throw new RuntimeException("The amount must not be a negative value!");
        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type){
        double total = 0.0f;
        List<Payment> l=getPayments();
        if ((l==null) || (l.isEmpty())) return total;

        if (l.size() == 1){
            Payment p = l.get(0);
            if(p.getType().equals(type)){
                return p.getAmount();
            }
            else
                return total;
        }
        else {
            for (Payment p : l) {
                if (p.getType().equals(type)) {
                    total += p.getAmount();
                }
            }
        }

        return total;
    }

}