package Components.AbilityTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import components.list.List;
import components.set.Set;

public class AbilityTree1 implements AbilityTree {
    // A map that stores abilities and their prerequisites
    private Map<String, String> abilityTree = new HashMap<>();

    // A set that tracks unlocked abilities
    private Set<String> unlockedAbilities = new HashSet<>();

    @Override
    public void addAbility(String ability, String prerequisite) {
        if (ability == null || ability.isEmpty()) {
            throw new IllegalArgumentException("Ability cannot be null or empty");
        }
        if (prerequisite != null && prerequisite.isEmpty()) {
            prerequisite = null; // Treat empty as no prerequisite
        }
        abilityTree.put(ability, prerequisite);
    }

    @Override
    public void unlockAbility(String ability) {
        if (canUnlockAbility(ability)) {
            unlockedAbilities.add(ability);
            System.out.println(ability + " has been unlocked.");
        } else {
            System.out.println(ability + " cannot be unlocked yet.");
        }
    }

    @Override
    public boolean isAbilityUnlocked(String ability) {
        return unlockedAbilities.contains(ability);
    }

    @Override
    public String getPrerequisite(String ability) {
        return abilityTree.getOrDefault(ability, "None");
    }

    @Override
    public Set<String> getUnlockedAbilities() {
        return new HashSet<>(unlockedAbilities); // Return a copy to avoid external modification
    }

    @Override
    public boolean canUnlockAbility(String ability) {
        String prerequisite = abilityTree.get(ability);
        return prerequisite == null || unlockedAbilities.contains(prerequisite);
    }

    @Override
    public void resetTree() {
        unlockedAbilities.clear();
        System.out.println("Ability tree has been reset.");
    }

    @Override
    public List<String> getAvailableAbilities() {
        List<String> availableAbilities = new ArrayList<>();
        for (String ability : abilityTree.keySet()) {
            if (canUnlockAbility(ability) && !unlockedAbilities.contains(ability)) {
                availableAbilities.add(ability);
            }
        }
        return availableAbilities;
    }

    @Override
    public void removeAbility(String ability) {
        // Remove the ability and its associated prerequisite
        abilityTree.remove(ability);
        unlockedAbilities.remove(ability);
        // Additionally, check if removing this ability affects others' unlock status
        // Implement further logic if needed based on your application's requirements
    }
}