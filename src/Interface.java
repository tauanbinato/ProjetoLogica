import java.util.ArrayList;

public class Interface {
	
	public static void mostrarValoresVerdadeIniciais(ArrayList<Object> propArrayList , Integer num) {
		
		try {
			for(int i = 0 ; i < num ; i ++) {
				for(Object obj : propArrayList) {

					if(obj instanceof Variavel) {
						System.out.print(((Variavel) obj).getValorVerdade().get(i));
						System.out.print(" ");
					}
					if (obj instanceof Conectivo) {
						System.out.print(((Conectivo) obj).getValorVerdade().get(i));
						System.out.print(" ");
					}
				}
				System.out.println("");
			}
		} catch(Exception e) {
			System.out.println("ERRO AO MOSTRAR INTERFACE -> " + e.getMessage());
		}
	}
	
	public static void mostrarValorVerdadeVariavel(Variavel v) {
		
		for(String var : v.getValorVerdade()) {
			System.out.print(var);
		}
		System.out.println("");
	}
	
	
	public static void mostrarInfoVariavel(Variavel v) {
		System.out.println("Variavel: " + v.getLetra() + " Valores Verdade: " + v.getValorVerdade());
	}
	
	public static void mostrarInfoConectivo(Conectivo c) {
		System.out.println("Variavel: " + c.getSimbulo() + " Valores Verdade: " + c.getValorVerdade());
	}
	
	public static void mostrarUserProp(String prop) {
		System.out.println(prop);
	}
	
	public static void spacer(int n) {
		
		for(int i = 0; i < n; i++) {
			System.out.print(".");
		}
		System.out.println("");
	}
	
}
