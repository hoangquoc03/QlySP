package org.example;
import java.sql.*;
public class ProductManager {
    private boolean validate(Product p) {
        if (p.name.isBlank() || p.title.isBlank() || p.catalog.isBlank()) {
            System.out.println(" Không được để trống!");
            return false;
        }
        if (p.price <= 0) {
            System.out.println(" Giá phải > 0!");
            return false;
        }
        return true;
    }

    public void addProduct(Product p) {
        if (!validate(p)) return;

        String sql = "{ call add_product(?, ?, ?, ?, ?) }";

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false);

            try (CallableStatement cs = conn.prepareCall(sql)) {
                cs.setString(1, p.name);
                cs.setDouble(2, p.price);
                cs.setString(3, p.title);
                cs.setDate(4, p.created);
                cs.setString(5, p.catalog);

                cs.execute();
                conn.commit();
                System.out.println(" Thêm thành công!");

            } catch (Exception e) {
                conn.rollback();
                System.out.println( e.getMessage());
            }

        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }

    public void listProducts() {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{ call get_all_products() }");
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt(1) + " | " +
                                rs.getString(2) + " | " +
                                rs.getDouble(3) + " | " +
                                rs.getString(6)
                );
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void search(String name) {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{ call search_product(?) }")) {

            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{ call delete_product(?) }")) {

            cs.setInt(1, id);
            cs.execute();
            System.out.println(" Xóa thành công!");

        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }

    public void stats() {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{ call stats_by_catalog() }");
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getString(1) + ": " + rs.getInt(2));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
