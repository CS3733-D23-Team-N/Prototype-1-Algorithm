import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "yzhu8", "Ytyt20197!")) {
            //Select test
            PreparedStatement ps = connection.prepareStatement("Select * from l1edges where edgeID = ?");
            ps.setString(1, "CDEPT002L1_CDEPT003L1");

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                System.out.println(result.getString("startNode") + "||" + result.getString("endNode"));
            }
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}