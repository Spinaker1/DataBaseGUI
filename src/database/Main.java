/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author User
 */
public class Main
{
    public static void main(String[] argv)
   {
       DBMananger javaDB = new DBMananger();
       GUI gui = new GUI(javaDB);
       
       java.awt.EventQueue.invokeLater(new Runnable() 
       {
            public void run() 
            {
                gui.setVisible(true);
            }
        });
    }
}

