package Components.AbilityTree;

import components.list.List;
import components.set.Set;

/**
 * Enhanced interface for the AbilityTree, extending the core operations with
 * additional features.
 */
public interface AbilityTree extends AbilityTreeKernel {

    /**
     * Returns a set of all abilities that have been unlocked.
     *
     * @return A set of unlocked abilities.
     * @ensures the returned set contains all abilities that have been unlocked.
     */
    Set<String> getUnlockedAbilities();

    /**
     * Checks if an ability can be unlocked by verifying that all prerequisites are
     * satisfied.
     *
     * @param ability The ability to check.
     * @return true if the ability can be unlocked, false otherwise.
     */
    boolean canUnlockAbility(String ability);

    /**
     * Resets the ability tree, locking all abilities.
     *
     * @ensures all abilities are locked and the set of unlocked abilities is
     *          cleared.
     */
    void resetTree();

    /**
     * Retrieves a list of abilities that can currently be unlocked based on the
     * current state.
     *
     * @return A list of abilities that have all their prerequisites met.
     */
    List<String> getAvailableAbilities();

    /**
     * Removes an ability from the tree.
     *
     * @param ability The ability to remove.
     * @ensures the ability is no longer in the ability tree.
     */
    void removeAbility(String ability);
}