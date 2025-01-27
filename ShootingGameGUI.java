import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShootingGameGUI extends JPanel implements ActionListener, KeyListener {
    private int playerX = 250;  // Initial player position
    private int playerY = 500;
    private int playerWidth = 50;
    private int playerHeight = 30;
    private int playerSpeed = 15;
    private ArrayList<Rectangle> bullets = new ArrayList<>();
    private ArrayList<Rectangle> enemies = new ArrayList<>();
    private Timer timer;
    private boolean left, right, shoot;

    public ShootingGameGUI() {
        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        timer = new Timer(1000 / 60, this);  // 60 FPS
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayer(g);
        drawBullets(g);
        drawEnemies(g);
        checkCollisions(g);
    }

    private void drawPlayer(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, playerWidth, playerHeight);
    }

    private void drawBullets(Graphics g) {
        g.setColor(Color.YELLOW);
        for (Rectangle bullet : bullets) {
            g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
        }
    }

    private void drawEnemies(Graphics g) {
        g.setColor(Color.RED);
        for (Rectangle enemy : enemies) {
            g.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);
        }
    }

    private void movePlayer() {
        if (left && playerX > 0) {
            playerX -= playerSpeed;
        }
        if (right && playerX < getWidth() - playerWidth) {
            playerX += playerSpeed;
        }
    }

    private void shootBullet() {
        if (shoot) {
            Rectangle bullet = new Rectangle(playerX + playerWidth / 2 - 5, playerY, 10, 20);
            bullets.add(bullet);
            shoot = false;  // Reset shooting flag
        }
    }

    private void moveBullets() {
        ArrayList<Rectangle> toRemove = new ArrayList<>();
        for (Rectangle bullet : bullets) {
            bullet.y -= 10;  // Move bullet upwards
            if (bullet.y < 0) {
                toRemove.add(bullet);
            }
        }
        bullets.removeAll(toRemove);
    }

    private void moveEnemies() {
        for (Rectangle enemy : enemies) {
            enemy.y += 5;  // Move enemies downwards
        }
        if (Math.random() < 0.05) {  // Random chance to create a new enemy
            enemies.add(new Rectangle((int) (Math.random() * (getWidth() - 50)), 0, 50, 50));
        }
    }

    private void checkCollisions(Graphics g) {
        ArrayList<Rectangle> toRemoveBullets = new ArrayList<>();
        ArrayList<Rectangle> toRemoveEnemies = new ArrayList<>();

        for (Rectangle bullet : bullets) {
            for (Rectangle enemy : enemies) {
                if (bullet.intersects(enemy)) {
                    toRemoveBullets.add(bullet);
                    toRemoveEnemies.add(enemy);
                }
            }
        }

        bullets.removeAll(toRemoveBullets);
        enemies.removeAll(toRemoveEnemies);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        movePlayer();
        shootBullet();
        moveBullets();
        moveEnemies();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            shoot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shooting Game");
        ShootingGameGUI gamePanel = new ShootingGameGUI();
        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
