package dnd.monster_service.utils;

import org.springframework.stereotype.Component;

@Component
public class RuleHelper {

    static public int mapCrToXp(float cr) {
        if (cr == 0)
            return 10;
        if (cr == 0.125)
            return 25;
        if (cr == 0.25)
            return 50;
        if (cr == 0.5)
            return 100;
        if (cr == 1)
            return 200;
        if (cr == 2)
            return 450;
        if (cr == 3)
            return 700;
        if (cr == 4)
            return 1100;
        if (cr == 5)
            return 1800;
        if (cr == 6)
            return 2300;
        if (cr == 7)
            return 2900;
        if (cr == 8)
            return 3900;
        if (cr == 9)
            return 5000;
        if (cr == 10)
            return 5900;
        if (cr == 11)
            return 7200;
        if (cr == 12)
            return 8400;
        if (cr == 13)
            return 10000;
        if (cr == 14)
            return 11500;
        if (cr == 15)
            return 13000;
        if (cr == 16)
            return 15000;
        if (cr == 17)
            return 18000;
        if (cr == 18)
            return 20000;
        if (cr == 19)
            return 22000;
        if (cr == 20)
            return 25000;
        if (cr == 21)
            return 33000;
        if (cr == 22)
            return 41000;
        if (cr == 23)
            return 50000;
        return 0;
    }

    static public int bonusFromAbilityScore(int abilityScore) {
        return (abilityScore - 10) / 2;
    }
}
