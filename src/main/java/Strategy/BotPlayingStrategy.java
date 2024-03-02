package Strategy;

import models.*;

public interface BotPlayingStrategy {
     Move decideMove(Player player, Board board);
}
