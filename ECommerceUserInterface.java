//MOHAMMED TAUSEEF ASAD
//501135583



import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals(""))
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts();
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks();
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				String name = "";
				String address = "";

				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();

				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();

				boolean success = amazon.createCustomer(name, address);
				if (!success)
				{
					System.out.println(amazon.getErrorMessage());
				}
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// Get order number from scanner
				if (scanner.hasNextLine())
					orderNumber = scanner.nextLine();
				// Ship order to customer (see ECommerceSystem for the correct method to use
				boolean success = amazon.shipOrder(orderNumber);
				if (!success)
				{
					System.out.println(amazon.getErrorMessage());
				}
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNext())
					customerId = scanner.next();
				// Print all current orders and all shipped orders for this customer
				boolean success = amazon.printOrderHistory(customerId);
				if (!success)
				{
					System.out.println(amazon.getErrorMessage());
				}
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productId = "";
				String customerId = "";

				System.out.print("Product Id: ");
				// Get product Id from scanner
				if (scanner.hasNext())
					productId = scanner.next();

				System.out.print("\nCustomer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNext())
					customerId = scanner.next();
				// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
				String prodOrder = amazon.orderProduct(productId, customerId, "");
				if (prodOrder == null) {
					System.out.println(amazon.getErrorMessage());
				}
				// Print Order Number string returned from method in ECommerceSystem
				else {
					System.out.println(prodOrder);
				}
			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNext())
					productId = scanner.next();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNext())
					customerId = scanner.next();
				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				// get book format and store in options string
				if (scanner.hasNext())
					options = scanner.next();
				// Order product. Check for error message set in ECommerceSystem
				// Print order number string if order number is not null
				String bookOrder = amazon.orderProduct(productId, customerId, options);
				if (bookOrder == null) {
					System.out.println(amazon.getErrorMessage());
				}
				else {
					System.out.println(bookOrder);
				}

			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNext())
					productId = scanner.next();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNext())
					customerId = scanner.next();
				System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
				// get shoe size and store in options
				if (scanner.hasNext())
					options = scanner.next();
				System.out.print("\nColor: \"Black\" \"Brown\": ");
				// get shoe color and append to options
				if (scanner.hasNext())
					options= options + " " + scanner.next();
				//order shoes
				String shoeOrder = amazon.orderProduct(productId, customerId, options);
				if (shoeOrder == null) {
					System.out.println(amazon.getErrorMessage());
				}
				else {
					System.out.println(shoeOrder);
				}

			}


			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// get order number from scanner
				if (scanner.hasNext())
				{
					orderNumber = scanner.next();
				}
				// cancel order. Check for error
				boolean success = amazon.cancelOrder(orderNumber);
				if (!success)
				{
					System.out.println(amazon.getErrorMessage());
				}
			}

			else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) // List all the books by the given author according to year published
			{
				String authorName = "";

				System.out.print("Author's Name: ");
				// get author's name from scanner
				if (scanner.hasNextLine())
				{
					authorName = scanner.nextLine();
				}
				amazon.printAuthorBooks(authorName);

			}
			else if (action.equalsIgnoreCase("SORTBYPRICE")) // sort products by price
			{
				amazon.sortByPrice();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.sortByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			System.out.print("\n>");
		}
	}
}
