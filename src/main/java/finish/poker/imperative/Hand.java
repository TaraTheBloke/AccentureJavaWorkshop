package finish.poker.imperative;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Hand {

    private final String cards;
    private final String name;
    private final Map<String, Integer> rankCounts;
    private final int suitCount;
    private final int sequenceStart;

    public Hand(String cards) {
        this.rankCounts = rankCountsFor(cards);
        this.suitCount = suitCountFor(cards);
        this.sequenceStart = sequenceStartFor(rankCounts);
        this.name = nameHand();
        this.cards = cards;
    }

    String cards() {
        return cards;
    }

    String name() {
        return name;
    }

    private Map<String, Integer> rankCountsFor(String cards) {
        HashMap<String, Integer> rankCounts = new HashMap<>();
        for (String card : cards.split(" ")) {
            String rank = card.substring(0, 1);
            int count = rankCounts.getOrDefault(rank, 0);
            rankCounts.put(rank, ++count);
        }
        return rankCounts;
    }

    private int suitCountFor(String cards) {
        HashSet<String> suits = new HashSet<>();
        for (String card : cards.split(" ")) {
            String suit = card.substring(1, 2);
            suits.add(suit);
        }
        return suits.size();
    }

    private int sequenceStartFor(Map<String, Integer> rankCounts) {
        Set<String> ranks = rankCounts.keySet();
        List<String> sequence = Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
        for (int i = 0; i < 10; i++) {
            if (ranks.containsAll(sequence.subList(i, i + 5))) {
                return i + 1;
            }
        }
        return -1;
    }

    private String nameHand() {
        if (isRoyalFlush()) {
            return "royal flush";
        }
        if (isStraightFlush()) {
            return "straight flush";
        }
        if (isFourOfAKind()) {
            return "four of a kind";
        }
        if (isFullHouse()) {
            return "full house";
        }
        if (isFlush()) {
            return "flush";
        }
        if (isStraight()) {
            return "straight";
        }
        if (isThreeOfAKind()) {
            return "three of a kind";
        }
        if (isTwoPair()) {
            return "two pair";
        }
        if (isPair()) {
            return "one pair";
        }
        return "high card";
    }

    private boolean isRoyalFlush() {
        return isFlush() && sequenceStart == 10;
    }

    private boolean isStraightFlush() {
        return isFlush() && sequenceStart > 0;
    }

    private boolean isFourOfAKind() {
        return rankCounts.values().contains(4);
    }

    private boolean isFullHouse() {
        return rankCounts.keySet().size() == 2;
    }

    private boolean isFlush() {
        return suitCount == 1;
    }

    private boolean isStraight() {
        return sequenceStart > 0;
    }

    private boolean isThreeOfAKind() {
        return rankCounts.values().contains(3);
    }

    private boolean isTwoPair() {
        return rankCounts.keySet().size() == 3;
    }

    private boolean isPair() {
        return rankCounts.keySet().size() == 4;
    }
}
