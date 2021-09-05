package com.company;

import com.company.ui.MyGUIForm;

public class Start {

    public static void main(String[] args) {
	    MyGUIForm  myGUIForm = new MyGUIForm();
        myGUIForm.pack();
        myGUIForm.setSize(470, 300);
        myGUIForm.setLocationRelativeTo(null);
	    myGUIForm.setVisible(true);
    }
}
