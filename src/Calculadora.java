import java.util.ArrayList;
import java.util.Arrays;

public class Calculadora {

	public  static ArrayList<Object> propArrayList  = new ArrayList<Object>();
	private static ArrayList<String> varArrayList   = new ArrayList<String>();
	private static ArrayList<String> conecArrayList = new ArrayList<String>();
	private static String[] ordem = {Conectivo.c_neg,Conectivo.c_and,Conectivo.c_or,Conectivo.c_cond,Conectivo.c_biCond};
	private static Integer valorVerdadeNum;
	private static Integer numVariaveisDistintas;
	private static String userProp;
	
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
			
			valorVerdadeNum = (int) Math.pow(2, onlyDiffVars.size());
			numVariaveisDistintas = onlyDiffVars.size();
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
						//System.out.println(((Variavel) propArrayList.get(i)).getLetra() + ": " + ((Variavel) propArrayList.get(i)).getValorVerdade());
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
							//System.out.println(((Variavel) propArrayList.get(i)).getLetra() + ": " + ((Variavel) propArrayList.get(i)).getValorVerdade());
							
						} else {
							// Se já foi calculado, utilize os próprios valores.
							Variavel v = varsCalculadas.get(indexVarThere);
							propArrayList.set(i, v);
							//System.out.println(((Variavel) propArrayList.get(i)).getLetra() + ": " + ((Variavel) propArrayList.get(i)).getValorVerdade());
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

		} catch (Exception e) {
			System.out.println("ERRO AO INICIALIZAR VALORES VERDADE -> " + e.getMessage());
		}
		
	}
	
	public static void calcularProposicao() {
		
		calcularConectivo((Variavel)propArrayList.get(0),(Conectivo)propArrayList.get(1),(Variavel)propArrayList.get(2));
//		//Seguimos o calculo na ordem correta.
//		for(int i = 0; i < ordem.length; i++) {
//			for(Object obj : propArrayList) {
//				if( obj instanceof Conectivo) {
//					// Se for um conectivo da ordem na preposição, começe por ele.
//					if(((Conectivo) obj).getSimbulo().equals(ordem[i]))  {
//						
//					}
//				}
//			}
//		}
	}
	
	private static ArrayList<String> calcularConectivo(Variavel v1  , Conectivo c , Variavel v2) {
		
		// Checks de segurança.
		if(v1.getValorVerdade().size() <= 0 && v2.valorVerdade.size() <= 0) return null;
		if(v1.getValorVerdade().size() != v2.getValorVerdade().size()) return null;
		
		ArrayList<String> result = new ArrayList<String>();
	
		System.out.println("Calculando: " + v1.getLetra() + " " + c.getSimbulo() + " " + v2.getLetra());
		for(int i = 0; i < v1.getValorVerdade().size(); i++) {
			String simbulo = c.getSimbulo();
			
			//Implicação ->
			if (Conectivo.c_cond.equals(simbulo)) {
				//Na implicação só é falso se v1 verdadeiro implicar em v2 falso.
				if(v1.getValorVerdade().get(i).equals("v") && v2.getValorVerdade().get(i).equals("f")){
					result.add("f");
				}else {
					result.add("v");
				}	
			}
			//Disjunção v
			else if (Conectivo.c_or.equals(simbulo)){
				//Na disjunção só é falso quando os dois são falsos
				if(v1.getValorVerdade().get(i).equals("f") && v2.getValorVerdade().get(i).equals("f")) {
					result.add("f");
				}else {
					result.add("v");
				}
			}
			//Conjunção ^
			else if (Conectivo.c_and.equals(simbulo)){
				//Na conjunção só é verdade quando os dois são verdades
				if(!(v1.getValorVerdade().get(i).equals("v") && v2.getValorVerdade().get(i).equals("v"))) {
					result.add("f");
				}else {
					result.add("v");
				}
			}
			//Implicação dupla <->
			else if (Conectivo.c_biCond.equals(simbulo)){
				//Na bicondicional só é verdade quando v1 e v2 são iguais.
				if(!((v1.getValorVerdade().get(i).equals("v") && v2.getValorVerdade().get(i).equals("v")) ||
					(v1.getValorVerdade().get(i).equals("f") && v2.getValorVerdade().get(i).equals("f")))) {
					result.add("f");
				} else {
					result.add("v");
				}
			}
			else {
				System.out.println("erro: condicional inexistente.");
				return null;
			}
		}
		Interface.mostrarValoresVerdade(result);
		return result;
	}
	
	public static void mostrarValores() {
		Interface.mostrarInfoProposicao(numVariaveisDistintas,conecArrayList.size(),valorVerdadeNum);
		Interface.spacer(userProp.length());
		Interface.mostrarUserProp(userProp);
		Interface.mostrarValoresVerdadeIniciais(propArrayList , valorVerdadeNum);
		Interface.spacer(userProp.length());
	}
	
	public static boolean checkBalanced(ArrayList<Object> prop) {
		
		try {
			if(prop.size() <= 0) return false;
		
			ArrayList<String> parentesis = new ArrayList<String>();
			int i = -1;
			
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
			
			Interface.erroBalanceamento();
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
	
	public static void clear() {
		propArrayList.clear();
		varArrayList.clear();
		conecArrayList.clear();
		valorVerdadeNum = 0;
		numVariaveisDistintas = 0;
	}

}
