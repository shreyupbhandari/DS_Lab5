import java.util.LinkedList;

public class History
{
    /**
       Notepad will call this function when thier text changes.

       deletion is a boolean that indicates if the action was a deletion of text or the insertion of text.
       position is the postion where the change took place
       Change is the string of characters that is the change.
     */
	
  private State data;
  private LinkedList<State> undoData=new LinkedList<>();
  private LinkedList<State> redoData=new LinkedList<>();
  
   public void addEvent(boolean deletion, int position, String Change)
   {
	   
	   data= new State(deletion, position, Change);
	   undoData.push(data);
	   redoData.clear();
	   
   }

    /**
       Notepad will call this function when it wishes to undo the last event.

       note is a variable sto the Notepad that called this function
     */
   public void undoEvent(NotePad note)
   {
	   	State topData= undoData.pop();
	   
   		redoData.push(topData);
   
		boolean delete = topData.deletion;
		int position =topData.position;
		String change = topData.change;
   
		if (delete)
		{
			note.insert(position, change);
		}
	   
		else
		{
			note.remove(position, change.length());
		}
	   
   }
	
    /**
       Notepad will call this function when it wishes to redo the last event that was undone.
       Note that new actions should clear out events that can be "redone"
       note is a variable to the Notepad that called this function
     */
   public void redoEvent(NotePad note)
   {
	   State topData= redoData.pop();
	   undoData.push(topData);
	   
		   boolean delete = topData.deletion;
		   int position =topData.position;
		   String change = topData.change;
	   
		   if (delete)
		   {
			   note.remove(position, change.length());
		   }
		   
		   else
		   {
			   note.insert(position, change);
		   }
		 
   }
	   

    /**
       returns true if there is undo data in the History
     */
   public boolean hasUndoData()
   {
       return !undoData.isEmpty();
   }

    /**
       returns true if there is undo data in the History
     */
   public boolean hasReDoData()
   {
       return !redoData.isEmpty();
   }
	

}
