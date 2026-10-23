package semester1.practice06.homework;

public class Asta {
    private String name = "Аста";
    private int maxHp = 1000;
    private int hp = 1000;
    private int antiMagicEnergy = 0;
    private int baseAttack = 120;
    private int defense = 35;
    private boolean isBlackFormActive = false;
    private boolean limitPushed = false;
    private String[] skills = {
            "Удар Демоноборця",
            "Чорний Ураган",
            "Метеорит Демона",
            "Форма Чорного Демона"
    };

    public Asta() {
    }

    public Asta(String name) {
        this.name = name;
    }

    public void showStatus() {
        int bars = (int) (((double) hp / maxHp) * 10);
        if (bars < 0) bars = 0;
        if (bars > 10) bars = 10;
        StringBuilder hpBar = new StringBuilder();
        for (int i = 0; i < bars; i++) hpBar.append("█");
        for (int i = bars; i < 10; i++) hpBar.append("░");

        int energyBars = (int) (((double) antiMagicEnergy / 100) * 10);
        StringBuilder energyBar = new StringBuilder();
        for (int i = 0; i < energyBars; i++) energyBar.append("█");
        for (int i = energyBars; i < 10; i++) energyBar.append("░");

        System.out.println("------------------------------------------------------------------");
        System.out.printf("[СТАТУС] Герой: %s | HP: [%s] %d/%d | Антимагія: [%s] %d/100\n",
                name, hpBar, hp, maxHp, energyBar, antiMagicEnergy);
        System.out.println("Форма Чорного Демона: " + (isBlackFormActive ? "АКТИВНА 🔥" : "Неактивна"));
        System.out.println("------------------------------------------------------------------");
    }

    public int basicSwordAttack() {
        int damage = baseAttack;
        if (isBlackFormActive) {
            damage *= 2;
        }
        antiMagicEnergy = Math.min(100, antiMagicEnergy + 20);
        return damage;
    }

    public int useSkill(int skillIndex) {
        if (skillIndex < 0 || skillIndex >= skills.length) {
            System.out.println("❌ Невірний індекс навички!");
            return 0;
        }

        int damage = 0;
        switch (skillIndex) {
            case 0:
                if (antiMagicEnergy < 25) {
                    System.out.println("❌ Недостатньо енергії антимагії! Потрібно 25.");
                    return 0;
                }
                antiMagicEnergy -= 25;
                damage = 220;
                System.out.println("⚔️ Аста застосовує «Удар Демоноборця»!");
                break;
            case 1:
                if (antiMagicEnergy < 50) {
                    System.out.println("❌ Недостатньо енергії антимагії! Потрібно 50.");
                    return 0;
                }
                antiMagicEnergy -= 50;
                damage = 380;
                System.out.println("🌪️ Аста розгортає «Чорний Ураган», змітаючи магію ворога!");
                break;
            case 2:
                if (antiMagicEnergy < 75) {
                    System.out.println("❌ Недостатньо енергії антимагії! Потрібно 75.");
                    return 0;
                }
                antiMagicEnergy -= 75;
                damage = 550;
                System.out.println("☄️ МЕТЕОРИТ ДЕМОНА! Колосальний розруб по супротивнику!");
                break;
            case 3:
                if (antiMagicEnergy < 100) {
                    System.out.println("❌ Недостатньо енергії антимагії! Потрібно 100.");
                    return 0;
                }
                antiMagicEnergy -= 100;
                isBlackFormActive = true;
                hp = Math.min(maxHp, hp + 300);
                System.out.println("🔥 ФОРМА ЧОРНОГО ДЕМОНА АКТИВОВАНО! Відновлено 300 HP, усі атаки подвоєно!");
                return 0;
            default:
                break;
        }

        if (isBlackFormActive) {
            damage *= 2;
        }
        return damage;
    }

    public void takeDamage(int incomingDmg) {
        int realDmg = Math.max(15, incomingDmg - defense);
        hp -= realDmg;
        if (hp < 0) {
            hp = 0;
        }
        if (!limitPushed && hp < maxHp * 0.25 && hp > 0) {
            pushLimits();
        }
    }

    public void pushLimits() {
        limitPushed = true;
        hp = Math.min(maxHp, hp + 150);
        antiMagicEnergy = Math.min(100, antiMagicEnergy + 50);
        System.out.println("\n🔥 «Моя магія — ніколи не здаватися!» — кричить Аста!");
        System.out.println("✨ Завзятість відновлює 150 HP та додає 50 очок антимагії!\n");
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAntiMagicEnergy() { return antiMagicEnergy; }
    public String[] getSkills() { return skills; }
}