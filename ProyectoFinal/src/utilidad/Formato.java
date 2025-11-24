package utilidad;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.text.DefaultFormatter;

public class Formato {

	public static void setSpinner(JSpinner spinner) {
		
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
		spinner.setEditor(editor);
		
		JFormattedTextField txt = editor.getTextField();
		DefaultFormatter formatter = (DefaultFormatter)txt.getFormatter();
		
		formatter.setAllowsInvalid(false); 
		formatter.setOverwriteMode(true);
	}
	
	
	public static boolean verificarEntradaRegex(String entrada, String regex, String mensaje) {
		boolean error = false;
    	
    	if(!entrada.trim().matches(regex)) {
    		JOptionPane.showMessageDialog(null, mensaje, "Campo Requerido", JOptionPane.WARNING_MESSAGE);
    		error = true;
    	}
    	
    	return error;
	}
	
	
	public static boolean entradaVacia(String entrada, String mensaje) {
		boolean error = false;
		
		if(entrada.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, mensaje, "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            
            error = true;
            
        }
		
		return error;
	}

}
