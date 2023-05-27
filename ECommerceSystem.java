//MOHAMMED TAUSEEF ASAD
//501135583



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem {
    private ArrayList<Product>  products = new ArrayList<Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    private ArrayList<Book> booksByAuthor   = new ArrayList<Book>(); // List to store books by author


    // These variables are used to generate order numbers, customer id's, product id's
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;

    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)
    String errMsg = null; //shows null because the result is invalid

    // Random number generator
    Random random = new Random(); //generates random numbers each time

    public ECommerceSystem()
    {
        // NOTE: do not modify or add to these objects!! - the TAs will use for testing
        // If you do the class Shoes bonus, you may add shoe products

        // Create some products. Notice how generateProductId() method is used
        products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
        products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
        products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney", 2007));
        products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
        products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
        products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
        products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast", 2008));
        products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive",2009));
        products.add(new Book("Book", generateProductId(), 44.0, 14, 12, "How to Teach Programming", "T. McInerney",2010));
        products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));
        products.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Ahm Gonna Make You Learn More", "T. McInerney",1997));

        // Creating shoe products.
        products.add(new Shoes("Nike Air Force 1", generateProductId(), 135.0, 35, 26, 46, 54, 29, 11,47,32,53,24));
        products.add(new Shoes("Nike Dunk Low", generateProductId(), 135.0, 34, 17, 34, 56, 11, 27,34,70,12,43));
        products.add(new Shoes("Nike Blazer Mid '77", generateProductId(), 155.0, 45, 67, 23, 44, 25,11,56,56,65,65));
        products.add(new Shoes("Nike Blazer Low", generateProductId(), 115.0, 47, 12, 99, 80, 50, 23,71,32,45,67));

        // Create some customers. Notice how generateCustomerId() method is used
        customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
        customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
        customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
        customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }

    private String generateOrderNumber()
    {
        return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
        return "" + customerId++;
    }

    private String generateProductId()
    {
        return "" + productId++;
    }

    public String getErrorMessage()
    {
        return errMsg;
    }

    public void printAllProducts()
    {
        for (Product p : products)
            p.print();
    }

    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
        for (Product p : products){
            if (p.getCategory().equals(Product.Category.BOOKS))
                p.print(); //prints all books by using p.getCategory() method
        }
    }

    // Print all current orders
    public void printAllOrders()
    {
        for (ProductOrder p : orders) {
            p.print();  //prints all current orders
        }
    }

    public void printAlLBooksByAuthor()
    {
        for (Book b : booksByAuthor) {
            b.print();
        }
    }
    // Print all shipped orders
    public void printAllShippedOrders()
    {
        for (ProductOrder p : shippedOrders)
        {
            p.print(); //prints all orders that has been shipped
        }
    }

    // Print all customers
    public void printCustomers()
    {
        for(Customer c : customers){
            c.print(); //prints all customers
        }
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public boolean printOrderHistory(String customerId)
    {
        // Make sure customer exists - check using customerId
        // If customer does not exist, set errMsg String and return false
        // see video for an appropriate error message string
        // ... code here
        Customer cust = null;
        for (Customer c : customers) //checked if customer exits
        {
            if (c.getId().equals(customerId))
            {
                cust = c;
            }
            else
            {
                errMsg = "Customer " + customerId + " Not Found";
            }
        }
        if (cust == null)
        {
            return false;
        }
        // Print current orders of this customer
        System.out.println("Current Orders of Customer " + customerId);
        // enter code here
        for (ProductOrder p : orders)
        {
            if (p.getCustomer().equals(cust))
            {
                p.print();
            }
        }
        // Print shipped orders of this customer
        System.out.println("\nShipped Orders of Customer " + customerId);
        //enter code here
        for (ProductOrder p : shippedOrders)
        {
            if (p.getCustomer().equals(cust))
            {
                p.print();
            }
        }
        return true;
    }

    public String orderProduct(String productId, String customerId, String productOptions)
    {
        // First check to see if customer object with customerId exists in array list customers
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Customer object
        Customer cust = null; //checked if customer exits or returns null
        for (Customer c : customers)
        {
            if (c.getId().equals(customerId))
            {
                cust = c;
            }
            else
            {
                errMsg = "Customer " + customerId + " Not Found"; //used errMsg to return not found
            }
        }
        if (cust == null)
        {
            return null;
        }
        // Check to see if product object with productId exists in array list of products
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Product object
        Product prod = null;
        for (Product p : products)
        {
            if (p.getId().equals(productId))
            {
                prod = p;
            }
            else
            {
                errMsg = "Product " + productId + " Not Found";
            }

        }
        if (prod == null)
        {
            return null;
        }

        // Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
        // See class Product and class Book for the method validOptions()
        // If options are not valid, set errMsg string and return null;
        if (productOptions != "")
        {
            if (prod.validOptions(productOptions) == false) // Checking if options are not valid
            {
                // Displays error message if user selects invalid option for book product
                if (prod.getCategory().equals(Product.Category.BOOKS))
                {
                    errMsg = "Product Book ProductId " + productId + " Invalid Options: " + productOptions;
                    return null;
                }
                // Displays error message if user selects invalid option for shoe product
                else if (prod.getCategory().equals(Product.Category.CLOTHING))
                {
                    errMsg = "Product Shoe ProductId " + productId + " Invalid Options: " + productOptions;
                    return null;
                }
                // Displays error message if user selects productID for wrong product category
                else
                {
                    errMsg = "Invalid Product Category for ProductID " + productId;
                    return null;
                }

            }
        }
        // Check if the product has stock available (i.e. not 0)
        // See class Product and class Book for the method getStockCount()
        // If no stock available, set errMsg string and return null

        if (prod.getStockCount(productOptions) == 0)
        {
            errMsg = "No " + productOptions + " Stock Available for ProductId " + productId; // Custom error message(not provided on video) if no stock available
            return null;
        }

        // Create a ProductOrder, (make use of generateOrderNumber() method above)
        // reduce stock count of product by 1 (see class Product and class Book)
        // Add to orders list and return order number string
        orders.add(new ProductOrder(generateOrderNumber(), prod, cust, productOptions)); // Adding new order to array list
        prod.reduceStockCount(productOptions);
        return "Order #"+ (orderNumber-1);
    }

    /*
     * Create a new Customer object and add it to the list of customers
     */

    public boolean createCustomer(String name, String address)
    {
        // Check name parameter to make sure it is not null or ""
        // If it is not a valid name, set errMsg (see video) and return false
        // Repeat this check for address parameter
        if (name == null || name == "") {
            errMsg = "Invalid Customer Name"; //setting errMsg
            return false;
        }
        if (address == null || address == "") {
            errMsg = "Invalid Customer Address"; //setting errMsg
            return false;
        }
        // Create a Customer object and add to array list
        else {
            customers.add(new Customer(generateCustomerId(),name, address)); //Adding new customer to array list
            return true;
        }

    }

    public boolean shipOrder(String orderNumber)
    {
        // Check if order number exists first. If it doesn't, set errMsg to a message (see video)
        // and return false
        // Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
        // return a reference to the order
        ProductOrder so = null;
        for (ProductOrder p: orders){
            if (orderNumber.equals(p.getOrderNumber())){
                so = p;

                shippedOrders.add(so);
                orders.remove(so);
                so.print();
                break;
            }
            else
            {
                errMsg = "Order " + orderNumber + " Not Found";
                continue;
            }

        }
        if (so == null){
            return false;
        }
        return true;

    }


    /*
     * Cancel a specific order based on order number
     */
    public boolean cancelOrder(String orderNumber) {
        // Check if order number exists first. If it doesn't, set errMsg to a message (see video)
        // and return false
        ArrayList<ProductOrder> removedOrders = new ArrayList<ProductOrder>(); // Temporary list to hold element we want to remove
        ProductOrder temp = null;
        for (ProductOrder p : orders) {
            if (orderNumber.equals(p.getOrderNumber())) {
                removedOrders.add(p);
                temp = p;
                break;
            } else {
                errMsg = "Order " + orderNumber + " Not Found";
                continue;
            }
        }
        orders.removeAll(removedOrders);
        if (temp == null) {
            return false;
        }
        return true;

    }
    public void printAuthorBooks(String authorName){
        // Method to print all books by the given author in increasing order of year
        //published.
        ArrayList <Book> BksByAuthor = new ArrayList<Book>();
        Book bks = null;
        for (Product b : products) {
            if (b.getCategory().equals(Product.Category.BOOKS)) {
                booksByAuthor.add((Book) b);
                bks = (Book) b;
                if (bks.getAuthor().equals(authorName))
                    BksByAuthor.add(bks);

            }
        }
        // Comparator to sort books in ascending order of year published
        Comparator<Book> ByAuthorNAME = new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if(o1.getYear() > o2.getYear())
                    return 1;
                else
                    return -1;
            }
        };
        Collections.sort(BksByAuthor,ByAuthorNAME);
        for (Book b : BksByAuthor)
            b.print();
    }

    // Sort products by increasing price
    public void sortByPrice()
    {
        Comparator<Product> comByPrice = new Comparator<Product>()
        {
            public int compare(Product prod1, Product prod2)
            {
                if(prod1.getPrice()>prod2.getPrice())
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            }
        };
        Collections.sort(products, comByPrice);
    }


    // Sort products alphabetically by product name
    public void sortByName()
    {
        Comparator<Product> comByName = new Comparator<Product>()
        {
            public int compare(Product prod1, Product prod2)
            {
                return prod1.getName().compareTo(prod2.getName());
            }
        };
        Collections.sort(products, comByName);
    }


    // Sort products alphabetically by product name
    public void sortCustomersByName()
    {
        Comparator<Customer> comByCustName = new Comparator<Customer>()
        {
            public int compare(Customer cust1, Customer cust2)
            {
                return cust1.getName().compareTo(cust2.getName());
            }
        };
        Collections.sort(customers, comByCustName);
    }


}
