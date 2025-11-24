import java.util.*;
import javax.swing.*;

public class QuizByte {

    static class Question {
        String q, a, b, c, d, correct;
        Question(String q, String a, String b, String c, String d, String correct) {
            this.q = q;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.correct = correct;
        }
    }

    static ArrayList<Question> list = new ArrayList<>();
    static {
        list.add(new Question("What is JVM?", "Java Version Manager", "Java Virtual Machine", "Joint Virtual Mode", "Just Vector Model", "b"));
        list.add(new Question("Which keyword creates an object?", "new", "create", "make", "obj", "a"));
        list.add(new Question("Which is not OOP?", "Inheritance", "Abstraction", "Encapsulation", "Compilation", "d"));
    }

    JFrame loginFrame, menuFrame, quizFrame, adminFrame;
    JTextField nameField;
    String username = "";
    int index = 0;
    int score = 0;

    public QuizByte() {
        loginScreen();
    }

    void loginScreen() {
        loginFrame = new JFrame("QuizByte - Login");
        loginFrame.setSize(400, 250);
        loginFrame.setLayout(null);

        JLabel name = new JLabel("Enter Your Name:");
        name.setBounds(30, 30, 200, 30);
        nameField = new JTextField();
        nameField.setBounds(150, 30, 200, 30);

        JButton start = new JButton("Start Quiz");
        start.setBounds(40, 110, 130, 40);

        JButton admin = new JButton("Admin Mode");
        admin.setBounds(200, 110, 130, 40);

        start.addActionListener(e -> {
            username = nameField.getText().trim();
            if (username.length() == 0) {
                JOptionPane.showMessageDialog(loginFrame, "Enter name first");
            } else {
                loginFrame.dispose();
                menuScreen();
            }
        });

        admin.addActionListener(e -> {
            passwordCheck();
        });

        loginFrame.add(name);
        loginFrame.add(nameField);
        loginFrame.add(start);
        loginFrame.add(admin);

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    void passwordCheck() {
        String pass = JOptionPane.showInputDialog("Enter Admin Password:");
        if ("admin".equals(pass)) {
            loginFrame.dispose();
            adminPanel();
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Wrong password");
        }
    }

    void menuScreen() {
        menuFrame = new JFrame("Menu");
        menuFrame.setSize(400, 250);
        menuFrame.setLayout(null);

        JLabel welcome = new JLabel("Welcome, " + username);
        welcome.setBounds(120, 30, 200, 30);

        JButton play = new JButton("Start Quiz");
        play.setBounds(120, 80, 150, 40);

        JButton exit = new JButton("Exit");
        exit.setBounds(120, 140, 150, 40);

        play.addActionListener(e -> {
            menuFrame.dispose();
            index = 0;
            score = 0;
            quizScreen();
        });

        exit.addActionListener(e -> System.exit(0));

        menuFrame.add(welcome);
        menuFrame.add(play);
        menuFrame.add(exit);

        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }

    void quizScreen() {
        quizFrame = new JFrame("Quiz");
        quizFrame.setSize(500, 350);
        quizFrame.setLayout(null);

        Question q = list.get(index);

        JLabel ql = new JLabel("Q" + (index + 1) + ": " + q.q);
        ql.setBounds(20, 20, 450, 30);

        JRadioButton A = new JRadioButton("A. " + q.a);
        JRadioButton B = new JRadioButton("B. " + q.b);
        JRadioButton C = new JRadioButton("C. " + q.c);
        JRadioButton D = new JRadioButton("D. " + q.d);

        A.setBounds(20, 70, 300, 30);
        B.setBounds(20, 110, 300, 30);
        C.setBounds(20, 150, 300, 30);
        D.setBounds(20, 190, 300, 30);

        ButtonGroup grp = new ButtonGroup();
        grp.add(A); grp.add(B); grp.add(C); grp.add(D);

        JButton next = new JButton(index == list.size() - 1 ? "Finish" : "Next");
        next.setBounds(180, 250, 120, 40);

        next.addActionListener(e -> {
            String chosen = "";
            if (A.isSelected()) chosen = "a";
            if (B.isSelected()) chosen = "b";
            if (C.isSelected()) chosen = "c";
            if (D.isSelected()) chosen = "d";

            if (chosen.equals(q.correct)) score++;

            quizFrame.dispose();
            index++;

            if (index == list.size()) {
                showResult();
            } else {
                quizScreen();
            }
        });

        quizFrame.add(ql);
        quizFrame.add(A);
        quizFrame.add(B);
        quizFrame.add(C);
        quizFrame.add(D);
        quizFrame.add(next);

        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quizFrame.setLocationRelativeTo(null);
        quizFrame.setVisible(true);
    }

    void showResult() {
        JOptionPane.showMessageDialog(null, username + ", your score: " + score + "/" + list.size());
        menuScreen();
    }

    void adminPanel() {
        adminFrame = new JFrame("Admin Panel");
        adminFrame.setSize(500, 450);
        adminFrame.setLayout(null);

        JLabel l1 = new JLabel("Question:");
        JTextField qf = new JTextField();
        JLabel l2 = new JLabel("Option A:");
        JTextField a = new JTextField();
        JLabel l3 = new JLabel("Option B:");
        JTextField b = new JTextField();
        JLabel l4 = new JLabel("Option C:");
        JTextField c = new JTextField();
        JLabel l5 = new JLabel("Option D:");
        JTextField d = new JTextField();
        JLabel l6 = new JLabel("Correct (a/b/c/d):");
        JTextField correct = new JTextField();

        l1.setBounds(30, 20, 200, 30);     qf.setBounds(150, 20, 300, 30);
        l2.setBounds(30, 70, 200, 30);     a.setBounds(150, 70, 300, 30);
        l3.setBounds(30, 120, 200, 30);    b.setBounds(150, 120, 300, 30);
        l4.setBounds(30, 170, 200, 30);    c.setBounds(150, 170, 300, 30);
        l5.setBounds(30, 220, 200, 30);    d.setBounds(150, 220, 300, 30);
        l6.setBounds(30, 270, 200, 30);    correct.setBounds(150, 270, 300, 30);

        JButton add = new JButton("Add Question");
        add.setBounds(150, 330, 150, 40);

        add.addActionListener(e -> {
            String qq = qf.getText();
            String qa = a.getText();
            String qb = b.getText();
            String qc = c.getText();
            String qd = d.getText();
            String cr = correct.getText();

            if (qq.isEmpty() || qa.isEmpty() || qb.isEmpty() || qc.isEmpty() || qd.isEmpty() || cr.isEmpty()) {
                JOptionPane.showMessageDialog(adminFrame, "Fill all fields");
                return;
            }

            list.add(new Question(qq, qa, qb, qc, qd, cr));
            JOptionPane.showMessageDialog(adminFrame, "Added Successfully");

            qf.setText("");
            a.setText("");
            b.setText("");
            c.setText("");
            d.setText("");
            correct.setText("");
        });

        adminFrame.add(l1); adminFrame.add(qf);
        adminFrame.add(l2); adminFrame.add(a);
        adminFrame.add(l3); adminFrame.add(b);
        adminFrame.add(l4); adminFrame.add(c);
        adminFrame.add(l5); adminFrame.add(d);
        adminFrame.add(l6); adminFrame.add(correct);
        adminFrame.add(add);

        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new QuizByte();
    }
}
