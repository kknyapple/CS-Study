package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17471
 * 
 * @author SSAFY
 *
 */
public class Main_17471_박정인 {
	static int N;
	static List<Integer>[] graph;
	static int[] people;
	static boolean[] isSelected;
	static int min = Integer.MAX_VALUE;
	static List<Integer> aList, bList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N + 1];
		people = new int[N + 1];
		isSelected = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		// 사람수 초기화 
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		// 그래프 초기화 
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());

			for (int j = 0; j < M; j++) {
				int x = Integer.parseInt(st.nextToken());
				graph[i].add(x);
				graph[x].add(i);
			}
		}

		// 부분집합 >> 공집합 제외
		// 선택된거는 A, 나머지는 B
		// 공집합 체크
		// A, B 각각 연결체크
		// 연결되었으면 최소값 갱신
		
		// 조합의 경우 3C1 == 3C2, A/B로 나누는 거라서 한쪽이 정해지면 반대쪽은 자동으로 정해진다. 
		// 따라서 절반만 체크해도 된다. 
		for (int i = 1; i <= N/2 + 1; i++) {
			combination(0, 0, i);
		}
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	// 조합을 이용한 선거구에 포함될 영역 나누기 
	private static void combination(int cnt, int start, int len) {
		if (cnt == len) {
			aList = new ArrayList<>();			
			bList = new ArrayList<>();
			
			for (int i = 1; i <= N; i++) {
				if (isSelected[i])	aList.add(i);
				else bList.add(i);
			}
			
			if (aList.size() == 0 || aList.size() == N)	return;
			
			if (check(aList) && check(bList)) {
				min = Math.min(min, Math.abs(countPeople(aList) - countPeople(bList)));
			}
			
			return;
		}
		
		for (int i = start; i <= N; i++) {
			isSelected[i] = true;
			combination(cnt + 1, i + 1, len);
			isSelected[i] = false;
		}
	}
	
	// bfs를 이용한 연결 여부 체크 
	private static boolean check(List<Integer> list) {
		boolean[] visited = new boolean[N + 1];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(list.get(0));
		visited[list.get(0)] = true;
		
		while (!q.isEmpty()) {
			int now = q.poll();
			
			for (int next : graph[now]) {
				
				if (!list.contains(next))	continue;
				
				if (visited[next])	continue;
				
				q.offer(next);
				visited[next] = true;
			}
		}
		
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (visited[i])	cnt++;
		}
		
		return cnt == list.size();
	}
	
	// 선거구 사람수 구하기 
	private static int countPeople(List<Integer> list) {
		int total = 0;
		
		for (int idx : list) {
			total += people[idx];
		}
		return total;
	}
}
