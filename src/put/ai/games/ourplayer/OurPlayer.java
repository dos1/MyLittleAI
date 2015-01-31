package put.ai.games.ourplayer;

import java.util.List;
import java.util.Random;
import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;

public class OurPlayer extends Player {

    private final Random random = new Random(0xdeadbeef);

    @Override
    public String getName() {
        return "Sebastian Krzyszkowiak 106553 Piotr Siupa 106605";
    }

    @Override
    public Move nextMove(Board b) {
    	
        List<Move> moves = b.getMovesFor(getColor());
        
        Board btemp = b.clone();
        
        Move winningMove = null, losingMove = null, tieingMove = null, nothingSpecialMove = null;
        
        for (Move move: moves) {
        	btemp.doMove(move);
        	Color winner = btemp.getWinner(getOpponent(getColor()));
        	if (winner == getColor()) {
        		winningMove = move;
        	}
        	if (winner == getOpponent(getColor())) {
        		losingMove = move;
        	}
        	if (winner == Color.EMPTY) {
        		tieingMove = move;
        	}
        	if (winner == null) {
        		nothingSpecialMove = move;
        	}
        	btemp.undoMove(move);
        }
        
        if (winningMove != null) {
        	return winningMove;
        }
        if ((losingMove != null) && (tieingMove != null)) {
        	return tieingMove;
        }
        return nothingSpecialMove;
        
    }
    
    private int heuristics(final Board board, final Color player) {
    	final int MILIJON = 1000000;
    	final int POL_MILIJONA = 500000;
    	final Color winner = board.getWinner(getOpponent(player));  //TODO check it
    	if (winner != null) {
			if (winner.equals(player))
				return MILIJON;
			if (winner.equals(Color.EMPTY))
				return -POL_MILIJONA;
			else
				return -MILIJON;
    	}
		
		return random.nextInt(100);
    }
    
}
