import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Write a description of class MatchingGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchingGame
{
    //Will need an aray List of players.
    private ArrayList<String> player;
    private String currentPlayer;

    //Holds the winning players name.
    private String winner;

    //Number of unmatched buttons
    private int numUnMatched;
    private int rows;
    private int cols;
    
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JLabel topLabel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem newMenuItem;
    private JButton tempButton;    
    private ArrayList<JButton> list;
    private ArrayList<Image> iconList;

    //PlayGame will provide the number of rows and cols
    //Depending on the difficulty selected by the user
    public MatchingGame(int row, int col, String p1, String p2, String p3, String p4) {
        rows = row;
        cols = col;
        numUnMatched = rows * cols;
        label = new JLabel("Game in progress: It is " + p1 + "'s turn.");
        label.setFont(new Font("Calibri", Font.PLAIN, 24));
        label.setHorizontalAlignment(JLabel.CENTER);

        frame = new JFrame(); 
        frame.setSize(700, 700);

        //Center the Frame upon open
        frame.setLocationRelativeTo(null);

        // make it easy to close the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(rows, cols));

        ButtonHandler handler = new ButtonHandler(); 
        list = new ArrayList<JButton>();
        iconList = new ArrayList<Image>();
        int offSet = 0;
        int name = 0;
        for (int i = 0; i < rows * cols; i++) {                   
            JButton button = new JButton();   
            button.addActionListener(handler); 
            button.setFont(new Font("Calibri", Font.PLAIN, 110 - rows * cols));
            //button.setBackground(new Color(51, 51, 51));
            button.setOpaque(true);
            if (offSet == 2) {
                name ++;
                offSet = 0;
            }
            offSet ++;

            button.setName("" + name);
            list.add(button);               

        }
        //RFACTOR CODE, INCLUDE METHODS WHICH SO THESE TASKS
        //===========Randomize Buttons===============//
        long seed = System.nanoTime();
        Collections.shuffle(list, new Random(seed));   
        //============================================//
        //============Populate JPanel=================//
        for (JButton button : list) {
            panel.add(button);
        } 

        frame.add(panel);
        frame.add(label,BorderLayout.SOUTH);
        //============================================//
        //==============ADDING MENU TO FRAME==========//
        fileMenu = new JMenu("File");
        fileMenu.setFont(new Font("Calibri", Font.PLAIN, 24));
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setFont(new Font("Calibri", Font.PLAIN, 24));
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        newMenuItem = new JMenuItem("New Game");
        newMenuItem.setFont(new Font("Calibri", Font.PLAIN, 24));
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        exitMenuItem.addActionListener((ActionEvent event) -> {
                System.exit(0);
            });

        newMenuItem.addActionListener((ActionEvent event) -> {
                for (JButton button : list) {                    
                    button.setEnabled(true);
                    button.setText("");
                }                

            });

        fileMenu.add(exitMenuItem);
        fileMenu.add(newMenuItem);

        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        //==============================================//

        frame.setVisible(true);
    }
    //Need to store all JButtons by there corresponding index posions (on the board)
    //Two of which will be the
    private void populateMap(JButton board[][]) {

    }
    //This is where the button click implementation will occur
    public class ButtonHandler implements ActionListener{  
        boolean ignore = false;
        //This method is called when you click on any of the buttons 
        //Store the button clicked, check if the button clicked is null
        //if no then compare, if same disable both, otherwise rehide each
        public void actionPerformed(ActionEvent e) {
            if (ignore) {
                return;
            } else if (numUnMatched <= 0) {
                label.setText("GAME OVER!");
            }
            JButton button = (JButton)e.getSource();
            button.setEnabled(false);
            button.setText(button.getText());            

            if (tempButton != null && tempButton.getName().equals(button.getName())) {
                //Two buttons, they are Same               
                //===========DISABLE BUTTONS============
                tempButton.setEnabled(false);
                tempButton.setText(tempButton.getName());
                button.setEnabled(false);
                button.setText(button.getName());
                numUnMatched -= 2;
                //======================================
                //Dereference the temp button    
                tempButton = null;                
            } else if (tempButton != null && !(tempButton.getName().equals(button.getName()))) {
                ignore = true;
                //Two buttons, Not the Same             
                button.setText(button.getName());               

                Timer timer = new Timer();

                TimerTask task = new TimerTask() {
                        int secondsToWait = 1;
                        @Override                        
                        public void run() {                        
                            secondsToWait--;
                            button.setEnabled(false);
                            button.setText(button.getName());                            
                            tempButton.setText(tempButton.getName());

                            if (secondsToWait == 0) {
                                tempButton.setEnabled(true);
                                tempButton.setText("");
                                button.setEnabled(true);
                                button.setText("");            
                                tempButton = null;

                                timer.cancel();
                                timer.purge();                                
                            }                            
                        }
                    };
                timer.scheduleAtFixedRate(task, 1000, 1000); 
                ignore = false;
            } else if (tempButton == null) {
                tempButton = button;   
                tempButton.setText(tempButton.getName());
                tempButton.setEnabled(false);
            }
            ignore = false;
        }        
    }
}
