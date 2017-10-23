package finish.poker.functional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Hand {

    private final String cards;
    private final String name;
    private final Map<String, Long> rankCounts;
    private final int suitCount;
    private final int sequenceStart;

    Hand(String cards) {
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

    private Map<String, Long> rankCountsFor(String cards) {
        return Stream.of(cards.split(" "))
                     .map(card -> card.substring(0, 1))
                     .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private int suitCountFor(String cards) {
        return Stream.of(cards.split(" "))
                     .map(card -> card.substring(1, 2))
                     .collect(Collectors.toSet())
                     .size();
    }

    private int sequenceStartFor(Map<String, Long> rankCounts) {
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
        return rankCounts.values().contains(4L);
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
        return rankCounts.values().contains(3L);
    }

    private boolean isTwoPair() {
        return rankCounts.keySet().size() == 3;
    }

    private boolean isPair() {
        return rankCounts.keySet().size() == 4;
    }
}
