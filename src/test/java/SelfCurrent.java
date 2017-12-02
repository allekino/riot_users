import Utils.API;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import org.junit.Test;

public class SelfCurrent {
    Platform platform = Platform.EUW;
    @Test
    public void getSelfGame() throws RiotApiException {
        Summoner summoner = API.getSummoner("Wargamer97");
        RiotApi api = API.getInstance();

        MatchList mathes = api.getMatchListByAccountId(platform, summoner.getAccountId());
        for (MatchReference match : mathes.getMatches()){
            int a = 0;
            Match myMatch = api.getMatch(platform, match.getGameId());
        }
//        Match match = api.getMatch(platform, );
    }
}
