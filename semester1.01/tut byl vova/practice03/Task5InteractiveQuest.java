public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("/************************************************************\\");
            System.out.println("|               ПРИГОДА: ВТЕЧА З ТЕМНИЦІ ЧАКЛУНА             |");
            System.out.println("\\************************************************************/");

            System.out.print("Введіть ім'я вашого героя: ");
            String name = scanner.nextLine();

            System.out.println("\nОберіть клас героя:");
            System.out.println("1 — Воїн (HP: 120, Сила: 15, Мана: 10, Спритність: 8)");
            System.out.println("2 — Маг (HP: 75, Сила: 6, Мана: 50, Спритність: 10)");
            System.out.println("3 — Розбійник (HP: 90, Сила: 10, Мана: 15, Спритність: 18)");
            System.out.print("Ваш вибір (1-3): ");

            int classChoice = 0;
            if (scanner.hasNextInt()) {
                classChoice = scanner.nextInt();
            }
            scanner.nextLine();

            int playerHp;
            int strength;
            int mana;
            int agility;
            String className;

            switch (classChoice) {
                case 1 -> {
                    className = "Воїн";
                    playerHp = 120;
                    strength = 15;
                    mana = 10;
                    agility = 8;
                }
                case 2 -> {
                    className = "Маг";
                    playerHp = 75;
                    strength = 6;
                    mana = 50;
                    agility = 10;
                }
                case 3 -> {
                    className = "Розбійник";
                    playerHp = 90;
                    strength = 10;
                    mana = 15;
                    agility = 18;
                }
                default -> {
                    className = "Волоцюга";
                    playerHp = 60;
                    strength = 8;
                    mana = 5;
                    agility = 8;
                }
            }

            System.out.printf("\n[Створено персонажа]: %s %s (HP: %d, STR: %d, MP: %d, AGI: %d)\n",
                    className, name, playerHp, strength, mana, agility);

            System.out.println("\nВи прокидаєтеся в сирій камері. Перед вами 3 проходи.");
            System.out.print("Куди ви прямуєте? (північ / схід / захід): ");
            String direction = scanner.nextLine().trim();

            boolean hasKeyOrArtifact = false;
            boolean questPassed = false;

            switch (direction.toLowerCase()) {
                case "північ" -> {
                    System.out.println("\n--- [Кімната Стародавньої Загадки] ---");
                    System.out.println("Магічний кристал спалахує і промовляє: ");
                    System.out.println("«Скільки буде 7 * 8 - 6?»");
                    System.out.print("Ваша відповідь: ");

                    int answer = -1;
                    if (scanner.hasNextInt()) {
                        answer = scanner.nextInt();
                    }
                    scanner.nextLine();

                    boolean isCorrect = (answer == 50);

                    playerHp = isCorrect ? (playerHp + 20) : (playerHp - 30);

                    if (isCorrect) {
                        System.out.println("✨ Кристал сяє зеленим світлом! Ви здобули прихильність духів (+20 HP).");
                        questPassed = true;
                    } else {
                        System.out.println("⚡ Невірно! Кристал вдарив вас розрядом блискавки (-30 HP).");
                    }
                }

                case "схід" -> {
                    System.out.println("\n--- [Арсенал та зачинена скриня вартового] ---");
                    System.out.println("Ви знайшли ковану скриню зі складним зачіпним замком.");

                    boolean hasSpellBook = (mana >= 20);

                    if (agility >= 16 || (mana >= 25 && hasSpellBook)) {
                        System.out.println("🔓 Успіх! Завдяки високим характеристикам скриня відчинилася!");
                        System.out.println("Ви знайшли 'Амулет Світла' (+20 HP) та 'Легендарний Меч'!");
                        playerHp += 20;
                        strength += 5;
                        hasKeyOrArtifact = true;
                        questPassed = true;
                    } else {
                        System.out.println("💥 Пастка! Скриня вистрілила стрілами (-25 HP). Вам не вистачило спритності чи мани.");
                        playerHp -= 25;
                    }
                }

                case "захід" -> {
                    System.out.println("\n--- [Печера кам'яного голема] ---");
                    System.out.println("Шлях перегороджує велетенський Кам'яний Голем!");
                    System.out.println("Оберіть тактику бою:");
                    System.out.println("1 — Лобова атака (потрібна Сила >= 12)");
                    System.out.println("2 — Магічний бар'єр (потрібна Мана >= 30)");
                    System.out.println("3 — Прослизнути повз (потрібна Спритність >= 12)");
                    System.out.print("Ваша дія (1-3): ");

                    int combatChoice = 0;
                    if (scanner.hasNextInt()) {
                        combatChoice = scanner.nextInt();
                    }
                    scanner.nextLine();

                    switch (combatChoice) {
                        case 1 -> {
                            if (strength >= 12) {
                                System.out.println("⚔️ Ви пробили захист голема потужним ударом!");
                                questPassed = true;
                            } else {
                                System.out.println("🤕 Ваша сила занадто мала. Голем відкинув вас (-35 HP).");
                                playerHp -= 35;
                            }
                        }
                        case 2 -> {
                            if (mana >= 30) {
                                System.out.println("🔮 Ви створили магічний щит і спопелили голема!");
                                questPassed = true;
                            } else {
                                System.out.println("💥 Недостатньо мани! Бар'єр вибухнув у руках (-30 HP).");
                                playerHp -= 30;
                            }
                        }
                        case 3 -> {
                            if (agility >= 12) {
                                System.out.println("🥷 Ви безшумно прослизнули в тіні повз голема!");
                                questPassed = true;
                            } else {
                                System.out.println("👣 Ви перечепилися і голем розчавив вас (-40 HP).");
                                playerHp -= 40;
                            }
                        }
                        default -> {
                            System.out.println("❌ Ви завагалися і голем завдав раптового удару (-30 HP).");
                            playerHp -= 30;
                        }
                    }
                }

                default -> {
                    System.out.println("\n❓ Ви заблукали в коридорах і натрапили на отруйні шипи (-20 HP).");
                    playerHp -= 20;
                }
            }

            System.out.println("\n============================================================");
            if (playerHp <= 0) {
                System.out.println("💀 КІНЦІВКА: ПОРАЗКА!");
                System.out.println("Герой " + name + " загинув у темряві підземелля...");
            } else if (questPassed) {
                System.out.println("🏆 ВІТАЄМО! ВИ УСПІШНО ВИБРАЛИСЯ З ТЕМНИЦІ!");
                System.out.println("Ворота розчиняються, і герой " + name + " виходить на світло сонця!");
            } else {
                System.out.println("🚪 Ви ледве втекли назад у камеру, але двері зачинилися навжди...");
                System.out.println("КІНЦІВКА: ЗАПЛЮЩЕНІ ДВЕРІ (Поразка)");
            }

            String rank;
            if (playerHp >= 100) {
                rank = "S (Легендарний герой)";
            } else if (playerHp >= 50) {
                rank = "A (Вправний шукач пригод)";
            } else if (playerHp > 0) {
                rank = "B (Уцілілий щасливчик)";
            } else {
                rank = "F (Павлий у бою)";
            }

            System.out.println("------------------------------------------------------------");
            System.out.println("📊 ФІНАЛЬНА СТАТИСТИКА:");
            System.out.println("Герой: " + name + " (" + className + ")");
            System.out.println("Залишок здоров'я: " + Math.max(0, playerHp) + " HP");
            System.out.println("Знайдені артефакти: " + (hasKeyOrArtifact ? "Амулет Світла" : "Немає"));
            System.out.println("Фінальний ранг: " + rank);
            System.out.println("/************************************************************/");

            scanner.close();
}