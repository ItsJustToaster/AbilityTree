package Components.AbilityTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of the AbilityTreeKernel interface.
 * This implementation uses a HashMap for storing abilities and their
 * prerequisites
 * and a HashSet to track unlocked abilities.
 *
 * Representation:
 * - abilityPrerequisites (Map): Stores abilities and their prerequisites.
 * - Key: Ability name.
 * - Value: Prerequisite for the ability or "None".
 * - unlockedAbilities (Set): Stores abilities that are currently unlocked.
 *
 * Valid Configurations:
 * - All abilities in abilityPrerequisites must have a non-null prerequisite
 * (can be "None").
 * - An ability can only be unlocked if its prerequisite is "None" or already
 * unlocked.
 *
 * Convention:
 * - All keys (abilities) in abilityPrerequisites must be non-null.
 * - Each value (prerequisite) in abilityPrerequisites must either be "None"
 * or another valid ability present in abilityPrerequisites.
 * - All entries in unlockedAbilities must exist as keys in
 * abilityPrerequisites.
 *
 * Correspondence:
 * - abilityPrerequisites represents the ability tree.
 * - unlockedAbilities represents the subset of abilities in the tree that are
 * unlocked.
 */
public class AbilityTree1L implements AbilityTreeKernel {

    private final Map<String, String> abilityPrerequisites; // Map to store abilities and their prerequisites
    private final Set<String> unlockedAbilities; // Set to track unlocked abilities

    /**
     * Constructor for AbilityTree1L.
     */
    public AbilityTree1L() {
        this.abilityPrerequisites = new HashMap<>();
        this.unlockedAbilities = new HashSet<>();
    }

    @Override
    public void addAbility(String ability, String prerequisite) {
        if (ability == null || prerequisite == null) {
            throw new IllegalArgumentException("Ability and prerequisite cannot be null.");
        }
        abilityPrerequisites.put(ability, prerequisite.isEmpty() ? "None" : prerequisite);
    }

    @Override
    public void unlockAbility(String ability) {
        if (!abilityPrerequisites.containsKey(ability)) {
            throw new IllegalArgumentException("Ability does not exist in the tree.");
        }
        String prerequisite = abilityPrerequisites.get(ability);
        if ("None".equals(prerequisite) || unlockedAbilities.contains(prerequisite)) {
            unlockedAbilities.add(ability);
        }
    }

    @Override
    public boolean isAbilityUnlocked(String ability) {
        return unlockedAbilities.contains(ability);
    }

    @Override
    public String getPrerequisite(String ability) {
        return abilityPrerequisites.getOrDefault(ability, "None");
    }
}
