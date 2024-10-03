package Components.AbilityTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AbilityTree {

    // A map that stores abilities and their prerequisites
    private Map<String, String> abilityTree = new HashMap<>();

    // A set that tracks unlocked abilities
    private Set<String> unlockedAbilities = new HashSet<>();

    // Adds an ability with a prerequisite to the tree
    public void addAbility(String ability, String prerequisite) {
        if (prerequisite == null) {
            prerequisite = ""; // Treat null as no prerequisite
        }
        abilityTree.put(ability, prerequisite);
    }

    // Unlocks an ability if its prerequisites are satisfied
    public void unlockAbility(String ability) {
        if (canUnlockAbility(ability)) {
            unlockedAbilities.add(ability);
            System.out.println(ability + " has been unlocked.");
        } else {
            System.out.println(ability + " cannot be unlocked yet.");
        }
    }

    // Checks if a specific ability is unlocked
    public boolean isAbilityUnlocked(String ability) {
        return unlockedAbilities.contains(ability);
    }

    // Retrieves the prerequisite for a specific ability
    public String getPrerequisite(String ability) {
        return abilityTree.getOrDefault(ability, "None");
    }

    // Returns a list of all abilities that have been unlocked
    public Set<String> getUnlockedAbilities() {
        return unlockedAbilities;
    }

    // Checks if an ability can be unlocked (all prerequisites are unlocked)
    public boolean canUnlockAbility(String ability) {
        String prerequisite = abilityTree.get(ability);
        return prerequisite == null || unlockedAbilities.contains(prerequisite);
    }

    // Resets the ability tree, locking all abilities
    public void resetTree() {
        unlockedAbilities.clear();
        System.out.println("Ability tree has been reset.");
    }

    // A simple demonstration of the component in action
    public static void main(String[] args) {
        AbilityTree tree = new AbilityTree();

        // Adding abilities and prerequisites
        tree.addAbility("Fireball", null); // No prerequisite for Fireball
        tree.addAbility("Flame Burst", "Fireball");
        tree.addAbility("Inferno", "Flame Burst");

        // Attempting to unlock abilities
        tree.unlockAbility("Inferno"); // Should not unlock since prerequisites aren't met
        tree.unlockAbility("Fireball"); // Should unlock Fireball
        tree.unlockAbility("Flame Burst"); // Should unlock Flame Burst
        tree.unlockAbility("Inferno"); // Should now unlock Inferno

        // Checking if an ability is unlocked
        System.out.println("Is Inferno unlocked? " + tree.isAbilityUnlocked("Inferno"));

        // Resetting the ability tree
        tree.resetTree();
        System.out.println("Unlocked abilities: " + tree.getUnlockedAbilities());
    }
}
