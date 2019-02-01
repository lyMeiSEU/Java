/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.map.tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class MapProcessor {

    private BufferedImage map;// ��ͼ����
    private int viewportWidth;// ��ͼ��ʾ���Ŀ��
    private int viewportHeight;// ��ͼ��ʾ���ĸ߶�
    private int showCenterX;// ��ͼ�Ӿ����ĵ�ˮƽ����
    private int showCenterY;// ��ͼ�Ӿ����ĵĴ�ֱ����
    private int cutMapWidth;// ��ȡ��ͼ�Ŀ��
    private int cutMapHeight;// ��ȡ��ͼ�ĸ߶�
    private float scale;// ���ű���

    public MapProcessor(String mapPath, int viewportWidth, int viewportHeight, int scale) {
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        replaceMap(mapPath);
        dealWithScale(scale);
        dealWithCutMapSize();
    }

    public void replaceMap(String mapPath) {
        try {
            map = ImageIO.read(MapProcessor.class.getResource(mapPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.showCenterX = map.getWidth() / 2;
        this.showCenterY = map.getHeight() / 2;
    }

    public Image zoom(int width, int height) {
        return map.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    private ImageIcon cut() {
    	System.out.println("��ͼ�Ӿ�����ˮƽ���꣺"+showCenterX);
    	System.out.println("��ͼ�Ӿ����Ĵ�ֱ���꣺"+showCenterY);
    	System.out.println(showCenterX - cutMapWidth / 2);
    	System.out.println(showCenterY - cutMapHeight / 2);
    	System.out.println(cutMapWidth);
    	System.out.println(cutMapHeight);
        BufferedImage subimage = map.getSubimage(
                showCenterX - cutMapWidth / 2, showCenterY - cutMapHeight / 2,// ��ʼ��ȡ�������
                cutMapWidth, cutMapHeight);// ��ȡ��ͼ�Ĵ�С

        return new ImageIcon(subimage.getScaledInstance(
                viewportWidth, viewportHeight, BufferedImage.SCALE_DEFAULT));// ���ص�ǰ��ʾ�ĵ�ͼ

    }

    public ImageIcon adjustWindow(int viewportWidth, int viewportHeight) {
        this.viewportWidth = viewportWidth;// �����ӿڿ��

        this.viewportHeight = viewportHeight;// �����ӿڸ߶�

        dealWithCutMapSize();// ������Ҫ��ȡ��ͼ�Ĵ�С

        validateShowCenterX();// ��֤��ͼ�Ӿ����ĵ�ˮƽ����

        validateShowCenterY();// ��֤��ͼ�Ӿ����ĵĴ�ֱ����

        return this.cut();// ���ص�ǰ��ʾ�ĵ�ͼ

    }

    public ImageIcon adjustScale(int scale) {
        float forestallScale = this.scale;// ����ǰ�ı���

        dealWithScale(scale);// �������

        dealWithCutMapSize();// ������Ҫ��ȡ��ͼ�Ĵ�С

        if (this.scale < forestallScale) {// ������С����ͼ���Ӿ����Ŀ��ܱ仯

            validateShowCenterX();// ��֤��ͼ�Ӿ����ĵ�ˮƽ����

            validateShowCenterY();// ��֤��ͼ�Ӿ����ĵĴ�ֱ����

        }
        return this.cut();// ���ص�ǰ��ʾ�ĵ�ͼ

    }

    public ImageIcon moveOfHorizontal(int distance) {
        if (scale == 0) {// ������

            this.showCenterX += distance;
        } else {
            if (scale > 0) {// �Ŵ�

                this.showCenterX += distance / scale;
            } else {// ��С

                this.showCenterX += distance * -scale;
            }
        }
        validateShowCenterX();// ��֤��ͼ�Ӿ����ĵ�ˮƽ����

        return this.cut();// ���ص�ǰ��ʾ�ĵ�ͼ

    }

    public ImageIcon moveOfVertical(int distance) {
        if (scale == 0) {// ������

            this.showCenterY += distance;
        } else {
            if (scale > 0) {// �Ŵ�

                this.showCenterY += distance / scale;
            } else {// ��С

                this.showCenterY += distance * -scale;
            }
        }
        validateShowCenterY();// ��֤��ͼ�Ӿ����ĵĴ�ֱ����

        return this.cut();// ���ص�ǰ��ʾ�ĵ�ͼ

    }

    public void revert() {
        this.showCenterX = map.getWidth() / 2;// ���õ�ͼ�Ӿ����ĵ�ˮƽ����

        this.showCenterY = map.getHeight() / 2;// ���õ�ͼ�Ӿ����ĵĴ�ֱ����

    }

    public void adjustShowCenter(int x, int y) {
        this.showCenterX = x;
        this.showCenterY = y;
        validateShowCenterX();
        validateShowCenterY();
    }

    private void dealWithScale(int scale) {
        if (scale == 0) {// ������

            this.scale = 0;
        } else {
            this.scale = scale / 100f;
            if (scale > 0) {// �Ŵ�

                this.scale += 1;
            } else {// ��С

                this.scale -= 1;
            }
        }
    }

    private void validateShowCenterX() {
        int w = cutMapWidth / 2;
        if (cutMapWidth % 2 != 0) {
            w += 1;
        }
        if (showCenterX < w) {// �ӵ�ͼ���Ե��ʼ��ȡ

            showCenterX = w;
        } else {
            if (showCenterX > map.getWidth() - w) {// ��ȡ����ͼ�ұ�Ե

                showCenterX = map.getWidth() - w;
            }
        }
    }

    private void validateShowCenterY() {
        int h = cutMapHeight / 2;
        if (cutMapHeight % 2 != 0) {
            h += 1;
        }
        if (showCenterY < h) {// �ӵ�ͼ�ϱ�Ե��ʼ��ȡ

            showCenterY = h;
        } else {
            if (showCenterY > map.getHeight() - h) {// ��ȡ����ͼ�±�Ե

                showCenterY = map.getHeight() - h;
            }
        }
    }

    private void dealWithCutMapSize() {
        if (scale == 0) {// ������

            cutMapWidth = viewportWidth;
            cutMapHeight = viewportHeight;
        } else {
            if (scale > 0) {// �Ŵ�

                cutMapWidth = (int) (viewportWidth / scale);
                cutMapHeight = (int) (viewportHeight / scale);
            } else {// ��С

                cutMapWidth = (int) (viewportWidth * -scale);
                cutMapHeight = (int) (viewportHeight * -scale);
            }
        }
    }

    public BufferedImage getMap() {
        return map;
    }

    public int getShowCenterX() {
        return showCenterX;
    }

    public int getShowCenterY() {
        return showCenterY;
    }

    public int getCutMapWidth() {
        return cutMapWidth;
    }

    public int getCutMapHeight() {
        return cutMapHeight;
    }

    public float getScale() {
        return scale;
    }

    public int parseScale(double scale) {
        if (scale == 0) {
            return 0;
        } else {
            if (scale > 0) {
                return (int) ((scale - 1) * 100);
            } else {
                return (int) ((scale + 1) * 100);
            }
        }
    }
    //
    private int rightClickX;
    private int rightClickY;
    private int rightClickToMapX;
    private int rightClickToMapY;

    public void rightClick(int x, int y) {
        rightClickX = rightClickToMapX = x;
        rightClickY = rightClickToMapY = y;
        if (scale != 0) {
            if (scale > 0) {
                rightClickToMapX = (int) (rightClickToMapX / scale);
                rightClickToMapY = (int) (rightClickToMapY / scale);
            } else {
                rightClickToMapX = (int) (rightClickToMapX * -scale);
                rightClickToMapY = (int) (rightClickToMapY * -scale);
            }
        }
        rightClickToMapX += showCenterX - cutMapWidth / 2;
        rightClickToMapY += showCenterY - cutMapHeight / 2;
    }

    public int getRightClickX() {
        return rightClickX;
    }

    public int getRightClickY() {
        return rightClickY;
    }

    public int getRightClickToMapX() {
        return rightClickToMapX;
    }

    public int getRightClickToMapY() {
        return rightClickToMapY;
    }
}
