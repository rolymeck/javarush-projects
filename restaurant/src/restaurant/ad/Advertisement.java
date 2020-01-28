package restaurant.ad;

public class Advertisement {
    private final String name;
    private final long initialAmount;
    private int hits;
    private final int duration;
    private long amountPerOneDisplaying;

    public Advertisement(String name, long initialAmount, int hits, int duration) {
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount / hits;
    }

    public String getName()
    {
        return name;
    }

    public int getDuration()
    {
        return duration;
    }

    public long getAmountPerOneDisplaying()
    {
        return amountPerOneDisplaying;
    }

    public int getHits()
    {
        return hits;
    }

    public void revalidate() {
        if (hits <= 0) throw new UnsupportedOperationException();
        if (hits == 1) { amountPerOneDisplaying += initialAmount % amountPerOneDisplaying; }
        hits--;
    }
}