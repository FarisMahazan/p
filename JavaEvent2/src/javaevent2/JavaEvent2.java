package javaevent2;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class JavaEvent2 extends JFrame {
    JMenuBar mb;
    JMenu m1, m2;
    JMenuItem mi1, mi2;
    JTextArea ta;
    TextArea taa;
    JComboBox cx1, cx2;
    String menu[] = {"Nasi", "Mee", "Kerabu", "Air"};
    String menu2[][] = {
                        
                        {"Nasi Tomato", "Nasi Beriyani", "Nasi Kerabu", "Nasi Campur", "Nasi Kandar", "Nasi Dalca", "Nasi Arab",} ,
                        {"Mee Soto", "Mee Sotong", "Mee Kari", "Mee Udang", "Mee Rebus/Sup"},
                        {"Kerabu Maggi", "Kerabu Mangga", "Kerabu Perut", "Kerabu Kaki Ayam", "Kerabu Seafood"},
                        {"Air Khatira", "Air Green Tea", "Air Tebu", "Air Soya", "Air Qistina", "Air Blue Lemon", "Air Bandung Cincau", "Air Suam"}
            
                        };

    JavaEvent2(){
        mb = new JMenuBar();
        m1 = new JMenu("File");
        
            m2 = new JMenu("New");
            m1.add(m2);
                mi1 = new JMenuItem("Add Text");
                mi1.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent a){
                        
                        //TextArea From Awt
                        taa.append("My name is : Suparjo \n"
                                + "I like : My Mother \n"
                                + "I like Movie : Kamen Rider \n"
                                + "\t My Age : 20yrs \n"
                                + "\t My hobby : Games");
                        
                        //TextArea From Swing
                        ta.append("My name is : Suparjo \n"
                                + "I like : My Mother \n"
                                + "I like Movie : Kamen Rider \n"
                                + "\t My Age : 20yrs \n"
                                + "\t My hobby : Games");
                    }
                });
                m2.add(mi1);
                mi2 = new JMenuItem("Exit");
                m1.add(mi2);
        
        mb.add(m1);
        
        
        taa = new TextArea();
        taa.setBounds(10, 20, 350, 100);
        add(taa);
        
        ta = new JTextArea();
        ta.setBounds(10, 140, 350, 100);
        add(ta);
        
        cx1 = new JComboBox(menu);
        cx1.setBounds(10, 270, 350, 30);
        cx1.addItemListener(new ItemListener(){
           @Override
           
           public void itemStateChanged (ItemEvent r){
               
             if(r.getStateChange() == ItemEvent.SELECTED){
                 
                 int numberIndex = cx1.getSelectedIndex();
                 
                 cx2.removeAllItems();
                 for(String namaMenu2 : menu2[numberIndex]){
                     cx2.addItem(namaMenu2);
                 }
             }  
           } 
        });
        add(cx1);
        
        cx2 = new JComboBox();
        cx2.setBounds(10, 310, 350, 30);
        add(cx2);
        
        
        setJMenuBar(mb);
        
        setLayout(null);
        setVisible(true);
        setSize(400,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
       new JavaEvent2();
    }
    
}
