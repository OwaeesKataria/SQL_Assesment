package Product;
import java.util.*;

class Product implements Comparable<Product> {
    private int productId;
    private String productName;
    private String productType;
    private String productCategory;
    private double productPrice;

    public Product(int productId, String productName, String productType, String productCategory, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public double getProductPrice() {
        return productPrice;
    }

    @Override
    public int compareTo(Product other) {
        return Double.compare(this.productPrice, other.productPrice);
    }

    @Override
    public String toString() {
        return "Product [ID: " + productId +
               ", Name: " + productName +
               ", Type: " + productType +
               ", Category: " + productCategory +
               ", Price: " + productPrice + "]";
    }
}

class CategoryPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        int categoryCompare = p1.getProductCategory().compareTo(p2.getProductCategory());
        if (categoryCompare == 0) {
            return Double.compare(p1.getProductPrice(), p2.getProductPrice());
        }
        return categoryCompare;
    }
}

public class ProductSortApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        System.out.println("Enter the product details:");

        while (true) {
            System.out.print("Enter product id: ");
            int productId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter product Name: ");
            String productName = scanner.nextLine();

            System.out.print("Enter product category: ");
            String productCategory = scanner.nextLine();

            System.out.print("Enter product price: ");
            double productPrice = scanner.nextDouble();

            Product product = new Product(productId, productName, "", productCategory, productPrice);
            products.add(product);

            System.out.print("Do you want to add another product? (yes/no): ");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("no")) {
                break;
            }
            scanner.nextLine(); // Consume newline
        }

        Collections.sort(products); // Sort by price using Comparable

        System.out.println("Sorted products by price:");
        for (Product product : products) {
            System.out.println(product);
        }

        Collections.sort(products, new CategoryPriceComparator()); // Sort by category and price using Comparator

        System.out.println("Sorted products by category and price:");
        for (Product product : products) {
            System.out.println(product);
        }

        scanner.close();
    }
}
