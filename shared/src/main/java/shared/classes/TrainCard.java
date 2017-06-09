package shared.classes;

/**
 * Created by billrichards on 5/24/17.
 */

public class TrainCard {

    private TrainCardColors color;

    private String id;

    public TrainCard(TrainCardColors color) {
        this.color = color;
    }
    public TrainCard(String id, TrainCardColors color) {
        this.id = id;
        this.color = color;
    }


    public TrainCardColors getColor() {
        return color;
    }

    public String getId() { return id; }

    public String getColorName() {
        switch (color) {
            case WHITE:
                return "White";
            case BLACK:
                return "Black";
            case RED:
                return "Red";
            case ORANGE:
                return "Orange";
            case YELLOW:
                return "Yellow";
            case GREEN:
                return "Green";
            case BLUE:
                return "Blue";
            case PURPLE:
                return "Purple";
            case WILD:
                return "Wild";
        }
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TrainCard)) {
            return false;
        }
        TrainCard card = (TrainCard) obj;
        return card.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
