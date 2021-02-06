import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * @author Invisible
 * @version 0.1
 * @date 2021/2/6 21:55
 */
public class TestIO {
    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("D:\\Work\\IDEAProject\\DD\\resources\\mysql.properties");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while((line = bufferedReader.readLine()) !=null){
            String[] splits = line.split("=");
            String key = splits[0].trim();
            String value = splits[1].trim();

            System.out.println("key:"+key+"     value:"+value);
        }

    }

}
