/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwq.map.mwing;

import com.mwq.map.tool.InstancePool;
import com.mwq.map.tool.MapProcessor;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

/**
 *
 * @author Administrator
 */
public class SmallMapLabel extends JLabel {

    private MapProcessor mapProcessor;
    private float xScale;// 鹰眼漫游图的水平缩小比例
    private float yScale;// 鹰眼漫游图的垂直缩小比例

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mapProcessor == null) {
            mapProcessor = InstancePool.getMapProcessor();
            if (mapProcessor != null) {
                refreshScale();// 计算鹰眼漫游图的缩小比例

                drawRect(g);// 绘制矩形�?
            }
        } else {
            drawRect(g);// 绘制矩形�?
        }
    }

    public void refreshScale() {
        xScale = 200f / mapProcessor.getMap().getWidth();// 计算鹰眼漫游图的水平缩小比例

        yScale = 200f / mapProcessor.getMap().getHeight();// 计算鹰眼漫游图的垂直缩小比例

    }

    private void drawRect(Graphics g) {
        int w = (int) (xScale * mapProcessor.getCutMapWidth());// 定义矩形宽度

        int h = (int) (yScale * mapProcessor.getCutMapHeight());// 定义矩形高度

        int x = (int) (xScale * mapProcessor.getShowCenterX()) - w / 2;// 定义水平轴的起始绘制坐标

        int y = (int) (yScale * mapProcessor.getShowCenterY()) - h / 2;// 定义垂直轴的起始绘制坐标
        // 验证水平坐标

        if (x < 0) {
            x = 0;
        } else {
            if (x + w == 200) {
                x -= 1;
            }
        }
        // 验证垂直坐标
        if (y < 0) {
            y = 0;
        } else {
            if (y + h == 200) {
                y -= 1;
            }
        }
        g.setColor(Color.RED);// 设置笔触颜色

        g.drawRect(x, y, w, h);// 绘制矩形�?
    }
}
