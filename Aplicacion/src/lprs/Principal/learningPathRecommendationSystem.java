package lprs.principal;

import javax.swing.JOptionPane;

import lprs.consola.ConsolaPrincipal;

public class learningPathRecommendationSystem {

	
	
	
	
	
	
	public static void main(String[] args) {
		
		ConsolaPrincipal consolaP = new ConsolaPrincipal();
		
		String cadena=JOptionPane.showInputDialog("Digita una cadena");
        JOptionPane.showMessageDialog(null, cadena);
		try {
			consolaP.mostrarConsolaPrincipal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
