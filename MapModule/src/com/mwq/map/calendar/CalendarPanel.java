/*
 * CalendarPanel.java
 *
 * Created on 2008��6��28��, ����2:48
 */
package com.mwq.map.calendar;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author  Administrator
 */
public class CalendarPanel extends javax.swing.JPanel {

    private CalendarDialog dialog;

    /** Creates new form CalendarPanel */
    public CalendarPanel() {
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
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
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

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        textField.setEnabled(enabled);
        button.setEnabled(enabled);
    }

private void buttonActionPerformed(java.awt.event.ActionEvent evt) {                                       
// TODO add your handling code here:    
    Dimension textFieldPreferredSize = getTextField().getPreferredSize();// ����ı������ѡ��С

    Point locationOnScreen = getTextField().getLocationOnScreen();// ����ı�������Ļ�ϵ���ʼ���Ƶ�

    int x = (int) locationOnScreen.getX();// ����Ի�����X�����ʼ���Ƶ�

    int y = (int) (locationOnScreen.getY() 
            + textFieldPreferredSize.getHeight());// ����Ի�����Y�����ʼ���Ƶ�

    dialog = new CalendarDialog(null, true);// �����Ի������

    Dimension dialogPreferredSize = dialog.getPreferredSize();// ��öԻ������ѡ��С

    dialog.setBounds(x, y, (int) dialogPreferredSize.getWidth(), (int) dialogPreferredSize.getHeight());
    new Thread() {// ����������һ���߳�

        @Override
        public void run() {// �ع��÷���

            while (true) {
                if (MTableCell.getSelectedDay() != null) {// ����û�ѡ��������

                    getTextField().setText(CalendarDialog.getYear() 
                            + "-" + CalendarDialog.getMonth() 
                            + "-" + MTableCell.getSelectedDay());
                    dialog.dispose();// ��������ѡ���

                    MTableCell.setSelectedDay(null);
                    break;// ����ѭ��

                }
                try {
                    Thread.sleep(1000);// ����1��

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }.start();
    dialog.setVisible(true);// ��������ѡ���ɼ�
}                                      

private void textFieldKeyPressed(java.awt.event.KeyEvent evt) {                                     
// TODO add your handling code here:
    evt.consume();
}                                    

    // Variables declaration - do not modify                     
    private javax.swing.JButton button;
    private javax.swing.JTextField textField;
    // End of variables declaration                   

    public javax.swing.JTextField getTextField() {
        return textField;
    }
}