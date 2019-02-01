/*
 * MTreePanel.java
 *
 * Created on 2008��6��30��, ����9:29
 */
package com.mwq.map.mwing;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author  Administrator
 */
public class MTreePanel extends javax.swing.JPanel {

    /** Creates new form MTreePanel */
    public MTreePanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        textField = new javax.swing.JTextField();
        button = new javax.swing.JButton();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        textField.setColumns(9);
        textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldMouseClicked(evt);
            }
        });
        add(textField);

        button.setText("��");
        button.setMargin(new java.awt.Insets(0, 6, 0, 6));
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
        add(button);
    }// </editor-fold>                        

private void buttonActionPerformed(java.awt.event.ActionEvent evt) {                                       
// TODO add your handling code here:
    Dimension preferredSize = getTextField().getPreferredSize();
    Point locationOnScreen = getTextField().getLocationOnScreen();
    int x = (int) locationOnScreen.getX();
    int y = (int) (locationOnScreen.getY() + preferredSize.getHeight());
    MTreeDialog dialog = new MTreeDialog(null, true,getTextField());
    dialog.setBounds(x, y, (int) dialog.getPreferredSize().getWidth(), (int) dialog.getPreferredSize().getHeight());
    dialog.setVisible(true);
}                                      

private void textFieldMouseClicked(java.awt.event.MouseEvent evt) {                                       
// TODO add your handling code here:
    this.buttonActionPerformed(null);
}                                      

    // Variables declaration - do not modify                     
    private javax.swing.JButton button;
    private javax.swing.JTextField textField;
    // End of variables declaration                   

    public javax.swing.JTextField getTextField() {
        return textField;
    }
}