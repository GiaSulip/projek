import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TTT1 extends JFrame implements ItemListener, ActionListener {
    int i, j, ii, jj, x, y, yesnull;
    int a[][] = { { 10, 1, 2, 3, 11 }, { 10, 1, 4, 7, 11 }, { 10, 1, 5, 9, 11 }, { 10, 2, 5, 8, 11 },
            { 10, 3, 5, 7, 11 }, { 10, 3, 6, 9, 11 }, { 10, 4, 5, 6, 11 }, { 10, 7, 8, 9, 11 } };

    boolean state, type, set, gameOver;
    int playerScore, computerScore;

    Icon ic1, ic2, icon, ic11, ic22;
    Checkbox c1, c2;
    JLabel l1, l2, l3, playerNameLabel, player2NameLabel, welcomeLabel;
    JButton b[] = new JButton[9];
    JButton reset, continueButton, stopButton;
    JTextField playerNameInput, player2NameInput;

    public void showButton() {
        x = 10;
        y = 10;
        j = 0;

        if (welcomeLabel != null) {
            remove(welcomeLabel);
        }

        playerNameLabel = new JLabel("Player 1: ");
        playerNameLabel.setBounds(10, 350, 60, 25);
        playerNameLabel.setForeground(new Color(0, 111, 255));
        add(playerNameLabel);

        playerNameInput = new JTextField("Player 1");
        playerNameInput.setBounds(70, 350, 80, 25);
        add(playerNameInput);

        player2NameLabel = new JLabel("Player 2: ");
        player2NameLabel.setBounds(160, 350, 60, 25);
        player2NameLabel.setForeground(new Color(0, 128, 109));
        add(player2NameLabel);

        player2NameInput = new JTextField("Player 2");
        player2NameInput.setBounds(220, 350, 80, 25);
        add(player2NameInput);

        for (i = 0; i <= 8; i++, x += 100, j++) {
            b[i] = new JButton();
            if (j == 3) {
                j = 0;
                y += 100;
                x = 10;
            }
            b[i].setBounds(x, y, 100, 100);
            add(b[i]);
            b[i].addActionListener(this);
        }

        reset = new JButton("RESET");
        reset.setBounds(340, 350, 100, 50);
        add(reset);
        reset.addActionListener(this);

        continueButton = new JButton("Continue");
        continueButton.setBounds(340, 410, 100, 25);
        add(continueButton);
        continueButton.addActionListener(this);

        stopButton = new JButton("Stop");
        stopButton.setBounds(340, 440, 100, 25);
        add(stopButton);
        stopButton.addActionListener(this);

        l1 = new JLabel("Your Score: 0");
        l2 = new JLabel("Computer Score: 0");
        l3 = new JLabel("Current Score: 0 - 0");

        l1.setBounds(470, 10, 120, 20);
        l1.setForeground(new Color(0, 111, 255));
        l2.setBounds(470, 30, 150, 20);
        l2.setForeground(new Color(0, 128, 109));
        l3.setBounds(470, 50, 150, 20);

        add(l1);
        add(l2);
        add(l3);
    }

    public void check(int num1) {
        for (ii = 0; ii <= 7; ii++) {
            for (jj = 1; jj <= 3; jj++) {
                if (a[ii][jj] == num1) {
                    a[ii][4] = 11;
                }
            }
        }
    }

    public void complogic(int num) {
        for (i = 0; i <= 7; i++) {
            for (j = 1; j <= 3; j++) {
                if (a[i][j] == num) {
                    a[i][0] = 11;
                    a[i][4] = 10;
                }
            }
        }
        for (i = 0; i <= 7; i++) {
            set = true;
            if (a[i][4] == 10) {
                int count = 0;
                for (j = 1; j <= 3; j++) {
                    if (b[(a[i][j] - 1)].getIcon() != null) {
                        count++;
                    } else {
                        yesnull = a[i][j];
                    }
                }
                if (count == 2) {
                    b[yesnull - 1].setIcon(ic2);
                    this.check(yesnull);
                    set = false;
                    break;
                }
            } else if (a[i][0] == 10) {
                for (j = 1; j <= 3; j++) {
                    if (b[(a[i][j] - 1)].getIcon() == null) {
                        b[(a[i][j] - 1)].setIcon(ic2);
                        this.check(a[i][j]);
                        set = false;
                        break;
                    }
                }
                if (!set)
                    break;
            }
            if (!set)
                break;
        }
    }

    TTT1() {
        super("Tic Tac Toe");

        welcomeLabel = new JLabel("TIC TAC TOE GAME");
        welcomeLabel.setFont(new Font("Georgia", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(111, 0, 128));
        welcomeLabel.setBounds(150, 40, 300, 40);
        add(welcomeLabel);

        CheckboxGroup cbg = new CheckboxGroup();
        c1 = new Checkbox("VS COMPUTER", cbg, false);
        c2 = new Checkbox("VS FRIEND", cbg, false);
        c1.setBounds(250, 200, 150, 40);
        c2.setBounds(250, 250, 150, 40);

        Font font = new Font("Georgia", Font.BOLD, 18);
        c1.setFont(font);
        c2.setFont(font);
        c1.setForeground(new Color(111, 0, 128));
        c2.setForeground(new Color(111, 0, 128));

        add(c1);
        add(c2);
        c1.addItemListener(this);
        c2.addItemListener(this);
        setLayout(null);
        setSize(500, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        state = true;
        type = true;
        set = true;
        ic1 = new ImageIcon("resources/ic1.jpg");
        ic2 = new ImageIcon("resources/ic2.jpg");
        ic11 = new ImageIcon("resources/ic11.jpg");
        ic22 = new ImageIcon("resources/ic22.jpg");

        setLayout(null);
        setSize(650, 550);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void itemStateChanged(ItemEvent e) {
        if (c1.getState()) {
            type = false;
            getContentPane().setBackground(new Color(230, 173, 219));
        } else if (c2.getState()) {
            type = true;
            getContentPane().setBackground(new Color(251, 152, 228));
        }
        remove(c1);
        remove(c2);
        repaint(0, 0, 650, 550);
        showButton();
    }

    public void actionPerformed(ActionEvent e) {
        if (type == true) {
            if (e.getSource() == reset) {
                resetGame();
            } else if (e.getSource() == continueButton) {
                gameOver = false;
                for (i = 0; i <= 8; i++) {
                    b[i].setIcon(null);
                }
                hideWelcomeLabel();
            } else if (e.getSource() == stopButton) {
                gameOver = true;
                determineWinner();
            } else {
                for (i = 0; i <= 8; i++) {
                    if (e.getSource() == b[i]) {
                        if (b[i].getIcon() == null && !gameOver) {
                            if (state == true) {
                                icon = ic2;
                                state = false;
                            } else {
                                icon = ic1;
                                state = true;
                            }
                            b[i].setIcon(icon);
                            checkWin();
                        }
                    }
                }
            }
        } else if (type == false) {
            if (e.getSource() == reset) {
                resetGame();
            } else if (e.getSource() == continueButton) {
                gameOver = false;
                for (i = 0; i <= 8; i++) {
                    b[i].setIcon(null);
                }
                hideWelcomeLabel();
            } else if (e.getSource() == stopButton) {
                gameOver = true;
                determineWinner();
            } else {
                for (i = 0; i <= 8; i++) {
                    if (e.getSource() == b[i]) {
                        if (b[i].getIcon() == null && !gameOver) {
                            b[i].setIcon(ic1);
                            if (b[4].getIcon() == null) {
                                b[4].setIcon(ic2);
                                this.check(5);
                            } else {
                                this.complogic(i);
                            }
                            checkWin();
                        }
                    }
                }
            }
        }
    }

    public void checkWin() {
        for (i = 0; i <= 7; i++) {
            Icon icon1 = b[(a[i][1] - 1)].getIcon();
            Icon icon2 = b[(a[i][2] - 1)].getIcon();
            Icon icon3 = b[(a[i][3] - 1)].getIcon();
            if ((icon1 == icon2) && (icon2 == icon3) && (icon1 != null)) {
                if (icon1 == ic1) {
                    b[(a[i][1] - 1)].setIcon(ic11);
                    b[(a[i][2] - 1)].setIcon(ic11);
                    b[(a[i][3] - 1)].setIcon(ic11);
                    JOptionPane.showMessageDialog(TTT1.this, playerNameInput.getText() + " won!");
                    playerScore++;
                } else if (icon1 == ic2) {
                    b[(a[i][1] - 1)].setIcon(ic22);
                    b[(a[i][2] - 1)].setIcon(ic22);
                    b[(a[i][3] - 1)].setIcon(ic22);
                    JOptionPane.showMessageDialog(TTT1.this, player2NameInput.getText() + " won!");
                    computerScore++;
                }
                updateScore();
                gameOver = true;
                break;
            }
        }
    }

    public void updateScore() {
        l1.setText("Your Score: " + playerScore);
        l2.setText("Computer Score: " + computerScore);
        l3.setText("Current Score: " + playerScore + " - " + computerScore);
    }

    public void resetGame() {
        for (i = 0; i <= 8; i++) {
            b[i].setIcon(null);
        }
        state = true;
        gameOver = false;
        playerScore = 0;
        computerScore = 0;
        a = new int[][] { { 10, 1, 2, 3, 11 }, { 10, 1, 4, 7, 11 }, { 10, 1, 5, 9, 11 }, { 10, 2, 5, 8, 11 },
                { 10, 3, 5, 7, 11 }, { 10, 3, 6, 9, 11 }, { 10, 4, 5, 6, 11 }, { 10, 7, 8, 9, 11 } };
        updateScore();
    }

    public void determineWinner() {
        if (playerScore > computerScore) {
            JOptionPane.showMessageDialog(TTT1.this, playerNameInput.getText() + " is the overall winner!");
        } else if (playerScore < computerScore) {
            JOptionPane.showMessageDialog(TTT1.this, player2NameInput.getText() + " is the overall winner!");
        } else {
            JOptionPane.showMessageDialog(TTT1.this, "It's a draw!");
        }
    }

    public void hideWelcomeLabel() {
        if (welcomeLabel != null) {
            welcomeLabel.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TTT1();
    }
}
