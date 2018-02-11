package reflection;

public class ReflectionClass implements ReflectionInterface{
	
	private String var1;
	private int var2;
	
	public ReflectionClass(){
		this.var1 = "Reflection initial";
		this.var2 = 0;	
	}
	public ReflectionClass(String var1, int var2){
		this.var1 = var1;
		this.var2 = var2;
	}
	

	@Override
	public String draw() {
		System.out.println("Sample drawing");
		return "Sample drawing";
	}
	
	@Override
	public String toString() {
		return var1+" "+var2;
	}

	private void privateMethod(){
		System.out.print("Accessing Private Method");
	}
	
	private void privateMethodWithParams(String test){
		System.out.print("Accessing Private Method with Param ==> "+test);
	}

	public String getVar1() {
		return var1;
	}


	public void setVar1(String var1) {
		this.var1 = var1;
	}


	public int getVar2() {
		return var2;
	}


	public void setVar2(int var2) {
		this.var2 = var2;
	}

}
