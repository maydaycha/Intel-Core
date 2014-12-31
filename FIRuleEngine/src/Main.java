import com.intel.formosa.*;
import com.intel.formosa.mqtt.*;
import com.intel.formosa.params.*;

public class Main {	

	
		
    public static void main(String args[]) {
    	
    	Thread t1 = new Thread(new Go());
    	t1.start();
    }
}
