package Components.AbilityTree;

import java.util.ArrayList;
import java.util.HashSet;

import components.list.List;
import components.set.Set;

/**
 * Abstract class that provides layered implementations of secondary methods
 * for the AbilityTree, using only the kernel methods.
 */
public abstract class AbilityTreeSecondary implements AbilityTree {

    @Override
    public Set<String> getUnlockedAbilities() {
        Set<String> unlockedAbilities = new HashSet<>();
        for (String ability : abilityTree()) {
            if (isAbilityUnlocked(ability)) {
                unlockedAbilities.add(ability);
            }
        }
        return unlockedAbilities;
    }

    @Override
    public boolean canUnlockAbility(String ability) {
        if (!abilityTree().contains(ability)) {
            throw new IllegalArgumentException("Ability does not exist in the tree.");
        }
        String prerequisite = getPrerequisite(ability);
        return prerequisite == null || isAbilityUnlocked(prerequisite);
    }

    @Override
    public void resetTree() {
        Set<String> unlockedAbilities = getUnlockedAbilities();
        for (String ability : unlockedAbilities) {
            // Lock each ability - assuming unlockAbility can toggle lock/unlock
            // If unlockAbility only unlocks, replace with appropriate lock mechanism
            unlockAbility(ability);
        }
    }

    @Override
    public List<String> getAvailableAbilities() {
        List<String> availableAbilities = new ArrayList<>();
        for (String ability : abilityTree()) {
            if (!isAbilityUnlocked(ability) && canUnlockAbility(ability)) {
                availableAbilities.add(ability);
            }
        }
        return availableAbilities;
    }

    @Override
    public void removeAbility(String ability) {
        if (!abilityTree().contains(ability)) {
            throw new IllegalArgumentException("Ability does not exist in the tree.");
        }
        abilityTree().remove(ability);
        resetTree(); // Ensure that any dependent abilities are re-evaluated
    }

    // Overriding Object methods using kernel methods only

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AbilityTree: ");
        sb.append("Unlocked abilities: ").append(getUnlockedAbilities().toString());
        sb.append(", All abilities: ").append(abilityTree().toString());
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        AbilityTreeSecondary other = (AbilityTreeSecondary) obj;
        return this.getUnlockedAbilities().equals(other.getUnlockedAbilities()) &&
                this.abilityTree().equals(other.abilityTree());
    }

    @Override
    public int hashCode() {
        return getUnlockedAbilities().hashCode() * 31 + abilityTree().hashCode();
    }

    // Abstract methods for kernel methods
    protected abstract Set<String> abilityTree(); // Returns the ability map

    protected abstract void unlockAbility(String ability);

    protected abstract boolean isAbilityUnlocked(String ability);

    protected abstract String getPrerequisite(String ability);

    protected abstract void addAbility(String ability, String prerequisite);
}
