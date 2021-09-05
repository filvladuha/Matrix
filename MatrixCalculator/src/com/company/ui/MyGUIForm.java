package com.company.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyGUIForm extends JFrame
{
    private JPanel rootPanel;
    private JButton plusButton;
    private JButton minusButton;
    private JButton multiButton;
    private JButton cleanButton;
    private JTextField A21;
    private JTextField A11;
    private JTextField A12;
    private JTextField A22;
    private JTextField B11;
    private JTextField B12;
    private JTextField B21;
    private JTextField B22;
    private JTextField C11;
    private JTextField C12;
    private JTextField C21;
    private JTextField C22;
    private JComboBox sizeCombo;
    private JButton save;
    private JTextField A04;
    private JTextField A05;
    private JTextField A01;
    private JTextField A02;
    private JTextField A03;
    private JTextField B01;
    private JTextField B02;
    private JTextField B03;
    private JTextField B04;
    private JTextField B05;
    private JTextField C01;
    private JTextField C02;
    private JTextField C03;
    private JTextField C04;
    private JTextField C05;
    private JTextField A401;
    private JTextField A402;
    private JTextField A403;
    private JTextField A404;
    private JTextField A405;
    private JTextField A406;
    private JTextField A407;
    private JTextField B401;
    private JTextField B402;
    private JTextField B403;
    private JTextField B404;
    private JTextField B405;
    private JTextField B406;
    private JTextField B407;
    private JTextField C401;
    private JTextField C402;
    private JTextField C403;
    private JTextField C404;
    private JTextField C405;
    private JTextField C406;
    private JTextField C407;
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] matrixC;
    private JTextField[] nameA;
    private JTextField[] nameB;
    private JTextField[] nameC;
    private int size;

    public MyGUIForm() {
        // нужно сделать ограничение (макс длинна 3 в мА,мБ мкас длинна 6 (или нисего не менять) в мС т.к. 999*999 = 998 001) по вводу чисел в ячейки
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Калькулятор");
        setResizable(false);
        add(rootPanel);
        setDefaultSize();
        makeMatrixVisible(false,0,36); //делает все JTextField для матриц 3Х3 и 4х4 невидимыми

        sizeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                size = sizeCombo.getSelectedIndex() + 2;
                matrixC = new int[size][size];
                matrixB = new int[size][size];
                matrixA = new int[size][size];

                if (size == 2){
                    nameA = new JTextField[] {A11, A12, A21, A22}; // матрицаА 2Х2
                    nameB = new JTextField[]{B11, B12, B21, B22};
                    nameC = new JTextField[]{C11, C12, C21, C22};

                    cleanMaster(); /* чтобы при переходе от размера к размеру данные стирались, даже если пользователь
                                      решит не нажимать на кнопку стереть (при переходе стирается все, даже если нет
                                      ответа или если заполнена токлько одна матрца или даже одна ячейка) */

                    makeMatrixVisible(false, 0, 36); // делает матрицу 3х3 невидимой
                    pack();
                    setSize(470, 300);
                }

                if(size == 3){
                    nameA = new JTextField[] {A01,A02,A03, A11,A04,A12, A21,A05,A22}; // матрицаА 3Х3
                    nameB = new JTextField[] {B01,B02,B03, B11,B04,B12, B21,B05,B22};
                    nameC = new JTextField[] {C01,C02,C03, C11,C04,C12, C21,C05,C22};
                    cleanMaster();
                    makeMatrixVisible(true, 0, 15); // делает матрицу 3х3 видимой (т.к. true)
                    makeMatrixVisible(false, 15, 36); // делает матрицу 4х4 невидимой
                    pack();
                    setSize(500, 350);
                }
                if(size == 4){
                   nameA = new JTextField[]{A401,A402,A403,A404, A01,A02,A405,A03, A11,A04,A406,A12, A21,A05,A407,A22};
                   nameB = new JTextField[]{B401,B402,B403,B404, B01,B02,B405,B03, B11,B04,B406,B12, B21,B05,B407,B22};
                   nameC = new JTextField[]{C401,C402,C403,C404, C01,C02,C405,C03, C11,C04,C406,C12, C21,C05,C407,C22};
                    cleanMaster();
                    makeMatrixVisible(true, 0, 36); // делает матрицу 4х4 видимой
                    pack();
                    setSize(520, 400);
                }

            }
        });
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createMatrixA(matrixA);
                    createMatrixB(matrixB);
                    for (int i = 0; i < matrixA.length; i++) {
                        for (int j = 0; j < matrixA[i].length; j++) {
                            matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
                        }
                    }
                    setMatrixC(matrixC);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка. Введите в матрицу числа.");
                }
            }
        });
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createMatrixA(matrixA);
                    createMatrixB(matrixB);
                    for (int i = 0; i < matrixA.length; i++) {
                        for (int j = 0; j < matrixA[i].length; j++) {
                            matrixC[i][j] = matrixA[i][j] - matrixB[i][j];
                        }
                    }
                    setMatrixC(matrixC);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка. Введите в матрицу числа.");
                }
            }
        });
        multiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createMatrixA(matrixA);
                    createMatrixB(matrixB);
                    int m = matrixA.length;
                    int n = matrixB[0].length;
                    int o = matrixB.length;
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < n; j++) {
                            for (int k = 0; k < o; k++) {
                                matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                            }
                        }
                    }
                    setMatrixC(matrixC);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка. Введите в матрицу числа.");
                }
            }
        });
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanMaster();
                save.setEnabled(false);
            }
        });
        save.addActionListener(new ActionListener() { // отображать произведенную операцию в  файле (need to do)
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("test.txt");

                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
                    writer.println();
                    writer.println("-------");
                    writer.println("МатрицаА");
//                    writer.print("| ");
//                    for (int i = 0; i < size * 2; i++) {
//                        String tmp = nameA[i].getText();
//                        if(i == size )
//                        {
//                            writer.println("| ");
//                            writer.print("| ");
//                        }
//                        writer.print(tmp + " ");
//                    }
//                    writer.print("|");
                    for(int i = 0; i < matrixA.length; i++){
                        writer.print("|");
                        for(int j = 0; j < matrixA.length; j++){
                            writer.format("%4d", matrixA[i][j]);
                        }
                        writer.print("|");
                        writer.println();
                    }

                    writer.println();
                    writer.println("МатрицаБ");
                    for(int i = 0; i < matrixB.length; i++){
                        writer.print("|");
                        for(int j = 0; j < matrixB.length; j++){
                            writer.format("%4d", matrixB[i][j]);
                        }
                        writer.print("|");
                        writer.println();
                    }

                    writer.println();
                    writer.println("МатрицаС (результат)");
                    for(int i = 0; i < matrixC.length; i++){
                        writer.print("|");
                        for(int j = 0; j < matrixC.length; j++){
                            writer.format("%8d", matrixC[i][j]);
                        }
                        writer.print("|");
                        writer.println();
                    }

                    writer.flush();
                    writer.close();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Данные успешно сохранены");
                save.setEnabled(false); //чтобы нельзя было сохраняять одно и тоже много раз
            }
        });

    }

    private void createMatrixA(int[][] matrixA) {
        int a = 0;
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA.length; j++) {
                matrixA[i][j] = Integer.parseInt(nameA[a++].getText());
            }
        }
    }

    private void createMatrixB (int[][] matrixB){
        int b = 0;
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB.length; j++) {
                matrixB[i][j] = Integer.parseInt(nameB[b++].getText());
            }
        }
    }

    private void setMatrixC (int[][] matrixC){
        int c = 0;
        for (int i = 0; i < matrixC.length; i++) {
            for (int j = 0; j < matrixC.length; j++) {
                nameC[c++].setText(String.valueOf(matrixC[i][j]));
            }
        }
        save.setEnabled(true);
    }

    private void setDefaultSize(){ // устанавливает начальный разме
        size = 2;
        matrixC = new int[size][size];
        matrixB = new int[size][size];
        matrixA = new int[size][size];
        nameA = new JTextField[]{A11, A12, A21, A22}; // ячейки для матрицы 2Х2
        nameB = new JTextField[]{B11, B12, B21, B22};
        nameC = new JTextField[]{C11, C12, C21, C22};
    }

    private void makeMatrixVisible(boolean tof, int fromAmount, int toAmount){ // T/F делает видимыми или нет // два инта указывают С какого и ДО какого индекса нужно пребирать массив
        JTextField[] textABC = new JTextField[]{A01, A02, A03, A04, A05, B01, B02, B03, B04, B05, C01, C02, C03, C04, C05,
                A401, A402, A403, A404, A405, A406, A407, B401, B402, B403, B404, B405, B406, B407, C401, C402, C403, C404, C405, C406, C407};
        //15 (0 - 15) // 21 (15 - 36)  => //36 fromAmount

        if (!tof) {
            for (int i = fromAmount ; i < toAmount; i++) {
                textABC[i].setVisible(false);
            }
        }
        if(tof){
            for (int i = fromAmount ; i < toAmount; i++) {
                textABC[i].setVisible(true);
            }
        }
    }

    private void cleanMaster(){  //перебирает массив типа JTextField,стирая с него все данные,тем самым отчищая матрицу от введеннных данных
        for(JTextField jTextFieldA : nameA){
            jTextFieldA.setText("");
        }
        for (JTextField jTextFieldB : nameB) {
            jTextFieldB.setText("");
        }
        for (JTextField jTextFieldB : nameC) {
            jTextFieldB.setText("");
        }
    }
}