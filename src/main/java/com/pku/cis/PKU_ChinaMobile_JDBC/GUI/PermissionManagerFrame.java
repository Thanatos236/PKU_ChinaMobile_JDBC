package com.pku.cis.PKU_ChinaMobile_JDBC.GUI;
import com.pku.cis.PKU_ChinaMobile_JDBC.Server.PermissionManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.EmptyBorder;


public class PermissionManagerFrame extends JFrame {

    public static String userInfo[][];
    public static String head[] = {"用户","权限"};
    public static PermissionManager pm;
    public static int userCount;
    public static JButton glbbutton;
    public static JButton glbbutton_1;
    public static JButton glbbutton_2;
    public static JTable glbtable;
    public static JScrollPane panel;
    public static JSplitPane splitPane;

    static final int FHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    static final int FWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PermissionManagerFrame frame = new PermissionManagerFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void fetchData()
    {
        userCount = 3;
        userInfo = new String[userCount][];
        userInfo[0] = new String[2];
        userInfo[0][0] = "mrpen";
        userInfo[0][1] = "管理员";
        userInfo[1] = new String[2];
        userInfo[1][0] = "mrpen3";
        userInfo[1][1] = "管理员";
        userInfo[2] = new String[2];
        userInfo[2][0] = "mrpen2";
        userInfo[2][1] = "普通用户";

    }
    public JTable updateTable()
    {
        fetchData();
        userInfo[2][1] = "普通用户";
        JTable t =  new JTable(userInfo,head);
        t.setRowSelectionInterval(0, 0);
        t.setBackground(Color.WHITE);
        t.setBorder(null);
        t.setRowHeight(30);
        t.setEnabled(true);
        return t;
    }
    /**
     * Create the frame.
     */
    public PermissionManagerFrame() {
        pm = new PermissionManager();
        setTitle("透明网管系统——权限管理");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 484, 300);
        glbtable = updateTable();
        panel = new JScrollPane(glbtable);
        panel.getViewport().setBackground(Color.WHITE);

