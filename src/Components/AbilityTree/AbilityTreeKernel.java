package Components.AbilityTree;

public interface AbilityTreeKernel {

    /**
     * Adds an ability with a prerequisite to the ability tree.
     *
     * @param ability      The ability to add.
     * @param prerequisite The prerequisite for this ability.
     * @ensures ability is added to the tree with its prerequisite.
     */
    void addAbility(String ability, String prerequisite);

    /**
     * Unlocks an ability if its prerequisites are satisfied.
     *
     * @param ability The ability to unlock.
     * @ensures ability is added to the set of unlocked abilities if possible.
     */
    void unlockAbility(String ability);

    /**
     * Checks whether a specific ability is unlocked.
     *
     * @param ability The ability to check.
     * @return true if the ability is unlocked; false otherwise.
     */
    boolean isAbilityUnlocked(String ability);

    /**
     * Retrieves the prerequisite for a specific ability.
     *
     * @param ability The ability to get the prerequisite for.
     * @return The prerequisite for the given ability, or "None" if there is no
     *         prerequisite.
     */
    String getPrerequisite(String ability);
}
