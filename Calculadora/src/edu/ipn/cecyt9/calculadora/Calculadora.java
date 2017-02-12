
package edu.ipn.cecyt9.calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Math.toRadians;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * 
 * Interfaz para nuestra calculadora basica
 * 
 * @author:  emmanuel 
 * @version:  1.0 
 * @date: 06-09-2015 
 */
public class Calculadora extends JFrame {

	/**
	 * generado
	 */
	private static final long serialVersionUID = 1583724102189855698L;

	/** numero tecleado */
	JTextField pantalla;

	/** guarda el resultado de la operacion anterior o el número tecleado */
	double resultado;
        String cad = "";

        double resultado2;
	/** para guardar la operacion a realizar */
	String operacion;

	/** Los paneles donde colocaremos los botones */
	JPanel panelNumeros, panelOperaciones;

	/** Indica si estamos iniciando o no una operación */
	boolean nuevaOperacion = true;

	/**
	 * Constructor. Crea los botones y componentes de la calculadora
	 */
	public Calculadora() {
		super();
		setSize(400, 400);
		setTitle("Calculadora Simple");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		// Vamos a dibujar sobre el panel
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());

		pantalla = new JTextField("0", 20);
		pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
		pantalla.setFont(new Font("Arial", Font.BOLD, 25));
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(false);
		pantalla.setBackground(Color.BLACK);
		panel.add("North", pantalla);

		panelNumeros = new JPanel();
		panelNumeros.setLayout(new GridLayout(4, 3));
		panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));
                panelNumeros.setBackground(Color.BLACK);

		for (int n = 9; n >= 0; n--) {
			nuevoBotonNumerico("" + n);
		}

		nuevoBotonNumerico(".");

		panel.add("Center", panelNumeros);

		panelOperaciones = new JPanel();
		panelOperaciones.setLayout(new GridLayout(6, 1));
		panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
                panelOperaciones.setBackground(Color.BLACK); 
                
		nuevoBotonOperacion("+");
		nuevoBotonOperacion("-");
		nuevoBotonOperacion("*");
		nuevoBotonOperacion("/");
                nuevoBotonOperacion("sen");
                nuevoBotonOperacion("cos");
                nuevoBotonOperacion("tan");
                nuevoBotonOperacion("^");
                nuevoBotonOperacion("!");
                nuevoBotonOperacion("°");
		nuevoBotonOperacion("=");
		nuevoBotonOperacion("CE");

		panel.add("East", panelOperaciones);

		validate();
	}

	/**
	 * Crea un boton del teclado numérico y enlaza sus eventos con el listener
	 * correspondiente
	 * 
	 * @param digito
	 *            boton a crear
	 */
	private void nuevoBotonNumerico(String digito) {
		JButton btn = new JButton();
		btn.setText(digito);
                btn.setBackground(new java.awt.Color(104,170,15));
                btn.setSize(10,10);
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				numeroPulsado(btn.getText());
			}
		});

		panelNumeros.add(btn);
	}

	/**
	 * Crea un botón de operacion y lo enlaza con sus eventos.
	 * 
	 * @param operacion
	 */
	private void nuevoBotonOperacion(String operacion) {
		JButton btn = new JButton(operacion);
                btn.setBackground(new java.awt.Color(104,170,15));
		btn.setForeground(Color.BLACK);

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				operacionPulsado(btn.getText());
			}
		});

		panelOperaciones.add(btn);
	}

	/**
	 * Gestiona las pulsaciones de teclas numéricas
	 * 
	 * @param digito
	 *            tecla pulsada
	 */
	private void numeroPulsado(String digito) {
		if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(digito);
		} else {
			pantalla.setText(pantalla.getText() + digito);
		}
		nuevaOperacion = false;
	}

	/**
	 * Gestiona el gestiona las pulsaciones de teclas de operación
	 * 
	 * @param tecla
	 */
	private void operacionPulsado(String tecla) {
		if (tecla.equals("=")) {
			calcularResultado();
		} else if (tecla.equals("CE")) {
			resultado = 0;
			pantalla.setText("");
			nuevaOperacion = true;
		} else {
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pantalla.getText());
			}
		}

		nuevaOperacion = true;
	}

	/**
	 * Calcula el resultado y lo muestra por pantalla
	 */
	private void calcularResultado() {
            System.out.println(resultado);
		if (operacion.equals("+")) {
			resultado += new Double(pantalla.getText());
		} else if (operacion.equals("-")) {
			resultado -= new Double(pantalla.getText());
		} else if (operacion.equals("/")) {
			resultado /= new Double(pantalla.getText());
		} else if (operacion.equals("*")) {
			resultado *= new Double(pantalla.getText());
		}else if  (operacion.equals("^")){
                        double tot = 1;
                        for(int a=0; a< new Double(pantalla.getText());a++){
                            tot *= resultado;
                        }
                        resultado = tot; 
		}else if  (operacion.equals("!")){
                    double factorial = resultado;
                    double res = factorial; 
                    while(factorial!=1)
                    {
                        factorial--; 
                        res *= factorial;
                    }
                    resultado=res;
                }else if  (operacion.equals("°")){
                    if(resultado % (int)resultado != 0){
                        int hr = (int) resultado; 
                        int min = 0; 
                        double seg = 0;
                        double min1 = ((resultado - hr)*60)+.01;
                        System.out.println(min1);
                        if(min1 % (int) min1 != 0){
                            min = (int) min1;
                            seg = ((min1 - min)*60)+.01; 
                            System.out.println(min+","+seg);
                        }
                        cad = (Integer.toString(hr)+"°"+Integer.toString(min)+"'"+Integer.toString((int)seg)+"''");
                    }else{
                        cad = (Integer.toString((int)resultado)+"°");
                    }
                    
                }else if  (operacion.equals("sen")){
                    //Los resultados podrían no ser exactos debido al valor de la constante PI en Java.
                    resultado = Math.sin(toRadians(Double.parseDouble(pantalla.getText())));
                }else if  (operacion.equals("cos")){
                    //Los resultados podrían no ser exactos debido al valor de la constante PI en Java.
                    resultado = Math.cos(toRadians(Double.parseDouble(pantalla.getText())));
                }else if  (operacion.equals("tan")){
                    //Los resultados podrían no ser exactos debido al valor de la constante PI en Java.
                    resultado = Math.tan(toRadians(Double.parseDouble(pantalla.getText())));
                }
                if(operacion.equals("°")){
                    pantalla.setText(cad);
                }else{
                    pantalla.setText("" + resultado);
                }
            operacion = "";
	}
}
