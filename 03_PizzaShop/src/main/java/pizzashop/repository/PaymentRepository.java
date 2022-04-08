package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PaymentRepository {
    private static String filename = "E:\\LA FACULTATEEEE\\An III, sem 2\\VVSS\\Laboratoare\\VVSS-Project---GTX\\03_PizzaShop\\src\\main\\java\\pizzashop\\files\\payments.txt";
    private List<Payment> paymentList;

    public PaymentRepository() {
        this.paymentList = new ArrayList<>();
        readPayments();
    }

    private void readPayments() {
        //ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        //File file = new File(classLoader.getResource(filename).getFile());
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line = null;
            while ((line = br.readLine()) != null) {
                Payment payment = getPayment(line);
                paymentList.add(payment);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Payment getPayment(String line) {
        Payment item = null;
        if (line == null || line.equals("")) return null;
        StringTokenizer st = new StringTokenizer(line, ",");
        int tableNumber = Integer.parseInt(st.nextToken());
        String type = st.nextToken();
        double amount = Double.parseDouble(st.nextToken());
        item = new Payment(tableNumber, PaymentType.valueOf(type), amount);
        return item;
    }

    public void add(Payment payment) {
        paymentList.add(payment);
        writeAll();
    }

    public List<Payment> getAll() {
        return paymentList;
    }

    public void writeAll() {
        //ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        //File file = new File(classLoader.getResource(filename).getFile());

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            for (Payment p : paymentList) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllPayments() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}