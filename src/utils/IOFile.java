package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOFile {
    public static final String USER_PATH = "src/data/user.txt";
    public static final String PRODUCT_PATH = "src/data/product.txt";
    public static final String CART_PATH = "src/data/cart.txt";
    public static final String CATALOG_PATH = "src/data/catalog.txt";
    public static final String ORDER_PATH = "src/data/order.txt";

    public static <T, E> void writeToFile(String path, Map<T, E> list) {
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {

            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T, E> Map<T, E> readFromFile(String path) {
        Map<T, E> list = new HashMap<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            list = (Map<T, E>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
