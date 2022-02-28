import java.sql.* ;

public class DataBaseMain {

    public static void main(String[] args) {

        insertInDataBase() ;
        readFromDataBase() ;
        //updateDataBase() ;
        //deleteFromDataBase() ;

    }

    public static void insertInDataBase() {

        final String URL = "jdbc:postgresql://idc.cluster-custom-cjcsijnttbb2.eu-central-1.rds.amazonaws.com:5432/AZadic";
        final String USERNAME = "ftuser" ;
        final String PASSWORD = "password1" ;
        try {
            Connection myConn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            /* create a preparedstatement and populate with data*/
            PreparedStatement preparedStatement = myConn.prepareStatement("INSERT INTO produse (numeprodus,stock,pret) VALUES(?,?,?)");
            preparedStatement.setString(1,"Ciocolata calda");
            preparedStatement.setInt(2,300);
            preparedStatement.setDouble(3,10.1231);
             /* execute */
            int updatedValues = preparedStatement.executeUpdate();

            preparedStatement.close();
            myConn.close();
        }catch(SQLException e) {
            if ( e.getMessage().contains("dublicate key value violates")) {
                System.out.println("You already have one in database!");
            }
            if ( e.getMessage().contains("does not exist")) {
                System.out.println("Connection error.");
            }
        }

    }

    private static void readFromDataBase( ) {

        /*Connect to database... */
        final String URL = "jdbc:postgresql://idc.cluster-custom-cjcsijnttbb2.eu-central-1.rds.amazonaws.com:5432/AZadic";
        final String USERNAME = "ftuser" ;
        final String PASSWORD = "password1" ;
        try {
            Connection myConn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            /* Create a query */
            Statement statement = myConn.createStatement();
            /* Execute the query */
            ResultSet resultSet = statement.executeQuery("SELECT * FROM produse");

            while (resultSet.next()) {
                String numeProdus = resultSet.getString("numeprodus");
                String stock = resultSet.getString("stock");
                double pret = resultSet.getDouble("pret") ;
                System.out.println(numeProdus + " " + stock + " " + pret);
            }

            resultSet.close();
            statement.close();
            myConn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateDataBase () {

        /*Connect to database... */
        final String URL = "jdbc:postgresql://idc.cluster-custom-cjcsijnttbb2.eu-central-1.rds.amazonaws.com:5432/AZadic";
        final String USERNAME = "ftuser" ;
        final String PASSWORD = "password1" ;
        try {
            /* update users */
            Connection myConn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = myConn.prepareStatement("UPDATE users set userpassword=? where username=?");
            preparedStatement.setString(1,"hd95rcd");
            preparedStatement.setString(2,"dZadic");
            /* execute */
            int updatedValues = preparedStatement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteFromDataBase () {

    }

}
