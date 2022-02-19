import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.*;
import java.io.*;
public class GameAnimal extends JFrame {
	
	private JButton[][] jB1 = new JButton[8][10];
	//String names[] = new String[9];
	String names[]={"a","a","b","c","d","e","f","g","h","i"};
	//private JButton[][] buttonLogout = new JButton("Logout");
	public GameAnimal() {

        //implementGame(this);
    }

    private void implementGame(GameAnimal ga){
        setTitle("Game Animal");
        setSize(1500, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel jP1 = new JPanel();
        jP1.setLayout(null);
        jP1.setBounds(50, 50, 1400, 850);
        jP1.setVisible(true);
        jP1.setBackground(Color.GREEN);
        
        /*jB1[0][0].setEnabled(true);
        jB1[0][0].setBounds(50,50,40,40);
		jB1[0][0].setVisible(true);
		jB1[0][0].setBackground(Color.GRAY);
		jP1.add(jB1[0][0]);*/
        for(int i=0;i<8;i++){
        	for(int j=0;j<10;j++){
        		JButton jB2 = new JButton();
        		jB2.setBounds(i*70+70, j*70+70, 70, 70);
        		jB2.setBackground(Color.GREEN);
        		jB2.setEnabled(true);
        		jB2.setVisible(true);
        		if(i==0){
        			//jB2.setText(""+j);
        			Border thickBorder = new LineBorder(Color.GREEN, 1);
        			jB2.setBorder(thickBorder);
        			if(j!=0){
        				jB2.setText(names[j]);
        			}
        		}
        		if(j==0){
        			Border thickBorder = new LineBorder(Color.GREEN, 1);
        			jB2.setBorder(thickBorder);
        			if(i!=0){
        				jB2.setText(""+i);
        			}
        		}
        		jB1[i][j]= jB2;
        		/*if(i==2&&j==9){
        			try {
        	    	    Image img = ImageIO.read(getClass().getResource("//C://Users//Vinay//Documents//elephant.png"));
        	    	    jB2.setIcon(new ImageIcon(img));
        	    	  } catch (IOException ex) {
        	    	  }
        		}*/

        		jP1.add(jB1[i][j]);
        	}
        }
        ga.add(jP1);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        //pack();
    	
    }
    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
    }

    public static void main(String[] args) {

       /* EventQueue.invokeLater(new Runnable() {
        
        	//@Override
        public void run() {
                GameAnimal game = new GameAnimal();
                game.setVisible(true);
                //game.setSize(600,600);
            }
        });*/
    	GameAnimal game = new GameAnimal();
        game.setVisible(true);
        game.setExtendedState(game.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        game.implementGame(game);
        game.setLayout(null);
    }
}