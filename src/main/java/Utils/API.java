package Utils;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameInfo;
import net.rithms.riot.api.endpoints.spectator.dto.Observer;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Objects;

public class API {
    private static final Logger logger = LogManager.getLogger(API.class);
    private static final String path = "\"League of Legends.exe\" \"8394\" \"LoLLauncher.exe\" \"\" \"spectator spectator.euw1.lol.riotgames.com:80 %s %s EUW1\" \"-UseRads\"";
    private static volatile RiotApi instance;

    public static Summoner getSummoner(String summonerName) {
        try {
            RiotApi api = getApi();
            return api.getSummonerByName(Platform.EUW, summonerName);
        } catch (Exception e) {
            logger.error("Ошибка поиска игрока: " + summonerName);
            logger.error(e);
        }
        return null;
    }

    private static RiotApi getApi() {
        ApiConfig config = new ApiConfig().setKey(new API().getKey());
        RiotApi localInstance = instance;
        if (localInstance == null) {
            synchronized (RiotApi.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RiotApi(config);
                    logger.info("Получили API");
                }
            }
        }
        return localInstance;
    }

    public static RiotApi getInstance() {
        return getApi();
    }

    private static CurrentGameInfo getActiveGame(Summoner summoner) {
        try {
            RiotApi api = getApi();
            return api.getActiveGameBySummoner(Platform.EUW, summoner.getId());
        } catch (Exception e) {
//            logger.error("Игра не найдена");
        }
        return null;
    }

    public static String getObserver(Summoner summoner) {
        CurrentGameInfo game = API.getActiveGame(summoner);
        if (game == null) {
            return "";
        }
        if (!Objects.equals(game.getGameType(), "MATCHED_GAME"))
        {
            return "";
        }
        Observer observer = game.getObservers();
//        logger.debug(observer);
        return String.format(path, observer, game.getGameId());
//        return observer.toString();
    }

    private String getKey() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("token.xml").getFile());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList element = doc.getElementsByTagName("key");
            return element.item(0).getTextContent();
        } catch (Exception e) {
            logger.error(e);
        }
        return "";
    }
}
