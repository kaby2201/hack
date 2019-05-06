import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*

4 - Quiz

Relevante temaer

* Programkontroll (Løkker)
* Collections

 */

public class Quiz extends JPanel implements ActionListener {

    static JFrame myFrame;
    static Quiz myQuiz;

    ArrayList<String> questionList = new ArrayList<String>();

    JButton myButton1;
    JButton myButton2;

    void CreateAndShowPanel() {

        this.setPreferredSize(new Dimension(550, 250));  // makes myFrame resize itself to the panel

        myButton1 = new JButton("Legg til spørsmål");
        myButton2 = new JButton("Start spillet");
        myButton1.addActionListener( this );
        myButton2.addActionListener( this );
        this.add(myButton1);
        this.add(myButton2);

        myFrame.add(this, BorderLayout.CENTER);
        myFrame.pack();
        myFrame.setLocationRelativeTo( null );  // centers the window

    }


    public static void main(String[] args) {

        myFrame = new JFrame();
        myFrame.setTitle("4 - Quiz");
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setLayout( new BorderLayout() );
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myQuiz = new Quiz();
        myQuiz.CreateAndShowPanel();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String question = "abc", answer = "def";

        if(e.getSource().equals( myButton1 )) {

            while(!question.equals("")) {
                question = JOptionPane.showInputDialog("Legg til et nytt spørsmål.");
                questionList.add(question);
            }
        }

        if(e.getSource().equals( myButton2 )) {

            for(int i = 0; i < questionList.size(); i++) {

                if( !questionList.get(i).equals("") ) {

                    answer = JOptionPane.showInputDialog(questionList.get(i));
                    JOptionPane.showMessageDialog(this ,"Spørsmål: " +
                            questionList.get(i) + "\n" + "Svar: " + answer);

                }
            }
        }
    }
}
