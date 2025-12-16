import javafx.application.Application;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.*;
import java.util.*;

public class QuizGameAllInOne extends Application {

    // ====== DB CONFIG ======
    static final String DB_URL = "jdbc:mysql://localhost:3306/quizdb";
    static final String DB_USER = "root";
    static final String DB_PASS = "";

    // ====== GAME VARS ======
    List<Map<String,String>> questions = new ArrayList<>();
    int index = 0, score = 0;
    String playerName = "Player";
    Label qLabel = new Label(), timerLabel = new Label("Time: 15");
    ToggleGroup tg = new ToggleGroup();
    RadioButton r1 = new RadioButton(), r2 = new RadioButton(), r3 = new RadioButton(), r4 = new RadioButton();
    Timeline timer;
    Stage stage;

    @Override
    public void start(Stage s) {
        stage = s;

        TextInputDialog d = new TextInputDialog();
        d.setHeaderText("Enter Your Name");
        d.showAndWait().ifPresent(n -> playerName = n);

        loadQuestions();

        r1.setToggleGroup(tg); r2.setToggleGroup(tg);
        r3.setToggleGroup(tg); r4.setToggleGroup(tg);

        Button next = new Button("Next");
        Button restart = new Button("Restart");
        Button exit = new Button("Exit");

        next.setOnAction(e -> nextQ());
        restart.setOnAction(e -> restartGame());
        exit.setOnAction(e -> stage.close());

        VBox box = new VBox(10, timerLabel, qLabel, r1, r2, r3, r4, next, restart, exit);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20));

        stage.setScene(new Scene(box, 420, 420));
        stage.setTitle("Quiz Game");
        stage.show();

        showQuestion();
    }

    void loadQuestions() {
        questions.clear();
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM questions ORDER BY RAND() LIMIT 5")) {
            while (rs.next()) {
                Map<String,String> q = new HashMap<>();
                q.put("q", rs.getString("question"));
                q.put("a", rs.getString("opt1"));
                q.put("b", rs.getString("opt2"));
                q.put("c", rs.getString("opt3"));
                q.put("d", rs.getString("opt4"));
                q.put("ans", rs.getString("answer"));
                questions.add(q);
            }
        } catch (Exception e) { e.printStackTrace(); }
        index = 0; score = 0;
    }

    void showQuestion() {
        if (timer != null) timer.stop();
        if (index >= questions.size()) { finish(); return; }

        Map<String,String> q = questions.get(index);
        qLabel.setText((index+1)+". "+q.get("q"));
        r1.setText(q.get("a")); r2.setText(q.get("b"));
        r3.setText(q.get("c")); r4.setText(q.get("d"));
        tg.selectToggle(null);

        timerLabel.setText("Time: 15");
        timer = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            int t = Integer.parseInt(timerLabel.getText().replace("Time: ","")) - 1;
            timerLabel.setText("Time: " + t);
            if (t <= 0) nextQ();
        }));
        timer.setCycleCount(15);
        timer.play();
    }

    void nextQ() {
        if (timer != null) timer.stop();
        RadioButton sel = (RadioButton) tg.getSelectedToggle();
        if (sel != null && sel.getText().equals(questions.get(index).get("ans"))) score++;
        index++;
        showQuestion();
    }

    void finish() {
        saveScore();
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Score: " + score + "/5");
        a.showAndWait();
    }

    void restartGame() {
        loadQuestions();
        showQuestion();
    }

    void saveScore() {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement p = c.prepareStatement("INSERT INTO results(name,score) VALUES(?,?)")) {
            p.setString(1, playerName);
            p.setInt(2, score);
            p.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
