package semester1.practice06.homework;

import java.util.Scanner;

public class Task5AnimeHeroBattle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Asta asta = new Asta("Аста");

        String bossName = "Патри (Маг Світла)";
        int bossMaxHp = 1200;
        int bossHp = 1200;
        int bossAttack = 140;

        System.out.println("================ ⚔️ АРЕНА КОРОЛІВСТВА КОНЮШИНИ ⚔️ ================");
        System.out.printf("Бос: %s [HP: %d / %d]\n", bossName, bossHp, bossMaxHp);

        int round = 0;
        while (asta.isAlive() && bossHp > 0) {
            round++;
            System.out.println("\n--- Раунд " + round + " ---");
            asta.showStatus();
            System.out.printf("Ворог: %s [HP: %d / %d]\n", bossName, bossHp, bossMaxHp);

            System.out.println("\nОберіть дію Асти:");
            System.out.println("[1] Удар мечем Демоноборця (+20 енергії)");
            System.out.println("[2] Застосувати суперздібність (навички)");
            System.out.println("[3] Захисна стійка меча (знизити шкоду від ворога вдвічі)");
            System.out.println("[4] Переглянути статус");
            System.out.print("Ваш вибір: ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            scanner.nextLine();

            boolean playerActed = true;

            switch (choice) {
                case 1:
                    int dmg = asta.basicSwordAttack();
                    bossHp -= dmg;
                    System.out.printf("\n💥 Аста розсікає простір мечем Демоноборця! Завдано %d шкоди.\n", dmg);
                    System.out.println("⚡ Шкала антимагії збільшилася: " + asta.getAntiMagicEnergy() + " / 100");
                    break;
                case 2:
                    System.out.println("\nОберіть навичку:");
                    System.out.println("[0] Удар Демоноборця (25 енергії -> 220 шкоди)");
                    System.out.println("[1] Чорний Ураган (50 енергії -> 380 шкоди)");
                    System.out.println("[2] Метеорит Демона (75 енергії -> 550 шкоди)");
                    System.out.println("[3] Форма Чорного Демона (100 енергії -> Активація режиму демона + 300 HP)");
                    System.out.print("Виберіть номер навички (0-3): ");

                    int skillChoice = 0;
                    if (scanner.hasNextInt()) {
                        skillChoice = scanner.nextInt();
                    }
                    scanner.nextLine();

                    int skillDmg = asta.useSkill(skillChoice);
                    if (skillDmg > 0) {
                        bossHp -= skillDmg;
                        System.out.printf("✨ Навичка успішно завдала %d шкоди супротивнику!\n", skillDmg);
                    }
                    break;
                case 3:
                    System.out.println("\n🛡️ Аста закривається величезним мечем у захисну стійку!");
                    break;
                case 4:
                    playerActed = false;
                    System.out.println("\n[ℹ️] Статус оновлено. Робіть хід у наступному раунді.");
                    break;
                default:
                    System.out.println("\n❌ Невідома дія! Ви втрачаєте пильність.");
                    break;
            }

            if (bossHp <= 0) {
                break;
            }

            if (playerActed) {
                System.out.printf("\n🔥 %s атакує «Променями Покарання Світла»!\n", bossName);
                int incoming = bossAttack;
                if (choice == 3) {
                    incoming /= 2;
                    System.out.println("🛡️ Меч частково блокує магію! Шкода зменшена вдвічі.");
                }
                asta.takeDamage(incoming);
                System.out.printf("🛡️ Аста отримує шкоду. Залишок HP: %d\n", asta.getHp());
            }
        }

        System.out.println("\n==================================================================");
        if (asta.isAlive()) {
            System.out.println("🏆 ПЕЧАТЬ ТРІУМФУ! Аста здолав Патри та захистив Королівство Конюшини!");
        } else {
            System.out.println("💀 ПОРАЗКА... Аста втратив сили на полі бою, але його воля незламна.");
        }
        System.out.println("==================================================================");

        scanner.close();
    }
}