
public enum LampState {
    YELLOW("Y"),
    RED("R"),
    OFF("O");

    private final String characterRepresentation;

    LampState(String characterRepresentation) {
        this.characterRepresentation = characterRepresentation;
    }

    @Override
    public String toString() {
        return characterRepresentation;
    }
}
