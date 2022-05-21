package KT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Visual {
    private JFrame mainFrame;
    private Board board;
    private Cell currentPosition;
    private int prevX;
    private int prevY;
    private int move;
    private JButton[][] btnArray;
    static final int X = 8;

    public static void main(String[] args) {
        Visual demoKT = new Visual();
    }

    public Visual() {
        createGui();
    }

    private void createGui() {
        this.createWindow();
    }

    private void createWindow() {
        this.mainFrame = new JFrame("Knight's Tour Algorithm Demo");
        this.mainFrame.setSize(800, 800);
        this.mainFrame.setLayout(new GridLayout(9, 8));
        this.mainFrame.setVisible(true);

        this.mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        this.btnArray = new JButton[8][8];
        this.createBoard();

        JButton btnStart = new JButton("Start");
        btnStart.setBackground(Color.decode("#016358"));
        btnStart.setForeground(Color.white);
        btnStart.setFont(Font.getFont("Arial"));

        JButton btnNext = new JButton("Next");
        btnNext.setBackground(Color.decode("#013f74"));
        btnNext.setForeground(Color.white);
        btnNext.setFont(Font.getFont("Arial"));

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btnStart.setEnabled(false);
                initBoard();
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!board.isCorrectSolution()) {
                    findNextCell();
                } else {
                    btnNext.setEnabled(false);
                    JFrame successWindow = new JFrame("Success!");
                    successWindow.setSize(350, 250);
                    successWindow.setLayout(new GridLayout(2, 1, 0, 20));
                    successWindow.setVisible(true);
                    successWindow.setLocationRelativeTo(mainFrame);

                    JLabel successLabel = new JLabel("The Knight's tour was successfully generated!", JLabel.CENTER);
                    successLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    successWindow.add(successLabel);

                    JPanel qPanel = new JPanel();

                    JButton quitBtn = new JButton("Quit");
                    quitBtn.setBackground(Color.decode("#8F0303"));
                    quitBtn.setForeground(Color.WHITE);
                    quitBtn.setFont(Font.decode("Arial"));

                    qPanel.add(quitBtn);
                    successWindow.add(qPanel, BorderLayout.SOUTH);

                    quitBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            System.exit(0);
                        }
                    });
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 5, 5));
        panel.add(btnStart);
        panel.add(btnNext);

        this.mainFrame.add(panel, BorderLayout.SOUTH);

        JLabel legend1 = new JLabel("Knight: red", JLabel.CENTER);
        legend1.setFont(Font.decode("Arial"));

        this.mainFrame.add(legend1);
    }

    private void createBoard() {
        String letter = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (i) {
                    case 0:
                        letter = "H";
                        break;
                    case 1:
                        letter = "G";
                        break;
                    case 2:
                        letter = "F";
                        break;
                    case 3:
                        letter = "E";
                        break;
                    case 4:
                        letter = "D";
                        break;
                    case 5:
                        letter = "C";
                        break;
                    case 6:
                        letter = "B";
                        break;
                    case 7:
                        letter = "A";
                        break;
                }
                JButton btn = new JButton(letter + (j + 1));
                btn.setFont(Font.getFont("Arial"));
                this.mainFrame.add(btn);
                this.btnArray[i][j] = btn;
                btn.setEnabled(false);
            }
        }
    }

    private void initBoard() {
        this.board = new Board();

        int xKnight = (int) Math.floor(Math.random() * X);
        int yKnight = (int) Math.floor(Math.random() * X);

        this.move = 1;
        //set the initial position inside the board
        board.setCounter(xKnight, yKnight, this.move++);

        //find a solution for the initial position
        Cell cell = new Cell(xKnight, yKnight);
        this.btnArray[xKnight][yKnight].setBackground(Color.decode("#9e0003"));

        this.currentPosition = cell;
    }

    private void findNextCell() {
        this.btnArray[this.currentPosition.getRow()][this.currentPosition.getColumn()].setBackground(Color.decode("#003b00"));
        this.currentPosition = this.board.findSolution(this.currentPosition.getRow(), this.currentPosition.getColumn(), this.move++);
        this.btnArray[this.currentPosition.getRow()][this.currentPosition.getColumn()].setBackground(Color.decode("#9e0003"));
    }
}