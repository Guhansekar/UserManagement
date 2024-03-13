package com.users;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;
import java.sql.DriverManager;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class User {

	private JFrame frmManagement;
	private JTextField textId;
	private JTextField textName;
	private JTextField textAge;
	private JTextField textCity;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User window = new User();
					window.frmManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public User() {
		initialize();
		connector();
		loadData();
	}
	
	PreparedStatement psmt;
	Statement smt;
	ResultSet rs;
	Connection con=null;
	public void connector() {
		
		try {
			
			String url="jdbc:mysql://localhost:3306/user";
			String name="root";
			String passw="guhan";
			con=DriverManager.getConnection(url,name,passw);
			
			
		}
		catch(Exception e) {
			
		}
	}
	
	public void clear() {
		textId.setText("");
		textName.setText("");
		textAge.setText("");
		textCity.setText("");
		textName.requestFocus();
		
	}
	
	public void loadData() {
		try {
			psmt=con.prepareStatement("select * from usertab;");
			rs=psmt.executeQuery();
			table.setModel(DButils.resultsetToTableModel(rs));
		}catch(Exception e) {
			
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManagement = new JFrame();
		frmManagement.getContentPane().setBackground(new Color(255, 228, 225));
		frmManagement.setTitle("management");
		frmManagement.setBounds(100, 100, 856, 536);
		frmManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManagement.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Management System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(22, 23, 188, 28);
		frmManagement.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(22, 63, 308, 243);
		frmManagement.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 30, 59, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("NAME");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(20, 62, 59, 21);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("AGE");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(20, 94, 59, 21);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("CITY");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(20, 126, 59, 21);
		panel.add(lblNewLabel_1_1_1_1);
		
		textId = new JTextField();
		textId.setBounds(89, 32, 188, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(89, 64, 188, 20);
		panel.add(textName);
		
		textAge = new JTextField();
		textAge.setColumns(10);
		textAge.setBounds(89, 96, 188, 20);
		panel.add(textAge);
		
		textCity = new JTextField();
		textCity.setColumns(10);
		textCity.setBounds(89, 128, 188, 20);
		panel.add(textCity);
		
		JButton btnSave = new JButton("save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textId.getText();
				String name=textName.getText();
				String age=textAge.getText();
				String city=textCity.getText();
				
				if(name==null | name.isEmpty() | name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"enter the name");
					textName.requestFocus();
					return;
				}
				if(age==null | age.isEmpty() |age.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"enter the age");
					textAge.requestFocus();
				}
				if(city==null | city.isEmpty() | city.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"enter the city");
					textCity.requestFocus();
				}
				
				if(id.isEmpty()) {
					String qry="insert into usertab (name,age,city) values(?,?,?);";
					try {
						psmt=con.prepareStatement(qry);
						psmt.setString(1,name);
						psmt.setString(2,age);
						psmt.setString(3,city);
						psmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"insert success");
						clear();
						loadData();
						
						
						
					}catch(Exception es) {
					System.out.println(es);	
					}
				}
				
					
				}
				
			
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnSave.setBounds(20, 169, 89, 23);
		panel.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textId.getText();
				String name=textName.getText();
				String age=textAge.getText();
				String city=textCity.getText();
					String qry="update usertab set name=?,age=?,city=? where id=?;";
					try {
						psmt=con.prepareStatement(qry);
						psmt.setString(4,id);
						psmt.setString(1,name);
						psmt.setString(2,age);
						psmt.setString(3,city);
						psmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"update success");
						clear();
						loadData();
						
						
						
					}catch(Exception es) {
					System.out.println(es);	
					}
			
		}
			}
		);
		
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnUpdate.setBounds(113, 169, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!textId.getText().isEmpty()) {
					String id=textId.getText();
					int result=JOptionPane.showConfirmDialog(null,"are you sure delete?","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(result==JOptionPane.YES_OPTION) {
					try {
						psmt=con.prepareStatement("delete from usertab where id=?;");
						psmt.setString(1, id);
						psmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"delete success");
						clear();
						loadData();
						
					}catch(Exception eee) {
						System.out.println(eee);
						
					}}
					
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnDelete.setBounds(209, 169, 89, 23);
		panel.add(btnDelete);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=table.getSelectedRow();
				TableModel model=table.getModel();
				textId.setText(model.getValueAt(row, 0).toString());
				textName.setText(model.getValueAt(row, 1).toString());
				textAge.setText(model.getValueAt(row, 2).toString());
				textCity.setText(model.getValueAt(row, 3).toString());
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setRowHeight(20);
		table.setBounds(378, 63, 452, 301);
		frmManagement.getContentPane().add(table);
	}
}
