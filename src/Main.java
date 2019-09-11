
public class Main {
	
	public static void main(String args[]) {
		
		Interface.userPedirProposicao();
		String input = User.insereInput();
		input = input.trim().replaceAll("[^-><-]", " $0 ");
		input = input.replaceFirst(" ", "");
		Calculadora.prepararProposicao(input);
		
	}
	
}
