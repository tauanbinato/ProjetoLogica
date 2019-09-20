
public class Main {
	
	public static void main(String args[]) {
		
		Interface.userPedirProposicao();
		String input = User.insereInput();
		input = input.replaceAll("\\s+","");
		input = input.replaceAll("[[a-z]&&[^<->]]", " $0 ").trim();
		input = input.replaceAll(" {2,}", " ");;
		Calculadora.prepararProposicao(input);
		
	}
	
}
