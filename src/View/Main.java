package View;
import javax.swing.JFrame;
    public class Main {
//        public static void main(String[] args) {
//            JFrame window = new JFrame();wwwwws
//            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            window.setResizable(false);
//            window.setTitle("Disney Trivia Maze");
//
//            GamePanel gamePanel = new GamePanel();
//            window.add(gamePanel);
//
//            window.pack();
//s
//            window.setLocationRelativeTo(null);
//            window.setVisible(true);w
//
//            gamePanel.startGameThread();
//        }
        public static void main(String[] args) {
            GamePanel gamePanel = new GamePanel();
            new GameFrame(gamePanel);
            gamePanel.startGameThread();
        }
    }
