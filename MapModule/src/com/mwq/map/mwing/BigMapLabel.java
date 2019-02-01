/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.map.mwing;

import com.mwq.map.dao.Dao;
import com.mwq.map.tool.InstancePool;
import com.mwq.map.tool.MapProcessor;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author Administrator
 */
public class BigMapLabel extends JLabel {

    private static final Dao dao = Dao.getInstance();
    private static final Vector<Integer> ids = new Vector();// ���������
    private static final Vector<Integer> xs = new Vector();// X�����꼯
    private static final Vector<Integer> ys = new Vector();// Y�����꼯
    private static final Vector<String> texts = new Vector();// ����ı���
    private static final Vector<Boolean> shows = new Vector();// �Ƿ���ʾ��
    private static MapProcessor mapProcessor;// ��ͼ����������
    private static int operateIndex = -1;// ������ǵ�����
    private static int stressId = -1;// ���ر�ǵ�����

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.ORANGE);// ���ñʴ���ɫ

        for (int i = 0; i < texts.size(); i++) {
            int x = xs.get(i);// ��ǵ��X������

            int y = ys.get(i);// ��ǵ��Y������

            if (ids.get(i) == stressId) {// Ϊ���ر�ǵ�

                g.setColor(Color.RED);// �޸ıʴ���ɫ

                g.fillOval(x - 5, y - 5, 10, 10);// ���Ʊ�ǵ�

                if (shows.get(i)) {// �����ʾ����ı�

                    g.drawString(texts.get(i), x + 8, y + 5);// ���Ʊ���ı�

                }
                g.setColor(Color.ORANGE);// ��ԭ�ʴ���ɫ

            } else {// Ϊ��ͨ��ǵ�

                g.fillOval(x - 5, y - 5, 10, 10);// ���Ʊ�ǵ�

                if (shows.get(i)) {// �����ʾ����ı�

                    g.drawString(texts.get(i), x + 8, y + 5);// ���Ʊ���ı�

                }
            }
        }
    }

    public static void addSign(int id, int x, int y, String sign, boolean show) {
        ids.add(id);
        xs.add(x);
        ys.add(y);
        texts.add(sign);
        shows.add(show);
    }

    public static void updateSign(int x, int y, String sign, boolean show) {
        xs.set(operateIndex, x);
        ys.set(operateIndex, y);
        texts.set(operateIndex, sign);
        shows.set(operateIndex, show);
    }

    public void removeSign() {
        ids.remove(operateIndex);
        xs.remove(operateIndex);
        ys.remove(operateIndex);
        texts.remove(operateIndex);
        shows.remove(operateIndex);
    }

    public void setStress(int id) {
        stressId = id;// �������ر�ǵ�����

    }

public boolean isEnteredSign(int x, int y) {
    int xDistance, yDistance;// ���ǵ�ľ���

    for (int i = 0; i < texts.size(); i++) {
        xDistance = Math.abs(x - xs.get(i));// ����ˮƽ����

        yDistance = Math.abs(y - ys.get(i));// ���㴹ֱ����

        if (xDistance < 5 && yDistance < 5) {// ����ڸñ�ǵ�֮��

            operateIndex = i;// ����ñ�ǵ������

            mapProcessor.rightClick(xs.get(i), ys.get(i));// ����ñ�ǵ������

            return true;// ����ڸñ�ǵ�֮��

        }
    }
    mapProcessor.rightClick(x, y);// ����ñ�ǵ������

    return false;// ���δ���κα�ǵ�֮��

}

    @Override
    public void setIcon(Icon icon) {
        if (mapProcessor == null) {// ��δ��õ�ͼ����������

            if (InstancePool.getMapProcessor() != null) {// �����ͼ�����������Ѿ�����

                mapProcessor = InstancePool.getMapProcessor();// ��õ�ͼ����������

                refreshSigns();// ˢ�±��

            }
        } else {// �Ѿ���õ�ͼ����������

            refreshSigns();// ˢ�±��

        }
        super.setIcon(icon);
    }

    private void refreshSigns() {
        ids.clear();
        xs.clear();
        ys.clear();
        texts.clear();
        shows.clear();

        // ��ѯ���ϻ��������ı��
        Vector signs = dao.selectShowSigns(mapProcessor.getShowCenterX(), mapProcessor.getShowCenterY(),
                mapProcessor.getCutMapWidth(), mapProcessor.getCutMapHeight(), mapProcessor.getScale());

        // �������ԭ�������
        int originX = mapProcessor.getShowCenterX() - mapProcessor.getCutMapWidth() / 2;
        int originY = mapProcessor.getShowCenterY() - mapProcessor.getCutMapHeight() / 2;
        float scale = mapProcessor.getScale();
        for (int i = 0; i < signs.size(); i++) {// ������ǵ㼯��

            Vector sign = (Vector) signs.get(i);// ��ñ�ǵ�

            // �����������
            int x = (Integer) sign.get(2) - originX;
            int y = (Integer) sign.get(3) - originY;
            if (scale != 0) {// ����������

                if (scale > 0) {// �Ŵ�

                    x = (int) (x * scale);
                    y = (int) (y * scale);
                } else {// ��С

                    x = (int) (x / -scale);
                    y = (int) (y / -scale);
                }
            }
            addSign((Integer) sign.get(0), x, y, sign.get(4).toString(), ((Integer) sign.get(5) == 1 ? true : false));
        }
    }
}
