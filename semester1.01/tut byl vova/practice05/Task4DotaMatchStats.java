package semester1.practice05.homework;

public class Task4DotaMatchStats {

    public static void main(String[] args) {
        String[] heroes = {"Pudge", "Invoker", "Juggernaut", "Crystal Maiden", "Earthshaker"};
        int[] kills   = {8,  12, 10, 2,  4};
        int[] deaths  = {6,  3,  2,  9,  5};
        int[] assists = {14, 9,  8,  18, 16};
        int[] netWorth = {14500, 21200, 24800, 9800, 12300};
        int n = heroes.length;
        double[] kdaRatios = new double[n];
        int totalTeamKills = 0;
        int totalNetWorth = 0;
        for (int i = 0; i < n; i++) {
            totalTeamKills += kills[i];
            totalNetWorth += netWorth[i];
        }
        double maxKda = -1.0;
        int mvpIndex = 0;

        for (int i = 0; i < n; i++) {
            int effectiveDeaths = Math.max(1, deaths[i]);
            kdaRatios[i] = (double) (kills[i] + assists[i]) / effectiveDeaths;

            if (kdaRatios[i] > maxKda) {
                maxKda = kdaRatios[i];
                mvpIndex = i;
            }
        }
        System.out.println("========================================================================");
        System.out.println("                       🏆 DOTA 2: POST-MATCH REPORT");
        System.out.println("========================================================================");
        System.out.printf("%-16s | %-10s | %-9s | %-9s | %s\n",
                "Герой", "K / D / A", "Net Worth", "KDA Ratio", "Kill Participation");
        System.out.println("------------------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            String kdaStr = kills[i] + " / " + deaths[i] + " / " + assists[i];
            String formattedNetWorth = String.format("%,d", netWorth[i]);
            double killParticipation = 0.0;
            if (totalTeamKills > 0) {
                killParticipation = ((double) (kills[i] + assists[i]) / totalTeamKills) * 100.0;
            }

            System.out.printf("%-16s | %-10s | %-9s | %-9.2f | %.2f%%\n",
                    heroes[i],
                    kdaStr,
                    formattedNetWorth,
                    kdaRatios[i],
                    killParticipation);
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Загальна кількість вбивств команди: " + totalTeamKills);
        System.out.printf("Сумарний Net Worth команди:          %,d золота\n", totalNetWorth);
        System.out.printf("⭐ НАЙКРАЩИЙ ГРАВЕЦЬ МАТЧУ (MVP):    %s (KDA: %.2f)\n",
                heroes[mvpIndex], kdaRatios[mvpIndex]);
        System.out.println("========================================================================");
    }
}