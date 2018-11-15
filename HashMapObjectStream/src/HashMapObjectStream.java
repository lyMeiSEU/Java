import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;

public class HashMapObjectStream {
	private Info info;

	public HashMapObjectStream() {
		info = new Info();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Random random=new Random();
		SourceEn EN = new SourceEn();

		HashMap<String, String> mapNM = new HashMap<>();
		HashMap<String, String> mapSC = new HashMap<>();

		for (int i = 0; i < 1000; ++i) {
			Info info = new Info();
			info.setid(String.valueOf(i));
			info.setname(EN.name(i%569));
			info.setscore(Integer.toString(random.nextInt(100)));
			mapNM.put(info.getid(), info.getname());
			mapSC.put(info.getid(), info.getscore());
		}
		System.out.println("The Student info We made are: ");
		for (int i = 0; i < 1000; ++i) {
			Info info = new Info();
			info.setname(mapNM.get(String.valueOf(i)));
			info.setscore(mapSC.get(String.valueOf(i)));
			System.out.println(i + " " + info.getname() + " " + info.getscore());
		}
		write(mapNM, "D:/hashname.txt");
		write(mapSC, "D:/hashscore.txt");
		mapNM = read("D:/hashname.txt");
		mapSC = read("D:/hashscore.txt");
		
		System.out.println("The Student info We readed are: ");
		for (int i = 0; i < 1000; ++i) {
			Info info = new Info();
			info.setname(mapNM.get(String.valueOf(i)));
			info.setscore(mapSC.get(String.valueOf(i)));
			System.out.println(i + " " + info.getname() + " " + info.getscore());
		}
	}

	public static void write(HashMap<String, String> hashmap, String fil) throws FileNotFoundException, IOException {
		File file = new File(fil); /* TV []data=new TV[2]; data[0]=tv;data[1]=tv; */
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file, false));
		// objectOutputStream.writeObject(data);
		objectOutputStream.writeObject(hashmap);
		objectOutputStream.close();
		System.out.println("write over~!~~");
	}

	public static HashMap<String, String> read(String fil)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(fil);
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
		HashMap<String, String> v = (HashMap<String, String>) inputStream.readObject();
		return v;
	}
}
