package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Calculator extends JFrame {

	private JPanel background;
	private JTextField result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*
	 * eliminar ultimo caracter
	 */
	public static String removeOne(JTextField result) {
		String text = result.getText();
		int size = text.length() - 1;
		String newText = text.substring(0, size);
		return newText;
	}

	/*
	 * concatenar
	 */
	private static boolean searchPoint(String getText, String[] operations) {
		boolean found = false;
		for(int index = getText.length() - 1; index >= 0; index--) {
			String character = String.valueOf(getText.charAt(index));
			if(Arrays.asList(operations).contains(character)) {
				if(character == ".") {
					found = true;
					break;
				}
			}
		}
		System.out.println(getText.charAt(0));
		return found;
	}
	
	public static void verificar(String text, JTextField result) {
		String textResult = result.getText();
		String newText = "";
		String[] operations = {"/", "x", "+", "-", "."};
		boolean contains = false;
		if(Arrays.asList(operations).contains(text)) {
			String lastElement = textResult.substring(textResult.length() - 1);
			//try
			for(String operation : operations) {
				if(lastElement.equals(operation)) {
					contains = true;
					break;
				}
			}

			if(contains) {
				newText = removeOne(result);
				result.setText(newText + text);
			} else {
				result.setText(textResult + text);
			}
			System.out.println(searchPoint(textResult, operations));
		} else {
			result.setText(textResult + text);
		}
	}
	
	
	/*
	 * Eliminar
	 */
	public static void eliminate() {
		
	}
	
	/**
	 * Create the frame.
	 */
	
	public Calculator() {
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 465);
		background = new JPanel();
		background.setBackground(new Color(0, 255, 255));
		background.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(background);
		background.setLayout(null);
		
		result = new JTextField();
		result.setEditable(false);
		result.setHorizontalAlignment(SwingConstants.RIGHT);
		result.setFont(new Font("Tahoma", Font.PLAIN, 25));
		result.setBounds(10, 11, 270, 50);
		background.add(result);
		result.setColumns(10);
		
		JButton btn0 = new JButton("0");
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btn0.getText();
				verificar(textBtn, result);
			}
		});
		btn0.setBounds(10, 356, 60, 60);
		background.add(btn0);
		
		JButton btnPoint = new JButton(".");
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btnPoint.getText();
				verificar(textBtn, result);
			}
		});
		btnPoint.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnPoint.setBounds(80, 356, 60, 60);
		background.add(btnPoint);
		
		JButton btnEqual = new JButton("=");
		btnEqual.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnEqual.setBounds(150, 356, 60, 60);
		background.add(btnEqual);
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btn1.getText();
				verificar(textBtn, result);
			}
		});
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn1.setBounds(10, 285, 60, 60);
		background.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btn2.getText();
				verificar(textBtn, result);
			}
		});
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn2.setBounds(80, 285, 60, 60);
		background.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btn3.getText();
				verificar(textBtn, result);
			}
		});
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn3.setBounds(150, 285, 60, 60);
		background.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btn4.getText();
				verificar(textBtn, result);
			}
		});
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn4.setBounds(10, 214, 60, 60);
		background.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btn5.getText();
				verificar(textBtn, result);
			}
		});
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn5.setBounds(80, 214, 60, 60);
		background.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btn6.getText();
				verificar(textBtn, result);
			}
		});
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn6.setBounds(150, 214, 60, 60);
		background.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btn7.getText();
				verificar(textBtn, result);
			}
		});
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn7.setBounds(10, 143, 60, 60);
		background.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btn8.getText();
				verificar(textBtn, result);
			}
		});
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn8.setBounds(80, 143, 60, 60);
		background.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btn9.getText();
				verificar(textBtn, result);
			}
		});
		btn9.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn9.setBounds(150, 143, 60, 60);
		background.add(btn9);
		
		JButton btnDivision = new JButton("/");
		btnDivision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btnDivision.getText();
				verificar(textBtn, result);
			}
		});
		btnDivision.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDivision.setBounds(220, 143, 60, 60);
		background.add(btnDivision);
		
		JButton btnMultiplication = new JButton("x");
		btnMultiplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btnMultiplication.getText();
				verificar(textBtn, result);
			}
		});
		btnMultiplication.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnMultiplication.setBounds(220, 214, 60, 60);
		background.add(btnMultiplication);
		
		JButton btnAddition = new JButton("+");
		btnAddition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btnAddition.getText();
				verificar(textBtn, result);
			}
		});
		btnAddition.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnAddition.setBounds(220, 356, 60, 60);
		background.add(btnAddition);
		
		JButton btnSubtract = new JButton("-");
		btnSubtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtn = btnSubtract.getText();
				verificar(textBtn, result);
			}
		});
		btnSubtract.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSubtract.setBounds(220, 285, 60, 60);
		background.add(btnSubtract);
		
		JButton btnDelete = new JButton("C");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDelete.setBounds(220, 72, 60, 60);
		background.add(btnDelete);
		
		JButton btnDeleteOne = new JButton("<-");
		btnDeleteOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newText = removeOne(result);
				result.setText(newText);
			}
		});
		btnDeleteOne.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDeleteOne.setBounds(150, 72, 60, 60);
		background.add(btnDeleteOne);
	}
}
