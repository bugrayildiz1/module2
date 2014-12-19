package ss.week6.votemachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PartyList extends Observable {
    private final List<String> parties;

    public PartyList() {
        this.parties = new ArrayList<String>();
    }

    public void addParty(String party) {
        parties.add(party);

        setChanged();
        notifyObservers("party");
    }

    public List<String> getParties() {
        return parties;
    }
}
