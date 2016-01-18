package Utils;

import docpre.utils.GloabConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by chengke on 16/1/18.
 */
public class GloabConfigTest {

    @Before
    public void before(){
        try {
            GloabConfig.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGet() {
        String basepath = GloabConfig.get("BASE_PATH");
        Assert.assertEquals(basepath, "http://docpre.sweetvvck.com/");
    }
}
