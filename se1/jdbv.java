import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class JdbcJavaFXSimple extends Application {

    // DB config
    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "root";
    static final String PASS = "";

    @Override
    public void start(Stage stage) {

        Label lbl = new Label("Data from MySQL:");
        TextArea area = new TextArea();
        area.setEditable(false);

        Button loadBtn = new Button("Load Data");
        loadBtn.setOnAction(e -> loadData(area));

        VBox root = new VBox(10, lbl, loadBtn, area);
        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("JavaFX + JDBC (Java 19)");
        stage.show();
    }

    void loadData(TextArea area) {
        area.clear();
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT name FROM student");

            while (rs.next()) {
                area.appendText(rs.getString("name") + "\n");
            }

            con.close();
        } catch (Exception ex) {
            area.setText("DB Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
