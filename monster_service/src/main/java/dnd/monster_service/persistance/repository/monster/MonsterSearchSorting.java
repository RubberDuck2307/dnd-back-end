package dnd.monster_service.persistance.repository.monster;

public class MonsterSearchSorting {

    public MonsterSearchSorting(boolean ascending, String sortingType) {
        this.ascending = ascending;
        try {
            this.sortingType = SortingType.valueOf(sortingType);
        } catch (IllegalArgumentException e) {
            this.sortingType = SortingType.NAME;
        }
    }
    private boolean ascending;
    private SortingType sortingType;

    public boolean isAscending() {
        return ascending;
    }

    public SortingType getSortingType() {
        return sortingType;
    }

    public enum SortingType {
        NAME,
        CR;

        public String getMatchingField() {
            return switch (this) {
                case NAME -> "monsterName";
                case CR -> "cr";
            };
        }

    }
}
