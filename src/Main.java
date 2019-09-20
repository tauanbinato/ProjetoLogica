
public class Main {
	
	public static void main(String args[]) {
		
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
