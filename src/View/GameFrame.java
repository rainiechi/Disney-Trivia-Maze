package View;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {
    private static final String GAME_TITLE = "Disney Trivia Maze";
    private JMenuBar myMenuBar;
    private JMenu myGameMenu;
    private JMenu myHelpMenu;
    private JMenuItem mySaveItem;
    private JMenuItem myLoadItem;
    private JMenuItem myExitItem;
    private JMenuItem myAboutItem;
    private JMenuItem myInstructionItem;

    private JPanel myGamePanel;


    public GameFrame(final JPanel theGamePanel) {
        if (theGamePanel == null) {
            throw new IllegalArgumentException("Game Panel cannot be null");
        }
        initMenuBar();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(GAME_TITLE);

        myGamePanel = theGamePanel;
        add(myGamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initMenuBar() {
        myMenuBar = new JMenuBar();
        myGameMenu = new JMenu("Game");
        myHelpMenu = new JMenu("Help");
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
}
