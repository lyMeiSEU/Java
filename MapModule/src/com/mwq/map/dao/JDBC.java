/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.map.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class JDBC {

    private static final String DRIVERCLASS = "org.apache.derby.jdbc.EmbeddedDriver"; // ����

    private static final String URL = "jdbc:derby:db_map"; // Э��

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>(); // �����������ݿ�����

    private static Connection conn = null; // ���ݿ�����

    

    static { // ͨ����̬�����������ݿ����������������ݿⲻ���ڵ�����´������ݿ�

        try {
            Class.forName(DRIVERCLASS); // �������ݿ�����

            File databaseFile = new File("db_map");// �������ݿ��ļ�����

            if (!databaseFile.exists()) {// �ж����ݿ��ļ��Ƿ����

                String[] sqls = new String[5];// ���崴�����ݿ��SQL���

                sqls[0] = "create table tb_map (id int not null,name varchar(8) not null)";
                sqls[1] = "insert into tb_map(id,name) values(1,'map.jpg')";
                sqls[2] = "create table tb_sort (id int not null,father_id int not null,name varchar(20) not null,primary key (id))";
                sqls[3] = "create table tb_sign (id int not null,sort_id int not null,x int not null,y int not null,title varchar(20) not null,show int not null,scale float not null,date date not null,remark varchar(200),primary key (id))";
                sqls[4] = "create view v_sign_sort as SELECT tb_sign.x, tb_sign.y, tb_sign.title, tb_sort.id, tb_sort.name, tb_sign.show, tb_sign.scale, tb_sign.date, tb_sign.remark FROM tb_sign INNER JOIN tb_sort ON tb_sign.sort_id = tb_sort.id ";
                conn = DriverManager.getConnection(URL + ";create=true");// �������ݿ�����

                threadLocal.set(conn);// �������ݿ�����

                Statement stmt = conn.createStatement();// �������ݿ�����״̬����

                for (int i = 0; i < sqls.length; i++) {// ͨ��ִ��SQL��䴴�����ݿ�

                    stmt.execute(sqls[i]);// ִ��SQL���

                }
                stmt.close();// �ر����ݿ�����״̬����

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static Connection getConnection() { // �������ݿ����ӵķ���

        conn = (Connection) threadLocal.get(); // ���߳��л�����ݿ�����

        if (conn == null) { // û�п��õ����ݿ�����

            try {
                conn = DriverManager.getConnection(URL);// �����µ����ݿ�����

                threadLocal.set(conn); // �����ݿ����ӱ��浽�߳���

            } catch (Exception e) {
                String[] infos = {"δ�ܳɹ��������ݿ⣡", "��ȷ�ϱ�����Ƿ��Ѿ����У�"};
                JOptionPane.showMessageDialog(null, infos);// �����������ݿ�ʧ�ܵ���ʾ

                System.exit(0);// �ر�ϵͳ

                e.printStackTrace();
            }
        }
        return conn;
    }

    protected static boolean closeConnection() { // �ر����ݿ����ӵķ���

        boolean isClosed = true; // Ĭ�Ϲرճɹ�

        conn = (Connection) threadLocal.get(); // ���߳��л�����ݿ�����

        threadLocal.set(null); // ����߳��е����ݿ�����

        if (conn != null) { // ���ݿ����ӿ���

            try {
                conn.close(); // �ر����ݿ�����

            } catch (SQLException e) {
                isClosed = false; // �ر�ʧ��

                e.printStackTrace();
            }
        }
        return isClosed;
    }
}
