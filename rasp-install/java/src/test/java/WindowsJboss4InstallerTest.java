import com.baidu.rasp.install.windows.Jboss4Installer;
import com.baidu.rasp.uninstall.windows.Jboss4Uninstaller;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Constructor;

import static org.junit.Assert.assertEquals;

/**
 * @author: anyang
 * @create: 2019/03/25 17:17
 */
public class WindowsJboss4InstallerTest {
    @Test
    public void testGetInstallPath() {
        try {
            Class clazz = Jboss4Installer.class;
            Constructor constructor = clazz.getConstructor(String.class, String.class);
            Object installer = constructor.newInstance("JBoss 4-6", "/Users/anyang/Desktop/jacoco/sum/jboss-5.0.1.GA/");
            String path = Utils.invokeStringMethod(installer, "getInstallPath", new Class[]{String.class},
                    "/Users/anyang/Desktop/jacoco/sum/jboss-5.0.1.GA");
            assertEquals(path, "/Users/anyang/Desktop/jacoco/sum/jboss-5.0.1.GA\\rasp");
        } catch (Exception e) {
            //
        }
    }

    @Test
    public void testGetScript() {
        try {
            Class clazz = Jboss4Installer.class;
            Constructor constructor = clazz.getConstructor(String.class, String.class);
            Object installer = constructor.newInstance("JBoss 4-6", "/Users/anyang/Desktop/jacoco/sum/jboss-5.0.1.GA/");
            String path = Utils.invokeStringMethod(installer, "getScript", new Class[]{String.class},
                    "/Users/anyang/Desktop/jacoco/sum/jboss-5.0.1.GA\\rasp");
            assertEquals(path, "/Users/anyang/Desktop/jacoco/sum/jboss-5.0.1.GA\\rasp\\..\\bin\\run.bat");
        } catch (Exception e) {
            //
        }
    }

    @Test
    public void testModifyStartScript() {
        try {
            String content = IOUtils.toString(new FileReader(new File("/Users/anyang/Desktop/jacoco/sum/jboss-5.0.1.GA/bin/run.bat")));
            Class clazz = Jboss4Installer.class;
            Constructor constructor = clazz.getConstructor(String.class, String.class);
            Object installer = constructor.newInstance("JBoss 4-6", "/Users/anyang/Desktop/jacoco/sum/jboss-5.0.1.GA/");
            Utils.invokeStringMethod(installer, "modifyStartScript", new Class[]{String.class}, content);
        } catch (Exception e) {
            //
        }
        try {
            InputStream is = this.getClass().getResourceAsStream("/startWebLogic.sh");
            String content = IOUtils.toString(is, "UTF-8");
            Class clazz = Jboss4Uninstaller.class;
            Constructor constructor = clazz.getConstructor(String.class, String.class);
            Object installer = constructor.newInstance("JBoss 4-6", "/Users/anyang/Desktop/jacoco/sum/jboss-5.0.1.GA/");
            Utils.invokeStringMethod(installer, "modifyStartScript", new Class[]{String.class}, content);
        } catch (Exception e) {
            //
        }
    }
}
