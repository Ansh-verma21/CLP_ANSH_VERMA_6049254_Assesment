package cg.demo.spring.Assessment;



import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DaoClient {
    static OrderDao dao = new OrderDaoImpl();
    static Scanner scan = new Scanner(System.in);
        
	public static void main(String[] args) {
		String opt = null;
		do {
			System.out.println("1-ADD, 2--VIEW BY ORDER ID, 3--VIEW BY CUSTOMER NAME");
			int mtype = scan.nextInt();
			processMenu(mtype);
			System.out.println("press y to continue");
			opt=scan.next();
		}while(opt.equalsIgnoreCase("y"));

	}
	
	public static void processMenu(int mtype) {
		switch(mtype) {
		case 1:
			addOrder();
			break;
		case 2:
			viewOrderByOrderID();
			break;
		case 3:
			viewOrdersByCustName();
			break;
		default:
			System.out.println("Invalid option");
		}
	}

	public static void addOrder() {
		System.out.print("Enter customer id ");
        int id = scan.nextInt();

        System.out.print("Enter Order Amount ");
        double amt = scan.nextDouble();

        Order o = new Order(LocalDate.now(), amt);

        boolean flag = dao.addOrder(o, id);

        if (flag) {
            System.out.println("Order Added ");
        } else {
            System.out.println("Customer not found with id " + id);
        }		
	}
	
	public static void viewOrderByOrderID() {
		System.out.print("Enter order id ");
        int id = scan.nextInt();

        Order result = dao.getOrder(id);

        if (result == null) {
            System.out.println("No Order Found with ID: " + id);
        } else {
            System.out.println(
                "Order ID: "  + result.getOrderId()   +
                " Date: "     + result.getOrderDate() +
                " Amount: "   + result.getOrderAmt()  +
                " Customer: " + result.getCustomer().getCustomerName()
            );
        }
	}
	
	public static void viewOrdersByCustName() {
		System.out.print("Enter customer name ");
        scan.nextLine();                          
        String name = scan.nextLine();

        List<Order> list = dao.getOrders(name);

        if (list.isEmpty()) {
            System.out.println("No orders found for " + name);
        } else {
            for (Order o : list) {
                System.out.println("Order ID "  + o.getOrderId()   +" Date "     + o.getOrderDate() +" Amount "   + o.getOrderAmt()  +" Customer " + o.getCustomer().getCustomerName()
                );
            }
        }
	}
}