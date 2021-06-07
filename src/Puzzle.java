import java.awt.Frame;
import java.awt.Button;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class Puzzle extends Frame implements ActionListener {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    List<Button> buttons;
    Boolean gameOver;
    List<String> labelButtons = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "", "8");
    Puzzle() {
        super("Puzzle game");
        Collections.shuffle(labelButtons);
        b1 = new Button(labelButtons.get(0));
        b1.setBounds(50, 100, 40, 40);
        b2 = new Button(labelButtons.get(1));
        b2.setBounds(100, 100, 40, 40);
        b3 = new Button(labelButtons.get(2));
        b3.setBounds(150, 100, 40, 40);
        b4 = new Button(labelButtons.get(3));
        b4.setBounds(50, 150, 40, 40);
        b5 = new Button(labelButtons.get(4));
        b5.setBounds(100, 150, 40, 40);
        b6 = new Button(labelButtons.get(5));
        b6.setBounds(150, 150, 40, 40);
        b7 = new Button(labelButtons.get(6));
        b7.setBounds(50, 200, 40, 40);
        b8 = new Button(labelButtons.get(7));
        b8.setBounds(100, 200, 40, 40);
        b9 = new Button(labelButtons.get(8));
        b9.setBounds(150, 200, 40, 40);
        buttons = Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9);
        for (Button button : buttons) {
            button.addActionListener(this);
            add(button);
        }
        setSize(400, 400);
        setLayout(null);
        setVisible(true);
    }

    private void reinitPuzzle() {
        Collections.shuffle(labelButtons);
        for (Integer i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLabel(labelButtons.get(i));
            
        }
    }

    public static void main(String[] args) throws Exception {
        new Puzzle();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = buttons.indexOf(e.getSource());
        changePosition(index);
        checkIfGameOver();
    }


    private void checkIfGameOver() {
        gameOver = true;
        for (Integer i = 1; i < buttons.size(); i++) {
            String label = buttons.get(i - 1).getLabel();
            gameOver = gameOver && label.equals(i.toString());
        }
        if (gameOver) {
            JOptionPane.showMessageDialog(this, "Congratulations!!! You won the Game");
            reinitPuzzle();
        }
        
    }

    private void changePosition(int index) {
        List<Integer> steps = Arrays.asList(-3,-1, 1, 3);
        String label = buttons.get(index).getLabel();
        for (Integer step : steps) {
            int newIndex = index + step;
            if (newIndex >= 0 && newIndex < 9) {
                Button newButton = buttons.get(newIndex);
                if (newButton.getLabel().equals("")) {
                    newButton.setLabel(label);
                    buttons.get(index).setLabel("");
                    break;
                }

            }
        }
    }
}
