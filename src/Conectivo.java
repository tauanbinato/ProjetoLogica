import java.util.ArrayList;

public class Conectivo {
	
	private static String p_aberto   = "(";
	private static String p_fechado  = ")";
	private static String c_cond     = "->";  // Condicional
	private static String c_or       = "v";   // Ou
	private static String c_andMac   = "ˆ";   // E
	private static String c_and      = "^";
	private static String c_biCond   = "<->"; // Bicondicional
	private static String c_neg      = "˜";   // Negacao
	private static String[] conectivos = { p_aberto,p_fechado,c_cond,c_or,c_andMac,c_and,c_biCond,c_neg };
	
	private String simbulo;
	public  Integer valorVerdadeNum;
	public  ArrayList<String> valorVerdade;
	
	public Conectivo(String simb){
		valorVerdade = new ArrayList<String>();
		if(isConectivo(simb)) simbulo = simb;
	}
	
	public static boolean isConectivo(String con) {
		
		for(int i = 0; i < conectivos.length; i++) {
			if(con.equals(conectivos[i])) {	
				return true;
			}
		}
		return false;
	}
	
	public void setValorVerNum(Integer num) {
		valorVerdadeNum = num;
	}
	
	public String getSimbulo() {
		return simbulo;
	}
	
	public ArrayList<String> getValorVerdade() {
		return valorVerdade;
	}
	
	public void initValorVerdade() {
		valorVerdade.add("-");
	}
	
}
