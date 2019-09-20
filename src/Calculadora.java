import java.util.ArrayList;
import java.util.Arrays;

public class Calculadora {

	public  static ArrayList<Object> propArrayList  = new ArrayList<Object>();
	private static ArrayList<String> varArrayList   = new ArrayList<String>();
	private static ArrayList<String> conecArrayList = new ArrayList<String>();  
	private static Integer valorVerdadeNum;
	private static String userProp;
	
	public static void clear() {
		propArrayList.clear();
		varArrayList.clear();
		conecArrayList.clear();
		valorVerdadeNum = 0;
	}
	
	public static void prepararProposicao(String prop) {

		try {

			String rawProp = prop.toString().toLowerCase();
			userProp = rawProp;
			String rawPropArray[] = rawProp.split(" ");

			//System.out.println(Arrays.toString(rawPropArray));
			for(int i = 0; i < rawPropArray.length; i++) {

				//Checamos se é um conectivo, se n for será variável.
				if(!Conectivo.isConectivo(rawPropArray[i])) {
					//É variavel
					Variavel v = new Variavel(rawPropArray[i]);
					propArrayList.add(v);
					varArrayList.add(v.getLetra());
				} else {
					//É conectivo
					Conectivo c = new Conectivo(rawPropArray[i]);
					propArrayList.add(c);
					conecArrayList.add(c.getSimbulo());
				}
			}
			
			//Preparamos os valores verdades.
			prepararValoresVerdades();

		} catch(Exception e) {
			System.out.println("ERRO AO PREPARAR PROPOSICAO -> " + e.getMessage());
		}
	}

	private static void prepararValoresVerdades() {

		Integer i = 0;
		Integer varI = 0;
		Integer conI = 0;
		ArrayList<Variavel> onlyDiffVars = new ArrayList<Variavel>();
		
		try {
			for(Object obj : propArrayList) {

				if(obj instanceof Variavel) {

					if(varI == 0) {
						onlyDiffVars.add(((Variavel) obj));
					}

					if(varI > 0) {
						//Se já tiver a variável não faça nada.
						if(isThere(onlyDiffVars, ((Variavel) obj).getLetra())) {
							i++;
							continue;
						} else {
							onlyDiffVars.add(((Variavel) obj));
						}
					}
					varI++;
				}
				i++;
			}
			Interface.spacer(userProp.length());
			valorVerdadeNum = (int) Math.pow(2, onlyDiffVars.size());
			Interface.mostrarInfoProposicao(onlyDiffVars,conecArrayList,valorVerdadeNum);
			Interface.spacer(userProp.length());
		} catch (Exception e) {
			System.out.println("ERRO AO PREPARAR VALORES VERDADE -> " + e.getMessage());
		}

		inicializarValoresVerdadeVars();
	}
	
	private static void inicializarValoresVerdadeVars() {
		
		ArrayList<Variavel> varsCalculadas = new ArrayList<Variavel>();
		Integer i = 0;
		Integer varI = 0;
		Integer numValor_V_F = valorVerdadeNum/2;
		Integer numRepeat = 1;
		Integer indexVarThere = null;
		
		try {
			for(Object obj : propArrayList) {
				
				if(obj instanceof Variavel) {
					
					//Primeira iteração
					if(varI == 0) {
						Variavel v = new Variavel(((Variavel) propArrayList.get(i)).getLetra());
						v.setValorVerdade(numValor_V_F, numValor_V_F, numRepeat);
						varsCalculadas.add(v);
						indexVarThere = 0;
						numRepeat *= 2;
						propArrayList.set(i, v);
						System.out.println(((Variavel) propArrayList.get(i)).getLetra() + ": " + ((Variavel) propArrayList.get(i)).getValorVerdade());
					}
					else {
						//Se não tiver sido já calculada.
						if(!isThere(varsCalculadas, ((Variavel) propArrayList.get(i)).getLetra())) {
							Variavel v = new Variavel(((Variavel) propArrayList.get(i)).getLetra());
							numValor_V_F /= 2;
							v.setValorVerdade(numValor_V_F, numValor_V_F,numRepeat);
							numRepeat *= 2;
							varsCalculadas.add(v);
							indexVarThere = varsCalculadas.lastIndexOf(v);
							propArrayList.set(i, v);
							System.out.println(((Variavel) propArrayList.get(i)).getLetra() + ": " + ((Variavel) propArrayList.get(i)).getValorVerdade());
							
						} else {
							// Se já foi calculado, utilize os próprios valores.
							Variavel v = varsCalculadas.get(indexVarThere);
							propArrayList.set(i, v);
							System.out.println(((Variavel) propArrayList.get(i)).getLetra() + ": " + ((Variavel) propArrayList.get(i)).getValorVerdade());
						}
					}
					varI++;
					
				} else if ((obj instanceof Conectivo)) {
					for(int k = 0; k < valorVerdadeNum; k++) {
						((Conectivo) obj).initValorVerdade();
					}
				}
				i++;
			}

			Interface.spacer(userProp.length());
			Interface.mostrarUserProp(userProp);
			Interface.mostrarValoresVerdadeIniciais(propArrayList , valorVerdadeNum);
			Interface.spacer(userProp.length());

		} catch (Exception e) {
			System.out.println("ERRO AO INICIALIZAR VALORES VERDADE -> " + e.getMessage());
		}
		
	}
	
	public static boolean checkBalanced(ArrayList<Object> prop) {
		
		try {
			if(prop.size() <= 0) return false;
		
			ArrayList<String> parentesis = new ArrayList<String>();
			int i = 0;
			
			for(Object obj : prop) {
				if(obj instanceof Conectivo) {
					if(((Conectivo) obj).getSimbulo().equals("(")) {
						parentesis.add("(");
						i++;
					} 
					else if(((Conectivo) obj).getSimbulo().equals(")")) {
						parentesis.remove(i);
					}
				}
			}
			if(parentesis.size() == 0) return true;
			return false;
		}catch(Exception e) {
			System.out.println("ERRO AO CHECAR BALANCEADO -> " + e.getMessage());
			return false;
		}
	}
	
	// Funções auxiliares
	private static boolean isThere(ArrayList<Variavel> onlyDiff , String str) {

		for(Variavel var : onlyDiff) {
			if(str.equals(var.getLetra())) {
				return true;
			}
		}
		return false;
	}

}
