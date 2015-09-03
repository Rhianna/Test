public class Test {
	public static void main(String[] args) {
		String a = "bbcde";
		String[] b = a.split("\\u0063");
		for (String c : b) System.out.println(c);
		System.out.println((int)'c');
		
		System.out.println(args[0]);
		System.out.println(args[0].length());
		if (args[0].matches("[0-9a-fA-F]{4}")) System.out.println("yes!");
	}
}