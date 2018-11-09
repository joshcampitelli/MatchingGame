import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ButtonGroup;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

public class GameSettings extends JFrame
{
    private JPanel panel;
    private JLabel title;
    private JLabel difficulty;
    private JLabel players;
    private JButton startButton;
    private JButton exitButton;
    private ButtonGroup buttonGroup;
    private JRadioButton e, m, h, ex, t;
    private JTextField p1, p2, p3, p4;
    private JLabel l1, l2, l3, l4;
    private String s1, s2, s3, s4;

    public GameSettings() {
        //================JFRAME SETTINGS=================//
        super("Game Settings");
        setLocationRelativeTo(null);        
        setLayout(null);       
        setSize(375, 375);     //Set Default Size of Frame
        setResizable(false);   //Disable Resizing the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //=================================================//

        //=================JFRAME CONTENT==================//
        title = new JLabel("MATCHING GAME");
        title.setFont(new Font("Calibri", Font.PLAIN, 40));
        title.setBounds(40, 20, 700, 50);
        add(title);

        players = new JLabel("Players: ");
        players.setFont(new Font("Calibri", Font.PLAIN, 20));
        players.setBounds(75, 75, 700, 50);
        add(players);   

        p1 = new JTextField();
        l1 = new JLabel("Player 1: ");
        l1.setBounds(20, 120, 75, 20);
        p1.setBounds(75, 120, 75, 20);
        add(l1);
        add(p1);

        p2 = new JTextField();
        l2 = new JLabel("Player 2: ");
        l2.setBounds(20, 140, 75, 20);
        p2.setBounds(75, 140, 75, 20);
        add(l2);
        add(p2);

        p3 = new JTextField();
        l3 = new JLabel("Player 3: ");
        l3.setBounds(20, 160, 75, 20);
        p3.setBounds(75, 160, 75, 20);
        add(l3);
        add(p3);

        p4 = new JTextField();
        l4 = new JLabel("Player 4: ");
        l4.setBounds(20, 180, 75, 20);
        p4.setBounds(75, 180, 75, 20);
        add(l4);
        add(p4);

        difficulty = new JLabel("Difficulty: ");
        difficulty.setFont(new Font("Calibri", Font.PLAIN, 20));
        difficulty.setBounds(200, 75, 700, 50);
        add(difficulty);

        buttonGroup = new ButtonGroup();

        e = new JRadioButton("Easy");
        e.setBounds(220, 120, 150, 20);
        buttonGroup.add(e);
        add(e);

        m = new JRadioButton("Medium");
        m.setBounds(220, 140, 150, 20);
        buttonGroup.add(m);
        add(m);

        h = new JRadioButton("Hard");
        h.setBounds(220, 160, 150, 20);
        buttonGroup.add(h);
        add(h);        

        ex = new JRadioButton("Expert");
        ex.setBounds(220, 180, 150, 20);
        buttonGroup.add(ex);
        add(ex);    
        
        t = new JRadioButton("Timed Game");
        t.setBounds(175, 210, 150, 20);        
        add(t);

        startButton = new JButton("START");
        startButton.setBounds(75, 250, 100, 50);
        add(startButton);

        exitButton = new JButton("QUIT");   
        exitButton.setBounds(200, 250, 100, 50);        
        add(exitButton);
        //==================================================//

        setVisible(true);
        buttonHandlers();
    }

    private void buttonHandlers() {
        ButtonHandler handler = new ButtonHandler();
        startButton.addActionListener(handler); 
        exitButton.addActionListener((ActionEvent event) -> {
                System.exit(0);
            });
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            s1 = p1.getText();
            s2 = p2.getText();
            s3 = p3.getText();
            s4 = p4.getText();

            if (getSelectedButtonText(buttonGroup) != null) {
                if (getSelectedButtonText(buttonGroup).equals("Easy")) {
                    MatchingGame game = new MatchingGame(4, 4, s1, s2, s3, s4);
                } else if (getSelectedButtonText(buttonGroup).equals("Medium")) {
                    MatchingGame game = new MatchingGame(6, 6, s1, s2, s3, s4);
                } else if (getSelectedButtonText(buttonGroup).equals("Hard")) {
                    MatchingGame game = new MatchingGame(8, 8, s1, s2, s3, s4);
                } else if (getSelectedButtonText(buttonGroup).equals("Expert")) {
                    MatchingGame game = new MatchingGame(10, 10, s1, s2, s3, s4);
                }
            } else {
                JOptionPane.showMessageDialog(null, "you must select a dificulty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        public String getSelectedButtonText(ButtonGroup buttonGroup) {
            for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();

                if (button.isSelected()) {
                    return button.getText();
                }
            }

            return null;
        }
    }
}
