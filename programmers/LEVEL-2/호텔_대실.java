import java.util.*;

class Solution {
    
    private class Book implements Comparable<Book> {
		int start;
		int end;

		public Book(String start, String end) {
			this.start = convertTimeString2Int(start);
			this.end = convertTimeString2Int(end);
		}

		private int convertTimeString2Int(String time) {
			String[] split = time.split(":");
			return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
		}

		@Override
		public int compareTo(Book o) {
			return this.start != o.start ? this.start - o.start : this.end - o.end;
		}
	}

	List<Book> rooms = new ArrayList<>();
	
	public int solution(String[][] book_time) {
		PriorityQueue<Book> pq = new PriorityQueue<>();
		for (String[] bt : book_time) {
			pq.add(new Book(bt[0], bt[1]));
		}

		while (!pq.isEmpty()) {
			Book book = pq.poll();
			int roomCnt = rooms.size();
			
			boolean flag = false;
			for (int i = 0; i < roomCnt; i++) {
				if (rooms.get(i).end + 10 <= book.start) {
					rooms.set(i, book);
					flag = true;
					break;
				}
			}

			if (!flag) {
				rooms.add(book);
			}
		}

		return rooms.size();
	}
}