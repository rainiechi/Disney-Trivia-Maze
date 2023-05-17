package View;
import javax.swing.JFrame;
    public class Main {
//        public static void main(String[] args) {
//            JFrame window = new JFrame();
//            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            window.setResizable(false);
//            window.setTitle("Disney Trivia Maze");
//
//            GamePanel gamePanel = new GamePanel();
//            window.add(gamePanel);
//
//            window.pack();
//
//            window.setLocationRelativeTo(null);
//            window.setVisible(true);
//
//            gamePanel.startGameThread();
//        }
        public static void main(String[] args) {
            GamePanel gamePanel = new GamePanel();
            new GameFrame(gamePanel);
            gamePanel.startGameThread();
        }
    }
