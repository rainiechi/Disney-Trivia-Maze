package View;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static final String GAME_TITLE = "Disney Trivia Maze";
    private static final int BORDER = 15;
    private static final Color BG = new Color(123, 63, 0);
    private JMenuBar myMenuBar;
    private JMenuItem mySaveItem;
    private JMenuItem myLoadItem;
    private JMenuItem myExitItem;
    private JMenuItem myAboutItem;
    private JMenuItem myInstructionItem;

    private JPanel myGamePanel;
    private JButton resumeButton;


    /**
     * GameFrame Constructor.
     * @param theGamePanel the game panel to be contained by GameFrame
     */
    public GameFrame(final JPanel theGamePanel) {
        if (theGamePanel == null) {
            throw new IllegalArgumentException("Game Panel cannot be null");
        }
        resumeButtonSetUp();
        initMenuBar();
        menuBarListener();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(GAME_TITLE);
        myGamePanel = theGamePanel;
        add(myGamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Sets up the resume button that will be used in multiple windows.
     */
    public void resumeButtonSetUp() {
        resumeButton = new JButton("RESUME");
        //disposes the window and resumes game
        resumeButton.addActionListener(theEvent -> {
            Component comp = (Component) theEvent.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        });
    }

    /**
     * Sets up the menubar.
     */
    private void initMenuBar() {
        myMenuBar = new JMenuBar();
        JMenu myGameMenu = new JMenu("Game");
        JMenu myHelpMenu = new JMenu("Help");
        mySaveItem = new JMenuItem("Save");
        myLoadItem = new JMenuItem("Load");
        myExitItem = new JMenuItem("Exit");
        myAboutItem = new JMenuItem("About");
        myInstructionItem = new JMenuItem("Instruction");

        myMenuBar.add(myGameMenu);
        myMenuBar.add(myHelpMenu);

        myGameMenu.add(mySaveItem);
        myGameMenu.add(myLoadItem);
        myGameMenu.add(myExitItem);
        myHelpMenu.add(myAboutItem);
        myHelpMenu.add(myInstructionItem);
        setJMenuBar(myMenuBar);
    }

    /**
     * Adds listeners to Menu items.
     */
    public void menuBarListener() {
        myExitItem.addActionListener(theEvent -> showDialog(new ExitPanel()));
        myAboutItem.addActionListener(theEvent -> showDialog(new AboutPanel()));
        myInstructionItem.addActionListener(theEvent -> showDialog(new InstructionPanel()));
    }

    /**
     * Creates and show JDialog
     * @param thePanel the panel to be contained by the JDialog
     */
    private void showDialog(final JPanel thePanel) {
        JDialog dialog = new JDialog(this, "Dialog", true);
        dialog.getContentPane().add(thePanel);
        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo((Window) this);
        dialog.setVisible(true);
    }


    /**
     * ExitPanel class that creates the exit panel.
     */
    class ExitPanel extends JPanel {
        public ExitPanel() {
            setBackground(BG);
            JLabel exitLabel1 = new JLabel("CONFIRM EXIT");
            exitLabel1.setForeground(Color.ORANGE);
            JLabel exitLabel2 = new JLabel("UNSAVED PROGRESS WILL BE LOST");
            exitLabel2.setForeground(Color.WHITE);

            JPanel exitPanel1 = new JPanel();
            exitPanel1.setOpaque(false);
            exitPanel1.add(exitLabel1);
            JPanel exitPanel2 = new JPanel();
            exitPanel2.setOpaque(false);
            exitPanel2.add(exitLabel2);

            setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER,BORDER));
            setLayout(new GridLayout(4, 1, 10, 10));
            add(exitPanel1);
            add(exitPanel2);

            JButton exitButton = new JButton("EXIT");
            exitButton.addActionListener(theEvent -> dispose());
            add(exitButton);
            add(resumeButton);
        }
    }

    /**
     * AboutPanel class that creates the About panel.
     */
    class AboutPanel extends JPanel {
        public AboutPanel() {
            setBackground(BG);
            JLabel aboutLabel1 = new JLabel("ABOUT");
            aboutLabel1.setForeground(Color.ORANGE);
            JLabel aboutLabel2 = new JLabel("Made with Java 19");
            aboutLabel2.setForeground(Color.WHITE);
            JLabel aboutLabel3 = new JLabel("Created by: Amanda, Rainie, Karan");
            aboutLabel3.setForeground(Color.WHITE);

            JPanel aboutPanel1 = new JPanel();
            aboutPanel1.setOpaque(false);
            aboutPanel1.add(aboutLabel1);

            JPanel aboutPanel2 = new JPanel();
            aboutPanel2.setOpaque(false);
            aboutPanel2.add(aboutLabel2);

            JPanel aboutPanel3 = new JPanel();
            aboutPanel3.setOpaque(false);
            aboutPanel3.add(aboutLabel3);

            setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER,BORDER));
            setLayout(new GridLayout(4, 1, 10, 10));
            add(aboutPanel1);
            add(aboutPanel2);
            add(aboutPanel3);
            add(resumeButton);
        }
    }


    /**
     * InstructionPanel class that creates the Instruction panel.
     */
    class InstructionPanel extends JPanel {
        public InstructionPanel() {
            setBackground(BG);
            JLabel insLabel1 = new JLabel("INSTRUCTION");
            insLabel1.setForeground(Color.ORANGE);
            JLabel insLabel2 = new JLabel("Oh no! You're trapped in Andy's maze");
            insLabel2.setForeground(Color.WHITE);
            JLabel insLabel3 = new JLabel("Unlock doors by answering trivia questions");
            insLabel3.setForeground(Color.WHITE);
            JLabel insLabel4 = new JLabel("You must find Boo's door to beat the maze");
            insLabel4.setForeground(Color.WHITE);
            JLabel insLabel5 = new JLabel("Find infinity stones to help you along the way!");
            insLabel5.setForeground(Color.WHITE);

            JPanel insPanel1 = new JPanel();
            insPanel1.setOpaque(false);
            insPanel1.add(insLabel1);

            JPanel insPanel2 = new JPanel();
            insPanel2.setOpaque(false);
            insPanel2.add(insLabel2);

            JPanel insPanel3 = new JPanel();
            insPanel3.setOpaque(false);
            insPanel3.add(insLabel3);

            JPanel insPanel4 = new JPanel();
            insPanel4.setOpaque(false);
            insPanel4.add(insLabel4);

            JPanel insPanel5 = new JPanel();
            insPanel5.setOpaque(false);
            insPanel5.add(insLabel5);

            setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER,BORDER));
            setLayout(new GridLayout(6, 1, 10, 10));
            add(insPanel1);
            add(insPanel2);
            add(insPanel3);
            add(insPanel4);
            add(insPanel5);
            add(resumeButton);
        }
    }

}