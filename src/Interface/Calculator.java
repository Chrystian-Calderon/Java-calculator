package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
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
	 * concatenate
	 */
	private static boolean decimalPoint(String getText, String[] operations) {
		//invert text
		StringBuilder auxText = new StringBuilder(getText);
		String newText = auxText.reverse().toString();
		
		boolean found = false;
		for(char character : newText.toCharArray()) {
			if( Arrays.asList(operations).contains(String.valueOf(character)) ) {
				break;
			} else if( character == '.' ) {
				found = true;
				break;
			}
		}

		return found;
	}
	
	public static void verificar(String text, JTextField result) {
		String textResult = result.getText();
		String newText = "";
		String[] operations = {"/", "x", "+", "-", "."};
		boolean contains = false;
		
		if( text == "." && textResult.length() > 0 ) {
			String lastElement = textResult.substring(textResult.length() - 1);
			if( !(Arrays.asList(operations).contains(lastElement)) ) {
				if( !decimalPoint(textResult, Arrays.copyOfRange(operations, 0, 4)) ) {
					result.setText(textResult + text);
				}
			} else {
				newText = removeOne(result);
				result.setText(newText);
				textResult = result.getText();
				System.out.println(textResult);
				if( !decimalPoint(textResult, Arrays.copyOfRange(operations, 0, 4)) ) {
					result.setText(textResult + text);
				}
			}
		} else if( Arrays.asList(operations).contains(text) && textResult.length() > 0 ) {
			String lastElement = textResult.substring(textResult.length() - 1);
			//try 
			for(String operation : operations) {
				if(lastElement.equals(operation)) {
					contains = true; break;
				}
			}
			
			if(contains) {
				newText = removeOne(result);
				result.setText(newText + text);
			} else {
				result.setText(textResult + text);
			}
		} else {
			result.setText(textResult + text);
		}
	}
	
	/*
	 * math operation
	 */
	private static boolean searchOperators(String[] operations, String text) {
		boolean containsOperator = false;
		for(String operation : operations) {
			if( text.contains(operation) ) {
				containsOperator = true;
				break;
			}
		}
		return containsOperator;
	}
	
	private static int[] searchNumbers(int index, String text, String[] operations) {
		int posMax = index;
		int posMin = index;
		for( int varAux = index + 1; varAux < text.length(); varAux++ ) {
			String character = String.valueOf(text.charAt(varAux));
			if( !(Arrays.asList(operations).contains(character)) ) {
				posMax++;
			} else {
				if( text.charAt(index + 1) == '-' ) {
					posMax++;
				} else {
					break;
				}
			}
		}
		for( int varAux = index - 1; varAux > -1; varAux-- ) {
			String character = String.valueOf(text.charAt(varAux));
			if( !(Arrays.asList(operations).contains(character)) ) {
				posMin--;
			} else {
				break;
			}
		}
		
		int[] position = {posMax, posMin};
		return position;
	}
	
	private static String solve(int indexStart, int indexEnd, int index, String operation, String text) {
		System.out.println(indexStart + " " + indexEnd + " " + index);
		double num1 = Double.parseDouble(text.substring(indexStart, index));
		double num2 = Double.parseDouble(text.substring(index + 1, indexEnd + 1));
		double result = 0;
		String exit = "";
		
		switch(operation) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "x":
			result = num1 * num2;
			break;
		case "/":
			if(num2 != 0) {
				result = num1 / num2;
			}
			break;
		default:
			System.out.println("No existe operador");
			break;
		}
		
		exit = Double.toString(result);
		return exit;
	}
	
	public static void getResult(JTextField result) {
		String text = result.getText();
		String[] operations = {"/", "x", "+", "-"};
		int size = text.length();
		int posMax = 0, posMin = 0;
		
		boolean operators = true;
		
		while(operators) {
			int index = 0;
			while( index < size ) {
				if( text.charAt(0) == '-' ) {
					operators = false;
					break;
				}
				if( searchOperators(Arrays.copyOfRange(operations, 0, 2), text) ) {
					System.out.println("Entra");
					if( text.charAt(index) == '/' || text.charAt(index) == 'x' ) {
						int[] posicion = searchNumbers(index, text, operations);
						posMin = posicion[1];
						posMax = posicion[0];
						String character = String.valueOf(text.charAt(index));
						String operationResult = solve(posMin, posMax, index, character, text);
						System.out.println(operationResult);
						text = text.replace(text.substring(posMin, posMax + 1), operationResult);
						index = size;
					} else {
						index++;
					}
					
				} else {
					System.out.println("opcion2");
					if( searchOperators(Arrays.copyOfRange(operations, 2, 4), text) ) {
						if( text.charAt(index) == '-' || text.charAt(index) == '+' ) {
							int[] posicion = searchNumbers(index, text, operations);
							posMin = posicion[1];
							posMax = posicion[0];
							String character = String.valueOf(text.charAt(index));
							String operationResult = solve(posMin, posMax, index, character, text);
							text = text.replace(text.substring(posMin, posMax + 1), operationResult);
							index = size;
						} else {
							index++;
						}
					} else {
						operators = false;
						index = size;
					}
				}
			}
			System.out.println(text);
		}
		result.setText(text);
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
		btnEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getResult(result);
			}
		});
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
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result.setText("");
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDelete.setBounds(220, 72, 60, 60);
		background.add(btnDelete);
		
		JButton btnDeleteOne = new JButton("<");
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
