package weblsj;

public class RemoteTest {

	public static void main(String[] args) {
		TV tv = new TV("LG");
		
		MultiRemoteController mc = new MultiRemoteController();
		mc.remoteControl(tv);
		
		Computer com = new Computer(64);

		mc.remoteControl(com);

	}

}
