import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;


/**
 * This class implement client
 * @author Chen Jingyan
 * 
 */
public class Client {

    Socket sock;
    PrintWriter writer;
    TicTacToeView view;
    private String name;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    boolean playEnable = true;
    int count = 0;
    String myLetter = "O";
    boolean isFirstMove = false;
    boolean win = false;

    /**
     * Main Function: connect to the server and show the Mainframe
     */
    public void go() {
        view = new TicTacToeView();
        view.getExit().addActionListener(new exitListener());
        view.getSubmit().addActionListener(new submitListener());
        view.getButton1().addActionListener(new buttonListener());
        view.getButton2().addActionListener(new buttonListener());
        view.getButton3().addActionListener(new buttonListener());
        view.getButton4().addActionListener(new buttonListener());
        view.getButton5().addActionListener(new buttonListener());
        view.getButton6().addActionListener(new buttonListener());
        view.getButton7().addActionListener(new buttonListener());
        view.getButton8().addActionListener(new buttonListener());
        view.getButton9().addActionListener(new buttonListener());

        try {
            sock = new Socket("127.0.0.1", 5001);
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            listenForMessage();
            writer = new PrintWriter(sock.getOutputStream(), true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void listenForMessage() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                String msgFromOther;

                while (sock.isConnected()) {
                    try {
                        msgFromOther = bufferedReader.readLine();
                        if (msgFromOther.startsWith("SERVER")) {
                            JOptionPane.showMessageDialog(view.getFrame(), "“Game Ends. One of \n" +
                                    "the players left.");
                            return;
                        }
                        if (view.getButton()[Integer.parseInt(msgFromOther) - 1].getText() != null
                                && !"".equalsIgnoreCase(view.getButton()[Integer.parseInt(msgFromOther) - 1].getText())) {
                            return;
                        }
                        playEnable = true;
                        view.getMessageLable().setText("Your opponent has moved, now is your turn.");
                        view.getButton()[Integer.parseInt(msgFromOther) - 1].setForeground(isFirstMove ? Color.green : Color.RED);
                        view.getButton()[Integer.parseInt(msgFromOther) - 1].setText(isFirstMove ? "O" : "X");
                        view.getButton()[Integer.parseInt(msgFromOther) - 1].setFont(new Font("BOLD", Font.BOLD, 20));
                        count++;
                        checkWinner();
                    } catch (Exception e) {
                        closeEverything(sock, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkWinner() {
        //Checking every condition of tic-tac-toe game
        JButton btn1 = view.getButton1();
        JButton btn2 = view.getButton2();
        JButton btn3 = view.getButton3();
        JButton btn4 = view.getButton4();
        JButton btn5 = view.getButton5();
        JButton btn6 = view.getButton6();
        JButton btn7 = view.getButton7();
        JButton btn8 = view.getButton8();
        JButton btn9 = view.getButton9();
        String letter = "";
        //Check Horizontally
        if (btn1.getText() == btn2.getText() &&
                btn2.getText() == btn3.getText() &&
                btn1.getText() != "" &&
                btn2.getText() != "" &&
                btn3.getText() != "") {
            win = true;
            letter = btn1.getText();
        }

        //Check Horizontally
        else if (btn4.getText() == btn5.getText() &&
                btn5.getText() == btn6.getText() &&
                btn4.getText() != "" &&
                btn5.getText() != "" &&
                btn6.getText() != "") {
            letter = btn4.getText();
            win = true;
        }

        //Check Horizontally
        else if (btn7.getText() == btn8.getText() &&
                btn8.getText() == btn9.getText() &&
                btn7.getText() != "" &&
                btn8.getText() != "" &&
                btn9.getText() != "") {
            letter = btn7.getText();
            win = true;
        }

        //Check Vertically
        else if (btn1.getText() == btn4.getText() &&
                btn4.getText() == btn7.getText() &&
                btn1.getText() != "" &&
                btn4.getText() != "" &&
                btn7.getText() != "") {
            letter = btn1.getText();
            win = true;
        }

        //Check Vertically
        else if (btn2.getText() == btn5.getText() &&
                btn5.getText() == btn8.getText() &&
                btn2.getText() != "" &&
                btn5.getText() != "" &&
                btn8.getText() != "") {
            letter = btn2.getText();
            win = true;
        } else if (btn3.getText() == btn6.getText() &&
                btn6.getText() == btn9.getText() &&
                btn3.getText() != "" &&
                btn6.getText() != "" &&
                btn9.getText() != "") {
            letter = btn3.getText();
            win = true;
        }

        //Check Diagonally
        else if (btn1.getText() == btn5.getText() &&
                btn5.getText() == btn9.getText() &&
                btn1.getText() != "" &&
                btn5.getText() != "" &&
                btn9.getText() != "") {
            letter = btn1.getText();
            win = true;
        }

        //Check Diagonally
        else if (btn3.getText() == btn5.getText() &&
                btn5.getText() == btn7.getText() &&
                btn3.getText() != "" &&
                btn5.getText() != "" &&
                btn7.getText() != "") {
            letter = btn3.getText();
            win = true;
        }

        if (win) {
            if (letter.equalsIgnoreCase(myLetter)) {
                JOptionPane.showMessageDialog(view.getFrame(), "Congratulations. You Win.");
            } else {
                JOptionPane.showMessageDialog(view.getFrame(), "You lose.");
            }

        } else if (!win && count == 9) {
            JOptionPane.showMessageDialog(view.getFrame(), "Draw.");
        }
    }

    /**
     *
     */
    
    private class exitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			writer.println("LEAVE");
			
		}
    	
    }

   private class submitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            name = view.getInputItem().getText();
            if (name == null || name.isEmpty() || name.equalsIgnoreCase(" ")) {
                view.getMessageLable().setText("Name can't be Null!!!");
                return;
            }
            view.getMessageLable().setText("WELCOME " + name);
            view.getFrame().setTitle("Tic Tac Toe-Player: " + name);
            view.getSubmit().setEnabled(false);
            writer.println(":" + name);
            view.getInputItem().setEnabled(false);
        }

    }

   private class buttonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (name == null || name.isEmpty()) {
                view.getMessageLable().setText("Please enter your name first... ");
                return;
            }
            if(win){
                return;
            }
            if (!playEnable) {
                return;
            }

            int buttonIndex = 0;
            if (count == 0) {
                isFirstMove = true;
                myLetter = "X";
            }
            for (int i = 0; i < 9; i++) {
                if (e.getSource().equals(view.getButton()[i])) {
                    if (view.getButton()[i].getText() != null
                            && !"".equalsIgnoreCase(view.getButton()[i].getText())) {
                        return;
                    }
                    buttonIndex = i + 1;
                    view.getButton()[i].setText(isFirstMove ? "X" : "O");
                    view.getButton()[i].setFont(new Font("BOLD", Font.BOLD, 20));
                    view.getButton()[i].setForeground(isFirstMove ? Color.RED : Color.GREEN);
                }
            }
            writer.println(buttonIndex);
            playEnable = false;
            count++;
            view.getMessageLable().setText("Valid move, wait for your opponent.");
            checkWinner();
        }

    }


    /**
     * Entry-Point Function for the first client
     * @param args
     */
    public static void main(String[] args) {
        Client d = new Client();
        d.go();
    }

}
