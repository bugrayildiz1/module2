package ss.week6.votemachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class VoteList extends Observable {
    private final Map<String, Integer> votes;

    public VoteList() {
        this.votes = new HashMap<String, Integer>();
    }

    public void vote(String party) {
        if (!votes.containsKey(party)) {
            votes.put(party, 0);
        }

        votes.put(party, votes.get(party) + 1);

        setChanged();
        notifyObservers("vote");
    }

    public Map<String, Integer> getVotes() {
        return votes;
    }
}
