package org.example;
import java.sql.Date;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductManager pm = new ProductManager();

        while (true) {
            System.out.println("\n===== PRODUCT MANAGEMENT =====");
            System.out.println("1. Danh sách");
            System.out.println("2. Thêm");
            System.out.println("3. Xóa");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Thống kê");
            System.out.println("0. Thoát");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    pm.listProducts();
                    break;

                case 2:
                    System.out.print("Tên: ");
                    String name = sc.nextLine();

                    System.out.print(" Giá:   ");
                    double price = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Catalog: ");
                    String catalog = sc.nextLine();

                    pm.addProduct(new Product(
                            name, price, title,
                            new Date(System.currentTimeMillis()),
                            catalog
                    ));
                    break;

                case 3:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    pm.delete(id);
                    break;

                case 4:
                    System.out.print("Tên: ");
                    name = sc.nextLine();
                    pm.search(name);
                    break;

                case 5:
                    pm.stats();
                    break;

                case 0:
                    return;
            }
        }
    }
}