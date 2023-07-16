class Solution {
    private static class Process {
        int start;
        int end;

        public Process(String log) {
            String[] split = log.split(" ");
            String[] endTime = split[1].split(":");
            end = (int) ((Integer.parseInt(endTime[0]) * 3600
                                + Integer.parseInt(endTime[1]) * 60
                                + Double.parseDouble(endTime[2]))
                                * 1000);

            start = (int) (end - (Double.parseDouble(split[2].substring(0, split[2].length() - 1)) * 1000) + 1);
        }
    }

    public int solution(String[] lines) {
        Process[] processes = new Process[lines.length];

        for (int i = 0; i < lines.length; i++) {
            processes[i] = new Process(lines[i]);
        }

        int maxTps = 0;
        for (Process process : processes) {
            maxTps = Math.max(maxTps, getTps(processes, process));
        }

        return maxTps;
    }

    private int getTps(Process[] processes, Process process) {
        int sectionStart = process.end;
        int sectionEnd = sectionStart + 999;

        int t = 0;
        for (Process p : processes) {
            if (p.start <= sectionEnd && p.end >= sectionStart) {
                t++;
            }
        }

        return t;
    }
}