        JPanel panel_1 = new JPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, panel, panel_1);
        panel_1.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(BorderFactory.createTitledBorder("Operation"));
        panel_2.setBounds(9, 6, 146, 138);
        panel_1.add(panel_2);
        panel_2.setLayout(null);

        /*添加用户按钮*/
        glbbutton = new JButton("添加");
        glbbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame();
                glbbutton.setEnabled(false);
                glbbutton_1.setEnabled(false);
                glbbutton_2.setEnabled(false);
                glbtable.setEnabled(false);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        glbbutton.setEnabled(true);
                        glbbutton_1.setEnabled(true);
                        glbbutton_2.setEnabled(true);
                        glbtable.updateUI(); //更新表
                        glbtable.setEnabled(true);
                        super.windowClosing(e);
                    }
                });
                f.setResizable(false);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setBounds(100, 100, 329, 234);
                f.setLocation(FWidth / 3, FHeight / 3);
                f.setTitle("添加用户");

                JPanel contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                contentPane.setLayout(null);
                f.setContentPane(contentPane);
                JLabel label = new JLabel("用户名：");
                label.setBounds(22, 28, 90, 16);
                contentPane.add(label);
                JTextField textField = new JTextField();
                textField.setBounds(100, 22, 194, 28);
                contentPane.add(textField);
                textField.setColumns(10);
                JLabel label_1 = new JLabel("密码：");
                label_1.setBounds(22, 62, 90, 16);
                contentPane.add(label_1);
                JPasswordField textField_1 = new JPasswordField();
                textField.setColumns(10);
                textField_1.setBounds(100, 53, 194, 28);
                contentPane.add(textField_1);
                JLabel label_2 = new JLabel("确认密码：");
                label_2.setBounds(22, 90, 90, 16);
                contentPane.add(label_2);
                JPasswordField textField_2 = new JPasswordField();
                textField_2.setColumns(10);
                textField_2.setBounds(100, 84, 194, 28);
                contentPane.add(textField_2);
                JLabel label_3 = new JLabel("权限：");
                label_3.setBounds(22, 122, 77, 16);
                contentPane.add(label_3);
                JRadioButton radioButton = new JRadioButton("普通用户");
                radioButton.setBounds(87, 118, 103, 23);
                contentPane.add(radioButton);
                radioButton.setSelected(true);
                JRadioButton radioButton_1 = new JRadioButton("管理员");
                radioButton_1.setBounds(188, 118, 117, 23);
                contentPane.add(radioButton_1);
                ButtonGroup group = new ButtonGroup();// 创建单选按钮组
                group.add(radioButton);// 将radioButton增加到单选按钮组中
                group.add(radioButton_1);


                JPanel panel = new JPanel();
                panel.setBounds(16, 16, 297, 138);
                panel.setBorder(BorderFactory.createEtchedBorder());
                contentPane.add(panel);

                JButton button_1 = new JButton("取消");
                button_1.setBounds(188, 164, 117, 29);
                button_1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        glbbutton.setEnabled(true);
                        glbbutton_1.setEnabled(true);
                        glbbutton_2.setEnabled(true);
                        glbtable.updateUI(); //更新表
                        glbtable.setEnabled(true);
                        f.dispose();
                    }
                });
                contentPane.add(button_1);

                JButton button = new JButton("确认");
                button.setBounds(26, 164, 117, 29);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String psw1 = new String(textField_1.getPassword());
                        String psw2 = new String(textField_2.getPassword());
                        System.out.println(psw1);
                        if(!psw1.equals(psw2)) {
                            JOptionPane.showMessageDialog(null, "两次输入密码不一致!", "添加失败", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            String userName = textField.getText();
                            int permission;
                            if(radioButton.isSelected())
                                permission = 1;
                            else
                                permission = 0;
                            try {
                                if(pm.insert(userName, permission, psw1))
                                {
                                    JOptionPane.showMessageDialog(null,"添加成功","添加成功", JOptionPane.ERROR_MESSAGE);
                                    glbbutton.setEnabled(true);
                                    glbbutton_1.setEnabled(true);
                                    glbbutton_2.setEnabled(true);
                                    glbtable.updateUI(); //更新表
                                    glbtable.setEnabled(true);
                                    f.dispose();
                                }
                                else
                                    JOptionPane.showMessageDialog(null,"添加失败","添加失败", JOptionPane.ERROR_MESSAGE);
                            } catch (Exception e1) {
                                JOptionPane.showMessageDialog(null,e1.getMessage(),"添加失败", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });
                contentPane.add(button);
                f.setVisible(true);
            }
        });
        glbbutton.setBounds(6, 16, 135, 30);
        panel_2.add(glbbutton);


        /*编辑用户按钮*/
        glbbutton_1 = new JButton("编辑");
        glbbutton_1.setBounds(6, 58, 135, 30);
        glbbutton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame();
                glbbutton.setEnabled(false);
                glbbutton_1.setEnabled(false);
                glbbutton_2.setEnabled(false);
                glbtable.setEnabled(false);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        glbbutton.setEnabled(true);
                        glbbutton_1.setEnabled(true);
                        glbbutton_2.setEnabled(true);
                        glbtable.updateUI(); //更新表
                        glbtable.setEnabled(true);
                        super.windowClosing(e);
                    }
                });
                f.setResizable(false);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setBounds(100, 100, 327, 166);
                f.setLocation(FWidth / 3, FHeight / 3);
                f.setTitle("编辑用户");
                f.setVisible(true);
                String userName = userInfo[glbtable.getSelectedRowCount()][0];

                JPanel contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                f.setContentPane(contentPane);
                contentPane.setLayout(null);

                JPanel panel = new JPanel();
                panel.setBounds(16, 14, 285, 73);
                panel.setBorder(BorderFactory.createEtchedBorder());
                contentPane.add(panel);
                panel.setLayout(null);

                JLabel label = new JLabel("用户名：");
                label.setBounds(17, 11, 120, 15);
                panel.add(label);

                JTextField textField = new JTextField();
                textField.setBounds(70, 8, 190, 21);
                textField.setText(userName);
                textField.setEditable(false);
                panel.add(textField);
                textField.setColumns(10);
                JLabel label_3 = new JLabel("权限：");
                label_3.setBounds(17, 39, 101, 15);
                panel.add(label_3);
                JRadioButton radioButton_1 = new JRadioButton("管理员");
                radioButton_1.setBounds(176, 35, 101, 23);
                panel.add(radioButton_1);
                JRadioButton radioButton = new JRadioButton("普通用户");
                radioButton.setSelected(true);
                radioButton.setBounds(70, 35, 101, 23);
                panel.add(radioButton);

                ButtonGroup group = new ButtonGroup();// 创建单选按钮组
                group.add(radioButton);// 将radioButton增加到单选按钮组中
                group.add(radioButton_1);

                JButton button_1 = new JButton("取消");
                button_1.setBounds(170, 97, 117, 29);
                button_1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        glbbutton.setEnabled(true);
                        glbbutton_1.setEnabled(true);
                        glbbutton_2.setEnabled(true);
                        glbtable.updateUI(); //更新表
                        glbtable.setEnabled(true);
                        f.dispose();
                    }
                });
                contentPane.add(button_1);

                JButton button = new JButton("确认");
                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int permission;
                        if(radioButton.isSelected())
                            permission = 1;
                        else
                            permission = 0;
                        try {
                            if(pm.editPermission(userName, permission))
                            {
                                JOptionPane.showMessageDialog(null,"更改成功","更改成功", JOptionPane.PLAIN_MESSAGE);
                                glbbutton.setEnabled(true);
                                glbbutton_1.setEnabled(true);
                                glbbutton_2.setEnabled(true);
                                glbtable.updateUI(); //更新表
                                glbtable.setEnabled(true);
                                f.dispose();
                            }
                            else
                                JOptionPane.showMessageDialog(null,"添加失败","添加失败", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null,e1.getMessage(),"添加失败", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                button.setBounds(26, 97, 117, 29);
                contentPane.add(button);
            }
        });
        panel_2.add(glbbutton_1);

        /*删除操作*/
        glbbutton_2 = new JButton("删除");
        glbbutton_2.setBounds(6, 98, 135, 30);
        glbbutton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                glbbutton.setEnabled(false);
                glbbutton_1.setEnabled(false);
                glbbutton_2.setEnabled(false);
                glbtable.setEnabled(false);
                String userName = userInfo[glbtable.getSelectedRowCount()][0];
                int result = JOptionPane.showConfirmDialog(null, "确定删除该用户？", "提示", JOptionPane.YES_NO_OPTION);
                if(result == 0)
                {
                    try {
                        if(pm.remove(userName))
                            JOptionPane.showMessageDialog(null,"删除成功","提示", JOptionPane.PLAIN_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(null,"删除失败","删除失败", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null,e1.getMessage(),"删除失败", JOptionPane.ERROR_MESSAGE);
                    }
                }
                glbbutton.setEnabled(true);
                glbbutton_1.setEnabled(true);
                glbbutton_2.setEnabled(true);
                glbtable.updateUI(); //更新表
                glbtable.setEnabled(true);
            }
        });
        panel_2.add(glbbutton_2);
        splitPane.setDividerLocation(300);
        splitPane.setResizeWeight(1);
        splitPane.setDividerSize(0);
        getContentPane().add(splitPane);
    }
}