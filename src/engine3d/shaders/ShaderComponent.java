package engine3d.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Zeejfps on 12/17/13.
 */
public class ShaderComponent {

    private static final String DEFAULT_SHADER_PATH = "./res/shaders/";

    private final int type;
    private final String source;

    public ShaderComponent(int type, String fileName) {

        this.type = type;
        this.source = loadShaderSource(fileName);

    }

    private String loadShaderSource(String fileName) {

        final StringBuilder strBld = new StringBuilder();
        BufferedReader br= null;

        try {

            br = new BufferedReader(new FileReader(DEFAULT_SHADER_PATH + fileName));

            String line;
            while ((line = br.readLine()) != null) {
                strBld.append(line).append("\n");
            }

        } catch (Exception e) {

            e.printStackTrace();

            System.exit(1);
        } finally {

            if (br != null) {

                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        return strBld.toString();
    }

    public String getSource() {
        return source;
    }

    public int getType() {
        return type;
    }

}
