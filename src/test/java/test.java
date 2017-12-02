import GUI.Gui;
import Utils.API;
import Utils.Bat;
import Utils.Utils;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class test {
    private static final Logger logger = LogManager.getLogger(test.class);

    @Test
    public void TestApi() throws Exception {
        while (true) {
            Gui.clearFrame();

            Gui.paint();
            Utils utils = new Utils();
            Bat.clear();
            ArrayList<String> summoners = utils.reader();
            summoners.forEach(s -> {
                Summoner summoner;
                summoner = API.getSummoner(s);
                if (summoner != null) {
                    String observer = API.getObserver(summoner);
                    if (!Objects.equals(observer, "")) {
                        String name = summoner.getName();
                        logger.info("Name: " + name);
                        Gui.addButton(name.replace(" ", "_"));
                        try {
                            new Bat().createBat(name.replace(" ", "_"), observer);
                        } catch (IOException e) {
                            logger.error(e);
                        }
                    }
                }
            });
            logger.info("**********************************");
            Thread.sleep(150 * 1000);
        }
    }
}
