/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;

/**
 *
 * @author Nhật Thắng
 */
public class Menu extends ArrayList<String>{
    private String title;
    
    public Menu(String title) {
        this.title = title;
    }

    public int getNumOp() {
        return this.size();
    }
    
    public void display() {
        System.out.println("========== " + title + " ==========");
        for (String op : this) {
            System.out.println(op);
        }
    }
}
