package View;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static final String GAME_TITLE = "Disney Trivia Maze";
    private static final int BORDER = 15;
    private static final Color PINK = new Color(245,218,223);
    private static final Color BROWN = new Color(164,116,73);
    private static final Color DARK_BROWN = new Color(78,53,36);
    private static final Color BEIGE = new Color(242, 235, 228);
    private JMenuBar myMenuBar;
    private JMenuItem mySaveItem;
    private JMenuItem myLoadItem;
    private JMenuItem myExitItem;
    private JMenuItem myAboutItem;
    private JMenuItem myInstructionItem;
    private GamePanel myGamePanel;
    private JButton resumeButton;

    private WelcomePanel myWelcomePanel;


    /**
     * GameFrame Constructor.
     */
    public GameFrame() {
        setIcon();
        myWelcomePanel = new WelcomePanel();
        setContentPane(myWelcomePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(GAME_TITLE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        myGamePanel = new GamePanel();
        myGamePanel.playMusic(0);
    }

    public void saveGame() {
        myGamePanel.saveGame();
    }

    public void loadGame() {
        myGamePanel.loadGame();
    }


    /**
     * Sets the icon for the game to pixar ball.
     */
    private void setIcon() {
        ImageIcon icon = new ImageIcon("src/res/welcomeScreen/ball.png");
        setIconImage(icon.getImage());
    }

    /**
     * Switches from the welcome screen to the game screen.
     */
    public void switchToGamePanel(final GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
        resumeButtonSetUp();
        initMenuBar();
        setContentPane(theGamePanel.layeredPane);


        revalidate(); // Refresh the content pane
        theGamePanel.requestFocusInWindow();
        theGamePanel.startGameThread(); // Start the game thread
        showDialog(new InstructionPanel()); //show instructions
    }

    public void switchToEndPanel() {
        setContentPane(new EndPanel());
        myMenuBar.removeAll();

        revalidate();
    }

    public GamePanel getMyGamePanel() {
        return myGamePanel;
    }


    /**
     * Sets up the resume button that will be used in multiple windows.
     */
    public void resumeButtonSetUp() {
        resumeButton = new JButton("RESUME");
        resumeButton.setBackground(BEIGE);
        resumeButton.setForeground(DARK_BROWN);
        resumeButton.setBorder(BorderFactory.createLineBorder(DARK_BROWN, 1));
        resumeButton.setSize(new Dimension(60,18));
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
        menuBarListener();
    }

    /**
     * Adds listeners to Menu items.
     */
    public void menuBarListener() {
        myExitItem.addActionListener(theEvent -> showDialog(new ExitPanel()));
        myAboutItem.addActionListener(theEvent -> showDialog(new AboutPanel()));
        myInstructionItem.addActionListener(theEvent -> showDialog(new InstructionPanel()));
        mySaveItem.addActionListener(e -> saveGame());
        myLoadItem.addActionListener(e -> loadGame());
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
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }


    /**
     * ExitPanel class that creates the exit panel.
     */
    class ExitPanel extends JPanel {
        public ExitPanel() {
            setBackground(PINK);
            JLabel exitLabel1 = new JLabel("CONFIRM EXIT");
            exitLabel1.setForeground(DARK_BROWN);
            JLabel exitLabel2 = new JLabel("UNSAVED PROGRESS WILL BE LOST");
            exitLabel2.setForeground(BROWN);

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
            exitButton.setBackground(BEIGE);
            exitButton.setForeground(DARK_BROWN);
            exitButton.setBorder(BorderFactory.createLineBorder(DARK_BROWN, 1));
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
            setBackground(PINK);
            JLabel aboutLabel1 = new JLabel("ABOUT");
            aboutLabel1.setForeground(DARK_BROWN);
            JLabel aboutLabel2 = new JLabel("Made with Java 19");
            aboutLabel2.setForeground(BROWN);
            JLabel aboutLabel3 = new JLabel("Created by: Amanda, Rainie, Karan");
            aboutLabel3.setForeground(BROWN);

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
            setBackground(PINK);
            JLabel insLabel1 = new JLabel("INSTRUCTION");
            insLabel1.setForeground(DARK_BROWN);
            JLabel insLabel2 = new JLabel("Oh no! You're trapped in Andy's maze");
            insLabel2.setForeground(BROWN);
            JLabel insLabel3 = new JLabel("Unlock doors by answering trivia questions");
            insLabel3.setForeground(BROWN);
            JLabel insLabel4 = new JLabel("You must find Boo's door to beat the maze");
            insLabel4.setForeground(BROWN);
            JLabel insLabel5 = new JLabel("Find infinity stones to help you along the way!");
            insLabel5.setForeground(BROWN);
            JLabel insLabel6 = new JLabel("(Press M for map)");
            insLabel6.setForeground(BROWN);

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

            JPanel insPanel6 = new JPanel();
            insPanel6.setOpaque(false);
            insPanel6.add(insLabel6);

            setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER,BORDER));
            setLayout(new GridLayout(7, 1, 10, 10));
            add(insPanel1);
            add(insPanel2);
            add(insPanel3);
            add(insPanel4);
            add(insPanel5);
            add(insPanel6);
            add(resumeButton);
        }
    }

}
