package ss.week6.votemachine;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class VoteTUIView implements Observer, VoteView {
    private static final Scanner IN = new Scanner(System.in);

    private final VoteMachine machine;

    public VoteTUIView(VoteMachine argMachine) {
        this.machine = argMachine;
    }

    public void start() {
        String input;

        while (IN.hasNextLine()) {
            input = IN.nextLine();
            input = input.trim();

            if (input.startsWith("VOTE ") && input.length() > 5) {
                machine.vote(input.substring(5));
            } else if (input.startsWith("ADD PARTY ") && input.length() > 10) {
                machine.addParty(input.substring(10));
            } else if (input.startsWith("VOTES")) {
                showVotes(machine.getVotes());
            } else if (input.startsWith("PARTIES")) {
                showParties(machine.getParties());
            } else if (input.startsWith("EXIT")) {
                break;
            } else if (input.startsWith("HELP")) {
                System.out.println("VOTE [party]");
                System.out.println("ADD PARTY [party]");
                System.out.println("VOTES");
                System.out.println("PARTIES");
                System.out.println("EXIT");
                System.out.println("HELP");
            }
        }
    }

    public void showVotes(Map<String, Integer> votes) {
        System.out.println("[Votes]");

        for (Entry<String, Integer> vote : votes.entrySet()) {
            System.out.printf("%s: %d\n", vote.getKey(), vote.getValue());
        }
    }

    public void showParties(List<String> parties) {
        System.out.println("[Parties]");

        for (String party : parties) {
            System.out.println(party);
        }
    }

    public void showError(String message) {
        System.out.printf("Error: %s\n", message);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("vote")) {
            System.out.println("Someone voted");
        } else if (arg.equals("party")) {
            System.out.println("Party added");
        }
    }
}
