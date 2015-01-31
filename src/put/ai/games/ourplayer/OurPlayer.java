package put.ai.games.ourplayer;

import java.util.List;
import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;


public class OurPlayer extends Player {

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
        		
        		Board btemp2 = btemp.clone();
        		
        		List<Move> opponentMoves = btemp2.getMovesFor(getOpponent(getColor()));
        		
        		for (Move opponentMove: opponentMoves) {
        			btemp2.doMove(opponentMove);
            		Color winner2 = btemp2.getWinner(getColor());
            		
            		if (winner2 == getOpponent(getColor())) {
            			losingMove = move;
            			break;
            		}
            		btemp2.undoMove(opponentMove);
        		}
        		if (losingMove != move) {
        			nothingSpecialMove = move;
        		}
        	}
        	btemp.undoMove(move);
        }

        if (winningMove != null) {
        	return winningMove;
        }
        if ((losingMove != null) && (tieingMove != null)) {
        	return tieingMove;
        }
        if (nothingSpecialMove != null) {
        	return nothingSpecialMove;
        }
        if (tieingMove != null) {
        	return tieingMove;
        }
        return losingMove;
        
    }
    
}
