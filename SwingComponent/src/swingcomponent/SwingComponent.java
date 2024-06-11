package swingcomponent;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class SwingComponent extends JFrame{
JLabel lbl1,lbl2,lbl3,lbl4,lbl5;
JTextField txt1;
JPasswordField pf;
JCheckBox cb1,cb2;
JRadioButton rb1, rb2;
JButton btn1,btn2,btn3,btn4,btn5;
JTextArea ta1;
JComboBox cx1;

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/customer";
    String user = "root";
    String passw = "";
    
    SwingComponent(){
        lbl1 = new JLabel("Email :");
        lbl1.setBounds(30,30,100,30);
        add(lbl1);
        txt1 = new JTextField();
        txt1.setBounds(120,30,150,30);
        add(txt1);
        
        lbl2 = new JLabel("Password :");
        lbl2.setBounds(30,70,100,30);
        add(lbl2);
        pf = new JPasswordField();
        pf.setBounds(120,70,150,30);
        add(pf);
        
        lbl3 = new JLabel("Qualification :");
        lbl3.setBounds(30,110,100,30);
        add(lbl3);
        cb1 = new JCheckBox("SPM");
        cb1.setBounds(120,115,80,20);
        add(cb1);
        cb2 = new JCheckBox("DIPLOMA");
        cb2.setBounds(200,115,80,20);
        add(cb2);
        
        
        lbl4 = new JLabel("Gender :");
        lbl4.setBounds(30,150,100,30);
        add(lbl4);
        ButtonGroup bg1 = new ButtonGroup();
        rb1 = new JRadioButton("Male");
        rb1.setBounds(120,150,80,30);
        add(rb1);
        bg1.add(rb1);
        rb2 = new JRadioButton("Female");
        rb2.setBounds(200,150,80,30);
        add(rb2);
        bg1.add(rb2);
        
        lbl5 = new JLabel("Jabatan :");
        lbl5.setBounds(30,190,100,30);
        add(lbl5);
        String seat[] = {"Please Select", "JTMK", "JKM", "JKE", "JP"};
        cx1 = new JComboBox(seat);
        cx1.setBounds(120,190,150,30);
        add(cx1);
        
        btn1 = new JButton("Clear");
        btn1.setBounds(20,230,80,30);
        add(btn1);
        btn1.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e){
                txt1.setText("");
                pf.setText("");
                cb1.setSelected(false);
                cb2.setSelected(false);
                bg1.clearSelection();
                cx1.setSelectedItem("Please Select");
                ta1.setText("");
            }
        });
        
        btn2 = new JButton("Test");
        btn2.setBounds(110,230,80,30);
        add(btn2);
        btn2.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent s) {
                
                
                try{
                    Class.forName(driver);
                    Connection con = DriverManager.getConnection(url, user, passw);
                    
                    
                    if(!con.isClosed()){
                        JOptionPane.showMessageDialog(null, "Successful Connected");
                    }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error from button Test " + ex);
                }
                
            }
        });
        
        btn3 = new JButton("Submit");
        btn3.setBounds(200,230,80,30);
        add(btn3);
        btn3.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent d) {
                
                String email = txt1.getText();
                String pass = new String(pf.getPassword());
                
                String cb = new String();
                if(cb1.isSelected()){
                    cb = "SPM";
                } else if (cb2.isSelected()) {
                    cb = "DIPLOMA";
                } else {
                    cb = "Not Selected";
                }
                
                String g = new String();
                if(rb1.isSelected()){
                    g = "Male";
                } else if (rb2.isSelected()) {
                    g = "Female";
                } else {
                    g = "Not Selected";
                }
                
                String j = cx1.getSelectedItem().toString();
                
                
                ta1.append("Email : " + email + "\n" + 
                        "Password : " + pass + "\n" + 
                        "Qualification : " + cb + "\n" +
                        "Gender : " + g + "\n" +
                        "Jabatan : " + j + "\n");
                
                
                //INSERT INTO DATABASE TABLE
                try{
                    Class.forName(driver);
                    Connection con = DriverManager.getConnection(url, user, passw);
                    String sql = "insert into info (email,pass,quali,gen,faculty) values ('" + email + "','" + pass+ "','"+ cb +"','"+ g +"','"+ j +"')";
                    // insert into info (email,pass) values ('email','pass')
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    // Note:
                    // Insert, Update, Delete (USE executeUpdate())
                    // Select (USE executeUpdate())
                    JOptionPane.showMessageDialog(null, "Successful Insert Database");
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error Submit " + ex);
                }
            }
        });
        
        btn4 = new JButton("Delete");
        btn4.setBounds(290,230,80,30);
        add(btn4);
        btn4.addActionListener (new ActionListener (){
            @Override
            public void actionPerformed (ActionEvent x){
                int pkInfo = Integer.parseInt(JOptionPane.showInputDialog("Insert ID Key"));
                
                try{
                    Class.forName(driver);
                    Connection con = DriverManager.getConnection(url, user, passw);
                    String sql = "delete from info where pk_info="+pkInfo;
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    
                    JOptionPane.showMessageDialog(null, "Successful Delete Database by pk_Info " + pkInfo);
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error Delete " + ex);
                }
            }
        });
        
        btn5 = new JButton("Update");
        btn5.setBounds(290,270,80,30);
        add(btn5);
        btn5.addActionListener (new ActionListener (){
            @Override
            public void actionPerformed (ActionEvent x){
                int pkInfo = Integer.parseInt(JOptionPane.showInputDialog("Insert ID Key"));
                String email = txt1.getText();
                String pass = new String(pf.getPassword());
                
                try{
                    Class.forName(driver);
                    Connection con = DriverManager.getConnection(url, user, passw);
                    String sql = "update info set email='"+email+"', pass='"+pass+"' where pk_info="+pkInfo;
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    
                    JOptionPane.showMessageDialog(null, "Successful Update Database by pk_Info " + pkInfo);
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error Update " + ex);
                }
            }
        });
        
        ta1 = new JTextArea();
        ta1.setBounds(20,270,260,120);
        ta1.setEditable(false);
        add(ta1);
        
        
        //JFrame
        setLayout(null);
        setVisible(true);
        setSize(400,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new SwingComponent();
    }
    
}
