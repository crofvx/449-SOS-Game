import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
    private JFrame frame;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private JLabel playerLabel;
    private JTextField player1TextField;
    private JTextField player2TextField;
    private JRadioButton playerVsPlayerRadio;
    private JRadioButton playerVsComputerRadio;
    private JCheckBox showGridLinesCheckBox;
    private gameSOS game;

    public GameGUI() {
        game = new gameSOS(8);
        frame = new JFrame("Welcome to SOS Game");
        frame.setLayout(new BorderLayout());
        buttons = new JButton[8][8];
        initializeGUI();
    }

    private void initializeGUI() {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(8, 8));
        
        // Initialize buttons and add to gridPanel
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                JButton button = new JButton("");
                button.setPreferredSize(new Dimension(40, 40));
                button.setFont(new Font("Arial", Font.PLAIN, 20));
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(r, c);
                    }
                });
                buttons[r][c] = button;
                gridPanel.add(button);
            }
        }
        
        // Status label and player labels
        statusLabel = new JLabel("Current Turn: Blue");
        playerLabel = new JLabel("P1 (Blue): ");
        player1TextField = new JTextField("Player 1");
        player2TextField = new JTextField("Player 2");
        
        // Radio buttons for game modes
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new GridLayout(1, 2));
        playerVsPlayerRadio = new JRadioButton("P vs P", true);
        playerVsComputerRadio = new JRadioButton("P vs Com");
        ButtonGroup group = new ButtonGroup();
        group.add(playerVsPlayerRadio);
        group.add(playerVsComputerRadio);
        modePanel.add(playerVsPlayerRadio);
        modePanel.add(playerVsComputerRadio);

        // Checkbox to toggle grid lines
        showGridLinesCheckBox = new JCheckBox("Grid");
        showGridLinesCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.repaint();
            }
        });
        
        // Panel for text fields and checkboxes
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridLayout(4, 2));
        textFieldPanel.add(playerLabel);
        textFieldPanel.add(player1TextField);
        textFieldPanel.add(new JLabel("P2 (Red): "));
        textFieldPanel.add(player2TextField);
        textFieldPanel.add(showGridLinesCheckBox);
        
        // Add components to the frame
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(textFieldPanel, BorderLayout.SOUTH);
        frame.add(modePanel, BorderLayout.EAST);

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void buttonClicked(int row, int col) {
        JButton btn = buttons[row][col];
        if (btn.getText().equals("")) {
            char currentChar = (game.getCurrentPlayer() == 'b') ? 'S' : 'O';
            btn.setText(String.valueOf(currentChar));
            game.makeCell(row, col, currentChar);

            if (game.sosCheck(row, col)) {
                JOptionPane.showMessageDialog(frame, game.getCurrentPlayer() + " Wins!!!");
                game.flipP();
            } else {
                game.flipP();
            }
            statusLabel.setText("Current Turn: " + (game.getCurrentPlayer() == 'b' ? "Blue" : "Red"));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}
