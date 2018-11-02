package jump;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Jump extends JFrame implements KeyListener {

    public final static int JUMPSPEED = 18, GRAVITY = 1, JUMPMAX = 3, WIDTH = 1000, HEIGHT = 700, STAGE = 11;
    set stage[] = new set[2];
    set player = new set();
    Random rnd = new Random();
    short bit, jumpcount;
    String str;
    boolean first, gameover, left, right;

    public static void main(String[] args) {
        new Jump();
    }

    Jump() {
        for (int k = 0; k < 2; k++) {
            stage[k] = new set();
        }
        str = "";
        player.setxy(100, HEIGHT - 200);
        player.setv(0, 0);
        player.count = 0;
        bit = 0;
        stage[bit].setxy(0, HEIGHT - 200 + 20);
        for (int i = 0; i < 2; i++) {
            stage[i].setv(-4, 0);
            stage[i].select = -1;
        }
        first = gameover = true;
        left = right = false;
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < 10; i++) {
                stage[k].width[i] = 0;
            }
        }
        jumpcount = 0;
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        JComponent jcom = new JComponentEX();
        jcom.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        contentPane.add(jcom);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setTitle("JUMP");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setResizable(false);
        setFocusable(true);
    }

    void painting(Graphics g) {
        //ステージ描画
        for (int i = 0; i < 2; i++) {
            switch (stage[i].select) {
                case 0:
                    stage[i].width[0] = 200;
                    stage[i].width[1] = 100;
                    stage[i].width[2] = 200;
                    stage[i].width[3] = 100;
                    stage[i].width[4] = 200;
                    for (int k = 0; k < 3; k++) {
                        stage[i].y = 500 - 100 * k;
                        g.fillRect(stage[i].x + stage[i].sum(2 * k - 1), stage[i].y, stage[i].width[2 * k], HEIGHT);
                    }
                    break;
                case 1:
                    stage[i].width[0] = 200;
                    stage[i].width[1] = 100;
                    stage[i].width[2] = 200;
                    stage[i].width[3] = 100;
                    stage[i].width[4] = 200;
                    for (int k = 0; k < 3; k++) {
                        stage[i].y = 300 + 100 * k;
                        g.fillRect(stage[i].x + stage[i].sum(2 * k - 1), stage[i].y, stage[i].width[2 * k], HEIGHT);
                    }
                    break;
                case 2:
                    for (int k = 0; k < 6; k++) {
                        stage[i].width[k] = 160;
                        stage[i].y = 300 + k * 50;
                        g.fillRect(stage[i].x + stage[i].sum(k - 1), stage[i].y, stage[i].width[k], HEIGHT);
                    }
                    break;
                case 3:
                    for (int k = 0; k < 6; k++) {
                        stage[i].width[k] = 160;
                        stage[i].y = 600 - k * 50;
                        g.fillRect(stage[i].x + stage[i].sum(k - 1), stage[i].y, stage[i].width[k], HEIGHT);
                    }
                    break;
                case 4:
                    stage[i].width[0] = 450;
                    stage[i].width[1] = 100;
                    stage[i].width[2] = 450;
                    stage[i].y = 600;
                    g.fillRect(stage[i].x, stage[i].y, stage[i].width[0], HEIGHT);
                    g.fillRect(stage[i].x + stage[i].sum(1), stage[i].y, stage[i].width[2], HEIGHT);
                    break;
                case 5:
                    stage[i].width[0] = 450;
                    stage[i].width[1] = 100;
                    stage[i].width[2] = 450;
                    stage[i].y = 400;
                    g.fillRect(stage[i].x, stage[i].y, stage[i].width[0], HEIGHT);
                    g.fillRect(stage[i].x + stage[i].sum(1), stage[i].y, stage[i].width[2], HEIGHT);
                    break;
                case 6:
                    stage[i].width[0] = 450;
                    stage[i].width[1] = 100;
                    stage[i].width[2] = 450;
                    stage[i].y = 200;
                    g.fillRect(stage[i].x, stage[i].y, stage[i].width[0], HEIGHT);
                    g.fillRect(stage[i].x + stage[i].sum(1), stage[i].y, stage[i].width[2], HEIGHT);
                    break;
                case 7:
                    stage[i].width[0] = 200;
                    stage[i].width[1] = 450;
                    stage[i].width[2] = 200;
                    stage[i].y = 200;
                    g.fillRect(stage[i].x, stage[i].y, stage[i].width[0], HEIGHT);
                    g.fillRect(stage[i].x + stage[i].sum(1), stage[i].y, stage[i].width[2], HEIGHT);
                    break;
                case 8:
                    stage[i].width[0] = 200;
                    stage[i].width[1] = 450;
                    stage[i].width[2] = 200;
                    stage[i].y = 400;
                    g.fillRect(stage[i].x, stage[i].y, stage[i].width[0], HEIGHT);
                    g.fillRect(stage[i].x + stage[i].sum(1), stage[i].y, stage[i].width[2], HEIGHT);
                    break;
                case 9:
                    stage[i].width[0] = 200;
                    stage[i].width[1] = 450;
                    stage[i].width[2] = 200;
                    stage[i].y = 600;
                    g.fillRect(stage[i].x, stage[i].y, stage[i].width[0], HEIGHT);
                    g.fillRect(stage[i].x + stage[i].sum(1), stage[i].y, stage[i].width[2], HEIGHT);
                    break;
                case 10:
                    stage[i].width[0] = 200;
                    stage[i].width[1] = 600;
                    stage[i].width[2] = 200;
                    stage[i].y = 650;
                    g.fillRect(stage[i].x, stage[i].y, stage[i].width[0], HEIGHT);
                    g.fillRect(stage[i].x + stage[i].sum(1), stage[i].y, stage[i].width[2], HEIGHT);
                    //ジャンプ台
                    for (int k = 1; k < 5; k++) {
                        g.fillRect(stage[i].x + 150, stage[i].y - 5 * k, 50, 2);
                        g.fillRect(stage[i].x + stage[i].sum(1), stage[i].y - 5 * k, 200, 2);
                    }
                    break;
            }
        }
    }

    class set {

        int select;
        int width[] = new int[10];
        long count;
        int x, y, vx, vy;

        void setxy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void setv(int vx, int vy) {
            this.vx = vx;
            this.vy = vy;
        }

        int sum(int k) {
            int sum = 0;
            for (int i = 0; i < k + 1; i++) {
                sum += width[i];
            }
            return sum;
        }
    }

    void left() {
        player.x -= 2;
    }

    void right() {
        player.x += 2;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            jumpcount++;
            if (jumpcount < JUMPMAX + 1) {
                player.vy = -JUMPSPEED;
            }
        }
        if (gameover && key == KeyEvent.VK_ENTER) {
            player.setxy(100, HEIGHT - 200);
            player.setv(0, 0);
            player.count = 0;
            bit = 0;
            stage[bit].setxy(0, HEIGHT - 200 + 20);
            for (int i = 0; i < 2; i++) {
                stage[i].setv(-4, 0);
                stage[i].select = -1;
            }
            first = true;
            gameover = false;
            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < 10; i++) {
                    stage[k].width[i] = 0;
                }
            }
            jumpcount = 0;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public class JComponentEX extends JComponent implements Runnable {

        public JComponentEX() {
            (new Thread(this)).start();
        }

        public void run() {
            while (true) {
                repaint();

                if (left) {
                    left();
                }
                if (right) {
                    right();
                }

                player.y += player.vy;
                player.vy += GRAVITY;
                player.count -= (stage[0].vx + stage[1].vx) / 2;
                player.x += player.vx;
                stage[0].x += stage[0].vx;
                stage[1].x += stage[1].vx;

                for (int k = 0; k < 7; k++) {
                    if (!gameover && player.count / 10 > (1 + k) * 250 && player.count / 10 < 250 * (k + 2)) {
                        for (int i = 0; i < 2; i++) {
                            stage[i].vx = -5 - k;
                        }
                    }
                }

                if (player.y > jump.Jump.HEIGHT - 200 && first && stage[0].x + jump.Jump.WIDTH > player.x) {
                    player.y = jump.Jump.HEIGHT - 200;
                    player.vy = 0;
                    jumpcount = 0;
                }
                if (player.vx < 0) {
                    if (player.x < 0) {
                        gameover = true;
                    }
                }
                if (player.y > jump.Jump.HEIGHT - 50) {
                    gameover = true;
                }
                if (stage[bit].x < 0) {
                    bit++;
                    if (bit >= 2) {
                        bit = 0;
                    }
                    stage[bit].x = jump.Jump.WIDTH;
                    stage[bit].select = rand();
                    for (int i = 0; i < 10; i++) {
                        stage[bit].width[i] = 0;
                    }
                }
                //壁
                for (int i = 0; i < 2; i++) {
                    switch (stage[i].select) {
                        case 0:
                            for (int k = 0; k < 3; k++) {
                                if (stage[i].x + stage[i].sum(2 * k - 1) < player.x + 20 && player.x + 20 < stage[i].x + stage[i].sum(2 * k - 1) + 15 && 500 - 20 - k * 100 < player.y + 20) {
                                    player.vx = stage[i].vx - 10;
                                }
                            }
                            break;
                        case 1:
                            for (int k = 0; k < 3; k++) {
                                if (stage[i].x + stage[i].sum(2 * k - 1) < player.x + 20 && player.x + 20 < stage[i].x + stage[i].sum(2 * k - 1) + 15 && 300 - 20 + k * 100 < player.y + 20) {
                                    player.vx = stage[i].vx - 10;
                                }
                            }
                            break;
                        case 2:
                            if (stage[i].x < player.x + 20 && player.x + 20 < stage[i].x + 15 && 300 < player.y + 20) {
                                player.vx = stage[i].vx - 10;
                            }
                            break;
                        case 3:
                            for (int k = 0; k < 6; k++) {
                                if (player.y + 20 > 600 - k * 50 && player.x + 20 < stage[i].x + stage[i].sum(k - 1) + 15 && player.x + 20 > stage[i].x + stage[i].sum(k - 1)) {
                                    player.vx = stage[i].vx - 10;
                                }
                            }
                            break;
                        case 4:
                        case 9:
                            for (int k = 0; k < 2; k++) {
                                if (player.y + 20 > 600 && player.x + 20 < stage[i].x + stage[i].sum(2 * k - 1) + 15 && player.x + 20 > stage[i].x + stage[i].sum(2 * k - 1)) {
                                    player.vx = stage[i].vx - 10;
                                }
                            }
                            break;
                        case 5:
                        case 8:
                            for (int k = 0; k < 2; k++) {
                                if (player.y + 20 > 400 && player.x + 20 < stage[i].x + stage[i].sum(2 * k - 1) + 15 && player.x + 20 > stage[i].x + stage[i].sum(2 * k - 1)) {
                                    player.vx = stage[i].vx - 10;
                                }
                            }
                            break;
                        case 6:
                        case 7:
                            for (int k = 0; k < 2; k++) {
                                if (player.y + 20 > 200 && player.x + 20 < stage[i].x + stage[i].sum(2 * k - 1) + 15 && player.x + 20 > stage[i].x + stage[i].sum(2 * k - 1)) {
                                    player.vx = stage[i].vx - 10;
                                }
                            }
                            break;
                        case 10:
                            for (int k = 0; k < 2; k++) {
                                if (player.y + 20 > 650 && player.x + 20 < stage[i].x + stage[i].sum(2 * k - 1) + 15 && player.x + 20 > stage[i].x + stage[i].sum(2 * k - 1)) {
                                    player.vx = stage[i].vx - 10;
                                }
                            }
                            break;
                    }
                }
                //着地
                if (player.vy > 0) {
                    for (int i = 0; i < 2; i++) {
                        switch (stage[i].select) {
                            case 0:
                                for (int k = 0; k < 3; k++) {
                                    if (player.y + 20 > 500 - 20 - k * 100 && player.x + 20 > stage[i].x + stage[i].sum(2 * k - 1) && player.x < stage[i].x + stage[i].sum(2 * k)) {
                                        player.y = 500 - 20 - k * 100;
                                        player.vy = 0;
                                        jumpcount = 0;
                                    }
                                }
                                break;
                            case 1:
                                for (int k = 0; k < 3; k++) {
                                    if (player.y + 20 > 300 - 20 + k * 100 && player.x + 20 > stage[i].x + stage[i].sum(2 * k - 1) && player.x < stage[i].x + stage[i].sum(2 * k)) {
                                        player.y = 300 - 20 + k * 100;
                                        player.vy = 0;
                                        jumpcount = 0;
                                    }
                                }
                                break;
                            case 2:
                                for (int k = 0; k < 6; k++) {
                                    if (player.y + 20 > 300 + k * 50 && player.x + 20 > stage[i].x + stage[i].sum(k - 1) && player.x < stage[i].x + stage[i].sum(k)) {
                                        player.y = 300 + k * 50 - 20;
                                        player.vy = 0;
                                        jumpcount = 0;
                                    }
                                }
                                break;
                            case 3:
                                for (int k = 0; k < 6; k++) {
                                    if (player.y + 20 > 600 - k * 50 && player.x + 20 > stage[i].x + stage[i].sum(k - 1) && player.x < stage[i].x + stage[i].sum(k)) {
                                        player.y = 600 - k * 50 - 20;
                                        player.vy = 0;
                                        jumpcount = 0;
                                    }
                                }
                                break;
                            case 4:
                            case 9:
                                for (int k = 0; k < 2; k++) {
                                    if (player.y + 20 > 600 - 20 && player.x + 20 > stage[i].x + stage[i].sum(2 * k - 1) && player.x < stage[i].x + stage[i].sum(2 * k)) {
                                        player.y = 600 - 20;
                                        player.vy = 0;
                                        jumpcount = 0;
                                    }
                                }
                                break;
                            case 5:
                            case 8:
                                for (int k = 0; k < 2; k++) {
                                    if (player.y + 20 > 400 - 20 && player.x + 20 > stage[i].x + stage[i].sum(2 * k - 1) && player.x < stage[i].x + stage[i].sum(2 * k)) {
                                        player.y = 400 - 20;
                                        player.vy = 0;
                                        jumpcount = 0;
                                    }
                                }
                                break;
                            case 6:
                            case 7:
                                for (int k = 0; k < 2; k++) {
                                    if (player.y + 20 > 200 - 20 && player.x + 20 > stage[i].x + stage[i].sum(2 * k - 1) && player.x < stage[i].x + stage[i].sum(2 * k)) {
                                        player.y = 200 - 20;
                                        player.vy = 0;
                                        jumpcount = 0;
                                    }
                                }
                                break;
                            case 10:
                                for (int k = 0; k < 2; k++) {
                                    if (player.y + 20 > 650 - 20 && player.x + 20 > stage[i].x + stage[i].sum(2 * k - 1) && player.x < stage[i].x + stage[i].sum(2 * k)) {
                                        player.y = 650 - 20;
                                        player.vy = 0;
                                        jumpcount = 0;
                                    }
                                }
                                if (player.y == 650 - 20 && ((player.x + 20 > stage[i].x + 150 && player.x < stage[i].x + stage[i].sum(0)) || (player.x + 20 > stage[i].x + stage[i].sum(1) && player.x < stage[i].x + stage[i].sum(2)))) {
                                    player.vy = -40;
                                }
                                break;
                        }
                    }
                }
                str = String.valueOf(player.count / 10) + " m";
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }

        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, jump.Jump.WIDTH, jump.Jump.HEIGHT);

            g.setColor(Color.black);
            if (first) {
                g.fillRect(stage[0].x, stage[0].y, jump.Jump.WIDTH, jump.Jump.HEIGHT);
                if (stage[0].x + jump.Jump.WIDTH < 0) {
                    first = false;
                }
            }
            painting(g);
            if (!gameover) {
                switch (jumpcount) {
                    case 0:
                        g.setColor(Color.blue);
                        break;
                    case 1:
                        g.setColor(Color.green);
                        break;
                    case 2:
                        g.setColor(Color.yellow);
                        break;
                    default:
                        g.setColor(Color.red);
                        break;
                }
                g.fillRect(player.x, player.y, 20, 20);
            }

            g.setFont(new Font(null, Font.BOLD, 55));
            g.setColor(Color.BLUE);
            g.drawString(str, jump.Jump.WIDTH / 2 + 200, 100);

            //ゲームオーバー
            if (gameover) {
                stage[0].vx = 0;
                stage[1].vx = 0;
                if (player.count / 10 > 50) {
                    g.setFont(new Font(null, Font.BOLD, 120));
                    g.setColor(Color.red);
                    g.drawString("GAME OVER", jump.Jump.WIDTH / 2 - 380, jump.Jump.HEIGHT / 2);
                }
                if (player.count / 10 < 50) {
                    g.setColor(Color.white);
                    g.fillRect(jump.Jump.WIDTH / 2 + 100, 50, 200, 60);
                }
                g.setColor(Color.CYAN);
                g.setFont(new Font(null, Font.BOLD, 60));
                g.drawString("ENTERキーでSTART", jump.Jump.WIDTH / 2 - 300, jump.Jump.HEIGHT / 2 + 150);
            }
        }

        public int rand() {
            return (rnd.nextInt(STAGE));
        }
    }
}
