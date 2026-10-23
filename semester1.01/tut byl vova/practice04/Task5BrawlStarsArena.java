public class Task5BrawlStarsArena {
}
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("======================================================");
    System.out.println("           ⭐ BRAWL STARS: BOSS FIGHT ⭐");
    System.out.println("======================================================");
    System.out.println("Оберіть свого бійця:");
    System.out.println("1. Шеллі (Shelly)     - HP: 4000 | Атака: 1200 | Супер: 2800");
    System.out.println("2. Кольт (Colt)       - HP: 3000 | Атака: 1600 | Супер: 3600");
    System.out.println("3. Ель Прімо (Primo)  - HP: 6000 | Атака: 1100 | Супер: 2200");
    System.out.print("Ваш вибір (1-3): ");

    int choice = 1;
    if (scanner.hasNextInt()) {
        choice = scanner.nextInt();
    }
    scanner.nextLine();

    String brawlerName;
    int maxHp, playerHp;
    int baseAttack;
    int superAttack;

    switch (choice) {
        case 2 -> {
            brawlerName = "Кольт";
            maxHp = 3000;
            baseAttack = 1600;
            superAttack = 3600;
        }
        case 3 -> {
            brawlerName = "Ель Прімо";
            maxHp = 6000;
            baseAttack = 1100;
            superAttack = 2200;
        }
        default -> {
            brawlerName = "Шеллі";
            maxHp = 4000;
            baseAttack = 1200;
            superAttack = 2800;
        }
    }
    playerHp = maxHp;

    System.out.println("Ви обрали бійця: " + brawlerName + "! Бій починається!\n");

    int maxBossHp = 10000;
    int bossHp = maxBossHp;
    int baseBossDamage = 700;

    int ammo = 3;
    int superCharge = 0;
    int powerCubes = 0;
    int roundCount = 0;
    boolean bossStunned = false;

    while (playerHp > 0 && bossHp > 0) {
        roundCount++;

        if (ammo < 3) {
            ammo++;
        }

        boolean currentBossStunned = bossStunned;
        bossStunned = false;

        System.out.println("\n-------------------- РАУНД " + roundCount + " --------------------");

        int playerBars = (int) (((double) playerHp / maxHp) * 10);
        if (playerBars < 0) playerBars = 0;
        if (playerBars > 10) playerBars = 10;
        StringBuilder playerBarStr = new StringBuilder();
        for (int i = 0; i < playerBars; i++) playerBarStr.append("█");
        for (int i = playerBars; i < 10; i++) playerBarStr.append("░");

        int bossBars = (int) (((double) bossHp / maxBossHp) * 10);
        if (bossBars < 0) bossBars = 0;
        if (bossBars > 10) bossBars = 10;
        StringBuilder bossBarStr = new StringBuilder();
        for (int i = 0; i < bossBars; i++) bossBarStr.append("█");
        for (int i = bossBars; i < 10; i++) bossBarStr.append("░");

        double bossHpPercentage = ((double) bossHp / maxBossHp) * 100.0;
        int currentBossDamage = baseBossDamage;
        String bossPhaseName;

        if (bossHpPercentage > 60.0) {
            bossPhaseName = "СПОКІЙНИЙ (Шкода: 700)";
        } else if (bossHpPercentage >= 30.0) {
            currentBossDamage = (int) (baseBossDamage * 1.3);
            bossPhaseName = "⚠️ ANGRY! (+30% атаки)";
        } else {
            currentBossDamage = (int) (baseBossDamage * 1.7);
            bossPhaseName = "🔥 ENRAGED / GOD MODE! (+70% атаки)";
        }

        System.out.printf("[%s]     HP: [%s] %d/%d | Набої: %d/3 | Супер: [%s] %d%%\n",
                brawlerName, playerBarStr, playerHp, maxHp, ammo,
                (superCharge >= 100 ? "██████████" : "░░░░░░░░░░"),
                superCharge);
        System.out.printf("[Робо-Бос]  HP: [%s] %d/%d | Фаза: %s\n",
                bossBarStr, bossHp, maxBossHp, bossPhaseName);
        System.out.println("Куби підсилення: " + powerCubes + " шт. " + (powerCubes > 0 ? "(+" + (powerCubes * 15) + "% до атаки)" : ""));

        System.out.println("\nОберіть дію:");
        System.out.println("1 - Базова атака (-1 набій)");
        System.out.println("2 - СУПЕР АТАКА " + (superCharge >= 100 ? "(ГОТОВИЙ!)" : "(Не заряджено!)"));
        System.out.println("3 - Захисний маневр (відновлення набоїв та +15% HP)");
        System.out.println("4 - Ризик: пошук Енергетичного куба (Power Cube)");
        System.out.print("Ваш хід: ");

        int action = 1;
        if (scanner.hasNextInt()) {
            action = scanner.nextInt();
        }
        scanner.nextLine();

        double damageMultiplier = 1.0 + (powerCubes * 0.15);

        switch (action) {
            case 1 -> {
                if (ammo > 0) {
                    ammo--;
                    int dealtDamage = (int) (baseAttack * damageMultiplier);
                    bossHp -= dealtDamage;
                    superCharge += 35;
                    System.out.printf("\n💥 %s завдає вдалої атаки! Завдано %d шкоди!\n", brawlerName, dealtDamage);
                    System.out.println("🔋 Заряд супера: +35% (Поточний: " + superCharge + "%)");
                } else {
                    System.out.println("\n❌ Немає набоїв! Хід витрачено на перезарядку.");
                }
            }
            case 2 -> {
                if (superCharge >= 100) {
                    int dealtDamage = (int) (superAttack * damageMultiplier);
                    bossHp -= dealtDamage;
                    superCharge = 0; // Скидання заряду
                    System.out.println("\n💣💣💣 СУПЕР-АТАКА! Могутній удар!");
                    System.out.printf("%s знищує броню супротивника! Завдано %d шкоди!\n", brawlerName, dealtDamage);

                    if (brawlerName.equals("Шеллі")) {
                        System.out.println("✨ 'Супер-дріб' відкидає боса, зменшуючи його наступну атаку на 50%!");
                        currentBossDamage = (int) (currentBossDamage * 0.5);
                    } else if (brawlerName.equals("Ель Прімо")) {
                        System.out.println("🥊 'Метеорний стрибок' оглушує боса! Він пропустить наступний хід!");
                        bossStunned = true;
                    }
                } else {
                    System.out.println("\n❌ Супер ще не заряджений! (Потрібно 100%). Хід втрачено.");
                }
            }
            case 3 -> {
                ammo = Math.min(3, ammo + 2);
                int healAmount = (int) (maxHp * 0.15);
                playerHp = Math.min(maxHp, playerHp + healAmount);
                System.out.println("\n🛡️ Ви зайняли захисну позицію. Відновлено 2 набої та " + healAmount + " HP!");
            }
            case 4 -> {
                boolean foundCube = Math.random() < 0.6;
                if (foundCube) {
                    powerCubes++;
                    System.out.println("\n✨ Успіх! Ви знайшли Енергетичний куб! Ваша сила зросла на +15%!");
                } else {
                    System.out.println("\n💨 Невдача: пошуки куба були марними, ви нічого не знайшли.");
                }
            }
            default -> {
                System.out.println("\n❌ Невідома дія. Ви втратили пильність і пропустили хід!");
            }
        }

        if (bossHp <= 0) {
            break;
        }

        System.out.println("\n🤖 Хід Боса:");
        if (currentBossStunned) {
            System.out.println("Робо-Бос оглушений і не може атакувати цього раунду!");
        } else {
            boolean dodge = Math.random() < 0.15;
            if (dodge) {
                System.out.println("🤸 Неймовірно! " + brawlerName + " зробив акробатичний кувирок і ухилився від удару боса!");
            } else {
                playerHp -= currentBossDamage;
                System.out.printf("Робо-Бос завдає нищівного удару! %s отримує %d шкоди!\n", brawlerName, currentBossDamage);
            }
        }
        System.out.println("-------------------------------------------------");
    }

    System.out.println("\n================================================езде==");
    if (playerHp > 0) {
        System.out.println("🏆 ПЕРЕМОГА! РОБО-БОСА ЗНИЩЕНО!");
        System.out.println("Раундів зіграно: " + roundCount);
        System.out.println("Зібрано Power Cubes: " + powerCubes + " шт.");
        System.out.println("Нагорода: +10 Трофеїв 🏆, 100 Starr Drops! Ви — Зірковий Гравець!");
    } else {
        System.out.println("💀 ПОРАЗКА! Сейф зруйновано, а герой " + brawlerName + " зазнав поразки...");
        System.out.println("Порада: Спробуйте наступного разу взяти більш витривалого бійця (наприклад, Ель Прімо з високим HP).");
    }
    System.out.println("========================================================");

    scanner.close();
}