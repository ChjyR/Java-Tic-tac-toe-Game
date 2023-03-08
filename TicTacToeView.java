import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class implements the game panel
 * @author Chen Jingyan
 *
 */
public class TicTacToeView {

    JButton submit;
    JButton[] jButtons = new JButton[9];
    JFrame frame;
    JPanel mainPanel;
    JPanel messagePanel;
    JPanel board;
    JPanel namePanel;
    JTextField txt_inputname;
    JLabel messageLable;
    JMenuItem Exit;

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getMessageLable() {
        return messageLable;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getMessagePanel() {
        return messagePanel;
    }

    public JPanel getNamePanel() {
        return namePanel;
    }

    public TicTacToeView() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        messagePanel = new JPanel();
        board = new JPanel(new GridLayout(3, 3));
        namePanel = new JPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu Control = new JMenu("Control");
        JMenu Help = new JMenu("Help");
        Exit = new JMenuItem("Exit");
        Exit.addActionListener(new exitApp());
        JMenuItem instruction = new JMenuItem("Instruction");
        messageLable = new JLabel("Enter your player name...");
        txt_inputname = new JTextField(10);
        submit = new JButton("Submit");
        for (int i = 0; i < 9; i++) {
            jButtons[i] = new JButton();
            board.add(jButtons[i]);
        }

        messagePanel.add(messageLable);
        mainPanel.add(messagePanel, BorderLayout.NORTH);
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.add(namePanel, BorderLayout.SOUTH);
        namePanel.add(txt_inputname);
        namePanel.add(submit);

        menuBar.add(Control);
        menuBar.add(Help);
        Control.add(Exit);
        Help.add(instruction);
        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
        instruction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "Some information about the game:\n" +
                        "Criteria for a valid move:\n" +
                        "- The move is not occupied by any mark.\n" +
                        "- The move is made in the player’s turn.\n" +
                        "- The move is made within the 3 x 3 board.\n" +
                        "\n" +
                        "The game would continue and switch among the opposite player until it reaches either \n" +
                        "one of the following conditions:\n" +
                        "- Player 1 wins.\n" +
                        "- Player 2 wins.\n" +
                        "- Draw");
            }
        });

    }

    public JTextField getInputItem() {
        return txt_inputname;
    }

    public JMenuItem getExit() {
    	return Exit;
    }
    
    public JButton getSubmit() {
        return submit;
    }

    public JButton[] getButton() {
        return jButtons;
    }

    public JButton getButton1() {
        return jButtons[0];
    }

    public JButton getButton2() {
        return jButtons[1];
    }

    public JButton getButton3() {
        return jButtons[2];
    }

    public JButton getButton4() {
        return jButtons[3];
    }

    public JButton getButton5() {
        return jButtons[4];
    }

    public JButton getButton6() {
        return jButtons[5];
    }

    public JButton getButton7() {
        return jButtons[6];
    }

    public JButton getButton8() {
        return jButtons[7];
    }

    public JButton getButton9() {
        return jButtons[8];
    }

    static class exitApp implements ActionListener {
        /**
         * This method implements the Result button. The player can quit the game by
         * clicking the Exit button.
         */
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}


