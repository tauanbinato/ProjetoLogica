
public class Main {
	
	public static void main(String args[]) {
		
		pedeProposicao();
		Calculadora.mostrarValores();
		if(perguntaInicio()) {
			Calculadora.calcularProposicao();
		}
		
	}
	
	public static boolean perguntaInicio() {
		String input = null;
		Interface.userPerguntarStart();
		input = User.insereInput();
		switch(input) {
		case "s":
			return true;
		case "sim":
			return true;
		case "y":
			return true;
		case "yes":
			return true;
		default:
			return false;
		}
	}
	
	public static void pedeProposicao() {
		String input = null;
		while(!Calculadora.checkBalanced(Calculadora.propArrayList)) {
			Interface.userPedirProposicao();
			input = User.insereInput();
			input = input.replaceAll("\\s+","");
			input = input.replaceAll("[[a-z||[\\(\\)]]&&[^<->]]", " $0 ").trim();
			input = input.replaceAll(" {2,}", " ");
			Calculadora.clear();
			Calculadora.prepararProposicao(input);
		}
	}
	
}
