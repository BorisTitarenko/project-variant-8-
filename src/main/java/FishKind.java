public enum FishKind {
    SEA("SEA"),
    RIVER("RIVER");

    private final String kind;

    private FishKind(String k) {
        kind = k;
    }

    public boolean equalsName(String otherKind) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return kind.equals(otherKind);
    }

    public String toString() {
        return this.kind;
    }

}
