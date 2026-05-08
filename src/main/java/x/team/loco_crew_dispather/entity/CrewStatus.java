package x.team.loco_crew_dispather.entity;

public enum CrewStatus {
    WORKING("В работе"),
    ON_CALL("На вызове"),
    AWAITING_APPEARANCE("В ожидании явки");

    private final String title;

    CrewStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
