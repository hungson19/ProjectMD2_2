package business.designimpl;import business.design.IProduct;import business.entity.Product;import business.entity.Catalog;import utils.IOFile;import utils.validate.ProductValid;import java.util.*;public class ProductService implements IProduct {    private static Map<Integer, Product> products = new HashMap<>();    public ProductService() {        products = IOFile.readFromFile(IOFile.PRODUCT_PATH);    }    public void displayProducts(Map<String, Catalog> categories) {        System.out.println("Danh sách sản phẩm:");        for (Product product : products.values()) {            Catalog catalog = categories.get(product.getCategoryId());            if (product.isStatus() && catalog.isStatus()) {                System.out.println(product + " catalog: " + catalog.getCatalogName());            }        }    }    public void addProduct(Scanner scanner, CategoryService categoryService) {        String productName = ProductValid.validName( products.values(), scanner);        double unitPrice = ProductValid.validPrice(scanner);        int stock= ProductValid.validStock(scanner);        String description = ProductValid.validDesc(scanner);        while (true) {            categoryService.displayCategories();            System.out.print("Chọn ID danh mục của sản phẩm: ");            String categoryId = scanner.nextLine();            // Lấy danh mục từ ID            Catalog category = categoryService.getCategories().get(categoryId);            if (category != null) {                int productId = getMaxId() + 1;                // Tạo đối tượng Product với thông tin đã nhập                Product product = new Product(productId, productName, categoryId, description, unitPrice, stock);                // Thêm sản phẩm vào danh sách sản phẩm                products.put(productId, product);                IOFile.writeToFile(IOFile.PRODUCT_PATH, products);                System.out.println("Sản phẩm đã được thêm vào.");                return;            } else {                System.out.println("Không tìm thấy danh mục.");            }        }    }    public void editProduct(Scanner scanner, CategoryService categoryService) {        System.out.print("Nhập ID của sản phẩm cần chỉnh sửa: ");        int productId = Integer.parseInt(scanner.nextLine());        if (products.containsKey(productId)) {            Product product = products.get(productId);            String productName = ProductValid.validName(products.values(), scanner);            double unitPrice = ProductValid.validPrice(scanner);            int stock= ProductValid.validStock(scanner);            String description = ProductValid.validDesc(scanner);            product.setProductName(productName);            product.setDescription(description);            product.setUnitPrice(unitPrice);            product.setStock(stock);            while (true) {                System.out.print("Nhập ID danh mục mới của sản phẩm: ");                categoryService.displayCategories();                String newCategoryId = scanner.nextLine();                Catalog category = categoryService.getCategories().get(newCategoryId);                if (category != null) {                    product.setCategoryId(newCategoryId);                    break;                } else {                    System.out.println("Không tìm thấy danh mục.");                }            }            IOFile.writeToFile(IOFile.PRODUCT_PATH, products);            System.out.println("Thông tin sản phẩm đã được cập nhật.");        } else {            System.out.println("Không tìm thấy sản phẩm có ID là " + productId);        }    }    public void hideProductById(Scanner scanner) {        System.out.print("Nhập ID của sản phẩm cần ẩn: ");        int productId = Integer.parseInt(scanner.nextLine());        if (products.containsKey(productId)) {            products.get(productId).setStatus(false);            System.out.println("Sản phẩm đã được ẩn.");            IOFile.writeToFile(IOFile.PRODUCT_PATH, products);        } else {            System.out.println("Không tìm thấy sản phẩm có ID là " + productId);        }    }    public void hideProductsByIds(Scanner scanner) {        System.out.print("Nhập danh sách ID của các sản phẩm cần ẩn (cách nhau bằng dấu phẩy): ");        String[] productIds = scanner.nextLine().split(",");        int count = 0;        for (String productId : productIds) {            if (products.containsKey(productId)) {                products.get(productId).setStatus(false);                count++;            }        }        System.out.println(count + " sản phẩm đã được ẩn.");        IOFile.writeToFile(IOFile.PRODUCT_PATH, products);    }    public void searchProductByName(Scanner scanner) {        System.out.print("Nhập tên sản phẩm cần tìm: ");        String name = scanner.nextLine();        List<Product> foundProducts = new ArrayList<>();        for (Product product : products.values()) {            if (product.getProductName().toLowerCase().contains(name.toLowerCase())) {                foundProducts.add(product);            }        }        if (foundProducts.isEmpty()) {            System.out.println("Không tìm thấy sản phẩm nào có tên chứa '" + name + "'.");        } else {            System.out.println("Kết quả tìm kiếm:");            for (Product product : foundProducts) {                System.out.println(product);            }        }    }    private Catalog findCategoryByName(List<Catalog> categories, String name) {        for (Catalog catalog : categories) {            if (catalog.getCatalogName().equalsIgnoreCase(name)) {                return catalog;            }        }        return null;    }    public int getMaxId() {        int idMax = 0;        for (Product p : products.values()) {            if (idMax < p.getProductId()) {                idMax = p.getProductId();            }        }        return idMax;    }    public void displayProductsByCategory(Scanner scanner, CategoryService categoryService) {        System.out.println("Nhập mã danh mục:");        String id = scanner.nextLine();        for (Product product : products.values()) {            if (product.getCategoryId().equals(id)) {                System.out.println(product);            }        }    }    public void displayProductsSortedByPrice(boolean ascending) {        List<Product> sortedProducts = new ArrayList<>(products.values());        if (ascending) {            sortedProducts.sort(Comparator.comparingDouble(Product::getUnitPrice));        } else {            sortedProducts.sort((p1, p2) -> Double.compare(p2.getUnitPrice(), p1.getUnitPrice()));        }        System.out.println("Danh sách sản phẩm được sắp xếp theo giá:");        for (Product product : sortedProducts) {            System.out.println(product);        }    }    public void addToCart(UserService userService,Scanner scanner, CartService cartService) {        System.out.print("Nhập ID của sản phẩm cần thêm vào giỏ hàng: ");        int productId = Integer.parseInt(scanner.nextLine());        if (products.containsKey(productId)) {            Product product = products.get(productId);            int quantity;            while (true) {                try {                    System.out.print("Nhập số lượng: ");                    System.out.println("Số lượng sản phẩm hiện có: " + product.getStock());                    quantity = Integer.parseInt(scanner.nextLine());                    if (quantity < 0 || quantity > product.getStock()) {                        System.out.println("Số lượng không hợp lệ");                        continue;                    }                    break;                } catch (Exception e) {                    System.out.println("sai định dạng");                }            }            cartService.addToCart(userService,product, quantity);            System.out.println("Đã thêm sản phẩm vào giỏ hàng.");        } else {            System.out.println("Không tìm thấy sản phẩm có ID là " + productId);        }    }    public void displayHotProducts() {        System.out.println("Danh sách sản phẩm nổi bật:");        for (Product product : products.values()) {            if (product.isHotProduct()) {                System.out.println(product);            }        }    }    public Product findById(int id){        for (Product product : products.values()) {            if (product.getProductId() == id) {                return product;            }        }        return null;    }}