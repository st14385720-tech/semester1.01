package semester1.practice05.homework;

import java.util.Scanner;

public class Task5RobloxPetSimulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] petNames = {"Dog", "Cat", "Dragon", "Unicorn", "Huge Titanic Cat"};
        int[] petPowers = {15, 25, 120, 350, 2000};
        int[] dropChances = {45, 30, 15, 8, 2};
        int maxSlots = 8;
        String[] inventoryNames = new String[maxSlots];
        int[] inventoryPowers = new int[maxSlots];
        int petCount = 0;
        int coins = 300;
        boolean running = true;

        System.out.println("======================================================");
        System.out.println("           🐾 ROBLOX: PET SIMULATOR 🐾");
        System.out.println("======================================================");

        while (running) {
            System.out.println("\n------------------------------------------------------");
            System.out.printf("Баланс: %d монет | Вихованців у рюкзаку: %d/%d\n", coins, petCount, maxSlots);
            System.out.println("------------------------------------------------------");
            System.out.println("1 - Відкрити чарівне яйце (100 монет)");
            System.out.println("2 - Відкрити рюкзак-інвентар");
            System.out.println("3 - Порахувати силу команди");
            System.out.println("4 - Фармити монети (+50..150 монет)");
            System.out.println("0 - Вийти з гри");
            System.out.print("Ваш вибір: ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    if (coins < 100) {
                        System.out.println("❌ Недостатньо монет! Пофарміть монети (пункт 4).");
                        break;
                    }
                    if (petCount >= maxSlots) {
                        System.out.println("⚠️ Рюкзак переповнений! У вас уже 8/8 вихованців.");
                        break;
                    }

                    coins -= 100;
                    System.out.println("\n🥚 Трісь! Яйце розколюється...");

                    int roll = (int) (Math.random() * 100) + 1;
                    int cumulativeChance = 0;
                    int selectedIndex = 0;

                    for (int i = 0; i < dropChances.length; i++) {
                        cumulativeChance += dropChances[i];
                        if (roll <= cumulativeChance) {
                            selectedIndex = i;
                            break;
                        }
                    }

                    String wonName = petNames[selectedIndex];
                    int wonPower = petPowers[selectedIndex];
                    if (selectedIndex == 4) {
                        System.out.println("⭐⭐⭐ НЕЙМОВІРНО! СУПЕР-ДЖЕКПОТ! ⭐⭐⭐");
                        System.out.println("💎 " + wonName + "! (Сила: " + wonPower + ")");
                    } else if (selectedIndex == 3) {
                        System.out.println("🦄 ОГО! Рідкісний єдиноріг!");
                        System.out.println("✨ " + wonName + " (Сила: " + wonPower + ")");
                    } else {
                        System.out.println("✨ ВАМ ВИПАВ: " + wonName + " (Сила: " + wonPower + ")");
                    }

                    inventoryNames[petCount] = wonName;
                    inventoryPowers[petCount] = wonPower;
                    petCount++;

                    System.out.println("Вихованця додано в інвентар! Залишок монет: " + coins);
                }

                case 2 -> {
                    System.out.println("\n--- 🎒 ВАШ РЮКЗАК (ІНВЕНТАР) ---");
                    if (petCount == 0) {
                        System.out.println("Рюкзак порожній. Відкрийте яйця, щоб отримати вихованців!");
                    } else {
                        for (int i = 0; i < petCount; i++) {
                            System.out.printf("[Слот %d] %s (Сила: %d)\n", (i + 1), inventoryNames[i], inventoryPowers[i]);
                        }
                    }
                }

                case 3 -> {
                    System.out.println("\n--- СТАТИСТИКА ВАШОЇ КОМАНДИ ---");
                    if (petCount == 0) {
                        System.out.println("У вас немає вихованців для оцінки команди.");
                        break;
                    }

                    int totalPower = 0;
                    int maxPower = inventoryPowers[0];
                    String bestPetName = inventoryNames[0];

                    for (int i = 0; i < petCount; i++) {
                        totalPower += inventoryPowers[i];
                        if (inventoryPowers[i] > maxPower) {
                            maxPower = inventoryPowers[i];
                            bestPetName = inventoryNames[i];
                        }
                    }

                    System.out.println("Кількість вихованців: " + petCount + " шт.");
                    System.out.println("Загальна сила команди: " + totalPower + " очок шкоди");
                    System.out.println("🔥 Найсильніший улюбленець: " + bestPetName + " (Сила: " + maxPower + ")");
                }

                case 4 -> {
                    int earnedCoins = (int) (Math.random() * 101) + 50;
                    coins += earnedCoins;
                    System.out.println("\n⚔️ Ви фармите монети на арені... Отримано: +" + earnedCoins + " монет!");
                }

                case 0 -> {
                    running = false;
                    System.out.println("\nДякуємо за гру в Pet Simulator! До зустрічі!");
                }

                default -> {
                    System.out.println("\n❌ Невірний вибір! Оберіть пункт від 0 до 4.");
                }
            }
        }

        scanner.close();
    }
}