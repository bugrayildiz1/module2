package ss.week6.votemachine;

import java.util.List;
import java.util.Map;

public class VoteMachine {
    private final VoteList voteList;
    private final PartyList partyList;
    private final VoteView view;

    public VoteMachine() {
        this.voteList = new VoteList();
        this.partyList = new PartyList();
        this.view = new VoteTUIView(this);

        voteList.addObserver(view);
        partyList.addObserver(view);
    }

    public void start() {
        view.start();
    }

    public void addParty(String party) {
        partyList.addParty(party);
    }

    public void vote(String party) {
        voteList.vote(party);
    }

    public List<String> getParties() {
        return partyList.getParties();
    }

    public Map<String, Integer> getVotes() {
        return voteList.getVotes();
    }

    public static void main(String[] args) {
        VoteMachine machine = new VoteMachine();

        machine.start();
    }
}
