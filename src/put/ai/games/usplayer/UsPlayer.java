package put.ai.games.usplayer;

import java.util.List;
import java.util.Random;
import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;

public class UsPlayer extends Player {

    private Random random=new Random(0xdeadbeef);

    @Override
    public String getName() {
        return "Nasz gracz";
    }

    @Override
    public Move nextMove(Board b) {
        List<Move> moves = b.getMovesFor(getColor());
        return moves.get(moves.size()-1-random.nextInt(moves.size()));
    }
    
}
