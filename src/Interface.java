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
	
	public static void mostrarInfoProposicao(int numVariaveisDistintas,int i,int valorVerdadeNum) {
		System.out.println("Número de variáveis: " + numVariaveisDistintas + "| Numero de conectivos: " + i + "| Total valores verdade possíveis: " + valorVerdadeNum + "|");
	}
	
	public static void mostrarValoresVerdade(ArrayList<String> v) {
		for(String str : v) {
			System.out.print(str);
		}
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
	
	public final static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        System.out.print("ERRO AO LIMPAR CONSOLE -> " + e.getMessage());
	    }
	}
	
	public static void userPedirProposicao() {
		System.out.println("Insira a proposição que deseja calcular.");
		System.out.println("Conectivos: -> , ^ , v , ! , <->");
	}
	
	public static void userPerguntarStart() {
		System.out.println("Proposição pronta, deseja iniciar o calculo?");
	}
	
	public static void erroBalanceamento() {
		System.out.println("Sua fórmula não está balanceada, verifique se colocou os parenteses corretamente.");
	}
	
}
