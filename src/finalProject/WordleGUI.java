package finalProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WordleGUI extends JFrame {
	 private static Word ans;
	    public static int numGuesses;
	    public static int numWins;
	    public static int numGames;
	    public static int streak;
	    public static int maxStreak;
	    private int[] data;
	    private int[] NumberOfGuesses;
	    private static int row;
	    private static int col;
	    private static String guess;
	    private int counter;
	    private static RoundedButton[][] keys;
	    private static String[][] b;
	    private JFrame mainFrame;
	    private JTextField[][] textFields;
	    private JPanel panel;
	    private JPanel panel2;
	    private JPanel panel3;
	    private GridBagConstraints cons;
	    private JPanel borderPanel;
	    
	   

	        
    public WordleGUI() {
    	 keys = new RoundedButton[3][10];
    	 b = new String[][] {
    	            {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
    	            {"", "A", "S", "D", "F", "G", "H", "J", "K", "L"},
    	            {"", "", "Z", "X", "C", "V", "B", "N", "M", ""}
    	    };
        mainFrame = new JFrame("Wordle");
	    textFields = new JTextField[6][5];
	    panel = new JPanel();
	    panel2 = new JPanel();
	    panel3 = new JPanel();
	    cons = new GridBagConstraints();
	    borderPanel = new JPanel();
    	ans = new Word();
    	System.out.println(ans.getWord());
        numGuesses = 1;
        numWins = 0;
        numGames = 0;
        guess = "";
        row = 0;
        col = 0;
        streak = 0;
        maxStreak = 0;
        counter = 0;
    	data = new int[]{0, 0, 0, 0, 0, 0};
    	NumberOfGuesses = new int[]{1, 2, 3, 4, 5, 6};
    	
        
        mainFrame.setSize(1000, 1000);
        mainFrame.setLayout(new GridBagLayout());
        
        
        
        
        
        panel.setLayout(new GridLayout(6, 5, 5, 5));
        borderPanel.setLayout(new GridLayout(1, 1, 1, 1));
        panel2.setLayout(new GridLayout(1, 0));
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel wordle = new JLabel("Wordle");
        wordle.setFont(new Font("Book Antiqua", Font.BOLD, 50));
        wordle.setForeground(Color.WHITE);

        panel2.add(wordle);
        

        JLabel a = new JLabel("   ");
        a.setFont(new Font("Courier", Font.BOLD, 10));
        a.setForeground(Color.BLACK);
        borderPanel.add(a);

        borderPanel.setBackground(Color.BLACK);
        panel3.setBackground(Color.BLACK);
        panel2.setBackground(Color.BLACK);
        panel.setBackground(Color.BLACK);

        initializeArea();

        cons.gridx = 0;
        cons.gridy = 0;
        mainFrame.add(panel2, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        mainFrame.add(panel, cons);

        cons.gridx = 0;
        cons.gridy = 2;
        mainFrame.add(borderPanel, cons);

        cons.gridx = 0;
        cons.gridy = 3;
        panel3.setPreferredSize(new Dimension(600, 200));
        mainFrame.add(panel3, cons);
       
     

        mainFrame.getContentPane().setBackground(Color.decode("#00000"));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.validate();
        mainFrame.pack();
    }

    public void initializeArea() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
            	 RoundedButton button = new RoundedButton(b[i][j]);
                 keys[i][j] = button;

                 
                 button.addMouseListener(new MouseAdapter() {
                     @Override
                     public void mouseEntered(MouseEvent e) {
                         button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor to hand pointer when hovered over
                     }

                     @Override
                     public void mouseExited(MouseEvent e) {
                         button.setCursor(Cursor.getDefaultCursor()); // Set cursor to default when mouse exits
                     }
                 });
                 button.addActionListener((ActionListener) new ActionListener() {
                 
                	 public void actionPerformed(ActionEvent e) {
                     JButton button = (JButton) e.getSource();
                     String letter = button.getText();
                     if (counter < 5 && !letter.equals(("")) && !letter.equals("Enter") && !letter.equals("back")) {
                    	 letter(letter); 
          	        } 
                     
                     else if (letter.equals("Enter") && counter == 5 && Word.isWord(guess)) {
                    	 handlekeyPressed();
                     }
                     
                     else if (counter == 5 && letter.equals("Enter") && !Word.isWord(guess)) {
          	            dialogFader("Not in Word List!");
          	        } 
                     
                     else if (counter != 5 && letter.equals("Enter")) {
          	            dialogFader("Not enough Letters!");
          	        }
                     else if (letter.equals("back") && guess.length() > 0) {
          	        	backspace();
          	        } 
                                
                     
                 }
                 });
                 
                 button.addKeyListener(new KeyAdapter() { 
                	 public void keyPressed(KeyEvent e) {
             	        if (Character.isLetter(e.getKeyChar()) && counter < 5) {
             	            letter(e);
             	        } 
             	        
             	        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && guess.length() > 0) {
             	        	backspace();
             	        } 
             	        
             	        else if (counter == 5 && e.getKeyCode() == KeyEvent.VK_ENTER && Word.isWord(guess)) {
             	            handlekeyPressed();
             	           
             	        } 
             	        else if (counter == 5 && e.getKeyCode() == KeyEvent.VK_ENTER && !Word.isWord(guess)) {
             	            dialogFader("Not in Word List!");
             	        } else if (counter != 5 && e.getKeyCode() == KeyEvent.VK_ENTER) {
             	            dialogFader("Not enough Letters!");
             	        }
             	    }
                	 
                 });
                    
                 if (i == 2 && j == 0) {
                     // Make the button span two columns
                     button.setPreferredSize(new Dimension(90, 50));
                     button.setText("Enter");
                     panel3.add(button);
                 }
                 
                 else if (i== 2 && j ==1) {
                	 
                 }
                 
                 else if  (i == 2 && j==9) {
                	 button.setPreferredSize(new Dimension(90, 50));
                     button.setText("back");
                     panel3.add(button);
                 }
                 
                 else if(i==1 && j==0) {
                	 button.setBackground(Color.BLACK);
                	 button.setPreferredSize(new Dimension(50, 50));
                	 panel3.add(button);
                
                 }
                 
                 
                 
                 else {
                	 panel3.add(button);
                 }
                 
                
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                textFields[i][j] = new JTextField("");
                textFields[i][j].setBackground(Color.BLACK);
                textFields[i][j].setEditable(false);
                textFields[i][j].setHorizontalAlignment(JTextField.CENTER);
                textFields[i][j].setBorder(new LineBorder(Color.GRAY));
                textFields[i][j].setPreferredSize(new Dimension(70, 70));

                textFields[i][j].addKeyListener(new KeyAdapter() {
            
                	    public void keyTyped(KeyEvent e) {
                	        
                	    }

                	    @Override
                	    public void keyPressed(KeyEvent e) {
                	        if (Character.isLetter(e.getKeyChar()) && counter < 5) {
                	            letter(e);
                	        } 
                	        
                	        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && guess.length() > 0) {
                	        	backspace();
                	        } 
                	        
                	        else if (counter == 5 && e.getKeyCode() == KeyEvent.VK_ENTER && Word.isWord(guess)) {
                	            handlekeyPressed();
                	           
                	        } 
                	        else if (counter == 5 && e.getKeyCode() == KeyEvent.VK_ENTER && !Word.isWord(guess)) {
                	            dialogFader("Not in Word List!");
                	        } else if (counter != 5 && e.getKeyCode() == KeyEvent.VK_ENTER) {
                	            dialogFader("Not enough Letters!");
                	        }
                	    }

                	    @Override
                	    public void keyReleased(KeyEvent e) {
                	        
                	    }
                	});

                	panel.add(textFields[i][j]);
                }
            }
        }


    public void dialogFader(String labelinput) {
        JDialog dialog = new JDialog(mainFrame);
        dialog.setSize(200, 30);
        dialog.setLocationRelativeTo(panel);
        JPanel panel = new JPanel();
        JLabel label = new JLabel(labelinput);
        panel.add(label);
        dialog.getContentPane().add(panel);

        dialog.setUndecorated(true);
        dialog.setOpacity(1.0f);

        Timer fadeTimer = new Timer(20, new ActionListener() {
            float opacity = 1.0f;

            public void actionPerformed(ActionEvent e) {
                opacity -= 0.05f;

                if (opacity <= 0) {
                    dialog.dispose();
                } 
                else {
                    dialog.setOpacity(opacity);
                }
            }
        });

        Timer closeTimer = new Timer(900, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fadeTimer.start();
            }
        });

        dialog.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && counter == 5 && Word.isWord(guess)) {
                    fadeTimer.stop();
                    dialog.setOpacity(1.0f);
                    closeTimer.restart();
                } 
                else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && guess.length() > 0) {
                   backspace();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
            }
        });

        closeTimer.start();
        dialog.setVisible(true);
    }
    
    
    public void backspace() {
    	 counter--;
         guess = guess.substring(0, guess.length() - 1);
         col--;
         textFields[row][col].setText("");
         textFields[row][col].requestFocus();

    }
    
    public void letter(KeyEvent e) {
    	counter++;
        guess += e.getKeyChar();
        System.out.println(guess + " " + counter);
        
        textFields[row][col].setText(String.valueOf(e.getKeyChar()).toUpperCase());
        textFields[row][col].setFont(new Font("Franklin Gothic", Font.BOLD, 40));
        textFields[row][col].setForeground(Color.WHITE);
        textFields[row][col].requestFocus();
        col++;
    }
    
    //method called 
    public void letter(String e) {
    	counter++;
        guess += e.toLowerCase();
        System.out.println(guess + " " + counter);
        
        
        textFields[row][col].setText(e.toUpperCase());
        textFields[row][col].setFont(new Font("Franklin Gothic", Font.BOLD, 40));
        textFields[row][col].setForeground(Color.WHITE);
        textFields[row][col].requestFocus();
        col++;
    }

    public void handlekeyPressed() {
        for (int i = 0; i < 5; i++) {
            System.out.println(ans.correct(guess));
            if (ans.correct(guess).get(i).equals(1)) {
                for (int x = 0; x < 3; x++) {
                    for (int j = 0; j < 10; j++) {
                        if (b[x][j].equalsIgnoreCase(String.valueOf(guess.charAt(i))) && !keys[x][j].getBackground().equals(Color.decode("#538d4e"))) {
                            keys[x][j].setBackground(Color.decode("#b89c3c"));
                        }
                    }
                }
            }

            if (ans.correct(guess).get(i).equals(0)) {
                for (int x = 0; x < 3; x++) {
                    for (int j = 0; j < 10; j++) {
                        if (b[x][j].equalsIgnoreCase(String.valueOf(guess.charAt(i)))) {
                            keys[x][j].setBackground(Color.decode("#538d4e"));
                        }
                    }
                }
            } else {
                for (int x = 0; x < 3; x++) {
                    for (int j = 0; j < 10; j++) {
                        if (b[x][j].equalsIgnoreCase(String.valueOf(guess.charAt(i))) &&
                                !keys[x][j].getBackground().equals(Color.decode("#538d4e")) &&
                                !keys[x][j].getBackground().equals(Color.decode("#b89c3c"))) {
                            keys[x][j].setBackground(Color.decode("#403c3c"));
                        }
                    }
                }
            }
        }

        counter = 0;

        for (int i = 0; i < 5; i++) {
            if (ans.correct(guess).get(i).equals(1)) {
                textFields[row][i].setBackground(Color.decode("#b89c3c"));
                textFields[row][i].setBorder(new LineBorder(Color.decode("#b89c3c")));
                textFields[row][i].setOpaque(true);
            } else if (ans.correct(guess).get(i).equals(0)) {
                textFields[row][i].setBackground(Color.decode("#538d4e"));
                textFields[row][i].setBorder(new LineBorder(Color.decode("#538d4e")));
                textFields[row][i].setOpaque(true);
            } else {
                textFields[row][i].setBackground(Color.decode("#787c7f"));
                textFields[row][i].setBorder(new LineBorder(Color.decode("#787c7f")));
                textFields[row][i].setOpaque(true);
            }
        }

        if (ans.getWord().equals(guess)) {
            JOptionPane.showMessageDialog(mainFrame, "YOU WON");
            numGames++;
            numWins++;
            streak++;
            if (streak > maxStreak) {
            	maxStreak = streak;
            }
            
            if (numGuesses < 7) {
            	data[numGuesses-1]++;
                }
            resetGame();
        } 
        else {
            guess = "";
            col = 0;
            row++;
            numGuesses++;
            
            if (row != 6) {
                  textFields[row][col].requestFocus();
             }
            
            else {
           JOptionPane.showMessageDialog(mainFrame, "You lost, the word was: " + ans.getWord());
           numGames++;
           
           if (numGuesses < 7) {
           	data[numGuesses - 1]++;
               }
           
           if (streak > maxStreak) {
           	maxStreak = streak;
           }
           
           streak = 0;
           resetGame();
            }
        }
           
           
        }




    public static void main(String[] args) {
         new WordleGUI();
        
    }

    public void resetGame() {
            row = 0;
            col = 0;
            guess = "";
            counter = 0;
            
            ans = new Word();
            System.out.println(ans.getWord());

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    textFields[i][j].setText("");
                    textFields[i][j].setBackground(Color.BLACK);
                    textFields[i][j].setBorder(new LineBorder(Color.GRAY));
                    textFields[i][j].setOpaque(true);
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 10; j++) {
                	
                	if (i == 2 && j == 0) {
                        // Make the button span two columns
                        keys[i][j].setPreferredSize(new Dimension(90, 50));
                        keys[i][j].setText("Enter");
                        
                    }
                	
                	 else if  (i == 2 && j==9) {
                    	 keys[i][j].setPreferredSize(new Dimension(90, 50));
                	 }
                    
                    else if (i== 2 && j ==1) {
                   	 
                    }
                    
                    else if(i==1 && j==0 ) {
                    	keys[i][j].setBackground(Color.BLACK);
         
                   	
                    }
                    
                    else {
                   	
                    
                	
                    keys[i][j].setBackground(Color.GRAY);
                    keys[i][j].setHorizontalAlignment(JTextField.CENTER);
                    keys[i][j].setPreferredSize(new Dimension(50, 50));
                    if (b[i][j].equals("")) {
                        keys[i][j].setBackground(Color.GRAY);
                        keys[i][j].setBorder(new LineBorder(Color.GRAY));
                    }
                    keys[i][j].setBorder(new LineBorder(Color.GRAY));
                    keys[i][j].setFont(new Font("Franklin Gothic", Font.BOLD, 20));
                    keys[i][j].setForeground(Color.WHITE);
                }
            }
            }
            textFields[row][col].requestFocus();
            new Stats(data, NumberOfGuesses, numGuesses, numGames, numWins, streak, maxStreak);
            numGuesses = 1;
        }
    

    }
