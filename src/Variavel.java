import java.util.ArrayList;

public class Variavel {
	
	private String letra;
	public Integer valorVerdadeNum;
	public ArrayList<String> valorVerdade;
	
	public Variavel(String let){
		valorVerdade = new ArrayList<String>();
		this.letra = let;
	}
	
	public void setValorVerNum(Integer num) {
		valorVerdadeNum = num;
	}
	
	public ArrayList<String> getValorVerdade() {
		return valorVerdade;
	}
	
	public Integer getValorVerNum() {
		return valorVerdadeNum;
	}
	
	public String getLetra(){
		return letra;
	}
	
	public void setLetra(String newLetra) {
		letra = newLetra;
	}
	
	public void setValorVerdade(Integer numV , Integer numF , Integer repeatNum) {
		
		for(int j = 0; j < repeatNum; j ++) {
		
			for(int i = 0; i < numV; i ++) {
				valorVerdade.add("v");
			}
			for(int i = 0; i < numF; i ++) {
				valorVerdade.add("f");
			}
		
		}
	}
	
	public void setValorVerdadeEqual(ArrayList<String> vvAl) {
		valorVerdade = vvAl;
	}
	
}
