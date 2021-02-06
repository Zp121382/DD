import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Invisible
 * @version 0.1
 * @date 2021/2/6 22:45
 */
public class TestPro {
    public static void main(String[] args) throws Exception {
        // 创建配置文件对象
        Properties properties = new Properties();

        // 给对象加载资源
        FileReader fileReader = new FileReader("D:\\Work\\IDEAProject\\DD\\resources\\mysql.properties");
        properties.load(fileReader);

        System.out.println(properties);
    }
}
