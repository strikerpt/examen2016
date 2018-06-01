
package examen2016;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Fenetre extends JFrame
{
    //Ajout des caracteristiques à la fenetre
    JPanel container = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JLabel CHF = new JLabel("CHF");
    JLabel euro = new JLabel("EURO");
    JTextField texteCHF = new JTextField();
    JTextField texteEURO = new JTextField();
    JButton boutonCALCULER = new JButton("Calculer");
    JButton boutonEFFACER = new JButton("Effacer");
    JRadioButton radioCHF_EURO = new JRadioButton("CHF => €");
    JRadioButton radioEURO_CHF = new JRadioButton("€ => CHF");
    ButtonGroup groupRADIO = new ButtonGroup();


    public Fenetre()
    {

        //Donner les caracteristiques pour le container    
            this.setTitle("Convertisseur de devises");
            this.setSize(450,120);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setBackground(Color.WHITE);

        //Donner les caracteristiques pour le label

            Font police = new Font ("Verdana", Font.BOLD, 10);
            CHF.setFont(police);
            euro.setFont(police);

        //panel1

            panel1.setPreferredSize(new Dimension(450,28));
            panel1.setFont(police);

        //panel2
            panel2.setPreferredSize(new Dimension(450,28));
            panel2.setFont(police);

        //textfield1
            texteCHF.setFont(police);
            texteCHF.setPreferredSize(new Dimension (60,20));
            texteCHF.setBackground(Color.white);

        //textfield2
            texteEURO.setFont(police);
            texteEURO.setPreferredSize(new Dimension (60,20));
            texteEURO.setBackground(Color.white);

        //bouton1
            boutonCALCULER.setFont(police);
            boutonCALCULER.setBackground(Color.BLUE);
            boutonCALCULER.setForeground(Color.WHITE);
            boutonCALCULER.addActionListener(new bCalculerListener());

        //bouton2
            boutonEFFACER.setFont(police);
            boutonEFFACER.setBackground(Color.BLACK);
            boutonEFFACER.setForeground(Color.WHITE);
            boutonEFFACER.addActionListener(new bEffacerListener());

        //Ajouter les radios dans le groupe

            groupRADIO.add(radioCHF_EURO);
            groupRADIO.add(radioEURO_CHF);
            radioCHF_EURO.addActionListener(new rRadio1());
            radioEURO_CHF.addActionListener(new rRadio2());

        //Ajouter les items sur la fenetre
            panel1.add(CHF);
            panel1.add(texteCHF);
            panel1.add(radioCHF_EURO);
            panel1.add(radioEURO_CHF);
            panel1.add(texteEURO);
            panel1.add(euro);
            panel2.add(boutonCALCULER);
            panel2.add(boutonEFFACER);
            container.add(panel1);
            container.add(panel2);

            this.setContentPane(container);
            this.setVisible(true);

            //Appeller la méthode effacer pour que le boutoun effacer fonctionne
            methode_effacer();

    }



    public void methode_effacer()
    {    
        //Methode pour arriver à faire l'effacer et au même temps le truc par défaut
        texteCHF.setText("");
        texteEURO.setText("");
        texteCHF.setEnabled(true);
        texteEURO.setEnabled(false);
        radioCHF_EURO.setSelected(true);
        radioEURO_CHF.setSelected(false);
    }



    class rRadio1 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //Cas où le radio1 est allumé
            texteCHF.setText("");
            texteEURO.setText("");
            texteCHF.setEnabled(true);
            texteEURO.setEnabled(false);
            }
    }


    class rRadio2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //Cas où le radio2 est allumé
            texteCHF.setText("");
            texteEURO.setText("");
            texteCHF.setEnabled(false);
            texteEURO.setEnabled(true); 
        }
    }


    class bEffacerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //Appelle à la methode effacer pour effacer les champs
            methode_effacer();
        }
    }

    class bCalculerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //CALCUL

            //initialiser le popup

            JOptionPane popup = new JOptionPane();

            if(radioCHF_EURO.isSelected())
            {
                
                if(!texteCHF.getText().equals(""))
                {
                    //Calcul si on coche le radio1 et convertion de texte à double
                    Double x= new Double(texteCHF.getText());
                    
                    if(x>0)
                    {
                    Double y = x/1.3;
                    texteEURO.setText(String.format("%.2f", y));
                    }
                    
                    else
                    {
                        //popup pour dire aux utilisateurs de mettre que des chiffres postifs
                        popup.showMessageDialog(null, "Veuillez saisir des chiffres positifs", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                
                else
                {
                    //popup en cas que le chiffre soit negatif ou soit 0
                    popup.showMessageDialog(null, "Veuillez saisir la valeur en CHF", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if(radioEURO_CHF.isSelected())
            {
                if(!texteEURO.getText().equals(""))
                {
                    //Calcul si on coche le radio2 et convertion de texte à double
                    Double x= new Double(texteEURO.getText());
                    
                    if(x>0)
                    {
                    Double y = x*1.3;
                    texteCHF.setText(String.format("%.2f", y));
                    }
                    
                    else 
                    {
                        //popup pour dire aux utilisateurs de mettre que des chiffres postifs
                        popup.showMessageDialog(null, "Veuillez saisir des chiffres positifs", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                else 
                {
                    popup.showMessageDialog(null, "Veuillez saisir la valeur en EURO", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                }
            }               
        }
    }
}
