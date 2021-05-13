def convert_ms_time(time): # time : (string) hh:mm:ss.sss
    h, m, s = time.split(':')
    converted_time = (int(h) * 3600 + int(m) * 60 + float(s)) * 1000

    return converted_time

def get_tps(processes, start):
    end = start + 1000

    tps = 0
    for process in processes:
        if start <= process[1] and process[0] < end:
            tps += 1
    
    return tps

def solution(lines):
    processes = []
    for line in lines:
        _, end, processing_time = line.split()
        processing_time = float(processing_time[:-1]) * 1000
        end_time = convert_ms_time(end)
        start_time = end_time - processing_time + 1

        processes.append([start_time, end_time])
    
    ans = 0
    for process in processes:
        ans = max(ans, get_tps(processes, process[0]))
        ans = max(ans, get_tps(processes, process[1]))
    
    return ans


print('answer: 1', solution([
"2016-09-15 01:00:04.001 2.0s",
"2016-09-15 01:00:07.000 2s"
]))