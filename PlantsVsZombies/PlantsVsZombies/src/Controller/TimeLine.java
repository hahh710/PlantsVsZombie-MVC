package Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import Model.Game;

public class TimeLine {
	private ArrayList<Game> timeStore;
	private int currenti;

	public TimeLine () {
		timeStore = new ArrayList<Game>();
		currenti = 0;
	}
	public TimeLine (Game game) {
		timeStore = new ArrayList<Game>();
		Game temp = deepCopy(game);
		timeStore.add(temp);
		currenti = 0;
	}
	/*
	 * need to do "depp copy"
	 * Taking a game parameter will cause "field-by-field copy,field-for-field copy, or field copy".
	 * therefore, need to save it independently using serialization there were other ways but I used serialization for next milestone.
	 */
	public void addNext(Game game) {

		Game temp = deepCopy(game);
		
		//Controller(Game game, View view,Layout layout,PlantStore ps, Action action, Turn turn, CoolDown c,int stag)
		if(timeStore.size()> currenti+1) {
			for(int i = currenti;  i<timeStore.size()-1  ;i++) {
				timeStore.remove(i);
			}
			timeStore.add(temp);		
			currenti++;
		}else{ 
			timeStore.add(temp);
			currenti++;
		}
	}
	/*
	public void addNext(cabsule c) {

		//Controller(Game game, View view,Layout layout,PlantStore ps, Action action, Turn turn, CoolDown c,int stag)

		if(timeStore.size() > currenti) {
			timeStore.set(currenti, c );
			currenti ++;
		}else{ 
			timeStore.add(c);
			currenti ++;
		}
	}*/
	public Game deepCopy(Game orig) {
		Game obj = null;
		try {
			// Write the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(orig);
			out.flush();
			out.close();

			// Make an input stream from the byte array and read
			// a copy of the object back in.
			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(bos.toByteArray()));
			obj = (Game) in.readObject();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return (Game) obj;
	}



	public boolean isUndoAvailable() {
		if(currenti-1 < 0) {
			return false;
		}else
			return true;
	}
	
	public boolean isRedoAvailable(){
		if(timeStore.size() > currenti+1) {
			return true;
		}else
			return false;
	}

	public Game undo() {
			Game game = timeStore.get(currenti-1);
			currenti--;
			return game;
	}

	public Game redo() {
		if(timeStore.size() > currenti+1) {
			Game game = timeStore.get(currenti+1);
			currenti++;
			return game;
		}else {
			return timeStore.get(currenti);
		}
	}

}
