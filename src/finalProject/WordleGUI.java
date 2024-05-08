package finalProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/*
 * By: Yadel Gezehagne
 * The main goal of this project was to create a Wordle clone strictly using Java!
 * Last major updates: 5/1/24
 */


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
	    private static String[][] KeyBoard;
	    private JFrame mainFrame;
	    private JTextField[][] textFields;
	    private JPanel gamePanel;
	    private JPanel titlePanel;
	    private JPanel keyboardPanel;
	    private GridBagConstraints cons;
	    private JPanel borderPanel;
	    int index;
	    int delay;

	        
	    /*
	     * This constructor builds the Wordle frame and all other components.
	     * An area for improvement would be the better usage of newer
	     * layout managers such as MigLayout.
	     */
    public WordleGUI() {
    	 keys = new RoundedButton[3][10];
    	 KeyBoard = new String[][] {
    	            {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
    	            {"", "A", "S", "D", "F", "G", "H", "J", "K", "L"},
    	            {"", "", "Z", "X", "C", "V", "B", "N", "M", ""}
    	    };
        mainFrame = new JFrame("Wordle");
	    textFields = new JTextField[6][5];
	    gamePanel = new JPanel();
	    titlePanel = new JPanel();
	    keyboardPanel = new JPanel();
	    cons = new GridBagConstraints();
	    borderPanel = new JPanel();
    	ans = new Word();
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
    	index = 0;
        
      
        mainFrame.setLayout(new GridBagLayout());
        
        //Create empty bottom panel for good Gridbag constraint spacing (on the bottom).
        JLabel a = new JLabel("   ");
        a.setFont(new Font("Courier", Font.BOLD, 10));
        a.setForeground(Color.BLACK);
        borderPanel.add(a);
        

        
        JLabel leaderboard = new JLabel(createIcon("C:\\Users\\gbky2\\Downloads\\icon.png"));
        JLabel settings = new JLabel(createIcon("C:\\Users\\gbky2\\Downloads\\icon.png"));
    
       
        leaderboard.addMouseListener(new MouseAdapter() {
        			@Override
        				public void mouseEntered(MouseEvent e) {
            					leaderboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        				}

        			@Override
        				public void mouseExited(MouseEvent e) {
        						leaderboard.setCursor(Cursor.getDefaultCursor()); 
        				}
        			
        			public void mouseClicked(MouseEvent e) {
        				new Stats(data, NumberOfGuesses, -1, numGames, numWins, streak, maxStreak);
                    }
        			});
        
        
        
        //Set the layouts for the panels.
        gamePanel.setLayout(new GridBagLayout());
        titlePanel.setLayout((new GridLayout(1, 3)));
        keyboardPanel.setLayout(new GridBagLayout());

        
        //Create the title.
        JLabel wordle = new JLabel("Wordle");
        wordle.setFont(new Font("Book Antiqua", Font.BOLD, 50));
        wordle.setForeground(Color.WHITE);
     
        
        //Create the top panel which contains the settings icon, Wordle, and the leaderboard icon
        wordle.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(settings);
        titlePanel.add(wordle);
        titlePanel.add(leaderboard);
        titlePanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
      
        
    
        //Set the backgrounds to be black because dark theme is the best.
        borderPanel.setBackground(Color.BLACK);
        keyboardPanel.setBackground(Color.BLACK);
        titlePanel.setBackground(Color.BLACK);
        gamePanel.setBackground(Color.BLACK);

        initializeArea();

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.anchor = GridBagConstraints.NORTH;

      
        cons.weighty = 0.3;
        cons.gridx = 0;
        cons.gridy = 0;
        mainFrame.add(titlePanel, cons);
       
        
  
        cons.weighty = 0.5;
        cons.gridx = 0;
        cons.gridy = 1;
        mainFrame.add(gamePanel, cons);

        cons.weighty = 0.5;
        cons.gridx = 0;
        cons.gridy = 2;
        mainFrame.add(keyboardPanel, cons);
       
        cons.weighty = 1;
        cons.gridx = 0;
        cons.gridy = 3;
        mainFrame.add(borderPanel, cons);

        mainFrame.getContentPane().setBackground(Color.decode("#00000"));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.validate();
        mainFrame.pack();
    }

    //Creates the buttons (keyboard) and textfields (the screen)
    public void initializeArea() {
    	cons.gridx = 0;
    	cons.gridy = 0;
    	cons.insets = new Insets(2, 2, 2, 2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
            	cons.gridy = i;
                cons.gridx = j;
                
            	 RoundedButton button = new RoundedButton(KeyBoard[i][j]);
                 keys[i][j] = button;
                 button.addActionListener((ActionListener) new ActionListener() {
                 
                	 public void actionPerformed(ActionEvent e) {
                     JButton button = (JButton) e.getSource();
                     String letter = button.getText();
                     if (counter < 5 && !letter.equals(("")) && !letter.equals("Enter") && !letter.equals("back")) {
                    	 letterTyped(letter); 
          	        } 
                     
                     else if (letter.equals("Enter") && counter == 5 && Word.isWord(guess)) {
                    	 enterPressed();
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
                 
                 /*
                  * KeyListener for when the buttons have focus instead of the textfields.
                  * Giving the textfields focus repeatedly after a button is pressed wouldn't allow for letters to 
                  * be typed subsequently.
                  */
                 
                 button.addKeyListener(new KeyAdapter() { 
                	 public void keyPressed(KeyEvent e) {
             	        if (Character.isLetter(e.getKeyChar()) && counter < 5) {
             	            letterTyped(String.valueOf(e.getKeyChar()));
             	        } 
             	        
             	        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && guess.length() > 0) {
             	        	backspace();
             	        } 
             	        
             	        else if (counter == 5 && e.getKeyCode() == KeyEvent.VK_ENTER && Word.isWord(guess)) {
             	            enterPressed();
             	           
             	        } 
             	        else if (counter == 5 && e.getKeyCode() == KeyEvent.VK_ENTER && !Word.isWord(guess)) {
             	            dialogFader("Not in Word List!");
             	        } else if (counter != 5 && e.getKeyCode() == KeyEvent.VK_ENTER) {
             	            dialogFader("Not enough Letters!");
             	        }
             	    }
                	 
                 });
                    
                 //This is the logic for modifying the Rounded button class to create the Enter button.
                 if (i == 2 && j == 0) {
                     //Make the button span two columns
                     button.setPreferredSize(new Dimension(108, 50));
                     button.setText("Enter");                     
                     button.addMouseListener(new MouseAdapter() {
                         @Override
                         public void mouseEntered(MouseEvent e) {
                             button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
                         }

                         @Override
                         public void mouseExited(MouseEvent e) {
                             button.setCursor(Cursor.getDefaultCursor());
                         }
                     });
                     
                 

                     cons.gridwidth = 2;
                     keyboardPanel.add(button, cons);
                     cons.gridwidth = 1;
                 }
                 
                 
                 //Skip this box next to enter to allow Enter box to take up more space.
                 else if (i== 2 && j ==1) {
                	continue; 
                 }
                 
                 else if  (i == 2 && j==9) {
                	button.setPreferredSize(new Dimension(90, 50));
                     button.setText("back");
                     button.addMouseListener(new MouseAdapter() {
                         @Override
                         public void mouseEntered(MouseEvent e) {
                             button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
                         }

                         @Override
                         public void mouseExited(MouseEvent e) {
                             button.setCursor(Cursor.getDefaultCursor()); 
                         }
                     });
                     
                     cons.gridwidth = 2;
                     keyboardPanel.add(button, cons);
                     cons.gridwidth = 1;
                     
                 }
               
                 //Essentially a filler black space above the Enter button.
                 else if(i==1 && j==0) {
                	 continue;
                 }
                 
                 
                 
                 else { 
                     button.addMouseListener(new MouseAdapter() {
                         @Override
                         public void mouseEntered(MouseEvent e) {
                             button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
                         }

                         @Override
                         public void mouseExited(MouseEvent e) {
                             button.setCursor(Cursor.getDefaultCursor()); 
                         }
                     });
                	 keyboardPanel.add(button, cons);
                 }
                 
                
            }
        }
        
        //Creating the word boxes where each letter is displayed after typing or clicking a button
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
            	cons.gridy = i;
                cons.gridx = j;
                textFields[i][j] = new JTextField("");
                textFields[i][j].setFont(new Font("Franklin Gothic", Font.BOLD, 40));
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
                	            letterTyped(String.valueOf(e.getKeyChar()));
                	        } 
                	        
                	        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && guess.length() > 0) {
                	        	backspace();
                	        } 
                	        
                	        else if (counter == 5 && e.getKeyCode() == KeyEvent.VK_ENTER && Word.isWord(guess)) {
                	            enterPressed();
                	           
                	        } 
                	        else if (counter == 5 && e.getKeyCode() == KeyEvent.VK_ENTER && !Word.isWord(guess)) {
                	            dialogFader("Not in Word List!");
                	        } 
                	        
                	        else if (counter != 5 && e.getKeyCode() == KeyEvent.VK_ENTER) {
                	            dialogFader("Not enough Letters!");
                	        }
                	    }

                	    @Override
                	    public void keyReleased(KeyEvent e) {
                	        
                	    }
                	});

                	gamePanel.add(textFields[i][j], cons);
                }
            }
        }

    //Dialog fader for when there is either not enough letters or a word is not in the word list.
    public void dialogFader(String labelinput) {
        JDialog dialog = new JDialog(mainFrame);
        dialog.setSize(200, 30);
        dialog.setLocationRelativeTo(gamePanel);
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
        
        //Allows for letters to be pressed while the dialog fader is still active.
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
    
    
    public ImageIcon createIcon(String pic) {	
    	BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(pic));
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }

        Image resizedImage = myPicture.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(resizedImage);
    	return icon;

    }
    //Logic for when backspace is either clicked or pressed on the keyboard
    public void backspace() {
    	 counter--;
         guess = guess.substring(0, guess.length() - 1);
         col--;
         textFields[row][col].setText("");
         textFields[row][col].requestFocus();
    }
    
    
    //Logic for when a letter is pressed on the keyboard or clicked on the digital keyboard..
    public void letterTyped(String e) {
    	counter++;
        guess += e.toLowerCase();        
        textFields[row][col].setText(e.toUpperCase());
        textFields[row][col].setForeground(Color.WHITE);
        textFields[row][col].requestFocus();
        col++;
    }
    

    //Method is called when enter is pressed and its a valid call (five letters typed and a real word). 
    public void enterPressed()  {
         delay = 450; 
        Timer timer = new Timer(delay, (e) -> {
            if (index < 5) {
                if (ans.correct(guess).get(index).equals(1)) {
                    textFields[row][index].setBackground(Color.decode("#b89c3c"));
                    textFields[row][index].setBorder(new LineBorder(Color.decode("#b89c3c")));
                    textFields[row][index].setOpaque(true);
                } 
                
                else if (ans.correct(guess).get(index).equals(0)) {
                    textFields[row][index].setBackground(Color.decode("#538d4e"));
                    textFields[row][index].setBorder(new LineBorder(Color.decode("#538d4e")));
                    textFields[row][index].setOpaque(true);
                }
                
                else {
                    textFields[row][index].setBackground(Color.decode("#787c7f"));
                    textFields[row][index].setBorder(new LineBorder(Color.decode("#787c7f")));
                    textFields[row][index].setOpaque(true);
                }
                index++; //Move to the next iteration.
                
                //Makes the letters sequentially pop up quicker exponentially.
                delay -= 20 * index;
                ((Timer)e.getSource()).setDelay(delay); 
            } 
            
            
            else {
            	
                ((Timer)e.getSource()).stop();
                index = 0;
                //Stop the timer when all iterations are done and reset index.
            
                //This chunk of code re-colors the keyboard based off of the guess.
                for (int i = 0; i < 5; i++) {
                    if (ans.correct(guess).get(i).equals(1)) {
                        for (int x = 0; x < 3; x++) {
                            for (int j = 0; j < 10; j++) {
                                if (KeyBoard[x][j].equalsIgnoreCase(String.valueOf(guess.charAt(i))) && !keys[x][j].getBackground().equals(Color.decode("#538d4e"))) {
                                    keys[x][j].setBackground(Color.decode("#b89c3c"));
                                }
                            }
                        }
                    }

                    if (ans.correct(guess).get(i).equals(0)) {
                        for (int x = 0; x < 3; x++) {
                            for (int j = 0; j < 10; j++) {
                                if (KeyBoard[x][j].equalsIgnoreCase(String.valueOf(guess.charAt(i)))) {
                                    keys[x][j].setBackground(Color.decode("#538d4e"));
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < 3; x++) {
                            for (int j = 0; j < 10; j++) {
                                if (KeyBoard[x][j].equalsIgnoreCase(String.valueOf(guess.charAt(i))) &&
                                        !keys[x][j].getBackground().equals(Color.decode("#538d4e")) &&
                                        !keys[x][j].getBackground().equals(Color.decode("#b89c3c"))) {
                                    keys[x][j].setBackground(Color.decode("#403c3c"));
                                }
                            }
                        }
                    }
                }
                counter = 0;
                
                /*
                 * If the answer equals the word, increase the number of wins and the streak.
                 * Check to see if the current streak is larger than the max streak and update if needed.
                 */
                if (ans.getWord().equals(guess)) {
                    JOptionPane.showMessageDialog(mainFrame, "YOU WON");
                    numGames++;
                    numWins++;
                    streak++;
                    
                    if (streak > maxStreak) {
                    	maxStreak = streak;
                    }

                    data[numGuesses-1]++;    
                    resetGame();
                } 
                
                //Continue the game.
                else {
                    guess = "";
                    col = 0;
                    row++;
                    numGuesses++;
                    //Check to see if the last row is occupied, if not, continue the game.
                    if (row != 6) {
                          textFields[row][col].requestFocus();
                     	}
                    
                    //Or else you used the last guess and you lost.
                    else {
                    	JOptionPane.showMessageDialog(mainFrame, "You lost, the word was: " + ans.getWord());
                    	numGames++;
                    	
                    	if (streak > maxStreak) {
                    		maxStreak = streak;
                    	}
                    	
                    	//Reset streak
                    	streak = 0;
                    	resetGame();
                    	
                    }
                }
            }
        });

        //Start the Timer
        timer.start();
          
        }




    public static void main(String[] args) {
         new WordleGUI();
        
    }

   //Resets the color and all other variables associated with the last game play.
    public void resetGame() {
            row = 0;
            col = 0;
            guess = "";
            counter = 0;
            ans = new Word(); 

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
                        //Make the Enter button span two columns
                        keys[i][j].setPreferredSize(new Dimension(108, 50));
                        keys[i][j].setText("Enter");
                        
                    }
                	
                	 else if  (i == 2 && j==9) {
                    	 keys[i][j].setPreferredSize(new Dimension(90, 50));
                	 }
                    
                	//No button created next to Enter.
                    else if (i== 2 && j ==1) {
                   	 continue;
                    }
                    
                	
                	//Make button to take up space above Enter and color it black.
                    else if(i==1 && j==0 ) {
                    	keys[i][j].setBackground(Color.BLACK);
                   	
                    }
                    
                    else {
                    keys[i][j].setBackground(Color.GRAY);
                    keys[i][j].setHorizontalAlignment(JTextField.CENTER);
                    keys[i][j].setPreferredSize(new Dimension(50, 50));
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

